/**
 *
 */
/**/
$(function () {
    $("#help").button({
        label: "关于"
    });
    $("#refresh").button({
        label: "刷新"
    });
    $("#refresh").click(function () {
        location.reload();
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
    $("#datepickerButton").button({
        label: "日历"
    });

    $("#help").click(function () {
        var helpText= "(♥◠‿◠)ﾉ  感谢使用亿邦动力年假管理系统！<br/><br/>" +
            "您可以在这里查看您每年的年假天数和已休天数，也可以查看具体日期。<br/><br/>" +
            "如果您是领导，您可以查看您所领导的部门的成员的相关信息和休假详情。<br/><br/>" +
            "如果您是管理员，您可以对所有员工和部门以及休假信息进行增删改查工作。<br/><br/>" +
            "BUG反馈请联系技术部李涛，litao@ebrun.com 。<br/><br/>" +
            "(☆ﾟ∀ﾟ)  祝您工作愉快！";
        $("#helpText").html(helpText);
        $("#helpDialog").dialog({
            autoOpen: true,
            modal: true,
            minWidth: 600,
            minHeight: 400,
            maxWidth: 600,
            maxHeight: 600,
            title: "感谢使用！Thanks A Lot：╰(*°▽°*)╯",
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
            url: "logout?time=" + new Date().getTime(),
            success: function () {
                $("#dateDialog").dialog("close");
                location.reload();
            }
        });
    });

    $("#login").click(function () {
        $("#dateDialog").dialog("close");
        $("#loginForm").dialog({
            autoOpen: true,
            modal: true,
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
                duration: 500
            },

            buttons: {
                "login": function () {
                    $.ajax({
                        type: "POST",
                        url: "login?time=" + new Date().getTime(),
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
                    $("#dateDialog").dialog("open");
                }
            }

        })
    });
    $("#datepicker").blur();
    $("#datepickerButton").click(function () {
        $("#datepicker").focus();
    });
    $("#datepicker").datepicker({
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

    $("#dateDialog").dialog({
        autoOpen: true,
        //modal: true,
        minWidth: 280,
        minHeight: 200,
        maxWidth: 600,
        maxHeight: 400,
        title: "输入日期：Input Date  ✪ω✪",
        show: {
            effect: "bounce",
            duration: 500
        },
        hide: {
            effect: "explode",
            duration: 500
        },

        buttons: {
            "Ok": function () {
                showResultDialog();
            },
            "close": function () {
                $(this).dialog("close");
            }
        }

    });

});

function showResultDialog() {
    //var date = $("#datepicker").datepicker("getDate");
    //alert(date);
    //alert(date.Format("yyyy年MM月dd日"));
    var resultText = calculate();
    $("#resultText").html(resultText);
    //var resultText = $("#resultText").text();

    $("#resultDialog").dialog({
        autoOpen: true,
        modal: true,
        minWidth: 480,
        minHeight: 320,
        maxWidth: 480,
        maxHeight: 320,
        title: "计算结果：Result View   []~(￣▽￣)~*",
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
}
function calculate() {
    //var result = "wang";
    //var date = new Date();
    var holidays;//年假天数
    var currentDate = new Date();//当前日期
    //alert(typeof(date));
    //alert(typeof(date.getFullYear()));
    var inputDate = $("#datepicker").datepicker("getDate");

    var myYear = inputDate.getFullYear();//入职年份
    var myMonth = inputDate.getMonth() + 1;//入职月份
    //var myDay = inputDate.getDate();
    var myDate = inputDate.format("yyyy-MM-dd");
    var currentYear = currentDate.getFullYear();//当前年份
    var currentMonth = currentDate.getMonth() + 1;//当前月份

    //alert(currentYear);
    //alert(currentMonth);

    var fiscalYear;//当前财年
    var fiscalMonth = 4;
    //var fiscalDay = 1;

    var fiscalCount;//财年个数

    if (currentMonth < fiscalMonth) {//如果现在还没到4月
        fiscalYear = currentYear - 1;//财年为当前年份-1
        if (myMonth < fiscalMonth) {
            fiscalCount = fiscalYear - myYear + 2;
        } else {
            fiscalCount = fiscalYear - myYear + 1;
        }
        ;
    } else {
        fiscalYear = currentYear;
        if (myMonth < fiscalMonth) {//如果入职月份早于4月
            fiscalCount = fiscalYear - myYear + 2;//当前我正在经历第fiscalCount个财年
        } else {
            fiscalCount = fiscalYear - myYear + 1;
        }
        ;
    }
    ;

    //alert(fiscalCount);
    var monthCount;//入职月数
    if (fiscalCount > 1) {//如果正在经历财年
        holidays = fiscalCount + 4 - 1;
        if (holidays > 15) {
            holidays = 15;
        }
        ;
    } else {//如果正在经历第1个财年（没有跨4月1日，即到明年4月1日也只算半个财年，记为第1个财年）
        if (currentMonth < fiscalMonth) {
            monthCount = (currentYear - myYear) * 12 + 4 - myMonth;
        } else {
            monthCount = 12 + 4 - myMonth;
        }
        ;
        //holidays = parseInt(monthCount/(12/4));//取整
        //holidays = Math.ceil(monthCount/(12/4));//向上取整
        holidays = Math.round(monthCount / (12 / 4));//四舍五入
        //holidays = Math.floor(monthCount/(12/4));//向下取整
    }
    ;

    //alert(holidays);
    var result = "当前财年为 " + fiscalYear + " 财年。即 "
        + fiscalYear + "-04-01 ~ " + (fiscalYear + 1) + "-03-31 。<br>您的入职日期是 "
            //+myYear+"-"+myMonth+"-"+myDay
        + myDate + " <br>这是您入职后的第 "
        + fiscalCount + " 个财年。<br>在此期间，您有 " + holidays + " 天 年假！<br><br>谢谢！…~(～o￣▽￣)～o 。<img src='imgs/ebrunlogo03.png'>";
    //alert(result);
    return result;
}
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
};
