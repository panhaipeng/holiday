<%--
  Created by IntelliJ IDEA.
  User: DaoDao
  Date: 2015/5/12
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title></title>
</head>
<body>
<%@include file="head.jsp" %>
<!--
${employee.name}<br/>
${employee.email}<br/>
${employee.entryDate}<br/>
${employee.departmentId}<br/>
${employee.ifAdministration}<br/>
-->
${employee}
</body>
</html>