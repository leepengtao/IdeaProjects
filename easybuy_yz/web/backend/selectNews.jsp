<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新闻管理</title>
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
	#title a:link,#title a:visited,#title a:hover,#title a:active{
		color:#000;
	}		
	#pageBean{
		width:300px;
		margin-top:30px;
		margin-left:50%;
	}
	th{
		background:#06f;
		height:50px;
	}
	#add{
		margin-bottom:50px;
		margin-left:66%
	}
</style>
</head> 
<body>
<div id="table">
	<h1 align="center">新闻详情表</h1>
	<span id="add" style="font-size:23px"><a href="backend/addNews.jsp">新增</a></span>
	<div >
		<table border="1" width="700px" align="center">
			<tr>
				<th>序号</th><th>标题</th><th>创建时间</th><th></th>		
			</tr>		
				<c:forEach items="${findByNews.pageList }" var="news" varStatus="a">
					<tr <c:if test="${a.index%2!=0 }"> style="background:#778288"</c:if> >
						<td width="10%">${(a.index+1)+(findByNews.pageNo-1)*8 }</td>
						<td width="45%" id="title"><a href="NewsServlet?opr=select&id=${news.id }">${news.title }</a></td>
						<td width="25%">${news.time }</td>
						<td width="20%" >
						<a href="NewsServlet?opr=updateNews&id=${news.id }">修改</a>
						&nbsp;|&nbsp;<a href="NewsServlet?opr=delNews&id=${news.id }">删除</a></td>
					</tr>
				</c:forEach>	
		</table>
	</div>
</div> 
<div id="pageBean">
	<span>【${findByNews.pageNo }&nbsp;/&nbsp;${findByNews.totalPage }】</span>&nbsp;&nbsp;
	<a href="NewsServlet?opr=selectNews&pageNo=1">首页</a>&nbsp;&nbsp;
	<a href="NewsServlet?opr=selectNews&pageNo=${findByNews.pageNo-1 }">上一页</a>&nbsp;&nbsp;
	<a href="NewsServlet?opr=selectNews&pageNo=${findByNews.pageNo+1 }">下一页</a>&nbsp;&nbsp;
	<a href="NewsServlet?opr=selectNews&pageNo=${findByNews.totalPage }">尾页</a>
</div>
</body>
</html>