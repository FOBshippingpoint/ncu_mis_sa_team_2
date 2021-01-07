<%@page import="java.util.ArrayList"%>
<%@page import="ncu.im3069.demo.app.Movie"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

<head>
<%@ include file = "theater-header.jsp"%> 
<link rel=stylesheet type="text/css" href="/NCU_MIS_SA/statics/css/movie-list.css">

<%
ArrayList<Movie> movies = (ArrayList<Movie>) request.getAttribute("movies");
%>
<title>線上電影訂票系統</title>

</head>

<body>
<div class="main">
<h1>電影列表</h1>
	<div>
		<form action="/NCU_MIS_SA/search" method="post">
			<input type="text" name="search">
			<input type="submit" value="搜尋">
		</form>
	</div>
	<ul class="movie-list">
		<% 
		for (Movie m: movies) {
		%>
			<li class="movie-card">
				<a href="/NCU_MIS_SA/browse?m=<%=m.getId()%>">
					<div class="img-holder">
						<img src="/NCU_MIS_SA/images/<%=m.getId()%>.png">
					</div>
					<div class="card-detail">
						<h2 align="center"><%=m.getTitle()%></h2>
					</div>
				</a>
			</li>
		<%
		}
		%>
	</ul>
</div>
</body>

</html>