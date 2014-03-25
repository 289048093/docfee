<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 14-3-24
  Time: 下午5:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title></title>
    <style type="text/css">
        td {
            width: 100px;
        }
    </style>
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="js/global.js"></script>
    <script type="text/javascript">
        var params;
        var doctor;
        var products;
        var total_money_ = 0;
        var total_fee_ = 0;
        function setParams(par, doc, pro) {
            params = par;
            doctor = doc;
            products = pro;
        }
        function renderData() {
            if (!params) {
                setTimeout(renderData, 10);
                return;
            }
            var date = params.date;
            var docName = doctor.name;
            $("#doc_name_sp").html(docName);
            $("#date_sp").html(date);
            var list = eval(params.datalist);
            for (var i = 0; i < list.length; i++) {
                $('#t_body').append(generateTr(list[i]));
            }
            $('#total_money_td').html(total_money_.toFixed(2));
            $('#total_fee_td').html(total_fee_.toFixed(2));

        }
        function generateTr(data) {
            var name_ = products[data.productId].name;
            var price_ = data.price;
            var rate_ = data.rate;
            var num_ = data.num;
            var total_ = (parseFloat(price_) * parseInt(num_)).toFixed(2);
            var fee_ = (total_ * parseFloat(rate_)).toFixed(2);
            var td_ = "</td><td>";
            total_money_ += parseFloat(total_);
            total_fee_ += parseFloat(fee_);
            return ["<tr><td>" + name_, price_, rate_, num_, total_, fee_ + "</td><tr>"].join(td_);
        }
        $(function () {
            renderData();
        });

    </script>
</head>
<body>
<table>
    <thead>
    <tr>
        <td><span id="doc_name_sp"></span></td>
        <td><span id="date_sp"></span></td>
    </tr>
    <tr style="height:50px;">
        <td>药品名</td>
        <td>价格</td>
        <td>返点数</td>
        <td>数量</td>
        <td>总额</td>
        <td>返点金额</td>
    </tr>
    </thead>
    <tfoot>
    <tr>
        <td>合计</td>
        <td colspan="3"></td>
        <td id="total_money_td"></td>
        <td id="total_fee_td"></td>
    </tr>
    </tfoot>
    <tbody id="t_body">

    </tbody>
</table>

</body>
</html>