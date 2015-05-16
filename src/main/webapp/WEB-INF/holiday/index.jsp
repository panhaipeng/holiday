<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<%@include file="head.jsp" %>
<h2>Hello World!!</h2>
<a href="showEmployee/1">List All Employees</a><br/>
<a href="showDepartment">List All Department</a><br/>
<a href="showDepartmentByAdministration">showDepartmentByAdministration</a><br/>
<br/>
<form action="addDepartment" method="post">
    SuperiorDepartment:<input type="text" name="superiorDepartment"/><br/>
    DepartmentName:<input type="text" name="departmentName"/><br/>
    DepartmentLeader:<input type="text" name="departmentLeader" /><br/>
    Remark:<input type="textarea" name="remark"/><br/>
    <input type="submit" value="submit"/>
</form>
</body>
</html>