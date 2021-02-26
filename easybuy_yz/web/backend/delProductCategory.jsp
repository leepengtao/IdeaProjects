<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>删除页面</title>
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
</head>
<body>
<h1 align="center">删除目录</h1>
<form action="AdminProductServlet?opr=del" method="post">
	<table border="1" align="center"> 
		<tr>
			<td>
				需要删除的目录：<br>
				<span style="color:red">
				(注意:删除上级目录,下<br>
				级目录和目录下的所有<br>
				商品都被会被一起删除)</span>
				
			</td>
			<td>
				<select style="width:130px" name="typeOne" id="a" onchange="testVlaue()">
					<option>一级目录</option>
					<c:forEach items="${allCategory}" var="allCategory">
						<option>${allCategory.category.name }</option>				
					</c:forEach>
				</select>
				<select style="width:130px" name="typeTow" id="b" onchange="testVlaue()">
					<option>二级目录</option>
					<c:forEach items="${allCategory}" var="allCategory">					
						<c:forEach items="${allCategory.categoryListVo}" var="categoryListVo2">					
							<option>${categoryListVo2.category.name }</option>																					
						</c:forEach>			
					</c:forEach>
				</select>
				<select style="width:130px" name="typeThree" id="c" onchange="testVlaue()">
					<option>三级目录</option>					
					<c:forEach items="${allCategory}" var="allCategory">					
						<c:forEach items="${allCategory.categoryListVo}" var="categoryListVo2">					
							<c:forEach items="${categoryListVo2.categoryListVo}" var="categoryListVo3">										
								<option>
									${categoryListVo3.category.name }
								</option>
							</c:forEach>																				
						</c:forEach>			
					</c:forEach>
				</select>								
			</td>
		</tr>							
	</table>
	<p align="center"><input type="submit"></p>
</form>
<script type="text/javascript">
	function  testVlaue(){
	    if (document.getElementById("a").value!="一级目录"){
	    	document.getElementById("b").disabled=true;
	    	document.getElementById("c").disabled=true;
	    }else if(document.getElementById("b").value!="二级目录"){
	    	document.getElementById("a").disabled=true;
	    	document.getElementById("c").disabled=true;
	    }else if(document.getElementById("c").value!="三级目录"){
	    	document.getElementById("a").disabled=true;
	    	document.getElementById("b").disabled=true;
	    }
	 }
</script>
</body>
</html>