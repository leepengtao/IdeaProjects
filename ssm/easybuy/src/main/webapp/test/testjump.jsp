<%--
  Created by IntelliJ IDEA.
  User: superdemon
  Date: 2021/2/1
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>搭建成功!!!</h3>

    <c:forEach items="${list}" var="user">
        ${user.userName}
    </c:forEach>
</body>
</html>
