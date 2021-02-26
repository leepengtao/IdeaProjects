$(function () {// 在页面加载完成之后的入口函数
    //切换验证码
    $("#changeCode").click(function(){
        let randNum = Math.random();
        $("#imgCode").attr("src","Number.jsp?num="+randNum);
    });

    // 判断是否正确
    $.get("UserServlet")
    // 注册时所有项目是否为空判断
    $("#registerForm").submit(function () {
        let loginName = $("input[name='loginName']").val();         // 登录名
        let userName = $("input[username='userName']").val();       // 昵称
        let passWord = $("input[password='password']").val();       // 密码
        let repassword = $("input[repassword='repassword']").val(); // 确认密码
        let email = $("input[email='email']").val();    // 邮箱
        let mobile = $("input[mobile='mobile']").val();// 手机
        let identityCode = $("input[identityCode='identityCode']").val();  // 身份证号
        let sex = $("input[sex='identityCode']").val(); // 性别
        let code = $("input[code='code']").val();   // 输入的验证码

        if($.trim()=="loginName"){
            alert("用户名不能为空");
            return false;
        }
        if($.trim()=="userName"){
            alert("昵称不能为空");
            return false;
        }
        if($.trim()=="passWord"){
            alert("密码不能为空");
            return false;
        }
        if($.trim()=="repassword"){
            alert("确认密码不能为空");
            return false;
        }
        if($.trim()=="mobile"){
            alert("手机号不能为空");
            return false;
        }
        if($.trim()=="identityCode"){
            alert("身份证号不能为空");
            return false;
        }
        if($.trim()=="sex"){
            alert("性别不能为空");
            return false;
        }
        if($.trim()=="code"){
            alert("验证码不能为空");
            return false;
        }
    });
})