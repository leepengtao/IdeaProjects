<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改信息</title>
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>

<script type="text/javascript">
	var jq = jQuery.noConflict();
	jq(function($) {
		$("#updateForm").submit(function(){			
			let email = $("input[name='email']").val();
			let mobile = $("input[name='mobile']").val();						
			if ($.trim(email) != "") { //trim去除字符串首尾空格				
				if(email.lastIndexOf("@")==-1||email.lastIndexOf(".")==-1||email.lastIndexOf("@")>email.lastIndexOf(".")||email.lastIndexOf(".")>email.length-3||email.lastIndexOf(".")<email.length-4){
					$("#empty").html("email格式输入不正确！");
					return false;				
				}
			}
			var pattern=/^1[3,5,7,8,9]\d{9}$/;
			if ($.trim(mobile) != "") { //trim去除字符串首尾空格
				if(!pattern.test(+mobile)){
				$("#empty").html("电话号码格式不正确！");
				return false;
				}
			} 						
		})
	})
</script>
<style>
	td{
		text-align:center;
	}
	tr{
		height:40px;
	}
	input{
		font-size:15px;
	}
</style>
</head>
<body>
<h1 align="center">修改信息</h1>
<form action="UserServlet?opr=update" method="post" id="updateForm">
	<table border="1" align="center" > 		
		<tr>
			<td style="background:#9f0;width:150px">昵称：</td>
			<td style="width:250px"><input name="userName" style="width:97%;height:29px" value="${uesr.userName }"></td>
		</tr>		
		<tr>
			<td style="background:#9f0">密码：</td>
			<td><input name="password" style="width:97%;height:28px" value=""></td>
		</tr>
		
		<tr>
			<td style="background:#9f0">性别：</td>
			<td>
				<input name="sex" type="radio" id="female" <c:if test="${uesr.sex==0 }">checked="checked"</c:if>value="0">
				<label for="female">女</label>&nbsp;&nbsp;			
				<input name="sex" type="radio" id="male" <c:if test="${uesr.sex==1 }">checked="checked"</c:if>value="1">
				<label for="male">男</label>
			</td>
		</tr>
		<tr>
			<td style="background:#9f0">身份证：</td>
			<td><input name="identityCode" style="width:97%;height:28px" value="${uesr.identityCode }"></td>
		</tr>
		<tr>
			<td style="background:#9f0">邮箱：</td>
			<td><input name="email" style="width:97%;height:28px" value="${uesr.email }"></td>
		</tr>
		<tr>
			<td style="background:#9f0">电话：</td>
			<td><input name="mobile" style="width:97%;height:28px" value="${uesr.mobile }"></td>
		</tr>
		<tr>
			<td style="background:#9f0">权限：</td>
			<td>
				<input name="type" type="radio" id="user" <c:if test="${uesr.type==0 }">checked="checked"</c:if>value="0">
				<label for="user">普通用户</label>			
				<input name="type" type="radio" id="admin" <c:if test="${uesr.type==1 }">checked="checked"</c:if>value="1">
				<label for="admin">管理员</label>
			</td>
		</tr>
		<tr hidden="hidden">
			<td><input name="id" value="${uesr.id }"></td>
		</tr>		
	</table>
	<p align="center"><input type="submit" id="a"></p>
</form>
<div id="empty"></div>						
</body>
</html>