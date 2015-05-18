<%--
  Created by IntelliJ IDEA.
  User: DaoDao
  Date: 2015/5/13
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.employee==null}">
    <a id="index" href="index.jsp">欢迎</a>
    <input id="login" type="button"/>
</c:if>
<c:if test="${sessionScope.employee!=null}">
    <a id="index" href="index.jsp">欢迎</a>
    <a id="main" href="main">主页</a>
    <input id="logout" type="button"/>
    <p>&nbsp;&nbsp;(=^ ^=)&nbsp;&nbsp;欢迎：<c:out value="${ sessionScope.employee.name}"></c:out></p>
</c:if>