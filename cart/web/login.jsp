<%@ page language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录</title>
</head>
<body>
<c:if test="${!empty user}">
    <script>
        window.location = "/index.jsp";
    </script>
</c:if>

<c:if test="${empty user}">
    <form action="login" method="post" style="margin: 0 auto;">
        账号： <input type="text" name="name"> <br>
        密码： <input type="password" name="password"> <br>
        <input type="submit" value="登录">
    </form>
</c:if>

</body>

</html>