<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
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
    <script src="js/jquery-3.5.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>

        // 提示确认删除
        function delUser(id) {
            // 用户安全提示
            if (confirm("您确定要删除吗?")) {
                //  调用ajax的函数进行删除
                jq().delUser(id);       // 在js中调用jq函数, 固定格式: $().函数,
            }
        }


        // main
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

            };
        };
    </script>

    <script src="js/ajax.js"></script>

    <script type="text/javascript">
        /**
         * 总结使用Ajax流程:
         *
         *      判断操作: 上一页/下一页
         *      调用Ajax方法进行请求
         *      回调函数进行处理:
         *          难点: 处理表格数据后, jstl标签中的未处理的数据不变, 如userPage.PageNo
         *          原理: 第一次打开页面请求回来的数据是Spring的ModelAndView,用Ajax后请求回来的数据是json, 所以全部数据都要重写.
         *              后端PageBean中没有计数器, 网页中无法存储变量, jq操作的是DOM文件, 不是标准的输入输出流
         *          解决: 用html计数重写网页内容, 再获取
         *              pageNo: 获取网页文本的时候是字符串, 需要转成整形再计算, 传回服务器用json, 需要toString
         *              计数器在网页中需要清空, 再重新给值, 则用到pageNo的地方都要加span标签, 并归一类
         *
         *          方法个数: 翻页的判断
         *                  ajax删除用户
         *                  ajax翻页查询    再添加功能, 直接调用以下三个通用的方法即可
         *                  通用的ajax(get)
         *                  通用的ajax(post)
         *                  处理回调函数返回的用户表单结果集
         *
         * */

        var jq = jQuery.noConflict();   // 让渡
        // 翻页的判断点击操作
        jq(function () {    // 判断用户操作：根据点击的链接判断页码数
            var pageNo = 1;
            jq("#firstPage").click(function () {    // 首页
                // alert("可以了");
                pageNo = 1;
                nextPage(pageNo)                  // 调用ajax, 参数为页码, 下同
            });
            jq("#lastPage").click(function () {     // 上一页
                // alert("没问题")
                pageNo = parseInt(jq(".counter").html()) -1;    // 获取计数器中的页码, 减一为页码
                if (pageNo<1) {
                    return false;
                }
                nextPage(pageNo)
            });
            jq("#nextPage").click(function () {     // 下一页
                pageNo = parseInt(jq(".counter").html()) +1;    // 获取计数器中的页码, 加一为页码
                if (pageNo>parseInt(jq(".counterTotal").html())) {          // 不知道有6页,总页数需要获取
                    return false;
                }
                nextPage(pageNo)
                // jq.post("UserManage/showPage",{"pageNo":pageNO},function (data) {
                //     each(data);
                // });                  // 如果想用post方法则必须在这里写, 传递太多次会报错
            });
            jq("#finalPage").click(function () {    // 末页
                // alert("ojbk")
                pageNo = "${userPage.totalPages}";  // 直接获取总页数作为参数
                nextPage(pageNo)
            });
        });

        // ajax删除用户
        jq.fn.delUser=function(userId) {     // 保证jq函数可以被js调用的固定格式: $.fn.函数名字=funtion(){}
            var pageNo = parseInt(jq(".counter").html());    // 获取当前页的页码
            var url = "${pageContext.request.contextPath}/UserManage/delUser";
            // var json = {"userId":userId,"pageNo":pageNo};
            var data = JSON.stringify({"userId":userId,"pageNo":pageNo});
            console.log(data);
            table_post(url,data);       // 调用ajax
            // 由于jq.post无法设置contentType:"application/json", 需要额外修改源码, 故放弃
        };

        // ajax翻页查询
        function nextPage(pageNo) {
            var url = "UserManage/showPage?pageNo="+pageNo;
            var data = '';
            table_get(url,data)        // 调用ajax
        }


        // 处理回调函数返回的用户表单结果集
        function each(userPage) {                       // 替换用户表格的函数
            jq(".counter").empty().append(userPage.pageNo);     // 给当前页清空, 再加上回传回来的新页码
            jq(".counterTotal").empty().append(userPage.totalPages);    // 给总页数清空, 再加上传回来的总页数

            jq("#users tr:not(:first)").remove();   // 清空用户信息行, 这里用到了html的知识 ---------------
            jq.each(userPage.pageList,function (index,user) {   // 遍历结果集中的列表
                var str = '';
                str += '<tr>'                                   // 拼凑html
                    + '<td><input type="checkbox" name="uid" value="' + user.id + '"</td>'
                    + '<td>' + user.userName + '</td>'
                    + '<td>' + user.loginName + '</td>'
                    + '<td>' + user.sex + '</td>'
                    + '<td>' + user.email + '</td>'
                    + '<td>' + user.mobile + '</td>'
                    + '<td><a class="btn btn-default btn-sm" href="/UserManage/jumpToAddUser?loginName=' + user.loginName + '">修改</a>&nbsp;'
                    + '<a class="btn btn-default btn-sm" href="javascript:delUser('+ user.id + ')">删除</a></td>'
                    + '</tr>';
                jq("#users").append(str);       // 写回html
                console.log(str);               // 控制台输出
            })
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
        <form class="form-inline" action="/UserManage/searchLike?pageNo=${userPage.pageNo }" method="post">     <%--模糊查找为什么要传页码?--%>
            <div class="form-group">
                <input type="text" class="form-control" id="search" name="search" placeholder="请输入用户名">
                <button type="submit" class="btn btn-default">搜索</button>
            </div>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a type="button" class="btn btn-info" href="backend/add.jsp" role="button">添加用户</a>
        <a type="button" class="btn btn-info" href="javascript:void(0);" role="button" id="delSelected">删除选中</a>
        </form>
    </div>

    <%--为了批量删除功能, 特意嵌套了一个form, 和servlet中一个批量删除的方法--%>
    <form id="delForm" action="${pageContext.request.contextPath}/UserManage/delSelected" method="post">
    <table border="1" class="table table-bordered table-hover" id="users">
        <tr class="success">
            <th><input type="checkbox" id="firstCb"></th> <%-- 一个脚本完成点击这个, 下面的全部选中, 但是这个没有值, 不妨碍提交 --%>
            <th>昵称</th>
            <th>账号</th>
            <th>性别</th>
            <th>邮箱</th>
            <th>手机</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${userPage.pageList}" var="user">
            <tr>
                <td><input type="checkbox" name="uid" value="${user.id}"></td>
                <td>${user.userName}</td>
                <td>${user.loginName}</td>
                <td>${user.sex}</td>
                <td>${user.email}</td>
                <td>${user.mobile}</td>
                <td><a class="btn btn-default btn-sm" href="/UserManage/jumpToAddUser?loginName=${user.loginName}">修改</a>&nbsp;
                    <%--<a class="btn btn-default btn-sm" href="UserManageServlet?opr=delUser&userId=${user.id}">删除</a></td>--%>
                    <a class="btn btn-default btn-sm" href="javascript:delUser(${user.id})">删除</a></td>
            </tr>
        </c:forEach>
    </table>
    </form>
    <span  style="padding-left:400px;">
        当前页 / 总页数 [ <span class="counter">${userPage.pageNo}</span> /  <span class="counterTotal">${userPage.totalPages }</span> ]&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="javascript:void(0)" id="firstPage">首页</a> &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript:void(0)" id="lastPage">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript:void(0)" id="nextPage">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <%--<a href="UserManage/showAll?pageNo=${userPage.pageNo+1}" id="nextPage">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;--%>
            <a href="javascript:void(0)" id="finalPage">末页</a>&nbsp;&nbsp;&nbsp;&nbsp;
    </span>
</div>
</body>
</html>