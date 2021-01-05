<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="ncu.im3069.demo.app.Showing"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ncu.im3069.demo.app.Movie"%>
<html>

<head>
<%@ include file = "theater-header.jsp"%> 

<%
Movie movie = (Movie) request.getAttribute("movie");
ArrayList<Showing> showings = (ArrayList<Showing>) request.getAttribute("showings");
%>
<title>
	線上電影訂票系統
</title>

<style>
	.cover-holder {
		max-width: 300px;
	}
	
	.cover-holder img{
		width: 100%; 
		box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
	}
	
	.wrapper {
		display: grid;
		grid-template-columns: 300px 1fr 1fr 1fr;
	}
</style>

</head>

<body>
	<div class="main">
		<h1>瀏覽電影</h1>
		<div class="wrapper">
			<div class="cover-holder">
				<img src="/NCU_MIS_SA/images/<%= movie.getId() %>.png">
			</div>
			<ul>
				<li>標題：${movie.getTitle()}</li>
				<li>評分：
				<%	
				for (int i = 0; i < movie.getRating(); i++) {
				%>
					<img src="https://i.imgur.com/fHvZA5R.png">
				<%
				}
				%>
				</li>
				<li>簡介: <p><%= escape(movie.getIntroduction()) %></p></li>
				<li>版本： ${movie.getVersion()}</li>
				<li>價格： ${movie.getLength()}</li>
			</ul>
			<ul>
				<li>上映時間： ${movie.getOnDateString()}～${movie.getOffDateString()}</li>
				<ol>
				<% 
				for (int i = 0; i < showings.size(); i++) {
				%>
					<li><%= showings.get(i).getStartString() %>～<%= showings.get(i).getEndString() %></li>
				<% 
				}
				%>
				</ol>
			</ul>
			<div>
			<%
				if(null!=authMember) {
					if(authMember.isAdmin()) {
				%>
					<a href="/NCU_MIS_SA/admin-pages/edit-movie?m=${movie.getId()}">
						<input type="button" value="編輯">
					</a>
				<%
					}
				}
				%>
				<a href="/NCU_MIS_SA/member-pages/booking?m=${movie.getId()}">
					<input type="button" value="馬上訂票">
				</a>
			</div>
		</div>
	</div>
</body>

</html>