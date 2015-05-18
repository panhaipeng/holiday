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
    <div id="departmentDialog">
        <div id="departmentDialogContent">
            <div id="departmentList"></div>
            <div id="inputDepartment">
                <span>上级部门：</span><input type="text" id="inputSuperiorDepartment"><br/><br/>
                <span>部门名称：</span><input type="text" id="inputDepartmentName"><br/><br/>
                <span>部门领导：</span><input type="text" id="inputDepartmentLeaderName"><br/><br/>
                <span>备注信息：</span><textarea id="remark"></textarea><br/>
                <input type="text" id="inputDepartmentId">
                <input type="text" id="inputDepartmentLeader">
                <input type="text" id="inputSuperiorDepartmentNumber">
            </div>
            <div id="departmentButton">
                <input type="button" id="insertDepartmentButton" value="添加"><br/>
                <input type="button" id="updateDepartmentButton" value="修改"><br/>
                <input type="button" id="deleteDepartmentButton" value="删除"><br/>
            </div>
        </div>
    </div>
    <div id="employeeDialog">
        <div id="employeeTableDiv">
            <input id = pageNumber value="0"><input id = pageCount>
            <table id="employeeTable">
                <tr>
                    <th>号码</th>
                    <th>员工姓名</th>
                    <th>Email</th>
                    <th>入职日期</th>
                    <th>所属部门</th>
                    <th>管理</th>
                    <th>操作</th>
                </tr>
                <!--<tr>
                    <td class="tdEmployeeNumber">101</td>
                    <td class="tdEmployeeName">大脸猫</td>
                    <td class="tdEmployeeEmail">dalianmao@ebrun.com</td>
                    <td class="tdEmployeeEntryDate">2015-04-01</td>
                    <td class="tdEmployeeDepartment">人力资源部</td>
                    <td class="tdEmployeeIfAdministration">是</td>
                    <td class="tdEmployeeDo"><input class="updateEmployeeButton" type="button" value="修改"><input class="deleteEmployeeButton" type="button" value="删除"></td>
                </tr>
                <tr>
                <td class="tdEmployeeNumber">101</td>
                <td class="tdEmployeeName">大脸猫</td>
                <td class="tdEmployeeEmail">dalianmao@ebrun.com</td>
                <td class="tdEmployeeEntryDate">2015-04-01</td>
                <td class="tdEmployeeDepartment">人力资源部</td>
                <td class="tdEmployeeIsAdministration">是</td>
                <td class="tdEmployeeDo"><input class="updateEmployeeButton" type="button" value="修改"><input class="deleteEmployeeButton" type="button" value="删除"></td>
            </tr><tr>
                <td class="tdEmployeeNumber">101</td>
                <td class="tdEmployeeName">大脸猫</td>
                <td class="tdEmployeeEmail">dalianmao@ebrun.com</td>
                <td class="tdEmployeeEntryDate">2015-04-01</td>
                <td class="tdEmployeeDepartment">人力资源部</td>
                <td class="tdEmployeeIsAdministration">是</td>
                <td class="tdEmployeeDo"><input class="updateEmployeeButton" type="button" value="修改"><input class="deleteEmployeeButton" type="button" value="删除"></td>
            </tr><tr>
                <td class="tdEmployeeNumber">101</td>
                <td class="tdEmployeeName">大脸猫</td>
                <td class="tdEmployeeEmail">dalianmao@ebrun.com</td>
                <td class="tdEmployeeEntryDate">2015-04-01</td>
                <td class="tdEmployeeDepartment">人力资源部</td>
                <td class="tdEmployeeIsAdministration">是</td>
                <td class="tdEmployeeDo"><input class="updateEmployeeButton" type="button" value="修改"><input class="deleteEmployeeButton" type="button" value="删除"></td>
            </tr><tr>
                <td class="tdEmployeeNumber">101</td>
                <td class="tdEmployeeName">大脸猫</td>
                <td class="tdEmployeeEmail">dalianmao@ebrun.com</td>
                <td class="tdEmployeeEntryDate">2015-04-01</td>
                <td class="tdEmployeeDepartment">人力资源部</td>
                <td class="tdEmployeeIsAdministration">是</td>
                <td class="tdEmployeeDo"><input class="updateEmployeeButton" type="button" value="修改"><input class="deleteEmployeeButton" type="button" value="删除"></td>
            </tr><tr>
                <td class="tdEmployeeNumber">101</td>
                <td class="tdEmployeeName">大脸猫</td>
                <td class="tdEmployeeEmail">dalianmao@ebrun.com</td>
                <td class="tdEmployeeEntryDate">2015-04-01</td>
                <td class="tdEmployeeDepartment">人力资源部</td>
                <td class="tdEmployeeIsAdministration">是</td>
                <td class="tdEmployeeDo"><input class="updateEmployeeButton" type="button" value="修改"><input class="deleteEmployeeButton" type="button" value="删除"></td>
            </tr><tr>
                <td class="tdEmployeeNumber">101</td>
                <td class="tdEmployeeName">大脸猫</td>
                <td class="tdEmployeeEmail">dalianmao@ebrun.com</td>
                <td class="tdEmployeeEntryDate">2015-04-01</td>
                <td class="tdEmployeeDepartment">人力资源部</td>
                <td class="tdEmployeeIsAdministration">是</td>
                <td class="tdEmployeeDo"><input class="updateEmployeeButton" type="button" value="修改"><input class="deleteEmployeeButton" type="button" value="删除"></td>
            </tr><tr>
                <td class="tdEmployeeNumber">101</td>
                <td class="tdEmployeeName">大脸猫</td>
                <td class="tdEmployeeEmail">dalianmao@ebrun.com</td>
                <td class="tdEmployeeEntryDate">2015-04-01</td>
                <td class="tdEmployeeDepartment">人力资源部</td>
                <td class="tdEmployeeIsAdministration">是</td>
                <td class="tdEmployeeDo"><input class="updateEmployeeButton" type="button" value="修改"><input class="deleteEmployeeButton" type="button" value="删除"></td>
            </tr><tr>
                <td class="tdEmployeeNumber">101</td>
                <td class="tdEmployeeName">大脸猫</td>
                <td class="tdEmployeeEmail">dalianmao@ebrun.com</td>
                <td class="tdEmployeeEntryDate">2015-04-01</td>
                <td class="tdEmployeeDepartment">人力资源部</td>
                <td class="tdEmployeeIsAdministration">是</td>
                <td class="tdEmployeeDo"><input class="updateEmployeeButton" type="button" value="修改"><input class="deleteEmployeeButton" type="button" value="删除"></td>
            </tr><tr>
                <td class="tdEmployeeNumber">101</td>
                <td class="tdEmployeeName">大脸猫</td>
                <td class="tdEmployeeEmail">dalianmao@ebrun.com</td>
                <td class="tdEmployeeEntryDate">2015-04-01</td>
                <td class="tdEmployeeDepartment">人力资源部</td>
                <td class="tdEmployeeIsAdministration">是</td>
                <td class="tdEmployeeDo"><input class="updateEmployeeButton" type="button" value="修改"><input class="deleteEmployeeButton" type="button" value="删除"></td>
            </tr>-->
            </table>
        </div>

    </div>
    <div id="holidayDialog"></div>
    <div id="mineDialog"></div>
    <div id="reminderDialog"><p></p></div>
</div>
</body>
</html>