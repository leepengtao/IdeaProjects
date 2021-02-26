<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查询新闻</title>
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>

</head>
<body>
<div>
	<h1 align="center">${news.title }</h1>
	<span style="margin-left:60%">${news.time }</span>	
	<table align="center" style="width:700px">			
		<tr>
			<td><p border="1"></p></td>
		
			<td>
		</tr>
		<tr>
			<td style="font-size:20px">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				${news.content }
			</td>					
		</tr>	
	</table>
	<div align="center">
			<a href="NewsServlet?opr=selectNews"><button>返回</button></a>	
	</div>	
</div>					
</body>
</html>