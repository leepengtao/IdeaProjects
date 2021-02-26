<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增商品</title>
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
<style>
	td{
		text-align:center;
	}
	tr{
		height:40px;
	}
</style>
</head>
<body>
<h1 align="center">新增商品</h1>
<form action="AProductServlet?opr=add" method="post" enctype="multipart/form-data">
	<table border="1" align="center" > 
		<tr> 
			<td  width="270px" style="background:#9f0">
				<span style="color:red">新增商品</span>存放于where目录下：<br>
			</td>
			<td  width="180px">				
				<select style="width:97%;height:30px" name="type">
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
			<td style="background:#9f0">商品名称：</td>
			<td><input name="name" style="width:97%;height:28px"></td>
		</tr>
		<tr>
			<td style="background:#9f0">对商品的描述：</td>
			<td><input name="description" style="width:97%;height:28px"></td>
		</tr>		
		<tr>
			<td style="background:#9f0">商品价格：</td>
			<td><input name="price" style="width:97%;height:28px"></td>
		</tr>
		<tr>
			<td style="background:#9f0">商品库存：</td>
			<td><input name="stock" style="width:97%;height:28px"></td>
		</tr>
		<tr>
			<td style="background:#9f0">上传商品图片：</td>
			<td><input name="fileName" type="file"></td>
		</tr>						
	</table>
	<p align="center"><input type="submit"></p>
</form>						
</body>
</html>