<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 14-3-23
  Time: 上午10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
    <style type="text/css">
        td {
            width: 100px;
        }
    </style>
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript">
        if (!Number.prototype.toFixed) {
            Number.prototype.toFixed = function (num) {
                with (Math)return round(this.valueOf() * pow(10, num)) / pow(10, num);
            }
        }

        var products = ${products};
        var doctors = ${doctors};
        var proTd = "<select class='pro_sel' onchange='changeProduct(this)'><option value=''>--请选择--</option>";
        var proArr = [];
        for (var i = 0; i < products.length; i++) {
            proArr[products[i].id] = products[i];
            proTd += "<option value='" + products[i].id + "' >" + products[i].name + "</option>";
        }
        var docArr = [];
        for (var i = 0; i < doctors.length; i++) {
            docArr[doctors[i].id] = doctors[i];
        }
        proTd += "</select>";
        function addProduct() {
            var tr = "<tr ><td class='pro_td'>" + proTd + "</td>" +
                    "<td class='per_price_td'><input class='per_price_input'></td>" +
                    "<td class='rate_td'><input class='rate_input'></td>" +
                    "<td class='num_td'><input class='num_input'></td>" +
                    "<td class='total_td'></td>" +
                    "<td class='fee_td'></td>" +
                    "<td class='operate_td'><input type='button' value='删除' onclick='$(this).parent().parent().remove();countAll();'></td></tr>";
            $("#t_content").append(tr);
        }
        function changeProduct(o) {
            var sel = $(o);
            var proId = sel.val();
            if (!proId)return;
            var obj = proArr[proId];
            var rateInput = sel.parent().nextAll('.rate_td').find('.rate_input');
            rateInput.val(obj.defaultRate);
            sel.parent().nextAll('.per_price_td').find('.per_price_input').val(obj.price);
        }

        $(function () {
            $('.rate_input').live('keyup', function (e) {
                var rate = this.value;
                var num = $(this).parent().next().find('.num_input').val();
                if (rate && num) {
                    var total = $(this).parent().nextAll('.total_td');
                    var proId = $(this).parent().parent().find('.pro_td').find('.pro_sel').val()
                    var price = proArr[proId].price;
                    total.html(parseFloat(price) * parseInt(num));
                    var fee = $(this).parent().nextAll('.fee_td');
                    fee.html((parseFloat(rate) * parseInt(num)).toFixed(2));
                }
                countAll();
            });
            $('.num_input').live('keyup', function (e) {
                var rate = $(this).parent().prevAll('.rate_td').find('.rate_input').val();
                var num = $(this).val();
                if (rate && num) {
                    var total = $(this).parent().nextAll('.total_td');
                    var proId = $(this).parent().parent().find('.pro_td').find('.pro_sel').val()
                    var price = proArr[proId].price;
                    total.html(parseFloat(price) * parseInt(num));
                    var fee = $(this).parent().nextAll('.fee_td');
                    fee.html((parseFloat(rate) * parseInt(num)).toFixed(2));
                }
                countAll();
            });
            for (var i = 1; i < 13; i++) {
                $("#month").append("<option value='" + i + "'>" + i + "</option>")
            }
            var now = new Date();
            $('#year').val(now.getFullYear() - 2000);
            $('#month').val(now.getMonth() + 1);
        });
        function countAll() {
            var totalArr = $('#t_content').find('.total_td');
            var feeArr = $('#t_content').find('.fee_td');
            var totalTmp = 0;
            for (var i = 0; i < totalArr.length; i++) {
                var val = totalArr.eq(i).html();
                if (val)totalTmp += parseFloat(val);
            }
            var feeTmp = 0;
            for (var i = 0; i < feeArr.length; i++) {
                var val = feeArr.eq(i).html();
                if (val)feeTmp += parseFloat(val);
            }
            $('#allPrice').html(totalTmp.toFixed(2));
            $('#allFee').html(feeTmp.toFixed(2));
        }
        function save() {
            var params = generateParams();
            $.ajax({url: "count.save.do",
                data: params,
                success: function (data) {
                    alert(data);
                }
            });
        }
        function generateParams() {
            var trs = $('#t_content').children();
            var list = [];
            var docId = $('#doctor').val();
            for (var i = 0; i < trs.length; i++) {
                var proId = trs.eq(i).find('.pro_td').find('.pro_sel').val();
                var rate = trs.eq(i).find('.rate_td').find('.rate_input').val();
                var perPrice = trs.eq(i).find('.per_price_td').find('.per_price_input').val();
                var num = trs.eq(i).find('.num_td').find('.num_input').val();
                var tmp = {productId: proId, doctorId: docId, price: perPrice, num: num, rate: rate};
                list.push(object2String(tmp));
            }
            var date = 20 + $('#year').val() + "-" + $('#month').val();
            return {'docId': docId,
                'date': date,
                'datalist': "[" + list.toString() + "]"};
        }
        function object2String(obj) {
            var str = "{";
            for (var i in obj) {
                str += i + ":" + obj[i] + ",";
            }
            str = str.substring(0, str.length - 1) + "}";
            return str;
        }
        window.printWin = null;
        function print() {
            printWin = window.open("count/print.jsp");
            var params =  generateParams();
            printWin.window.params = params;
            printWin.window.doctor = docArr[params.docId];
        }
    </script>
</head>
<body>

医生:<select id="doctor">
    <%--<option value="">--请选择--</option>--%>
    <c:forEach items="${doctors}" var="doc">
        <option value="${doc.id}">${doc.name}</option>
    </c:forEach>
</select>
| 20<input id="year" style="width:30px;">年<select id="month"></select>月
<table border="1">
    <tr>
        <td>药品名称</td>
        <td>单价</td>
        <td>返点</td>
        <td>数量</td>
        <td>总额</td>
        <td>返点金额</td>
        <td>操作</td>
    </tr>
    <tbody id="t_content"></tbody>
    <tr>
        <td>总计</td>
        <td></td>
        <td></td>
        <td></td>
        <td id="allPrice"></td>
        <td id="allFee"></td>
        <td class="operate_td"></td>
    </tr>
    <tr>
        <td colspan="7"><input type="button" value="添加" onclick="addProduct();">|<input type="button" value="保存"
                                                                                        onclick="save()">|<input
                type="button" value="打印" onclick="print()"></td>
    </tr>
</table>
</body>
</html>