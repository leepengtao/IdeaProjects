<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<base href="<%=basePath%>">
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="../js/jquery-3.5.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <title>添加新闻</title>
</head>
<body>
<div class="container">

    <h3>欢迎：${sessionScope.userLogin.userName}&nbsp;&nbsp;登陆易买网管理系统</h3>
    <div style="float: left;margin: 2px;">
        <a class="btn btn-primary" href="UserManage/showAll" role="button">账号管理</a>
        <a class="btn btn-primary" href="ProductManageServlet?opr=showall" role="button">商品管理</a>
        <a class="btn btn-primary" href="#" role="button">商品分类</a>
        <a class="btn btn-primary" href="#" role="button">订单管理</a>
        <a class="btn btn-primary" href="NewsManage/showAll" role="button">新闻管理</a>
        <a class="btn btn-primary" href="#" role="button">回复买家留言</a>
    </div>
    <br />
    <br />
    <br />



    <div class="container">

        <form action="NewsManage/addNews" method="post">

            <div class="form-group">
                <label for="title">标题</label>
                <input type="text" class="form-control" id="title" name="title" placeholder="请输入登标题">
            </div>

            <div class="form-group">
                <label for="content">内容</label>
                <textarea cols="20" rows="10" class="form-control" id="content" name="content" placeholder="请输入内容"></textarea>
            </div>
            <div class="form-group" style="text-align: center">
                <input class="btn btn-primary" type="submit" value="提交" />
                <input class="btn btn-default" type="reset" value="重置" />
                <input class="btn btn-default" href="NewsManageServlet?opr=showall" type="button" value="返回" />
            </div>
        </form>
    </div>
</div>
</body>
</html>