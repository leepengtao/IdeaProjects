<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新建目录</title>
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
</head>
<body>
<h1 align="center">新建目录</h1>
<form action="AdminProductServlet?opr=add" method="post">
	<table border="1" align="center"> 
		<tr>
			<td>
				请选择新增目录位于哪级目录之下：<br>
				（备注：不选则创建一级目录）
			</td>
			<td>
				<select style="width:130px" name="type" id="a" onchange="testVlaue()">
					<option>一级目录之下</option>
					<c:forEach items="${allCategory}" var="allCategory">
						<option>${allCategory.category.name }</option>				
					</c:forEach>
				</select>
				<select style="width:130px" name="type1" id="b" onchange="testVlaue()">
					<option>二级目录之下</option>
					<c:forEach items="${allCategory}" var="allCategory">
						<c:forEach items="${allCategory.categoryListVo}" var="categoryListVo">															
							<option>${categoryListVo.category.name }</option>																								
						</c:forEach>			
					</c:forEach>					
				</select>								
			</td>
		</tr>
		<tr>
			<td>请输入目录名称：</td>
			<td>
				<input name="name" style="width:96%">			
			</td>
		</tr>				
	</table>
	<p align="center"><input type="submit"></p>
</form>
<script type="text/javascript">
	function  testVlaue(){
	    if (document.getElementById("a").value!="一级目录之下"){
	    	document.getElementById("b").disabled=true;
	    }else if(document.getElementById("b").value!="二级目录之下"){
	    	document.getElementById("a").disabled=false;
	    }
	 }
</script>
</body>
</html>