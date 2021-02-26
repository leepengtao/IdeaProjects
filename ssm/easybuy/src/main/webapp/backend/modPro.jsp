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
    <script src="js/jquery-3.5.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <title>修改商品属性</title>
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
    <br>
    <br>
    <br>
    <form action="ProductManageServlet?opr=modPro" method="post">
        <input value="${user.id}" name="id" hidden>

        <div class="form-group">
            <label for="name">账号：</label>
            <input type="text" class="form-control" id="name" name="name"  value="${user.loginName}" readonly="readonly" placeholder="请输入姓名" />
        </div>

        <div class="form-group">
            <label for="userName">昵称：</label>
            <input type="text" class="form-control" id="userName" name="userName" value="${user.userName}" placeholder="请输入昵称">
        </div>

        <div class="form-group">
            <label for="password">密码：</label>
            <input type="text" class="form-control" id="password" name="password"value="${user.password}"readonly="readonly">
        </div>



        <div class="form-group">
            <label>性别：</label>
            <input type="radio" name="sex" value="1" checked="checked"/>男
            <input type="radio" name="sex" value="0"/>女
        </div>

        <div class="form-group">
            <label for="identityCode">身份证号码：</label>
            <input type="text" class="form-control" id="identityCode" name="identityCode" value="${user.identityCode}" placeholder="请输入身份证号码">
        </div>


        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" class="form-control" name="email" id="email" value="${user.email}" placeholder="请输入邮箱"/>
        </div>

        <div class="form-group">
            <label for="mobile">手机：</label>
            <input type="text" class="form-control" name="mobile" id="mobile" value="${user.mobile}" placeholder="请输入手机号"/>
        </div>

        <div class="form-group">
            <label>管理员：</label>
            <input type="radio" name="type" value="1" />是
            <input type="radio" name="type" value="0" checked="checked"/>否
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回" />
        </div>
    </form>
</div>
</body>
</html>