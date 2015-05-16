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
    <a href="index.jsp">登录</a>
</c:if>
<c:if test="${sessionScope.employee!=null}">
    欢迎：<c:out value="${ sessionScope.employee.name}"></c:out>
    <a href="logout">退出</a>
</c:if>