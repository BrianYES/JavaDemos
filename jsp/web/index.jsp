<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
	<title>Index</title>
</head>
<body>

<%
	String name = "Brian";
	request.setAttribute("req_name", "Tsui");
	pageContext.setAttribute("p_name", "666");
%>

<%= name %>
<% out.print(name); %>
<%= pageContext.getAttribute("req_name", PageContext.REQUEST_SCOPE) %>
${requestScope.req_name}
${p_name}<br>
</body>
</html>