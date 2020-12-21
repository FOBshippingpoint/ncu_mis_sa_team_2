<!-- 
上方工具列，給其他檔案共用的jsp
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="ncu.im3069.demo.app.Member"%>
<%!
/** 實作alert，我沒試過 */
public String alert(String str) {
		return "<script>alert(\"" + str + "\")</script>";
	}
/** 宣告會員角色 */
	String memberRole = "";%>
<%
/** 設定會員角色（如果已登入） */
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
		/** 已登入則顯示下列資訊 */
 	if (session.getAttribute("member") != null) {
 	out.print("歡迎回來！");
 %> <span>${sessionScope.member.name}</span> <%=memberRole%> <span>
				| </span> <%
 	}
 %>
		</span> <a href="/NCU_MIS_SA/home.jsp">首頁</a>
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