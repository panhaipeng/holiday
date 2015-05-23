<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <title>亿邦动力-年假管理系统-1.0-Bate</title>
    <meta name="keywords" content="亿邦动力,年假,管理系统">
    <meta name="description" content="亿邦动力年假管理系统">

    <link rel="Shortcut Icon" href="imgs/favicon.ico"
          type="image/x-icon"/>

    <link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/jquery-ui.theme.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="js/jquery.ui.datepicker-zh-CN.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
</head>
<body>
<div id="bodyDiv">
    <input id="help" type="button"/>
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
                <span>上级部门：</span><input type="text" id="inputSuperiorDepartment" readonly="readonly"><br/><br/>
                <span>部门名称：</span><input type="text" id="inputDepartmentName"><br/><br/>
                <span>部门领导：</span><input type="text" id="inputDepartmentLeaderName"><br/><br/>
                <span>选择领导：</span><input type="button" id="chooseDepartmentLeaderButton"><br/><br/>
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
            <input id=pageNumber><input id=pageCount><input id=employeeKeyword>
            <table id="employeeTable">

            </table>
        </div>

    </div>
    <div id="holidayDialog">
        <div id="employeeInfo">
            <!--
            <span id="employeeInfoId"></span>
                        <span id="employeeInfoName">XXX</span><br/><br/>
                        <span id="employeeInfoEmail">XXXXXXXXXX@ebrun.com</span><br/><br/>
                        <span>2015&nbsp;&nbsp;财年</span><br/><br/>
                        <span>（2015-04-01 &nbsp;&nbsp;-&nbsp;&nbsp; 2016-03-31）</span><br/><br/>
                        <span>这是&nbsp;&nbsp;XXX&nbsp;&nbsp;的第&nbsp;&nbsp;N&nbsp;&nbsp;个财年</span><br/><br/>
                        <span>总共有&nbsp;&nbsp;N&nbsp;&nbsp;天年假</span><br/><br/>
                        <span>已经休假&nbsp;&nbsp;N&nbsp;&nbsp;天</span><br/><br/>
                        <span>休假日期列表&nbsp;&nbsp;→</span><br/><br/>
                        <span><input type="button" value="查看往年"><input type="button" value="添加年假"></span>
            -->
        </div>
        <div id="vacationInfo">
            <table id="vacationInfoTable">
                <!--
                <tr>
                    <td>2015-06-07</td>
                    <td>半天</td>
                    <td><input type="button" value="删除"></td>
                </tr>
                -->
            </table>
        </div>
    </div>
    <div id="mineDialog"></div>


    <div id="reminderDialog"><p></p></div>
    <div id="searchEmployeeListDialog">
        <input type="text" id="searchEmployeeListKeyword">
    </div>
    <div id="inputEmployeeDialog">
        <input id="inputEmployeeId"/>
        <span>号码：</span><input id="inputEmployeeNumber" type="text"/><br/>
        <span>姓名：</span><input id="inputEmployeeName" type="text"/><br/>
        <span>邮箱：</span><input id="inputEmployeeEmail" type="text"/><br/>
        <span>密码：</span><input id="inputEmployeePassword" type="text"/><br/>
        <span>入职：</span><input id="inputEmployeeEntryDate" type="text" readonly="readonly"/><input type="button"
                                                                                                   id="showEntryDateButton"><br/>
        <span>离职：</span><input id="inputEmployeeLeaveDate" type="text" readonly="readonly"/><input type="button"
                                                                                                   id="showLeaveDateButton"><br/>
        <span id="inputEmployeeDepartmentId"></span>
        <span>部门：</span><input id="inputEmployeeDepartmentName" type="text" readonly="readonly"/><input type="button"
                                                                                                        id="chooseDepartmentButton"><br/>
        <span id="inputEmployeeIfAdministrationValue"></span>
        <span>管理：</span><input id="inputEmployeeIfAdministration" type="text" readonly="readonly" value="NO"/><input
            type="button" id="changeIfAdministration"><br/>
        <span>备注：</span><textarea id="inputEmployeeRemark"></textarea>
    </div>
    <div id="chooseDepartmentDialog">
        <div id="listDepartmentTree">
        </div>
    </div>
    <div id="helpDialog">
        <p id="helpText"></p>
    </div>
    <div id="employeeList">
        <table id="employeeListTable">

        </table>
    </div>
    <div id="showMoreFiscalYearDiv">
        <div id="fiscalYearList"></div>
    </div>
    <div id="addVacationDiv">
        <div id="addVacation">
            <span>日期：</span><input id="inputVacationDate" type="text" readonly="readonly"/><input type="button"
                                                                                                       id="showVacationDateButton"><br/>
            <span id="inputIfFullDayValue"></span>
            <span>管理：</span><input id="inputIfFullDay" type="text" readonly="readonly" value="半天"/><input
                type="button" id="changeIfFullDayButton"><br/>
        </div>
    </div>
</div>
</body>
</html>