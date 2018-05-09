<%--
  Created by IntelliJ IDEA.
  User: xuyong
  Date: 2018/4/11
  Time: 下午1:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Check</title>
</head>
<body>

Check次数：${count} <br>

session: <%= session.getAttribute("count")%> <br>
request: <%=request.getAttribute("count")%>

</body>
</html>
