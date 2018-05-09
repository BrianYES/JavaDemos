<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         import="java.util.*"
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>购物车</title>
</head>
<body>

<h1 style="text-align: center">购物车</h1>

<c:if test="${empty orderItemList}">
    抱歉，购物车空空如也！
</c:if>

<c:if test="${!empty orderItemList}">
    <table align='center' border='1' cellspacing='0'>
        <tr>
            <td>商品名称</td>
            <td>单价</td>
            <td>数量</td>
            <td>小计</td>
        </tr>

        <c:forEach items="${orderItemList}" var="oi" varStatus="st">
            <tr>
                <td>${oi.product.name}</td>
                <td>${oi.product.price}</td>
                <td>${oi.num}</td>
                <td><fmt:formatNumber type="number" value="${oi.num*oi.product.price}" maxFractionDigits="2"/></td>
            </tr>
        </c:forEach>

        <tr>
            <td colspan="4" align="right">
                <a href="/createOrder">生成订单</a>
            </td>
        </tr>
    </table>


</c:if>



</body>
</html>