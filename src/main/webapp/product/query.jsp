<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 14-3-22
  Time: 下午2:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>query</title>
    <style>
        td{
            width:100px;
        }
    </style>
</head>
<body>
<table style="border:1px solid black;" border="1">
    <tr>
        <td>名称</td>
        <td>价格</td>
        <td>默认返点</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${products}" var="pro">
    <tr>
        <td>${pro.name}</td>
        <td>${pro.price}元</td>
        <td>${pro.defaultRate}</td>
        <td><a href="product.delete.do?id=${pro.id}">删除</a></td>
    </tr>
    </c:forEach>
</table>
</body>
</html>