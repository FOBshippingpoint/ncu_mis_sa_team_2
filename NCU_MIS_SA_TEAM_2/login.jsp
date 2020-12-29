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
<script src="statics/js/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
<!-- <script src="statics/js/js.cookie.min.js" crossorigin="anonymous"></script> -->
<script
	src="https://cdn.jsdelivr.net/npm/js-cookie@rc/dist/js.cookie.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.2/jquery.validate.min.js"
	integrity="sha512-UdIMMlVx0HEynClOIFSyOrPggomfhBKJE28LKl8yR3ghkgugPnG6iLfRfHwushZl1MOPSY6TsuBDGPK2X4zYKg=="
	crossorigin="anonymous"></script>

<script type="text/javascript">
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
</script>

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
	<form class="center" id="login-form" action="/NCU_MIS_SA/login"
		method="post">
		<table>
			<tr>
				<td align="right"><label for="emial">伊媚兒：</label></td>
				<td><input type="email" id="email" name="email"></td>
			</tr>
			<tr>
				<td align="right"><label for="password">密碼：</label></td>
				<td><input type="password" id="password" name="password"></td>
			</tr>
		</table>
		<br> <input type="submit" id="login-button" value="登入">
	</form>
</body>

</html>