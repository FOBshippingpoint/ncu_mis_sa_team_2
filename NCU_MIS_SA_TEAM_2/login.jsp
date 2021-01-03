<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

<head>
<%@ include file="theater-header.jsp"%>
<%
if (session.getAttribute("member") != null) {
	response.sendRedirect("/NCU_MIS_SA/home.jsp");
}
%>
<title>線上電影訂票系統</title>
<title>Login</title>

<!-- <script type="text/javascript">
	$(document).ready(function() {
		$('#login-form').validate({
			rules : {
				email : {
					required : true,
					email : true
				},
				password : "required",
			},
			messages : {
				email : {
					required : "必填",
					email : "請檢查Email格式"
				},
				password : "必填"
			}
		})
	});
</script> -->

<style>
.center {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	height: 300px;
}

#login-form label.error {
	margin-left: 10px;
	width: auto;
	display: inline;
}
</style>

</head>

<body>
	<div class="main">
	<h1>登入</h1>
	<form class="center" id="login-form" action="/NCU_MIS_SA/login"
		method="post">
		<table>
			<tr>
				<td align="right"><label for="emial">伊媚兒：</label></td>
				<td><input type="email" id="email" name="email" required></td>
			</tr>
			<tr>
				<td align="right"><label for="password">密碼：</label></td>
				<td><input type="password" id="password" name="password" required></td>
			</tr>
		</table>
		<br> <input type="submit" id="login-button" value="登入">
	</form>
	</div>
</body>

</html>