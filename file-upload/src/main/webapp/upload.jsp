<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传文件</title>
</head>
<body>

<form action="/upload" enctype="multipart/form-data" method="post">
    <input type="text" name="name"> <br>
    <input type="file" name="file"> <br>
    <input type="submit" value="提交">
</form>

</body>
</html>
