<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<style type="text/css">
.soubg{
	width:100%; min-width:1200px; height:35px; background-color:#f6f6f6; font-family:"宋体";
	font-size:12px;
}
.sou{
	width:1200px; height:35px; line-height:35px;
}
.s_sh{
	width:55px; height:35px; overflow:hidden; float:left;
}
.s_sh a{
	width:20px; height:35px; line-height:35px; overflow:hidden; float:left; display:inline; margin:0 3px; text-indent:-9999em;
}
.s_sh a.sh1{
	background:url(../images/sh1.png) no-repeat center 8px;
}
.s_sh a.sh2{
	background:url(../images/sh2.png) no-repeat center center;
}
.fl{
	float:left;
}
.fr{
	float:right;
}
a{
	color:#555555; text-decoration:none; cursor:pointer;
}
a:hover{
	color:#ff4e00; text-decoration:none; cursor:pointer;
}
div{
	margin:0 auto;
}	
</style>
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" ></script>
<meta charset="UTF-8">
<title>商品管理</title>
</head>
<body>
<div class="soubg">
	<c:if test="${empty loginUser }">
		<div class="sou">
	        <span class="fr">
	        	<span class="fl">你好，请<a href="Login.jsp">登录</a>&nbsp; <a href="Regist.jsp" style="color:#ff4e00;">免费注册</a>&nbsp;|&nbsp;<a href="#">我的订单</a>&nbsp;|</span>       	
	            <span class="fl">&nbsp;关注我们：</span>
	            <span class="s_sh"><a href="#" class="sh1">新浪</a><a href="#" class="sh2">微信</a></span>
	            <span class="fr">|&nbsp;<a href="#">手机版&nbsp;<img src="images/s_tel.png" align="absmiddle" /></a></span>
	        </span>
    	</div>
	</c:if>
	<c:if test="${not empty loginUser }">
		<c:if test="${loginUser.type==1 }">
			<div class="sou">
		        <span class="fr">
		        	<span class="fl">你好，&nbsp;<span style="color:red">${loginUser.userName }</span>欢迎您！</a>&nbsp;|&nbsp;<a href="admin.jsp" style="color:#ff4e00;">后台管理</a>&nbsp;|&nbsp;<a href="UserServlet?opr=logout" style="color:#ff4e00;">注销</a>&nbsp;|&nbsp;<a href="#">我的订单</a>&nbsp;|</span>       	
		            <span class="fl">&nbsp;关注我们：</span>
		            <span class="s_sh"><a href="#" class="sh1">新浪</a><a href="#" class="sh2">微信</a></span>
		            <span class="fr">|&nbsp;<a href="#">手机版&nbsp;<img src="images/s_tel.png" align="absmiddle" /></a></span>
		        </span>
    		</div>
		</c:if>
		<c:if test="${loginUser.type==0 }">
			<div class="sou">
		        <span class="fr">
		        	<span class="fl">你好，&nbsp;<span style="color:red">${loginUser.userName }</span>欢迎您！</a>&nbsp;|&nbsp;<a href="UserServlet?opr=logout" style="color:#ff4e00;">注销</a>&nbsp;|&nbsp;<a href="#">我的订单</a>&nbsp;|</span>       	
		            <span class="fl">&nbsp;关注我们：</span>
		            <span class="s_sh"><a href="#" class="sh1">新浪</a><a href="#" class="sh2">微信</a></span>
		            <span class="fr">|&nbsp;<a href="#">手机版&nbsp;<img src="images/s_tel.png" align="absmiddle" /></a></span>
		        </span>
	    	</div>
    	</c:if>
	</c:if>
</div>
<a href="../AdminProductServlet?opr=findAllCategory">目录管理</a>
<a href="../AProductServlet?opr=selectProduct&pageNo=1">商品管理</a>
<a href="../UserServlet?opr=selectUser&pageNo=1">客户管理</a>
<a href="../NewsServlet?opr=selectNews&pageNo=1">新闻管理</a>
</body>
</html>