<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/jquery-ui.theme.css"/>
    <link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
</head>
<body>
<div id="bodyDiv">
    <%@include file="head.jsp" %>
    <div id="loginForm">
        Email:<br/><input type="text" id="email" name="email"/><br/>
        Password:<br/><input type="password" id="password" name="password"/><br/>
    </div>
    <!--
    <a href="showEmployee/1">List All Employees</a><br/>
    <a href="showDepartment">List All Department</a><br/>
    <a href="showDepartmentByAdministration">showDepartmentByAdministration</a><br/>
    <br/>

    <form action="addDepartment" method="post">
        SuperiorDepartment:<input type="text" name="superiorDepartment"/><br/>
        DepartmentName:<input type="text" name="departmentName"/><br/>
        DepartmentLeader:<input type="text" name="departmentLeader"/><br/>
        Remark:<input type="textarea" name="remark"/><br/>
        <input type="submit" value="submit"/>
    </form>
    <div id="div1">
        <button id="btn1">显示</button>
    </div>
    <div id="div2">
        a
    </div>
    <div id="div3">
        a
    </div>
    -->
    <div id="mainDiv">
        <a href="#">
            <div id="departmentDiv">
                <p>&nbsp;部&nbsp;门</p>
            </div>
        </a>
        <a href="#">
            <div id="employeeDiv">
                <p>&nbsp;员&nbsp;工</p>
            </div>
        </a>
        <a href="#">
            <div id="holidayDiv">
                <p>&nbsp;休&nbsp;假</p>
            </div>
        </a>
        <a href="#">
            <div id="mineDiv">
                <P>&nbsp;我&nbsp;的</P>
            </div>
        </a>
    </div>
    <div id="departmentDialog"></div>
    <div id="employeeDialog"></div>
    <div id="holidayDialog"></div>
    <div id="mineDialog"></div>
</div>
</body>
</html>