<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="ncu.im3069.demo.app.Member"%>
<%!public String alert(String str) {
		return "<script>alert(" + str + ")</script>";
	}

	String memberRole = "";%>
<%
	if (request.getAttribute("memberIsAdmin") != null) {
	memberRole = (boolean) request.getAttribute("memberIsAdmin") ? "管理員" : "會員";
}
%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
.navbar {
	background-color: gray;
	text-align: right;
	padding: 10px;
}

.navbar a, span {
	color: white;
}

body {
	margin: 0;
	padding: 0;
}
</style>
</head>

<body>
	<div class="navbar">
		<span> <%
 	if (session.getAttribute("member") != null) {
 	out.print("歡迎回來！");
 %> <span>${sessionScope.member.name}</span> <%=memberRole%> <span>
				| </span> <%
 	}
 %>
		</span> <a href="home.jsp">首頁</a>
		<%
			// 已登入	
		if (session.getAttribute("member") != null) {
			out.print("| <a href=\"/NCU_MIS_SA/api/logout.do\">登出</a>");
		} else {
			out.print("| <a href=\"/NCU_MIS_SA/login.jsp\">登入</a>");
		}
		%>
	</div>
</body>

</html>