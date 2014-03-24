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
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript">
        var params;
        var doctor;
       $(function(){
           var date = params.date;
           var docName = doctor.name;
           $("#doc_name_sp").html(docName);
           $("#date_sp").html(date);
           if(params){
               var list = params.datalist;
               for(var i=0;i<list.lenth;i++){

               }
           }
       });
    </script>
</head>
<body>
print list
<table>
    <thead>
    <tr>
        <td><span id="doc_name_sp"></span></td>
        <td><span id="date_sp"></span></td>
    </tr>
    </thead>
    <tbody id="t_body">

    </tbody>
</table>

</body>
</html>