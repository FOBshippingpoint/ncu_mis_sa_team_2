<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

<head>
<%@ include file = "../theater-header.jsp"%> 
<%
ArrayList<Member> members = (ArrayList<Member>) request.getAttribute("members");
%>
<title>線上電影訂票系統</title>
</head>

<body>
<h1>使用者列表</h1>
<div>
	<table>
		<tr>
			<th>使用者編號</th>
			<th>使用者名稱</th>
			<th>信箱</th>
			<th>管理者身份</th>
		</tr>
		<% 
		for (Member m: members) {
		%>
			<tr>
				<td><%=m.getId()%></td>
				<td><%=m.getName()%></td>
				<td><%=m.getEmail()%></td>
				<td><%=m.isAdmin()?"是":"否"%></td>
				<td><a href="/NCU_MIS_SA/edit-member?m=<%=m.getId()%>">編輯</a></td>
			</tr>
		<% 
		} 
		%>
	</table>
</div>
</body>

</html>