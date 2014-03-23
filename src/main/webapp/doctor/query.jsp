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
<table border="1">
    <tr>
        <td>姓名</td>
        <td>医院</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${doctors}" var="doc">
    <tr>
        <td>${doc.name}</td>
        <td>${doc.hospital}元</td>
        <td><a href="doctor.delete.do?id=${doc.id}">删除</a></td>
    </tr>
    </c:forEach>
</table>
</body>
</html>