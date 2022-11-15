<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>文件列表</title>
</head>
<body>
<c:forEach var="fileName" items="${fileNameList}">
    <c:url var="downloadUrl" value="/download">
        <c:param name="fileName" value="${fileName}"/>
    </c:url>
    ${fileName}<a href="${downloadUrl}">下载</a> <br>
</c:forEach>
</body>
</html>
