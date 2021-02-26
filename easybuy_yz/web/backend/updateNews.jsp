<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改新闻</title>
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
<style>
	input{
		font-size:20px;
	}
	textarea{
		font-size:20px;
	}
	td{
		text-align:center;
	}
	tr{
		height:40px;
	}
</style>
</head>
<body>
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">	
	$(function(){
		$("#newsForm").submit(function(){
			let title = $("input[name='title']").val();
			let content = $("textarea[name='content']").val();
			if ($.trim(title) == "") { //trim去除字符串首尾空格
				$("#a").html("新闻标题不能为空！");
				return false;
			}
			if ($.trim(content) == "") { //trim去除字符串首尾空格
				$("#a").html("主题内容不能为空！");
				return false;
			}
		})
	})
</script>
<h1 align="center">修改新闻</h1>
<form action="NewsServlet?opr=update" method="post" id="newsForm">
	<table border="1" align="center" > 
		<tr style="width:120px">
			<td style="background:#9f0;width:120px">新闻标题：</td>
			<td><input name="title" style="width:97%" value="${news.title }"></td>
		</tr>
		<tr>
			<td style="background:#9f0">主要内容：</td>
			<td><textarea name="content" style="width:500px;height:300px">${news.content }</textarea>
		</tr>
		<tr>
			<td style="background:#9f0">新闻发布时间：</td>
			<td><input name="time" style="width:97%" value="${news.time }"></input>
		</tr>
		<tr hidden="hidden">			
			<td><input name="id"  value="${news.id }"></td>
		</tr>									
	</table>
	<p align="center"><input type="submit"></p>
</form>
<div id="a" align="center" style="color:red"></div>						
</body>
</html>