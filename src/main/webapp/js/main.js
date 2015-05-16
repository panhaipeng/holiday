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
    /*
     var tree = "";
     $("#btn1").button();
     $("#btn1").on("click", function () {

     $.ajax({
     type:"GET",
     url: "showDepartmentByAdministration?time="+new Date().getTime(),
     cache: false,
     success: function (data) {
     tree = showDepartment(data);
     // $("#div3").html();
     $("#div3").html(tree);
     }
     });
     //alert(tree);
     });

     function showDepartment(data) {
     var tree1 = "";
     //alert(data);
     //$(data).each(function(key){
     //    var value=data[key];
     //})
     $.each(data, function (key, value) {
     //alert(value.subordinateDepartment);
     if (typeof (value.subordinateDepartment) == "object") {
     tree1 += "<li id="+key+">" + value.departmentName + "<ul>" + showDepartment(value.subordinateDepartment) + "</ul>" + "</li>"
     } else {
     tree1 += "<li id="+key+">" + value.departmentName + "</li>"
     }
     });
     //alert(tree);
     return tree1;
     }
     */
    $("#departmentDiv").click(function () {
        $("#departmentDialog").dialog({
            autoOpen: true,
            minWidth: 1000,
            minHeight: 600,
            maxWidth: 1280,
            maxHeight: 600,
            title: "部门管理：",
            show: {
                effect: "bounce",
                duration: 1000
            },
            hide: {
                effect: "explode",
                duration: 1000
            },

            buttons: {
                "show": function () {
                    showDepartmentList()
                },
                "close": function () {
                    $(this).dialog("close");
                }
            }
        })
    });
    function showDepartmentList() {
        //alert(1);
        var tree = "";
        $.ajax({
            type: "GET",
            url: "showDepartmentByAdministration?time=" + new Date().getTime(),
            cache: false,
            success: function (data) {
                tree = showDepartment(data);
                // $("#div3").html();
                $("#departmentDialog").html(tree);
            }
        });
        //alert(tree);

        function showDepartment(data) {
            var tree1 = "";
            //alert(data);
            //$(data).each(function(key){
            //    var value=data[key];
            //})
            $.each(data, function (key, value) {
                //alert(value.subordinateDepartment);
                if (typeof (value.subordinateDepartment) == "object") {
                    tree1 += "<li id=" + key + ">" + value.departmentName + "<ul>" + showDepartment(value.subordinateDepartment) + "</ul>" + "</li>"
                } else {
                    tree1 += "<li id=" + key + ">" + value.departmentName + "</li>"
                }
            });
            //alert(tree);
            return tree1;
        }
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
});