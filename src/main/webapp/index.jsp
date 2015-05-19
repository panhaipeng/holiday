<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<title>亿邦动力-年假计算器-1.0-Bate</title>
<meta name="keywords" content="亿邦动力,年假,计算器">
<meta name="description" content="亿邦动力年假计算器">
<link rel="Shortcut Icon" href="imgs/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.theme.css">
<link rel="stylesheet" type="text/css" href="css/index.css">
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="js/index.js"></script>

</head>
<body>
	<div id="bodyDiv">
		<input id="refresh" type="button"/>
		<c:if test="${sessionScope.employee==null}">
			<input id="login" type="button"/>
		</c:if>
		<c:if test="${sessionScope.employee!=null}">
			<a id="main" href="main">首页</a>
			<input id="logout" type="button"/>
			<p>&nbsp;&nbsp;(=^ ^=)&nbsp;&nbsp;你好：<c:out value="${ sessionScope.employee.name}"></c:out></p>
		</c:if>
		<div id="loginForm">
			Email:<br/><input type="text" id="email" name="email"/><br/>
			Password:<br/><input type="password" id="password" name="password"/><br/>
		</div>
		<div id="dateDialog">
			点选入职日期：Date of entry<br/><br/><input type="text" id="datepicker" readonly="readonly"/><input id="datepickerButton" type="button"/>
		</div>
		<div id="resultDialog">
			<p id="resultText"></p>
		</div>
	</div>
</body>
</html>