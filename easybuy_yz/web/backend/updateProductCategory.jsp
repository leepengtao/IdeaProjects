<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改目录</title>
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
</head>
<body>
<h1 align="center">修改目录</h1>
<form action="AdminProductServlet?opr=update" method="post">
	<table border="1" align="center"> 
		<tr>
			<td>
				需要修改的目录：
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
		<tr>
			<td>
				修改到哪个目录下：
			</td>
			<td>
				<select style="width:130px" name="type1" id="d" onchange="testVlaue()">
					<option>一级目录之下</option>
					<c:forEach items="${allCategory}" var="allCategory">
						<option>${allCategory.category.name }</option>				
					</c:forEach>
				</select>
				<select style="width:130px" name="type2" id="e" onchange="testVlaue()">
					<option>二级目录之下</option>
					<c:forEach items="${allCategory}" var="allCategory">					
						<c:forEach items="${allCategory.categoryListVo}" var="categoryListVo2">					
							<option>${categoryListVo2.category.name }</option>																					
						</c:forEach>			
					</c:forEach>
				</select>
			</td>	
		</tr>
		<tr>
			<td>请输入修改后名称：</td>
			<td>
				<input name="name" style="width:96%">			
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
	    if (document.getElementById("d").value!="一级目录之下"){
	    	document.getElementById("e").disabled=true;
	    }else if(document.getElementById("e").value!="二级目录之下"){
	    	document.getElementById("d").disabled=true;
	    }
	 }
</script>
</body>
</html>