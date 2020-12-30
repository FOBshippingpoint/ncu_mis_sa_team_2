<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

<head>
<%@ include file = "theater-header.jsp"%> 
<%
Member member = (Member) session.getAttribute("member");

Member editMember = null;
if(null != session.getAttribute("editMember")) {
	editMember = (Member) session.getAttribute("editMember");
} else {
	editMember = member;
}
%>
<title>線上電影訂票系統</title>
</head>

<body>
<h1>註冊</h1>
<div>
	<%
	// 
	if(member.isAdmin()) {
	%>
		<form action="/NCU_MIS_SA/edit-member" method="post">
			<label for="name">使用者名稱</label>
			<input type="text" name="name" value="<%=editMember.getName()%>"><br>
			<label for="email">信箱</label>
			<input type="text" name="email" value="<%=editMember.getEmail()%>"><br>
			<% 
			if(member.getId() == editMember.getId()) {
			%>
			<label for="password">密碼</label>
			<input type="password" name="password" value="<%=editMember.getPassword()%>"><br>
			<%
			}
			%>
			<label for="is-admin">管理員身份</label>
			<input type="checkbox" name="is-admin" <%=editMember.isAdmin()?"checked":""%>><br>
			<input type="submit" value="更新">
		</form>
	<%
	} else {
	%>
		<form action="/NCU_MIS_SA/edit-member" method="post">
			<label for="name">使用者名稱</label>
			<input type="text" name="name" value="<%=editMember.getName()%>"><br>
			<label for="email">信箱</label>
			<input type="text" name="email" value="<%=editMember.getEmail()%>"><br>
			<label for="password">密碼</label>
			<input type="password" name="password" value="<%=editMember.getPassword()%>"><br>
			<input type="submit" value="更新">
		</form>
	<%
	}
	%>
	<form action="/NCU_MIS_SA/edit-member" method="post">
		<input style="display: none;" type="text" name="delete">
		<input type="submit" value="刪除">
	</form>
</div>
</body>

</html>