/**
 * Created by Administrator on 2015/5/16.
 */
$(function () {
    $("#help").button({
        label: "关于"
    });
    $("#index").button({
        label: "欢迎"
    });
    $("#login").button({
        label: "登录"
    });

    $("#logout").button({
        label: "退出"
    });
    $("#main").button({
        label: "主页"
    });
    $("#insertDepartmentButton").button({
        label: "添加"
    });
    $("#updateDepartmentButton").button({
        label: "修改"
    });
    $("#deleteDepartmentButton").button({
        label: "删除"
    });

    $("#showEntryDateButton").button({
        label: "日历"
    });
    $("#showLeaveDateButton").button({
        label: "日历"
    });

    $("#chooseDepartmentButton").button({
        label: "选择"
    });
    $("#changeIfAdministration").button({
        label: "变更"
    });

    $("#help").click(function () {
        var helpText = "感谢使用亿邦动力年假管理系统！<br/><br/>" +
            "您可以在这里查看您每年的年假天数和已休天数，也可以查看具体日期。<br/><br/>" +
            "如果您是领导，您可以查看您所领导的部门的成员的相关信息和休假详情。<br/><br/>" +
            "如果您是管理员，您可以对所有员工和部门以及休假信息进行增删改查工作。<br/><br/>" +
            "BUG反馈请联系技术部李涛，litao@ebrun.com 。<br/><br/>" +
            "祝您工作愉快！";
        $("#helpText").html(helpText);
        $("#helpDialog").dialog({
            autoOpen: true,
            minWidth: 600,
            minHeight: 400,
            maxWidth: 600,
            maxHeight: 600,
            title: "感谢使用！Thanks A Lot：",
            show: {
                effect: "bounce",
                duration: 500
            },
            hide: {
                effect: "explode",
                duration: 500
            },

            buttons: {
                "close": function () {
                    $(this).dialog("close");
                }
            }
        });
    });

    $("#logout").click(function () {
        $.ajax({
            type: "GET",
            url: "logout",
            success: function () {
                window.location.href = "index.jsp";
            }
        });
    });

    $("#login").click(function () {
        $("#loginForm").dialog({
            autoOpen: true,
            minWidth: 300,
            minHeight: 240,
            maxWidth: 600,
            maxHeight: 400,
            title: "(=^･ｪ･^=)    输入登录信息：",
            show: {
                effect: "bounce",
                duration: 500
            },
            hide: {
                effect: "explode",
                duration: 1000
            },

            buttons: {
                "login": function () {
                    $.ajax({
                        type: "POST",
                        url: "login",
                        data: {email: $("#email").val(), password: $("#password").val()},
                        success: function (data) {
                            if (data.login == "success") {
                                $("#loginForm").dialog("close");
                                window.location.href = "main";
                            } else {
                                $("#loginForm").dialog({title: "(=^･ｪ･^=)    登录失败，重新输入！"});
                            }
                        }
                    });
                },
                "close": function () {
                    $(this).dialog("close");
                }
            }
        })
    });
    $("#departmentDiv").click(function () {
        showDepartmentList();
        showDepartmentInput();
        hideUpdateDepartmentButton();
        hideInsertDepartmentButton();
        hideDeleteDepartmentButton();
        $("#departmentDialog").dialog({
            autoOpen: true,
            minWidth: 1000,
            minHeight: 600,
            maxWidth: 1280,
            maxHeight: 600,
            title: "部门管理：删除请点击 - 号，添加请点击 + 号，修改请点击部门名称",
            show: {
                effect: "bounce",
                duration: 500
            },
            hide: {
                effect: "explode",
                duration: 1000
            },

            buttons: {
                "close": function () {
                    $(this).dialog("close");
                }
            }
        })
    });
    /*
     function hideDepartmentList() {
     $("#departmentList").hide();
     }
     */
    function showDepartmentList() {
        //alert(1);
        var tree = "";
        $.ajax({
            type: "GET",
            url: "showDepartmentByAdministration?time=" + new Date().getTime(),
            cache: false,
            async: false,
            success: function (data) {
                tree = showDepartment(data);
                $("#departmentList").html(tree);
                $("#departmentList").show();
                $(".departmentUl").hide();
                $("li").css({"list-style": "none"});
                $("ul").css({"margin-left": "20px"});
                $(".deleteDepartmentFlag,.insertDepartmentFlag").css({
                    "font-family": "Helvetica, Arial, sans-serif",
                    "font-size": "1.1em",
                    "color": "#ff0084",
                    "font-weight": "bold"
                });
                $(".span1,.span2,.span3,.span4").hide();
                $(".departmentLi").hover(function () {
                    $(this).children(".departmentUl").show();
                    $(this).parent().parent().siblings(".departmentLi").children(".departmentUl").hide();
                });

                $(".departmentNameLiA").click(function (event) {
                    //var inputSuperiorDepartment = $(this).parent(".departmentLi").parent(".departmentUl").parent(".departmentLi").child("a").text();
                    var inputSuperiorDepartment = $(this).parent(".departmentLi").parent().parent().find(".departmentNameLiA").html();
                    var inputDepartmentName = $(this).text();
                    var inputDepartmentId = $(this).parent(".departmentLi").find(".span1:first").text();
                    var inputDepartmentLeader = $(this).parent(".departmentLi").find(".span2:first").text();
                    var inputDepartmentRemark = $(this).parent(".departmentLi").find(".span3:first").text();
                    var inputDepartmentLeaderName = $(this).parent(".departmentLi").find(".span4:first").text();
                    var inputSuperiorDepartmentNumber = $(this).parent(".departmentLi").parent().parent().attr("id");
                    //alert(inputSuperiorDepartmentNumber);
                    $("#inputSuperiorDepartment").val(inputSuperiorDepartment);
                    $("#inputDepartmentName").val(inputDepartmentName);
                    $("#inputDepartmentLeaderName").val(inputDepartmentLeaderName);
                    $("#remark").val(inputDepartmentRemark);
                    $("#inputDepartmentId").val(inputDepartmentId);
                    $("#inputDepartmentLeader").val(inputDepartmentLeader);
                    $("#inputSuperiorDepartmentNumber").val(inputSuperiorDepartmentNumber);

                    showUpdateDepartmentButton();
                    hideInsertDepartmentButton();
                    hideDeleteDepartmentButton();
                    event.stopPropagation();
                    return false;
                });
                $(".insertDepartmentFlag").click(function (event) {
                    var inputSuperiorDepartment = $(this).parent(".departmentLi").find(".departmentNameLiA").html();
                    //var inputDepartmentName = $(this).text();
                    //var inputDepartmentId = $(this).parent(".departmentLi").find(".span1:first").text();
                    //var inputDepartmentLeader = $(this).parent(".departmentLi").find(".span2:first").text();
                    //var inputDepartmentRemark = $(this).parent(".departmentLi").find(".span3:first").text();
                    //var inputDepartmentLeaderName = $(this).parent(".departmentLi").find(".span4:first").text();
                    var inputSuperiorDepartmentNumber = $(this).parent(".departmentLi").attr("id");
                    //alert(inputDepartmentLeaderName);
                    $("#inputSuperiorDepartment").val(inputSuperiorDepartment);
                    $("#inputDepartmentName").val("");
                    $("#inputDepartmentLeaderName").val("");
                    $("#remark").val("");
                    $("#inputDepartmentId").val();
                    $("#inputDepartmentLeader").val("");
                    $("#inputSuperiorDepartmentNumber").val(inputSuperiorDepartmentNumber);

                    hideUpdateDepartmentButton();
                    showInsertDepartmentButton();
                    hideDeleteDepartmentButton();
                    event.stopPropagation();
                    return false;
                });
                $(".deleteDepartmentFlag").click(function (event) {
                    var inputSuperiorDepartment = $(this).parent(".departmentLi").parent().parent().find(".departmentNameLiA").html();
                    var inputDepartmentName = $(this).next(".departmentNameLiA").text();
                    var inputDepartmentId = $(this).parent(".departmentLi").find(".span1:first").text();
                    var inputDepartmentLeader = $(this).parent(".departmentLi").find(".span2:first").text();
                    var inputDepartmentRemark = $(this).parent(".departmentLi").find(".span3:first").text();
                    var inputDepartmentLeaderName = $(this).parent(".departmentLi").find(".span4:first").text();
                    var inputSuperiorDepartmentNumber = $(this).parent(".departmentLi").parent().parent().attr("id");
                    //alert(inputSuperiorDepartmentNumber);
                    $("#inputSuperiorDepartment").val(inputSuperiorDepartment);
                    $("#inputDepartmentName").val(inputDepartmentName);
                    $("#inputDepartmentLeaderName").val(inputDepartmentLeaderName);
                    $("#remark").val(inputDepartmentRemark);
                    $("#inputDepartmentId").val(inputDepartmentId);
                    $("#inputDepartmentLeader").val(inputDepartmentLeader);
                    $("#inputSuperiorDepartmentNumber").val(inputSuperiorDepartmentNumber);

                    hideUpdateDepartmentButton();
                    hideInsertDepartmentButton();
                    //alert(1);
                    showDeleteDepartmentButton();
                    //$("#insertDepartmentButton").show();
                    event.stopPropagation();
                    return false;
                });
            }
        });
        //alert(tree);

        function showDepartment(data) {
            var tree1 = "";

            $.each(data, function (key, value) {
                //alert(value.subordinateDepartment);
                if (typeof (value.subordinateDepartment) == "object") {
                    tree1 += "<li id=" + key + " class='departmentLi'>&nbsp;&nbsp;&nbsp;<a href='#' class='departmentNameLiA'>" + value.departmentName + "</a>" +
                        "&nbsp;&nbsp;<a href='#' class='insertDepartmentFlag'>+</a><span  class='span1'>" + value.id + "</span><span class='span2'>" + value.departmentLeader + "</span>" +
                        "<span class='span3'>" + value.remark + "</span><span class='span4'>" + value.departmentLeaderName + "</span>" +
                        "<ul class='departmentUl'>" + showDepartment(value.subordinateDepartment) + "</ul>" + "</li>"
                } else {
                    tree1 += "<li id=" + key + " class='departmentLi'><a href='#' class='deleteDepartmentFlag'>-&nbsp;&nbsp;</a><a href='#' class='departmentNameLiA'>" + value.departmentName + "</a>" +
                        "&nbsp;&nbsp;<a href='#' class='insertDepartmentFlag'>+</a><span  class='span1'>" + value.id + "</span><span class='span2'>" + value.departmentLeader + "</span>" +
                        "<span class='span3'>" + value.remark + "</span><span class='span4'>" + value.departmentLeaderName + "</span></li>"
                }
            });
            //alert(tree);
            return tree1;
        }
    };

    function showDepartmentInput() {
        $("#inputSuperiorDepartment").val("");
        $("#inputDepartmentName").val("");
        $("#inputDepartmentLeaderName").val("");
        $("#remark").val("");
        $("#inputDepartmentId").val("");
        $("#inputDepartmentLeader").val("");
        $("#inputSuperiorDepartmentNumber").val("");
        $("#inputDepartment").show();
    };
    /*
     function showDepartmentButton() {
     $("#departmentButton").show();
     };
     */
    //function hideDepartmentButton(){
    //    $("#departmentButton").hide();
    //};
    function showInsertDepartmentButton() {
        $("#insertDepartmentButton").show();
    };
    function hideInsertDepartmentButton() {
        $("#insertDepartmentButton").hide();
    };
    function showUpdateDepartmentButton() {
        $("#updateDepartmentButton").show();
    };
    function hideUpdateDepartmentButton() {
        $("#updateDepartmentButton").hide();
    };
    function showDeleteDepartmentButton() {
        $("#deleteDepartmentButton").show();
    };
    function hideDeleteDepartmentButton() {
        $("#deleteDepartmentButton").hide();
    };

    $("#insertDepartmentButton").click(function () {
        var inputDepartmentName = $("#inputDepartmentName").val();
        var inputDepartmentRemark = $("#remark").val();
        var inputDepartmentLeader = $("#inputDepartmentLeader").val();
        var inputSuperiorDepartmentNumber = $("#inputSuperiorDepartmentNumber").val();
        $.ajax({
            type: "POST",
            url: "insertDepartment",
            data: {
                inputSuperiorDepartmentNumber: inputSuperiorDepartmentNumber,
                inputDepartmentName: inputDepartmentName,
                inputDepartmentLeader: inputDepartmentLeader,
                inputDepartmentRemark: inputDepartmentRemark
            },
            success: function (data) {
                if (data.insert == "success") {
                    $("#reminderDialog p").text("添加成功！");
                    $("#reminderDialog").dialog({
                        autoOpen: true,
                        minWidth: 300,
                        minHeight: 200,
                        maxWidth: 300,
                        maxHeight: 200,
                        title: "提示：",
                        show: {
                            effect: "bounce",
                            duration: 500
                        },
                        hide: {
                            effect: "explode",
                            duration: 1000
                        },

                        buttons: {
                            "close": function () {
                                $(this).dialog("close");
                                showDepartmentList();
                                showDepartmentInput();
                                hideInsertDepartmentButton();
                            }
                        }
                    });
                }
            }
        });
    });
    $("#deleteDepartmentButton").click(function () {
        var deleteDepartmentId = $("#inputDepartmentId").val();
        $.ajax({
            type: "GET",
            url: "deleteDepartment",
            data: {deleteDepartmentId: deleteDepartmentId},
            success: function (data) {
                if (data.delete == "success") {
                    $("#reminderDialog p").text("删除成功！");
                    $("#reminderDialog").dialog({
                        autoOpen: true,
                        minWidth: 300,
                        minHeight: 200,
                        maxWidth: 300,
                        maxHeight: 200,
                        title: "提示：",
                        show: {
                            effect: "bounce",
                            duration: 500
                        },
                        hide: {
                            effect: "explode",
                            duration: 1000
                        },

                        buttons: {
                            "close": function () {
                                $(this).dialog("close");
                                showDepartmentList();
                                showDepartmentInput();
                                hideDeleteDepartmentButton();
                            }
                        }
                    })
                }
            }
        });
    });
    $("#updateDepartmentButton").click(function () {
        var inputDepartmentId = $("#inputDepartmentId").val();
        var inputDepartmentName = $("#inputDepartmentName").val();
        var inputDepartmentLeader = $("#inputDepartmentLeader").val();
        var inputDepartmentRemark = $("#remark").val();
        $.ajax({
            type: "POST",
            url: "updateDepartment",
            data: {
                inputDepartmentId: inputDepartmentId,
                inputDepartmentName: inputDepartmentName,
                inputDepartmentLeader: inputDepartmentLeader,
                inputDepartmentRemark: inputDepartmentRemark
            },
            success: function (data) {
                if (data.update == "success") {
                    $("#reminderDialog p").text("修改成功！");
                    $("#reminderDialog").dialog({
                        autoOpen: true,
                        minWidth: 300,
                        minHeight: 200,
                        maxWidth: 300,
                        maxHeight: 200,
                        title: "提示：",
                        show: {
                            effect: "bounce",
                            duration: 500
                        },
                        hide: {
                            effect: "explode",
                            duration: 1000
                        },

                        buttons: {
                            "close": function () {
                                $(this).dialog("close");
                                showDepartmentList();
                                showDepartmentInput();
                                hideUpdateDepartmentButton();
                            }
                        }
                    })
                }
            }
        });
    });

    $("#employeeDiv").click(function () {
        employeeKeyword = "";
        pageNumber = 1;
        showEmployeeListByPage(employeeKeyword, pageNumber);
        $("#employeeDialog").dialog({
            autoOpen: true,
            minWidth: 1000,
            minHeight: 600,
            maxWidth: 1280,
            maxHeight: 600,
            title: "员工管理：    关键词： \" " + employeeKeyword + " \"  。    第  " + (pageNumber) + "  /  " + pageCount + "  页",
            show: {
                effect: "bounce",
                duration: 500
            },
            hide: {
                effect: "explode",
                duration: 1000
            },

            buttons: {
                "首页": function () {
                    pageNumber = 1;
                    showEmployeeListByPage(employeeKeyword, pageNumber);
                    $("#employeeDialog").dialog({title: "员工管理：    关键词： \" " + employeeKeyword + " \"  。    第  " + (pageNumber) + "  /  " + pageCount + "  页",});
                },
                "前页": function () {
                    pageNumber = pageNumber - 1;
                    if (pageNumber < 1) {
                        pageNumber = 1;
                    }
                    showEmployeeListByPage(employeeKeyword, pageNumber);
                    $("#employeeDialog").dialog({title: "员工管理：    关键词： \" " + employeeKeyword + " \"  。    第  " + (pageNumber) + "  /  " + pageCount + "  页",});
                },
                "后页": function () {
                    pageNumber = pageNumber + 1;
                    if (pageNumber > pageCount) {
                        pageNumber = pageCount;
                    }
                    showEmployeeListByPage(employeeKeyword, pageNumber);
                    $("#employeeDialog").dialog({title: "员工管理：    关键词： \" " + employeeKeyword + " \"  。    第  " + (pageNumber) + "  /  " + pageCount + "  页",});
                },
                "末页": function () {
                    pageNumber = pageCount;
                    showEmployeeListByPage(employeeKeyword, pageNumber);
                    $("#employeeDialog").dialog({title: "员工管理：    关键词： \" " + employeeKeyword + " \"  。    第  " + (pageNumber) + "  /  " + pageCount + "  页",});
                },
                "查找": function () {
                    searchEmployeeListDialog();
                },
                "重置": function () {
                    employeeKeyword = $("#searchEmployeeListKeyword").val();
                    pageNumber = 1;
                    showEmployeeListByPage(employeeKeyword, pageNumber);
                    $("#employeeDialog").dialog({title: "员工管理：    关键词： \" " + employeeKeyword + " \"  。    第  " + (pageNumber) + "  /  " + pageCount + "  页",});
                },
                "添加": function () {
                    inputEmployeeDialog();
                },
                "close": function () {
                    $(this).dialog("close");
                    $("#inputEmployeeId").val("");
                    $("#inputEmployeeNumber").val("");
                    $("#inputEmployeeName").val("");
                    $("#inputEmployeeEmail").val("");
                    $("#inputEmployeePassword").val("");
                    $("#inputEmployeeEntryDate").val("");
                    $("#inputEmployeeLeaveDate").val("");
                    $("#inputEmployeeDepartmentName").val("");
                    $("#inputEmployeeDepartmentId").val(1);
                    $("#inputEmployeeIfAdministration").val("NO");
                    $("#inputEmployeeIfAdministrationValue").val(0);
                    $("#inputEmployeeRemark").val("");
                }
            }
        })
    });
    function searchEmployeeListDialog() {
        $("#searchEmployeeListDialog").dialog({
            autoOpen: true,
            minWidth: 300,
            minHeight: 200,
            maxWidth: 300,
            maxHeight: 200,
            title: "关键词：号码|姓名|邮箱|日期|部门",
            show: {
                effect: "bounce",
                duration: 500
            },
            hide: {
                effect: "explode",
                duration: 1000
            },

            buttons: {
                "搜索": function () {
                    employeeKeyword = $("#searchEmployeeListKeyword").val();
                    pageNumber = 1;
                    showEmployeeListByPage(employeeKeyword, pageNumber);
                    $("#employeeDialog").dialog({title: "员工管理：    关键词： \" " + employeeKeyword + " \"  。    第  " + (pageNumber) + "  /  " + pageCount + "  页",});
                    $("#searchEmployeeListKeyword").val("");
                    $(this).dialog("close");
                },
                "close": function () {
                    $("#searchEmployeeListKeyword").val("");
                    $(this).dialog("close");
                }
            }
        })
    }

    var employeeKeyword;
    var pageNumber;
    var pageCount;

    function showEmployeeListByPage(employeeKeyword, pageNumber) {
        //pageNumber = $("#pageNumber").val();
        $.ajax({
            type: "GET",
            url: "showEmployeeListByPage?time=" + new Date().getTime(),
            data: {employeeKeyword: employeeKeyword, pageNumber: pageNumber, pageSize: 10},
            cache: false,
            async: false,
            success: function (data) {
                employeeKeyword = data.employeeKeyword;
                $("#employeeKeyword").val(employeeKeyword);
                pageNumber = data.pageNumber;
                $("#pageNumber").val(pageNumber);
                pageCount = data.pageCount;
                $("#pageCount").val(pageCount);
                var trTh = "<tr> " +
                    "<th class='tdEmployeeNumber'>号码</th> " +
                    "<th class='tdEmployeeName'>员工姓名</th> " +
                    "<th class='tdEmployeeEmail'>Email</th> " +
                    "<th class='tdEmployeeEntryDate'>入职日期</th> " +
                    "<th class='tdEmployeeDepartment'>所属部门</th> " +
                    "<th class='tdEmployeeIfAdministration'>管理</th> " +
                    "<th class='tdEmployeeDo'>操&nbsp;&nbsp;&nbsp;&nbsp;作</th> " +
                    "</tr>";
                var trTd = "";
                $(data.employeeList).each(function (index, employee) {
                    // alert(employee.name);
                    var entryDate = new Date(employee.entry_date);
                    if (employee.leave_date == null) {
                        var leaveDate = "";
                    } else {
                        var leaveDate = (new Date(employee.leave_date)).format("yyyy-MM-dd");
                    }//alert(leaveDate);
                    if (employee.remark == null) {
                        var employeeRemark = "";
                    } else {
                        var employeeRemark = employee.remark;
                    }
                    trTd += "<tr>" +
                        "<td class='tdEmployeeNumber'>" + employee.employee_number + "</td>" +
                        "<td class='tdEmployeeName'>" + employee.name + "</td>" +
                        "<td class='tdEmployeeEmail'>" + employee.email + "</td>" +
                        "<td class='tdEmployeeEntryDate'>" + entryDate.format("yyyy-MM-dd") + "</td>" +
                        "<td class='tdEmployeeDepartment'>" + employee.department_name + "</td>" +
                        "<td class='tdEmployeeIfAdministration'>" + employee.if_administration + "</td>" +
                        "<td class='tdEmployeeDo'><input class='updateEmployeeButton' type='button' value='修改'>" +
                        "<input class='deleteEmployeeButton' type='button' value='删除'>" +
                        "<span class='spanId'>" + employee.id + "</span>" +
                        "<span class='spanPassword'>" + employee.password + "</span>" +
                        "<span class='spanLeaveDate'>" + leaveDate + "</span>" +
                        "<span class='spanDepartmentId'>" + employee.department_id + "</span>" +
                        "<span class='spanRemark'>" + employeeRemark + "</span></td>" +
                        "</tr>";
                });
                $("#employeeTable").html("");
                $("#employeeTable").append(trTh);
                $("#employeeTable").append(trTd);

                $(".spanId,.spanPassword,.spanLeaveDate,.spanDepartmentId,.spanRemark").hide();

                $("#employeeTable tr .tdEmployeeNumber").css({
                    width: "60px"
                });
                $("#employeeTable tr .tdEmployeeName").css({
                    width: "100px"
                });
                $("#employeeTable tr .tdEmployeeEmail").css({
                    width: "240px"
                });
                $("#employeeTable tr .tdEmployeeEntryDate").css({
                    width: "120px"
                });
                $("#employeeTable tr .tdEmployeeDepartment").css({
                    width: "140px"
                });
                $("#employeeTable tr .tdEmployeeIfAdministration").css({
                    width: "60px"
                });
                $("#employeeTable tr .tdEmployeeDo").css({
                    width: "auto"
                });

                $(".updateEmployeeButton").button({
                    label: "修改"
                });
                $(".deleteEmployeeButton").button({
                    label: "删除"
                });
                $(".updateEmployeeButton").click(function () {
                    $("#inputEmployeeNumber").val($(this).parent(".tdEmployeeDo").prevAll(".tdEmployeeNumber").text());
                    $("#inputEmployeeName").val($(this).parent(".tdEmployeeDo").prevAll(".tdEmployeeName").text());
                    $("#inputEmployeeEmail").val($(this).parent(".tdEmployeeDo").prevAll(".tdEmployeeEmail").text());
                    $("#inputEmployeePassword").val($(this).nextAll(".spanPassword").text());
                    //alert($("#inputEmployeePassword").val());
                    $("#inputEmployeeEntryDate").val($(this).parent(".tdEmployeeDo").prevAll(".tdEmployeeEntryDate").text());
                    //alert($("#inputEmployeeEntryDate").val());
                    if ($(this).nextAll(".spanLeaveDate").text() == null) {
                        $("#inputEmployeeLeaveDate").val("");
                    } else {
                        $("#inputEmployeeLeaveDate").val($(this).nextAll(".spanLeaveDate").text());
                    }
                    $("#inputEmployeeDepartmentName").val($(this).parent(".tdEmployeeDo").prevAll(".tdEmployeeDepartment").text());
                    $("#inputEmployeeDepartmentId").val($(this).nextAll(".spanDepartmentId").text());
                    if ($(this).parent(".tdEmployeeDo").prevAll(".tdEmployeeIfAdministration").text() == true) {
                        $("#inputEmployeeIfAdministration").val("YES");
                        $("#inputEmployeeIfAdministrationValue").val(1);
                    } else {
                        $("#inputEmployeeIfAdministration").val("NO");
                        $("#inputEmployeeIfAdministrationValue").val(0);
                    }
                    if ($(this).nextAll(".spanRemark").text() == null) {
                        $("#inputEmployeeRemark").val("");
                    } else if (typeof($(this).nextAll(".spanRemark").text()) == "undefined") {
                        $("#inputEmployeeRemark").val("");
                    } else {
                        $("#inputEmployeeRemark").val($(this).nextAll(".spanRemark").text());
                    }
                    $("#inputEmployeeId").val($(this).nextAll(".spanId").text());

                    //alert($("#inputEmployeeRemark").val());

                    $("#inputEmployeeDialog").dialog({
                        autoOpen: true,
                        minWidth: 360,
                        minHeight: 600,
                        maxWidth: 360,
                        maxHeight: 600,
                        title: "修改员工信息：",
                        show: {
                            effect: "bounce",
                            duration: 500
                        },
                        hide: {
                            effect: "explode",
                            duration: 1000
                        },

                        buttons: {
                            "修改": function () {
                                updateEmployee();
                                //$(this).dialog("close");
                            },
                            "close": function () {
                                $(this).dialog("close");
                                $("#inputEmployeeId").val("");
                                $("#inputEmployeeNumber").val("");
                                $("#inputEmployeeName").val("");
                                $("#inputEmployeeEmail").val("");
                                $("#inputEmployeePassword").val("");
                                $("#inputEmployeeEntryDate").val("");
                                $("#inputEmployeeLeaveDate").val("");
                                $("#inputEmployeeDepartmentName").val("");
                                $("#inputEmployeeDepartmentId").val(1);
                                $("#inputEmployeeIfAdministration").val("NO");
                                $("#inputEmployeeIfAdministrationValue").val(0);
                                $("#inputEmployeeRemark").val("");
                            }
                        }
                    })

                });
                $(".deleteEmployeeButton").click(function () {
                    $("#inputEmployeeId").val($(this).nextAll(".spanId").text());
                    var deleteEmployeeId = ($("#inputEmployeeId").val());
                    $("#reminderDialog p").text("确定删除？？");
                    $("#reminderDialog p").css({
                        "font-family": "Helvetica, Arial, sans-serif",
                        "font-size": "1.1em",
                        "color": "#ff0084",
                        "font-weight": "bold"
                    });
                    $("#reminderDialog").dialog({
                        autoOpen: true,
                        minWidth: 300,
                        minHeight: 200,
                        maxWidth: 300,
                        maxHeight: 200,
                        title: "确定删除？",
                        show: {
                            effect: "bounce",
                            duration: 500
                        },
                        hide: {
                            effect: "explode",
                            duration: 1000
                        },

                        buttons: {
                            "删除": function () {
                                deleteEmployee(deleteEmployeeId);

                            }
                        }
                    });
                });
            }
        });
    }

    function updateEmployee() {
        var inputEmployeeNumber = $("#inputEmployeeNumber").val();
        var inputEmployeeName = $("#inputEmployeeName").val();
        var inputEmployeeEmail = $("#inputEmployeeEmail").val();
        var inputEmployeePassword = $("#inputEmployeePassword").val();
        var inputEmployeeEntryDate = $("#inputEmployeeEntryDate").val();
        var inputEmployeeLeaveDate = $("#inputEmployeeLeaveDate").val();
        var inputEmployeeDepartmentId = $("#inputEmployeeDepartmentId").val();
        var inputEmployeeIfAdministrationValue = $("#inputEmployeeIfAdministrationValue").val();
        var inputEmployeeRemark = $("#inputEmployeeRemark").val();
        var inputEmployeeId = $("#inputEmployeeId").val();
        /*
         alert(inputEmployeeNumber+","+inputEmployeeName+","+inputEmployeeEmail+","+
         inputEmployeePassword+","+inputEmployeeEntryDate+","+inputEmployeeLeaveDate+","+
         inputEmployeeDepartmentId+","+inputEmployeeIfAdministrationValue+","+inputEmployeeRemark+","+inputEmployeeId
         );
         */
        $.ajax({
            type: "POST",
            url: "updateEmployee?time=" + new Date().getTime(),
            data: {
                inputEmployeeId: inputEmployeeId,
                inputEmployeeNumber: inputEmployeeNumber,
                inputEmployeeName: inputEmployeeName,
                inputEmployeeEmail: inputEmployeeEmail,
                inputEmployeePassword: inputEmployeePassword,
                inputEmployeeEntryDate: inputEmployeeEntryDate,
                inputEmployeeLeaveDate: inputEmployeeLeaveDate,
                inputEmployeeDepartmentId: inputEmployeeDepartmentId,
                inputEmployeeIfAdministrationValue: inputEmployeeIfAdministrationValue,
                inputEmployeeRemark: inputEmployeeRemark
            },
            success: function (data) {
                if (data.update == "success") {
                    $("#reminderDialog p").text("修改成功！");
                    $("#reminderDialog").dialog({
                        autoOpen: true,
                        minWidth: 300,
                        minHeight: 200,
                        maxWidth: 300,
                        maxHeight: 200,
                        title: "提示：",
                        show: {
                            effect: "bounce",
                            duration: 500
                        },
                        hide: {
                            effect: "explode",
                            duration: 1000
                        },

                        buttons: {
                            "close": function () {
                                employeeKeyword = inputEmployeeName;
                                pageNumber = 1;
                                showEmployeeListByPage(employeeKeyword, pageNumber);
                                $("#employeeDialog").dialog({title: "员工管理：    关键词： \" " + employeeKeyword + " \"  。    第  " + (pageNumber) + "  /  " + pageCount + "  页"});
                                //$("#searchEmployeeListKeyword").val("");
                                $("#inputEmployeeId").val("");
                                $("#inputEmployeeNumber").val("");
                                $("#inputEmployeeName").val("");
                                $("#inputEmployeeEmail").val("");
                                $("#inputEmployeePassword").val("");
                                $("#inputEmployeeEntryDate").val("");
                                $("#inputEmployeeLeaveDate").val("");
                                $("#inputEmployeeDepartmentName").val("");
                                $("#inputEmployeeDepartmentId").val(1);
                                $("#inputEmployeeIfAdministration").val("NO");
                                $("#inputEmployeeIfAdministrationValue").val(0);
                                $("#inputEmployeeRemark").val("");
                                $(this).dialog("close");
                                $("#inputEmployeeDialog").dialog("close");
                            }
                        }
                    });
                }
            }
        });

    }

    function deleteEmployee(deleteEmployeeId) {
        //alert(deleteEmployeeId);

        $.ajax({
            type: "POST",
            url: "deleteEmployee",
            data: {
                deleteEmployeeId: deleteEmployeeId
            },
            success: function (data) {
                if (data.delete == "success") {
                    $("#reminderDialog p").text("删除成功！");
                    $("#reminderDialog p").css({
                        "font-family": "Helvetica, Arial, sans-serif",
                        "font-size": "1.1em",
                        "color": "#0073ea",
                        "font-weight": "bold"
                    });
                    $("#reminderDialog").dialog({
                        autoOpen: true,
                        minWidth: 300,
                        minHeight: 200,
                        maxWidth: 300,
                        maxHeight: 200,
                        title: "提示：",
                        show: {
                            effect: "bounce",
                            duration: 500
                        },
                        hide: {
                            effect: "explode",
                            duration: 1000
                        },

                        buttons: {
                            "close": function () {
                                //employeeKeyword = inputEmployeeName;
                                pageNumber = 1;
                                showEmployeeListByPage(employeeKeyword, pageNumber);
                                $("#employeeDialog").dialog({title: "员工管理：    关键词： \" " + employeeKeyword + " \"  。    第  " + (pageNumber) + "  /  " + pageCount + "  页"});
                                //$("#searchEmployeeListKeyword").val("");
                                $("#inputEmployeeId").val("");
                                $(this).dialog("close");
                            }
                        }
                    });
                }
            }
        });
    }

    function inputEmployeeDialog() {
        $("#inputEmployeeIfAdministrationValue").val(0);
        $("#inputEmployeeDialog").dialog({
            autoOpen: true,
            minWidth: 360,
            minHeight: 600,
            maxWidth: 360,
            maxHeight: 600,
            title: "填写员工信息：",
            show: {
                effect: "bounce",
                duration: 500
            },
            hide: {
                effect: "explode",
                duration: 1000
            },

            buttons: {
                "添加": function () {
                    insertEmployee();
                    //$(this).dialog("close");
                },
                "close": function () {
                    $(this).dialog("close");
                    $("#inputEmployeeId").val("");
                    $("#inputEmployeeNumber").val("");
                    $("#inputEmployeeName").val("");
                    $("#inputEmployeeEmail").val("");
                    $("#inputEmployeePassword").val("");
                    $("#inputEmployeeEntryDate").val("");
                    $("#inputEmployeeLeaveDate").val("");
                    $("#inputEmployeeDepartmentName").val("");
                    $("#inputEmployeeDepartmentId").val(1);
                    $("#inputEmployeeIfAdministration").val("NO");
                    $("#inputEmployeeIfAdministrationValue").val(0);
                    $("#inputEmployeeRemark").val("");
                }
            }
        })
    }

    function insertEmployee() {
        var inputEmployeeNumber = $("#inputEmployeeNumber").val();
        var inputEmployeeName = $("#inputEmployeeName").val();
        var inputEmployeeEmail = $("#inputEmployeeEmail").val();
        var inputEmployeePassword = $("#inputEmployeePassword").val();
        var inputEmployeeEntryDate = $("#inputEmployeeEntryDate").val();
        var inputEmployeeLeaveDate = $("#inputEmployeeLeaveDate").val();
        var inputEmployeeDepartmentId = $("#inputEmployeeDepartmentId").val();
        var inputEmployeeIfAdministrationValue = $("#inputEmployeeIfAdministrationValue").val();
        var inputEmployeeRemark = $("#inputEmployeeRemark").val();
        //alert(inputEmployeeNumber+","+inputEmployeeName+","+inputEmployeeEmail+","+
        //    inputEmployeePassword+","+inputEmployeeEntryDate+","+inputEmployeeLeaveDate+","+
        //    inputEmployeeDepartmentId+","+inputEmployeeIfAdministrationValue+","+inputEmployeeRemark
        //);

        $.ajax({
            type: "POST",
            url: "insertEmployee",
            data: {
                inputEmployeeNumber: inputEmployeeNumber,
                inputEmployeeName: inputEmployeeName,
                inputEmployeeEmail: inputEmployeeEmail,
                inputEmployeePassword: inputEmployeePassword,
                inputEmployeeEntryDate: inputEmployeeEntryDate,
                inputEmployeeLeaveDate: inputEmployeeLeaveDate,
                inputEmployeeDepartmentId: inputEmployeeDepartmentId,
                inputEmployeeIfAdministrationValue: inputEmployeeIfAdministrationValue,
                inputEmployeeRemark: inputEmployeeRemark
            },
            success: function (data) {
                if (data.insert == "success") {
                    $("#reminderDialog p").text("添加成功！");
                    $("#reminderDialog").dialog({
                        autoOpen: true,
                        minWidth: 300,
                        minHeight: 200,
                        maxWidth: 300,
                        maxHeight: 200,
                        title: "提示：",
                        show: {
                            effect: "bounce",
                            duration: 500
                        },
                        hide: {
                            effect: "explode",
                            duration: 1000
                        },

                        buttons: {
                            "close": function () {
                                employeeKeyword = inputEmployeeName;
                                pageNumber = 1;
                                showEmployeeListByPage(employeeKeyword, pageNumber);
                                $("#employeeDialog").dialog({title: "员工管理：    关键词： \" " + employeeKeyword + " \"  。    第  " + (pageNumber) + "  /  " + pageCount + "  页"});
                                //$("#searchEmployeeListKeyword").val("");
                                $("#inputEmployeeNumber").val("");
                                $("#inputEmployeeName").val("");
                                $("#inputEmployeeEmail").val("");
                                $("#inputEmployeePassword").val("");
                                $("#inputEmployeeEntryDate").val("");
                                $("#inputEmployeeLeaveDate").val("");
                                $("#inputEmployeeDepartmentName").val("");
                                $("#inputEmployeeDepartmentId").val(1);
                                $("#inputEmployeeIfAdministration").val("NO");
                                $("#inputEmployeeIfAdministrationValue").val(0);
                                $("#inputEmployeeRemark").val("");
                                $(this).dialog("close");
                                $("#inputEmployeeDialog").dialog("close");
                            }
                        }
                    });
                }
            }
        });

    }

    $("#showEntryDateButton").click(function () {
        $("#inputEmployeeEntryDate").focus();
    });
    $("#showLeaveDateButton").click(function () {
        $("#inputEmployeeLeaveDate").focus();
    });

    $("#inputEmployeeEntryDate").datepicker({
        changeMonth: true,
        changeYear: true,
        minDate: "2007-01-01",
        maxDate: "+0D",
        //showOn: "button",
        showOn: "focus",
        //showOn: "both",
        //buttonText: "日历",
        //buttonImage: "imgs/calendar.png",
        //buttonImageOnly: true
    });
    $("#inputEmployeeLeaveDate").datepicker({
        changeMonth: true,
        changeYear: true,
        minDate: "2007-01-01",
        maxDate: "+0D",
        //showOn: "button",
        showOn: "focus",
        //showOn: "both",
        //buttonText: "日历",
        //buttonImage: "imgs/calendar.png",
        //buttonImageOnly: true
    });

    $("#chooseDepartmentButton").click(function () {
        listDepartment();
        $("#chooseDepartmentDialog").dialog({
            autoOpen: true,
            minWidth: 420,
            minHeight: 600,
            maxWidth: 420,
            maxHeight: 600,
            title: "请选择部门：",
            show: {
                effect: "bounce",
                duration: 500
            },
            hide: {
                effect: "explode",
                duration: 1000
            },

            buttons: {
                "close": function () {
                    $(this).dialog("close");
                }
            }
        })
    });

    $("#inputEmployeeDepartmentId").val(1);
    $("#inputEmployeeIfAdministration").val("NO");
    $("#inputEmployeeIfAdministrationValue").val(0);
    $("#changeIfAdministration").click(function () {
        if ($("#inputEmployeeIfAdministration").val() == "NO") {
            $("#inputEmployeeIfAdministration").val("YES");
            $("#inputEmployeeIfAdministrationValue").val(1);
        } else {
            $("#inputEmployeeIfAdministration").val("NO");
            $("#inputEmployeeIfAdministrationValue").val(0);
        }
    });

    function listDepartment() {
        var tree = "";
        $.ajax({
            type: "GET",
            url: "showDepartmentByAdministration?time=" + new Date().getTime(),
            cache: false,
            async: false,
            success: function (data) {
                tree = listDepartmentTree(data);
                $("#listDepartmentTree").html(tree);
                $("#listDepartmentTree").show();
                $(".departmentUl").hide();
                $("li").css({"list-style": "none"});
                $("ul").css({"margin-left": "20px"});
                $(".span1").hide();
                $(".departmentLi").hover(function () {
                    $(this).children(".departmentUl").show();
                    $(this).siblings(".departmentLi").children().children().children(".departmentUl").hide();
                    $(this).parent().parent().parent().parent().siblings(".departmentLi").children(".departmentUl").hide();
                });
                $(".departmentNameLiA").click(function () {
                    $("#inputEmployeeDepartmentName").val($(this).text());
                    //$("#inputEmployeeDepartmentId").val($(this).sibling(".span1").text());
                    var inputEmployeeDepartmentId = $(this).next(".span1").text();
                    $("#inputEmployeeDepartmentId").val(inputEmployeeDepartmentId);
                    $("#chooseDepartmentDialog").dialog("close");
                });
            }
        });
        function listDepartmentTree(data) {
            var tree1 = "";
            $.each(data, function (key, value) {
                //alert(value.subordinateDepartment);
                if (typeof (value.subordinateDepartment) == "object") {
                    tree1 += "<li class='departmentLi'><a href='#' class='departmentNameLiA'>" + value.departmentName + "</a>" +
                        "<span  class='span1'>" + value.id + "</span>" +
                        "<ul class='departmentUl'>" + listDepartmentTree(value.subordinateDepartment) + "</ul></li>"
                } else {
                    tree1 += "<li class='departmentLi'><a href='#' class='departmentNameLiA'>" + value.departmentName + "</a>" +
                        "<span  class='span1'>" + value.id + "</span></li>"
                }
            });
            //alert(tree);
            return tree1;
        }
    }


    $("#holidayDiv").click(function () {
        $("#holidayDialog").dialog({
            autoOpen: true,
            minWidth: 1000,
            minHeight: 600,
            maxWidth: 1280,
            maxHeight: 600,
            title: "休假管理：",
            show: {
                effect: "bounce",
                duration: 500
            },
            hide: {
                effect: "explode",
                duration: 1000
            },

            buttons: {
                "close": function () {
                    $(this).dialog("close");
                }
            }
        })
    });
    $("#mineDiv").click(function () {
        $("#mineDialog").dialog({
            autoOpen: true,
            minWidth: 1000,
            minHeight: 600,
            maxWidth: 1280,
            maxHeight: 600,
            title: "我的休假：",
            show: {
                effect: "bounce",
                duration: 500
            },
            hide: {
                effect: "explode",
                duration: 1000
            },

            buttons: {
                "close": function () {
                    $(this).dialog("close");
                }
            }
        })
    });

});
/**
 * 时间对象的格式化
 */
Date.prototype.format = function (format) {
    /* 
     * format="yyyy-MM-dd hh:mm:ss"; 
     */
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    };

    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }

    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}