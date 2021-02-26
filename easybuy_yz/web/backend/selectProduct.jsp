<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查找商品</title>
<style type="text/css">
	td{
		text-align:center;
		height:37px;
	}
	a{
		text-decoration:none;
	}
	#table a:link,#table a:visited,#table a:hover,#table a:active{
		color:red;
	}
	#pageBean a:link,#pageBean a:visited,#pageBean a:hover,#pageBean a:active{
		color:#1EE;
	}		
	#pageBean{
		width:300px;
		margin-top:30px;
		margin-left:54%;
	}
	th{
		background:#06f;
		height:50px;
	}
	#add{
		margin-bottom:50px;
		margin-left:69%
	}
</style>
</head> 
<body>
<div id="table">
	<h1 align="center">商品详情表</h1>
	<span id="add" style="font-size:23px"><a href="AProductServlet?opr=addCategory">新增</a></span>
	<table border="1" width="920px" align="center">
		<tr>
			<th>id</th><th>商品目录</th><th>名称</th><th>描述</th><th>价格</th><th>库存</th><th></th>		
		</tr>		
			<c:forEach items="${productVoList }" var="productVo" varStatus="a">
				<tr <c:if test="${a.index%2!=0 }"> style="background:#778288"</c:if>>
					<td width="8%">${productVo.product.id }</td>
					<td width="26%">${productVo.parentName1 }&nbsp;/&nbsp;${productVo.parentName2 }&nbsp;/&nbsp;${productVo.parentName3 }</td>
					<td width="12%">${productVo.product.name }</td>
					<td width="22%">${productVo.product.description }</td><td width="12%">${productVo.product.price }</td>
					<td width="10%">${productVo.product.stock }</td><td width="10%">
					<a href="AProductServlet?opr=updateCategory&id=${productVo.product.id }">修改</a>
					&nbsp;|&nbsp;<a href="AProductServlet?opr=delCategory&id=${productVo.product.id }">删除</a></td>
				</tr>
			</c:forEach>	
	</table>
</div> 
<div id="pageBean">
	<span>【${findpage.pageNo }&nbsp;/&nbsp;${findpage.totalPage }】</span>&nbsp;&nbsp;
	<a href="AProductServlet?opr=selectProduct&pageNo=1">首页</a>&nbsp;&nbsp;
	<a href="AProductServlet?opr=selectProduct&pageNo=${findpage.pageNo-1 }">上一页</a>&nbsp;&nbsp;
	<a href="AProductServlet?opr=selectProduct&pageNo=${findpage.pageNo+1 }">下一页</a>&nbsp;&nbsp;
	<a href="AProductServlet?opr=selectProduct&pageNo=${findpage.totalPage }">尾页</a>
</div>
</body>
</html>