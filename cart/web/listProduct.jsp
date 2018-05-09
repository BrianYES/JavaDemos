<%@ page language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"
        import="java.util.*"
%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
          prefix="c"
%>

<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>商品列表</title>

    <style>
        .username {
            text-align: center;
        }
        table.products {
            text-align: center;
            border: solid 1px #000;
            margin: 10px auto;
            border-collapse: collapse;
            border-spacing: 0;
        }
        table.products th,
        table.products td {
            border: solid 1px #000;
        }

    </style>
</head>
<body>
<c:if test="${!empty user}">
    <div class="username">
        当前用户：${user.name}
    </div>
</c:if>
<%--<table class="products" align='center' border='1' cellspacing='0'>--%>
<table class="products">
    <thead>
    <tr>
        <th>id</th>
        <th>名称</th>
        <th>价格</th>
        <th>购买</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${products}" var="product" varStatus="st">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>
                <form action="addOrderItem" method="post">

                    数量<input type="text" value="1" name="num">
                    <input type="hidden" name="pid" value="${product.id}">
                    <input type="submit" value="购买">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
 

