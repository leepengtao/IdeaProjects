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
    <script src="../js/ajax.js"></script>
    <script>
        // 提示确认删除
        function delNews(id) {
            // 用户安全提示
            if (confirm("您确定要删除吗?")) {
                // 调用ajax函数
                jq().delNews(id);
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

        var jq = jQuery.noConflict();
        jq(function () {
            var pageNo = 1;
            jq("#firstPage").click(function () {
                pageNo = 1;
                nextPage(pageNo);
            });
            jq("#lastPage").click(function () {
                pageNo = parseInt(jq(".counter").html()) -1;
                if (pageNo <1)
                    return false;
                nextPage(pageNo);
            });
            jq("#nextPage").click(function () {
                pageNo = parseInt(jq(".counter").html())+1;
                if (pageNo > parseInt(jq(".counterTotal").html()))
                    return false;
                nextPage(pageNo);
            });
            jq("#finalPage").click(function () {
                pageNo = parseInt(jq(".counterTotal").html());
                nextPage(pageNo);
            });


        });

        // ajax翻页: 注意: 被外部函数调用的函数, 应该写在主函数外面, 主函数里的调用的可以写在里面 -------------
        function nextPage(pageNo) { // 可以写在里面 --------------
            var url = "/NewsManage/showPages?pageNo="+pageNo;
            var data = '';
            table_get(url,data);
        }

        function each(newsPage) {
            jq("#newsTable tr:not(:first)").remove();
            jq(".counter").empty().append(newsPage.pageNo);     // 这里注意使用remove直接连节点都清空, 而使用empty则是清空节点中的内容
            jq(".counterTotal").empty().append(newsPage.totalPages);

            jq.each(newsPage.pageList, function (index, news) {
                var str = '<tr><td><input type="checkbox" name="uid" value="'+news.id+'"></td>'
                    + '<td>'+ news.title+'</td>'
                    + '<td>'+ news.content+'</td>'
                    + '<td>'+ news.createTime+'</td>'
                    + '<td><a class="btn btn-default btn-sm" href="NewsManage/jumpToModNews?newsId='+ news.id + '">编辑</a>'
                    + '<a class="btn btn-default btn-sm" href="javascript:delNews('+news.id + ')">删除</a></td>';
                jq("#newsTable").append(str);
                console.log(str);
            });
        }

        // ajax删除
        jq.fn.delNews = function (newsId) {
            var pageNo = parseInt(jq(".counter").html());    // 获取当前页的页码
            var url = "/NewsManage/delNews?pageNo="+pageNo + "&newsId="+newsId;
            table_get(url);
        }
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
                <input type="email" class="form-control" id="search" placeholder="请输入关键字">
                <button type="submit" class="btn btn-default">搜索</button>
            </div>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a type="button" class="btn btn-info" href="/backend/addNews.jsp" role="button">投稿</a>
        <a type="button" class="btn btn-info" href="javascript:void(0);" role="button" id="delSelected">删除选中</a>
        </form>
    </div>
    <form  id="delForm" action="${pageContext.request.contextPath}/NewsManage/delSelected" method="post">
    <table class="table table-bordered table-hover" id="newsTable">
        <tr class="success">
            <th><input type="checkbox" id="firstCb"></th>
            <th>标题</th>
            <th>内容</th>
            <th>最后修改时间</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${newsPage.pageList}" var="news">
            <tr>
                <td><input type="checkbox" name="uid" value="${news.id}"></td>
                <td>${news.title}</td>
                <td>${news.content}</td>
                <td>${news.createTime}</td>
                <td><a class="btn btn-default btn-sm" href="NewsManage/jumpToModNews?newsId=${news.id}">编辑</a>
                <a class="btn btn-default btn-sm" href="javascript:delNews(${news.id})">删除</a></td>
            </tr>
        </c:forEach>
    </table>
    </form>
    <span  style="padding-left:400px;">
        当前页 / 总页数 [ <span class="counter">${newsPage.pageNo }</span> /  <span class="counterTotal">${newsPage.totalPages }</span> ]&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="javascript:void(0)" id="firstPage">首页</a> &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript:void(0)" id="lastPage">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript:void(0)" id="nextPage">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript:void(0)" id="finalPage">末页</a>&nbsp;&nbsp;&nbsp;&nbsp;
    </span>
</div>
</body>
</html>