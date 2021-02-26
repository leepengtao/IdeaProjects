/*
   $(function() {
       $("#myid").click(function() {
           $.ajax({
               url : "/EasyBuy/AuthCodeServlet",
               type : "get",
               success : function(dom) {
                   document.getElementById("authImg").src = "AuthCodeServlet";
               }
           });
       });
   });
   */

//切换验证码
$("#changeCode").click(function(){
    let randNum = Math.random();
    $("#imgCode").attr("src","Number.jsp?num="+randNum);
});


$(function(){
    var erro=0;
    /*$(".l_user").focus(function() {
        $("#lid").html("请输入用户名");
    });*/
    $(".l_user").blur(function(){
        if($(this).val()==""){
            $("#lid").html("");
        }else if ($(this).val() != ""
            && /^[a-zA-Z]{1}([a-zA-Z]|[0-9]){4,15}$/.test($(this).val())) {
            $("#lid").html("");
        } else {
            $("#lid").html("请输入正确的用户名(首字母为英文,5-15位)");
        }
    });

    $(".l_pwd").focus(function(){
        $("#pid").html("请输入密码");
    });
    $(".l_pwd").blur(function(){
        if($(this).val()==""){
            erro=1;
            $("#pid").html("");
        }else if ($(this).val() != ""
            && /^.{1,16}$/.test($(this).val())) {
            erro=0;
            $("#pid").html("");
        } else {
            $("#pid").html("密码格式不正确");
            erro=1;
        }
    });
});


function checks(myform) {
    if (myform.luser.value == "") {
        $("#lid").html("请输入用户名");
        return false;
    }else if (myform.lpwd.value =="") {
        $("#pid").html("请输入密码");
        return false;
    }
    return true;
}