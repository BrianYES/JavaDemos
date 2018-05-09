<%--
  Created by IntelliJ IDEA.
  User: xuyong
  Date: 2018/4/17
  Time: 下午3:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/login" method="post">
    用户名： <input type="text" name="userName"><br>
    <input type="submit" value="POST提交">
</form>
<hr>
<form action="${pageContext.request.contextPath}/login" method="get">
    用户名： <input type="text" name="userName"><br>
    <input type="submit" value="GET提交">
</form>

</body>
</html>
