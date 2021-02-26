<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
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
    <title>易买网后台管理</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="../js/jquery-3.5.1.min.js"></script>
    <script src="../js/bootstrap.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>



        // 提示确认删除
        function delPro(id) {
            // 用户安全提示
            if (confirm("您确定要删除吗?")) {
                // 访问路径
                location.href="${pageContext.request.contextPath}/ProductManageServlet?opr=delProduct&id="+id;
            }
        }
        window.onload = function () {

            // 点击全选功能
            document.getElementById("firstCb").onclick = function () {
                var checkboxs = document.getElementsByName("uid");
                for (var i=0; i<checkboxs.length; i++) {
                    checkboxs[i].checked = this.checked;
                }
            };

            // 删除选中
            document.getElementById("delSelected").onclick = function () {
                if (confirm("您确定删除选中的条目吗?")){
                    // 先判断是否有选中的条目
                    var flag = false;
                    var checkboxs = document.getElementsByName("uid");
                    for (var i = 0; i< checkboxs.length; i++) {// 遍历判断
                        if (checkboxs[i].checked) {
                            flag = true; // 如果有选中的, 就执行提交删除表格
                            break;
                        }
                    }
                    if (flag){
                        // 提交删除选中表单
                        document.getElementById("delForm").submit();

                    }
                }

            }
        };

    </script>
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
    </div>
    <div style="float: right;margin: 5px;">
        <form class="form-inline">
            <div class="form-group">
                <input type="email" class="form-control" id="search" placeholder="请输入商品名">
                <button type="submit" class="btn btn-default">搜索</button>
            </div>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a type="button" class="btn btn-info" href="ProductManageServlet?opr=showcat" role="button">按类目查看</a>
        <a type="button" class="btn btn-info" href="#" role="button">添加商品</a>
        <a type="button" class="btn btn-info" href="javascript:void(0);" role="button" id="delSelected">删除选中</a>
        </form>
    </div>
    <form  id="delForm" action="${pageContext.request.contextPath}/ProductManageServlet?opr=delSelected" method="post">
    <table class="table table-bordered table-hover">
        <tr class="success">
            <th><input type="checkbox" id="firstCb"></th>
            <th>商品名称</th>
            <th>价格</th>
            <th>商品描述</th>
            <th>库存</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${proPage.pageList}" var="product">
            <tr>
                <td><input type="checkbox" name="uid" value="${product.id}"></td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.description}</td>
                <td>${product.stock}</td>
                <td><a class="btn btn-default btn-sm" href="ProductManageServlet?opr=jumpToModUPro&productId=${product.id}">修改</a>&nbsp;
                    <a class="btn btn-default btn-sm" href="javascript:delPro(${product.id})">删除</a></td>
            </tr>
        </c:forEach>
    </table>
    </form>
    <span  style="padding-left:400px;">
    		当前页 / 总页数 [ ${proPage.pageNo } /  ${proPage.totalPages } ]&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="ProductManageServlet?opr=showall&pageNo=1">首页</a> &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="ProductManageServlet?opr=showall&pageNo=${proPage.pageNo-1 }">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="ProductManageServlet?opr=showall&pageNo=${proPage.pageNo+1 }">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="ProductManageServlet?opr=showall&pageNo=${proPage.totalPages }">末页</a>&nbsp;&nbsp;&nbsp;&nbsp;
    </span>
</div>
</body>
</html>