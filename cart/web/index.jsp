<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>首页</title>
</head>
<body>

<c:if test="${empty user}">
    <script>
        window.location = "login.jsp";
    </script>
</c:if>

<c:if test="${!empty user}">
    <div>
        下午好，${user.name}
        <a href="listOrderItem.jsp">购物车</a>
        <a href="listProduct">商场</a>
    </div>

</c:if>

</body>
</html>
