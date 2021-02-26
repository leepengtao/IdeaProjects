<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查找商品</title>

<style type = "text/css" > 
	#oneCategory{ 
	   width:100%;
	   height:35px; 
	   line-height:35px; 
	   background-color:yellow;
	} 
	#twoCategory{ 
	   width:100%;
	   height:35px; 
	   line-height:35px; 
	   text-align:center; 
	   background-color:gray;
	}
	a{
		text-decoration:none;
	} 
</style> 

</head> 
<body> 




<p align="center"><button id = "find" onclick="a()">查找商品</button></p>

<div id = "oneCategory" style = "display:none">
	<table align="center">
		<tr>
			<c:forEach items="${allCategory}" var="allCategory" varStatus="a">
				<td>
					<a href="javaScript:void(0)" onclick="b(this)">
						${allCategory.category.name }<c:set var="CategoryVo" value="${allCategory.categoryListVo }"></c:set>
					</a>&nbsp;&nbsp;|&nbsp;&nbsp;
								
				</td>								
	  		</c:forEach>
		</tr>
	</table>	
</div>
<div id = "twoCategory" style = "display:none">
	<c:forEach items="${CategoryVo}" var="CategoryVo">											
			<td><a href="javaScript:void(0)" onclick="b()">${CategoryVo.category.name }</a>&nbsp;&nbsp;|&nbsp;&nbsp;</td>																					
	</c:forEach>
</div>
<script type = "text/javascript"> 
	function Show_Hidden(obj){ 
	  if(obj.style.display=="block"){ 
	   obj.style.display='none'; 
	  }else{ 
	   obj.style.display='block'; 
	  } 
	} 
	function a(){ 
	    var oneCategory=document.getElementById("oneCategory"); 	 
	    Show_Hidden(oneCategory); 
	    return false; 	   
	} 
	function b(){ 
	    var oneCategory=document.getElementById("twoCategory"); 	 
	    Show_Hidden(oneCategory); 
	    return false; 	   
	} 
</script> 
</body>
</html>