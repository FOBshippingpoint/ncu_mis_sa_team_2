<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

<head>
<%@ include file = "theater-header.jsp"%> 
<title>線上電影訂票系統</title>

</head>

<body>
<div class="main">
	<h1>註冊</h1>
	<form action="/NCU_MIS_SA/register" method="post" id="form">
		<label for="name">使用者名稱</label>
		<input type="text" name="name" required><br>
		<label for="email">信箱</label>
		<input type="text" name="email" required><br>
		<label for="password">密碼</label>
		<input type="password" name="password" required><br>
		<% 
		if(null != request.getAttribute("isAdmin")) {
		%>
			<label for="is-admin">管理員身份</label>
			<input type="checkbox" name="is-admin"><br>
		<%
		}
		%>
		<input type="submit" value="註冊">
	</form>
</div>
</body>

</html>