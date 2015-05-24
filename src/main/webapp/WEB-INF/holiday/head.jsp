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

    <p>&nbsp;&nbsp;(=^ ^=)&nbsp;&nbsp;你好，请登录！</p>
</c:if>
<c:if test="${sessionScope.employee!=null}">
    <a id="index" href="index.jsp">欢迎</a>
    <a id="main" href="main">主页</a>
    <input id="logout" type="button"/>
    <span id="userId"><c:out value="${ sessionScope.employee.id}"></c:out></span>
    <span id="userIfAdministration"><c:out value="${ sessionScope.employee.ifAdministration}"></c:out></span>

    <p>&nbsp;&nbsp;(=^ ^=)&nbsp;&nbsp;你好：
        <c:out value="${ sessionScope.employee.name}"></c:out>
    </p>
</c:if>
<c:if test="${sessionScope.department==null}">
    <span id="userIsDepartmentLeader">false</span>
</c:if>
<c:if test="${sessionScope.department!=null}">
    <p id="userDepartmentInfo">
        <span id="userIfDepartmentLeader">true</span>
        <span id="userDepartmentId">${sessionScope.department.id}</span>
        <span id="userDepartmentNumber">${sessionScope.department.departmentNumber}</span>
        <span id="userDepartmentName">${sessionScope.department.departmentName}</span>
    </p>
</c:if>