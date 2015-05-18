/**
 * Created by Administrator on 2015/5/16.
 */
$(function () {
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
                duration: 1000
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
                duration: 1000
            },
            hide: {
                effect: "explode",
                duration: 1000
            },

            buttons: {
                "input": function () {
                    showDepartmentInput()
                },
                "show": function () {
                    showDepartmentList()
                },
                "hide": function () {
                    hideDepartmentList()
                },
                "close": function () {
                    $(this).dialog("close");
                }
            }
        })
    });
    function hideDepartmentList() {
        $("#departmentList").hide();
    }

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
                    $(this).siblings(".departmentLi").children(".departmentUl").hide();
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
    function showDepartmentButton() {
        $("#departmentButton").show();
    };
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
    $("#employeeDiv").click(function () {
        $("#employeeDialog").dialog({
            autoOpen: true,
            minWidth: 1000,
            minHeight: 600,
            maxWidth: 1280,
            maxHeight: 600,
            title: "员工管理：",
            show: {
                effect: "bounce",
                duration: 1000
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
                duration: 1000
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
                duration: 1000
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
                            duration: 500
                        },

                        buttons: {
                            "close": function () {
                                $(this).dialog("close");
                                showDepartmentList();
                                showDepartmentInput();
                                hideInsertDepartmentButton();
                            }
                        }
                    })
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
                            duration: 500
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
                            duration: 500
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

});