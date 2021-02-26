<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="css/style.css" />
<!--[if IE 6]>
    <script src="js/iepng.js" type="text/javascript"></script>
        <script type="text/javascript">
           EvPNG.fix('div, ul, img, li, input, a'); 
        </script>
    <![endif]-->
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>

<script type="text/javascript">
	$(function() {
		$("#changeCode").click(function() {
			$("#imgCode").attr("src", "Number.jsp?" + new Date());
		})
		$("#registForm").submit(function(){
			let loginName = $("input[name='loginName']").val();
			let userName = $("input[name='userName']").val();
			let password = $("input[name='password']").val();
			let repassword = $("input[name='repassword']").val();
			let email = $("input[name='email']").val();
			let mobile = $("input[name='mobile']").val();
			let code = $("input[name='code']").val();
			if ($.trim(loginName) == "") { //trim去除字符串首尾空格
				$("#empty").html("用户名不能为空！");
				return false;
			}
			if ($.trim(userName) == "") { //trim去除字符串首尾空格
				$("#empty").html("昵称不能为空！");
				return false;
			}
			if ($.trim(password) == "") { //trim去除字符串首尾空格
				$("#empty").html("密码不能为空！");
				return false;
			}
			if ($.trim(repassword) == "") { //trim去除字符串首尾空格
				$("#empty").html("请重新输入密码！");
				return false;
			}
			if ($.trim(email) == "") { //trim去除字符串首尾空格
				$("#empty").html("email不能为空！");
				return false;
			}else{
				if(email.lastIndexOf("@")==-1||email.lastIndexOf(".")==-1||email.lastIndexOf("@")>email.lastIndexOf(".")||email.lastIndexOf(".")>email.length-3||email.lastIndexOf(".")<email.length-4){
					$("#empty").html("email格式输入不正确！");
					return false;
				}
			}
			var pattern=/^1[3,5,7,8,9]\d{9}$/;
			if ($.trim(mobile) == "") { //trim去除字符串首尾空格
				$("#empty").html("电话号码不能为空！");
				return false;
			}else if(!pattern.test(+mobile)){
				$("#empty").html("电话号码格式不正确！");
				return false;
			} 
			if ($.trim(code) == "") { //trim去除字符串首尾空格
				$("#empty").html("验证码不能为空！");
				return false;
			}
			if($.trim(password)!=$.trim(repassword)){
				$("#empty").html("两次密码输入不一致，请重新输入！");
				return false;
			}
			
		})
	})
</script>


<title>易买网（注册）</title>
</head>
<body>
	<!--Begin Header Begin-->
	<div class="soubg">
		<div class="sou">
			<span class="fr">
				<span class="fl">
					你好，请	<a href="Login.jsp">登录</a>&nbsp; <a href="Regist.jsp" style="color: #ff4e00;">免费注册</a>&nbsp;
				</span> 
				<span class="fl">
					|&nbsp;关注我们：
				</span> 
				<span class="s_sh">
					<a href="#" class="sh1">新浪</a><a href="#" class="sh2">微信</a>
				</span> 
				<span class="fr">
					|&nbsp;<a href="#">手机版&nbsp;<img 	src="images/s_tel.png" align="absmiddle" /></a>
				</span>
			</span>
		</div>
	</div>
	<!--End Header End-->
	<!--Begin Login Begin-->
	<div class="log_bg">
		<div class="top">
			<div class="logo">
				<a href="Index.jsp"><img src="images/logo.png" /></a>
			</div>
		</div>
		<div class="regist">
			<div class="log_img">
				<img src="images/l_img.png" width="611" height="425" />
			</div>
			<div class="reg_c">
				<form id="registForm" method="post" action="UserServlet?opr=regist">
					<table border="0" style="width: 420px; font-size: 14px; margin-top: 20px;" cellspacing="0" cellpadding="0">
						<tr height="50" valign="top">
							<td width="95">&nbsp;</td>
							<td>
								<span class="fl" style="font-size: 24px;">注册</span>
								<span class="fr">
									已有商城账号，<a href="Login.jsp" style="color: #ff4e00;">我要登录</a>
								</span>
							</td>
						</tr>
						<tr height="50">
							<td align="right"><font color="#ff4e00">*</font>&nbsp;用户名 &nbsp;</td>
							<td><input type="text" value="" class="l_user" name="loginName" /></td>
						</tr>
						<tr height="50">
							<td align="right"><font color="#ff4e00">*</font>&nbsp;昵称 &nbsp;</td>
							<td><input type="text" value="" class="l_user" name="userName" /></td>
						</tr>
						<tr height="50">
							<td align="right"><font color="#ff4e00">*</font>&nbsp;密码 &nbsp;</td>
							<td><input type="password" value="" class="l_pwd" name="password" /></td>
						</tr>
						<tr height="50">
							<td align="right"><font color="#ff4e00">*</font>&nbsp;确认密码 &nbsp;</td>
							<td><input type="password" value="" class="l_pwd" name="repassword" /></td>
						</tr>
						<tr height="50">
							<td align="right"><font color="#ff4e00">*</font>&nbsp;邮箱 &nbsp;</td>
							<td><input type="text" value="" class="l_email" name="email" /></td>
						</tr>
						<tr height="50">
							<td align="right"><font color="#ff4e00">*</font>&nbsp;手机 &nbsp;</td>
							<td><input type="text" value="" class="l_tel" name="mobile" /></td>
						</tr>
						<tr height="50">
							<td align="right">身份证号码 &nbsp;</td>
							<td><input type="text" value="" class="l_mem" name="identityCode" /></td>
						</tr>
						<tr height="50">
							<td align="right">性别 &nbsp;</td>
							<td>
								<input type="radio" value="0" name="gender" id="female"/><label for="female">女</label>
								<input type="radio" value="1" name="gender" checked id="male"/><label for="male">男</label>
							</td>
						</tr>
						<tr height="50">
							<td align="right"><font color="#ff4e00">*</font>&nbsp;验证码 &nbsp;</td>
							<td>
								&nbsp;<img alt="" src="Number.jsp" id="imgCode"> 
								<input type="text" value="" class="l_ipt" name="code" /> 
								<a href="javaScript:void(0)" id="changeCode" style="font-size: 12px; font-family: '宋体';">换一张</a>
							</td>
						</tr>
						<tr height="60">
							<td>&nbsp;</td>
							<td><input type="submit" value="立即注册" class="log_btn" /></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td style="font-size: 12px; padding-top: 20px;">
							<span style="font-family: '宋体';color:red" class="fl"  id="empty">${msg } </span></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<!--End Login End-->
	<!--Begin Footer Begin-->
	<div class="btmbg">
		<div class="btm">
			备案/许可证编号：蜀ICP备12009302号-1-www.dingguagua.com Copyright © 2015-2018
			尤洪商城网 All Rights Reserved. 复制必究 , Technical Support: Dgg Group <br />
			<img src="images/b_1.gif" width="98" height="33" />
			<img src="images/b_2.gif" width="98" height="33" />
			<img src="images/b_3.gif" width="98" height="33" />
			<img src="images/b_4.gif" width="98" height="33" />
			<img src="images/b_5.gif" width="98" height="33" />
			<img src="images/b_6.gif" width="98" height="33" />
		</div>
	</div>
	<!--End Footer End -->

</body>


<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>
