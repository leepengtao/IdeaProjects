<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="fm" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'useradd2.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  
  	<fm:form method="post" action="douseradd2.html" modelAttribute="user">
  		<div>用户编码：<fm:input path="userCode"/> 
  		<fm:errors path="userCode" />
  		</div>
  		<div>用户名称：<fm:input path="userName"/><fm:errors path="userName" /> </div>
  		<div>用户密码：<fm:password path="userPassword"/> <fm:errors path="userPassword" /></div>
  		<div>出生日期：<fm:input path="birthday" onclick="WdatePicker();" Class="Wdate"/> <fm:errors path="birthday" /> </div>
  		<div>用户地址：<fm:input path="address"/> </div>
  		<div>联系电话：<fm:input path="phone"/> </div>
  		<div>用户角色: 
  			<fm:radiobutton path="userRole" value="1"/> 管理员
  			<fm:radiobutton path="userRole" value="2"/> 经理
  			<fm:radiobutton path="userRole" value="3"/> 员工
  		 </div>
		<div>
			<input type="submit" value="保存">
		</div>
	</fm:form>
	<%@include file="./common/foot.jsp" %>
  </body>
</html>
