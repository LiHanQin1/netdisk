/*弹出层*/
var useDispAll = 0;
var openedLay = 0;

function show(cover, id, useDisp) {
    var Sys = {};
    var ua = navigator.userAgent.toLowerCase();
    var s;
    (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
        (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
            (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
    //如果是ie6，隐藏页面select
    var objCover = document.getElementById(cover);
    if (Sys.ie == "6.0") {
        /*var n=document.getElementsByTagName("select").length;
        var m=document.getElementById(id).getElementsByTagName("select").length;
        for(var i=0;i<n;i++){
            document.getElementsByTagName("select")[i].style.display='none';}
        for(var j=0;j<m;j++){
            document.getElementById(id).getElementsByTagName("select")[j].style.display='';}
        */
        objCover.innerHTML = '<iframe style="width:100%;height:100%;"></iframe>';
    }

    var objId = document.getElementById(id);
    if ($(objId).css("display") != "none" && $(objId).css("visibility") != "hidden") return;
    objId.style.display = "";
    objId.style.visibility = "visible";
    var scrollW = document.documentElement.scrollWidth;
    var scrollH = document.documentElement.scrollHeight;
    if (Sys.safari || Sys.chrome) {
        var scrollH = document.body.scrollHeight;
        if (document.documentElement.clientHeight < objId.clientHeight) {
            var T = document.body.scrollTop;
        } else {
            var T = (document.documentElement.clientHeight - objId.clientHeight) / 2 + document.body.scrollTop;
        }
    } else {
        if (document.documentElement.clientHeight < objId.clientHeight) {
            var T = document.documentElement.scrollTop;
        } else {
            var T = (document.documentElement.clientHeight - objId.clientHeight) / 2 + document.documentElement.scrollTop;
        }
    }
    var L = (document.documentElement.clientWidth - objId.clientWidth) / 2 + document.documentElement.scrollLeft;

    objCover.style.visibility = "visible";
    objCover.style.display = "";
    objId.style.top = T + "px";
    objId.style.left = L + "px";
    objCover.style.width = scrollW + "px";
    //objCover.style.height=scrollH+"px";
    objCover.style.height = (document.body.scrollHeight > document.documentElement.scrollHeight ? document.body.scrollHeight : document.documentElement.scrollHeight) + "px"; //当弹出层出现，高度撑高后，要重新设置遮罩层高度
    useDispAll = useDisp;

    if (openedLay > 0) {
        var newMask = $(objCover).clone(); //alert(newMask.html());
        newMask.css("z-index", "2000");
        newMask.insertBefore($(objId));
    }
    openedLay++;

    window.onresize = function () {
        var objCover = document.getElementById(cover);
        var objId = document.getElementById(id);
        var scrollW = document.documentElement.scrollWidth;
        if (document.documentElement.clientHeight >= document.documentElement.scrollHeight) {
            var scrollH = document.documentElement.clientHeight;
        } else {
            var scrollH = document.documentElement.scrollHeight;
        }
        if (Sys.safari || Sys.chrome) {
            if (document.documentElement.clientHeight < objId.clientHeight) {
                var T = document.body.scrollTop;
            } else {
                var T = (document.documentElement.clientHeight - objId.clientHeight) / 2 + document.body.scrollTop;
            }
        } else {
            if (document.documentElement.clientHeight < objId.clientHeight) {
                var T = document.documentElement.scrollTop;
            } else {
                var T = (document.documentElement.clientHeight - objId.clientHeight) / 2 + document.documentElement.scrollTop;
            }
        }
        var L = (document.documentElement.clientWidth - objId.clientWidth) / 2 + document.documentElement.scrollLeft;
        objCover.style.width = scrollW + "px";
        objCover.style.height = scrollH + "px";
        objId.style.top = T + "px";
        objId.style.left = L + "px";
    }
}

function hide(cover, id) {
    //将页面全部select换件设为可用状态
    var Sys = {};
    var ua = navigator.userAgent.toLowerCase();
    var s;
    (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
        (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
    if (Sys.ie == "6.0") {
        /*var n=document.getElementsByTagName("select").length;
        for(var i=0;i<n;i++){
            document.getElementsByTagName("select")[i].style.display= '';
        }*/
    }
    var objCover = document.getElementById(cover);
    var objId = document.getElementById(id);

    objId.style.visibility = "hidden";
    //if(useDispAll==1){
    objId.style.display = "none";
    //}
    if (openedLay > 1) {
        $(objId).prev().remove();
    } else {
        objCover.style.visibility = "hidden";
        objCover.style.display = "none";
    }
    openedLay--;
}

//弹出框拖动
$(document).ready(function () {
    $(".tcMove").mousedown(function (e)//e鼠标事件
    {
        $(this).css("cursor", "move");//改变鼠标指针的形状

        var tarPop = $(this).parent();
        var offset = $(this).parent().offset();//DIV在页面的位置
        var x = e.pageX - tarPop.css("left").replace("px", "");//获得鼠标指针离DIV元素左边界的距离
        var y = e.pageY - tarPop.css("top").replace("px", "");//获得鼠标指针离DIV元素上边界的距离
        var maxWidth = $(window).width() - $(this).parent().outerWidth(true);//最大宽度
        var scrTop = $(document).scrollTop();
        var maxHeight = $(window).height() + scrTop - $(this).parent().outerHeight(true);//最大高度
        $(document).bind("mousemove", function (ev)//绑定鼠标的移动事件，因为光标在DIV元素外面也要有效果，所以要用doucment的事件，而不用DIV元素的事件
        {
            tarPop.stop();//加上这个之后

            var _x = ev.pageX - x;//获得X轴方向移动的值
            var _y = ev.pageY - y;//获得Y轴方向移动的值
            if (_x < 0) {
                _x = 0;
            }
            if (_y < 0) {
                _y = 0;
            }
            if (scrTop > 0 && _y < scrTop) {
                _y = scrTop;
            }
            if (_y <= scrTop) {
                _y = scrTop;
            }
            if (_x > maxWidth) {
                _x = maxWidth;
            }
            if (_y > maxHeight) {
                _y = maxHeight;
            }
            tarPop.animate({left: _x + "px", top: _y + "px"}, 10);
        });

    });

    $(document).mouseup(function () {
        $(".tcMove").css("cursor", "default");
        $(this).unbind("mousemove");
    })
});


//弹出层
//页面有select时弹出层
function showSelCover(id) {
    var objId = document.getElementById(id);
    var scrollW = document.documentElement.scrollWidth;
    if (document.documentElement.clientHeight >= document.documentElement.scrollHeight) {
        var scrollH = document.documentElement.clientHeight;
    } else {
        var scrollH = document.documentElement.scrollHeight;
    }

    var T = (document.documentElement.clientHeight - objId.clientHeight) / 2 + document.documentElement.scrollTop;

    var L = (document.documentElement.clientWidth - objId.clientWidth) / 2 + document.documentElement.scrollLeft;
    objId.style.top = T + "px";
    objId.style.left = L + "px";
    objId.style.visibility = "visible";
}

//function hideCover
function hideSelCover(id) {
    var objId = document.getElementById(id);
    objId.style.visibility = "hidden";
}


$(".nav_content").on("click", "li", function () {
    if (!$(this).hasClass("active")) {
        $(".nav_content li").removeClass("active");
        $(this).addClass("active");
    }
});
$(".top_menu").on("click", "li", function () {
    if (!$(this).hasClass("active")) {
        $(".top_menu li").removeClass("active");
        $(this).addClass("active");
    }
});
$(".file_list").on("click", ".file_box", function () {
    if ($(this).hasClass("active")) {
        $(this).removeClass("active");
    } else {
        $(this).addClass("active");
    }
});
$(".file_list2_left").on("click", "li", function () {
    if (!$(this).hasClass("on")) {
        $(".file_list2_left li").removeClass("on");
        $(this).addClass("on");
    }
});

$("#testTab").on("click", "tr", function () {
    if ($(this).children().hasClass("active")) {
        $(this).children().removeClass("active");
        $(this).children().eq(0).children().prop("checked", false);
    } else {
        $(this).children().addClass("active");
        $(this).children().eq(0).children().prop("checked", true);
    }
});

//列表全选checkbox
$(function () {
    if ($("#check_all").length > 0) {
        var $key = $("#check_all");
        $key.click(function () {
            if ($key.is(":checked")) {
                $(".file_box").addClass("active");
            } else {
                $(".file_box").removeClass("active");
            }
        });
        $(".file_box").click(function (e) {
            if (e.target.id == "check_all") {
                return;
            }
            if ($key.prop("checked") == true) {
                $key.prop("checked", false);
            }
        });
    }

});

$(function () {
    if ($("#check_all2").length > 0) {
        var $key = $("#check_all2");
        var name = $key.attr("name");
        $key.click(function () {
            if ($key.is(":checked")) {
                $("input[name=" + name + "]").prop("checked", true);
                $("#testTab td").addClass("active");
            } else {
                $("input[name=" + name + "]").prop("checked", false);
                $("#testTab td").removeClass("active");
            }
        });
        $("input[name=" + name + "]").click(function (e) {
            if (e.target.id == "check_all2") {
                return;
            }
            if ($key.prop("checked") == true) {
                $key.prop("checked", false);
            }
        });

    }
});

$(function () {
    if ($("#check_all3").length > 0) {
        var $key = $("#check_all3");
        var name = $key.attr("name");
        $key.click(function () {
            if ($key.is(":checked")) {
                $("input[name=" + name + "]").prop("checked", true);
                $("#table01 td").addClass("active");
            } else {
                $("input[name=" + name + "]").prop("checked", false);
                $("#table01 td").removeClass("active");
            }
        });
        $("input[name=" + name + "]").click(function (e) {
            if (e.target.id == "check_all3") {
                return;
            }
            if ($key.prop("checked") == true) {
                $key.prop("checked", false);
            }
        });

    }
});

/*我的共享切换*/
function yysz_kg() {
    if ($(".gx_wdhy").is(":checked")) {
        $(".qtr").hide();
        $(".bmgx").hide();
        $(".wdhy").show();
    } else if ($(".gx_syr").is(":checked")) {
        $(".wdhy").hide();
        $(".qtr").hide();
        $(".bmgx").show();
    } else if ($(".gx_qtr").is(":checked")) {
        $(".wdhy").hide();
        $(".bmgx").hide();
        $(".qtr").show();

    }
}

//新增文件夹
$(document).on('click', '#new_file', function () {
    $(".file_list1").before("<div class='file_box' ondblclick=\"location='main_list_file.htm'\"><img src='images/file_pic.gif' class='pic' /><br /><input name='new_name' type='text' class='new_name fl ml3' /><img src='images/ok_name.gif'  class='fl mlr3 new_name_ok'/><img src='images/dele.gif' class='fl new_name_dele'/></div>");
});

//新增文件夹2
var id = 1

function addTr() {
    //获得表格对象
    var tb = document.getElementById('testTab');
    //添加一行    
    var newTr = tb.insertRow(1);//在最下的位置

    //给这个行设置id属性，以便于管理（删除）
    newTr.id = 'tr' + id;
    //设置对齐方式（只是告诉你，可以以这种方式来设置任何它有的属性）
    newTr.align = 'center';
    //添加两列    
    var newTd0 = newTr.insertCell(0);
    var newTd1 = newTr.insertCell(1);
    var newTd2 = newTr.insertCell(2);
    var newTd3 = newTr.insertCell(3);
    var newTd4 = newTr.insertCell(4);

    //设置列内容和属性    
    newTd0.innerHTML = "<input name='d2' type='checkbox' value='' />";
    ;
    newTd1.innerHTML = "<img src='images/file_icon_s.png' width='24' class='fl' /><input name='new_name' type='text' class='new_name2 new_nameT fl ml3' /><img src='images/ok_name.gif'  class='fl mlr3 new_name_ok2'/><img src='images/dele.gif' class='fl new_name_dele2'/>";
    ;
    newTd2.innerHTML = "—";
    ;
    newTd3.innerHTML = "<span class='gray'>lieu213</span>";
    ;
    newTd4.innerHTML = "<span class='gray'>2013-12-04  12：00：00</span>";

    id++;

}


//重命名
$(document).on("click", "#rename", function () {
    if ($(".file_box").hasClass("active")) {
        $(".file_box.active input").removeClass("new_name_nor").addClass("new_name");
        $(".file_box.active input").attr("readonly", false);
        $(".file_box.active input").after("<img src='images/ok_name.gif'  class='fl mlr3 new_name_ok'/><img src='images/dele.gif' class='fl new_name_dele'/>");
    }
});

$(document).on("click", "#rename2", function () {
    if ($("#testTab td").hasClass("active")) {
        $("#testTab td.active .new_nameT").removeClass("new_name_nor2").addClass("new_name2");
        $("#testTab td.active .new_nameT").attr("readonly", false);
        $("#testTab td.active .new_nameT").after("<img src='images/ok_name.gif'  class='fl mlr3 new_name_ok2'/><img src='images/dele.gif' class='fl new_name_dele2'/>");
    }
});
//我的好友重命名
$(document).on("click", "#rename3", function () {
    if ($(".file_list2_rename li").hasClass("on")) {
        $(".file_list2_rename li.on input").removeClass("new_name_nor3").addClass("new_name3");
        $(".file_list2_rename li.on input").attr("readonly", false);
        $(".file_list2_rename li.on input").after("<img src='images/ok_name.gif'  class='fl mlr3 new_name_ok3 mt10'/><img src='images/dele.gif' class='fl new_name_dele mt10'/>");
    }
});

//重命名成功 
$(document).on("click", ".new_name_ok", function () {

    //1.获取文本框的数值，JQ获取数值



    //2.AJAX将数据传到后端，添加到数据库


    //3.数据库展示



    $(this).prev().removeClass("new_name").addClass("new_name_nor");
    $(this).prev().attr("readonly", true);
    $(this).next().remove();
    $(this).remove();

});

$(document).on("click", ".new_name_ok2", function () {
    $(this).prev().removeClass("new_name2").addClass("new_name_nor2");
    $(this).prev().attr("readonly", true);
    $(this).next().remove();
    $(this).remove();

});
//我的好友重命名
$(document).on("click", ".new_name_ok3", function () {
    $(this).prev().removeClass("new_name3").addClass("new_name_nor3");
    $(this).prev().attr("readonly", true);
    $(this).next().remove();
    $(this).remove();

});


//删除
$(document).on("click", ".new_name_dele", function () {
    $(this).parent().remove();
});

$(document).on("click", ".new_name_dele2", function () {
    $(this).parent().parent().remove();
});

$(document).on("click", ".gx_dele", function () {
    $(this).parent().remove();
});


//input、textarea设置默认文本 —— 例如：<input type="text" defaultTxt="不超30个字" defaultCol="#ff3333"/>/
function setDefaultTxt() {
    $("input[defaultTxt]").add($("textarea[defaultTxt]")).each(function (index, element) {
        var tag = $(this);
        tag.defTxt = tag.attr("defaultTxt"); //默认提示文字
        tag.defCol = tag.attr("defaultCol") ? tag.attr("defaultCol") : "#ccc"; //默认提示文字时的颜色
        tag.css("color", tag.defCol).val(tag.defTxt);
        tag.unbind("focus").unbind("blur");
        tag.bind("focus", {tag: tag}, focusIn).bind("blur", {tag: tag}, blurOut);
    });

    function focusIn(e) {
        //console.log("e:"+ e +" tag:"+ e.data.tag+" focus");
        var tag = e.data.tag;
        if (tag.val() == tag.defTxt) {
            tag.val("").css("color", "");
        }
    }

    function blurOut(e) {
        var tag = e.data.tag;
        if (tag.val() == "") {
            tag.val(tag.defTxt).css("color", tag.defCol);
        }
    }
}

//input修改文字立即触发修改事件
function immediatelyChg(tag, chgFun) {
    var element = $(tag).get(0);
    if (navigator.appVersion.toLowerCase().indexOf("msie") != -1) {
        element.onpropertychange = chgFun;
    } else {
        element.addEventListener("input", chgFun, false);
    }
}







