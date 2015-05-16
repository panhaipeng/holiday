<%--
  Created by IntelliJ IDEA.
  User: DaoDao
  Date: 2015/5/13
  Time: 16:41
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
<%@include file="head.jsp"%>
<h1>登录失败</h1>
</body>
</html>
