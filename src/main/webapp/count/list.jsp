<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 14-3-24
  Time: 下午4:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title></title>
    <meta http-equiv="content-type" charset="UTF-8">
    <style type="text/css">
        td{
            width: 100px;
        }
    </style>
</head>
<body>
<p> record list</p>
<table border="1">
    <tr>
        <td>医院</td>
        <td>医生</td>
        <td>药品名</td>
        <td>价格</td>
        <td>扣率</td>
        <td>数量</td>
        <td>时间</td>
    </tr>
    <c:forEach items="${requestScope.list}" var="item">
        <tr>
        <td>${item.doctor.hospital}</td>
        <td>${item.doctor.name}</td>
        <td>${item.product.name}</td>
        <td>${item.price}</td>
        <td>${item.rate}</td>
        <td>${item.num}</td>
        <td>${item.date}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>