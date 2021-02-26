<%--
  Created by IntelliJ IDEA.
  User: superdemon
  Date: 2021/2/6
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<base href="<%=basePath%>">
<html>
<head>
    <title>Title</title>

    <script src="../js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript">
        jq = jQuery.noConflict();
        jq(function () {
            jq("#btn").click(function () {
                // alert("ok");
                jq('ul li').remove();// 清空原有页面商品数据
                //1:页面加载完成后，直接去发送ajax请求，要到分页的数据
                var pageNo = 1;

                jq.ajax({
                    url:"Index/findProduct",
                    contentType:"application/json;charset=UTF-8",
                    data:"1",
                    dataType:"json",
                    type:"post",
                    success:function(proPage){
                        //1:解析并显示商品数据。
                        pro_table(proPage);
                    }
                });
            });
        });

        function pro_table(proPage) {
            alert("进来了");
            jq.each(proPage.pageList, function (i, product) {
                // alert(product.id);
                // var id = jq("<li></li>").append(product.id);
                var str;
                str += '<li><div class="name"><a href="/Product/showPro?id=' + product.id + '">' + product.name + '</a></div>'
                    + '<div class="price">'
                    + '<font>￥<span>' + product.price + '</span></font> &nbsp;' + product.stock
                    + '</div>'
                    + '<div class="img"><a href="/Product/showPro?id=' + product.id + '"><img src="images/' + product.fileName + '" width="185" height="155" /></a></div>'
                    + '</li>';
                jq("#pro_table").append(str);
            });
        }
    </script>
</head>
<body>
    <h3>能看到</h3>
    <ul id="pro_table">
        <li>1</li>
        <li>2</li>
        <li>3</li>
        <li>4</li>
    </ul>
    <button id="btn">点我</button>
</body>
</html>
