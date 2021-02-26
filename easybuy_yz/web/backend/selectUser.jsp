<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>客户管理</title>
<style type="text/css">
	td{
		text-align:center;
		height:37px;
	}
	a{
		text-decoration:none;
	}
	#table a:link,#table a:visited,#table a:hover,#table a:active{
		color:red;
	}
	#pageBean a:link,#pageBean a:visited,#pageBean a:hover,#pageBean a:active{
		color:#1EE;
	}		
	#pageBean{
		width:300px;
		margin-top:30px;
		margin-left:64%;
	}
	th{
		background:#06f;
		height:50px;
	}
	#add{
		margin-bottom:50px;
		margin-left:69%
	}
</style>
</head> 
<body>
<div id="table">
	<h1 align="center">客户详情表</h1>
	<span id="add" style="font-size:23px"></span>
	<table border="1" width="1200px" align="center">
		<tr>
			<th>id</th><th>登录名</th><th>昵称</th><th>性别</th><th>身份证</th><th>邮箱</th><th>电话</th><th>权限</th><th></th>		
		</tr>		
			<c:forEach items="${allUser.pageList }" var="User" varStatus="a">
				<tr <c:if test="${a.index%2!=0 }"> style="background:#778288"</c:if>>
					<td width="5%">${User.id }</td><td width="14%">${User.loginName }</td>
					<td width="10%">${User.userName }</td><td width="7%">${User.gender }</td>
					<td width="18%">${User.identityCode }</td><td width="16%">${User.email }</td>
					<td width="12%">${User.mobile }</td><td width="8%">${User.type }</td>
					<td width="10%">
					<a href="UserServlet?opr=updateUser&id=${User.id }">修改</a>
					&nbsp;|&nbsp;<a href="UserServlet?opr=delUser&id=${User.id }">删除</a></td>
				</tr>
			</c:forEach>	
	</table>
</div> 
<div id="pageBean">
	<span>【${allUser.pageNo }&nbsp;/&nbsp;${allUser.totalPage }】</span>&nbsp;&nbsp;
	<a href="UserServlet?opr=selectUser&pageNo=1">首页</a>&nbsp;&nbsp;
	<a href="UserServlet?opr=selectUser&pageNo=${allUser.pageNo-1 }">上一页</a>&nbsp;&nbsp;
	<a href="UserServlet?opr=selectUser&pageNo=${allUser.pageNo+1 }">下一页</a>&nbsp;&nbsp;
	<a href="UserServlet?opr=selectUser&pageNo=${allUser.totalPage }">尾页</a>
</div>
</body>
</html>