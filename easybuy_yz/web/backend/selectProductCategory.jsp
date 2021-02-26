<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">目录详情表</h1>
	<table border="1" align="center">
	
		<tr>
			<th style="background:red">一级目录</th>		
			<c:forEach items="${allCategory}" var="allCategory" varStatus="a">
				<td colspan="${allCategory.chiledSize  }" align="center" <c:if test="${a.index%2==0 }">style="background:yellow"</c:if> >${allCategory.category.name }</td>				
			</c:forEach>
		</tr>
		<tr>
			<th style="background:green">二级目录</th>
			<c:forEach items="${allCategory}" var="allCategory" varStatus="a">					
				<c:forEach items="${allCategory.categoryListVo}" var="categoryListVo2">					
					<td colspan="${categoryListVo2.size }"align="center"<c:if test="${a.index%2==0 }">style="background:yellow"</c:if>>${categoryListVo2.category.name }</td>																					
				</c:forEach>			
			</c:forEach>
		</tr>
		
		<tr>
			<th style="background:violet">三级目录</th>
			<c:forEach items="${allCategory}" var="allCategory" varStatus="a">					
				<c:forEach items="${allCategory.categoryListVo}" var="categoryListVo2">	
					<c:if test="${categoryListVo2.size==0 }"><td></td></c:if>
					<c:forEach items="${categoryListVo2.categoryListVo}" var="categoryListVo3">										
						<td align="center"<c:if test="${a.index%2==0 }">style="background:yellow"</c:if>>
							${categoryListVo3.category.name }
						</td>
					</c:forEach>																					
				</c:forEach>			
			</c:forEach>
		</tr>			
	</table>
	
	<p align="center">
		<a href="AdminProductServlet?opr=addCategory"><button>新增目录</button></a>
		<a href="AdminProductServlet?opr=updateCategory"><button>修改目录</button></a>
		<a href="AdminProductServlet?opr=delCategory"><button>删除目录</button></a>
	</p>
</body>
</html>