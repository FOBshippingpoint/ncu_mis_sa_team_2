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
</head>

<body>
	<h1>瀏覽電影</h1>
	<div>
		<ul>
			<li><img src="/NCU_MIS_SA/images/<%= movie.getId() %>.png"></li>
			<li>標題: ${movie.getTitle()}</li>
			<li>評分：
			<%	for (int i = 0; i < movie.getRating(); i++) {
			%>
				<img src="https://i.imgur.com/fHvZA5R.png">
			<%
				}
			%>
			</li>
			<li>簡介: <p><%= escape(movie.getIntroduction()) %></p></li>
			<li>版本： ${movie.getVersion()}</li>
			<li>價格： ${movie.getLength()}</li>
			<li>上映時間： ${movie.getOnDateString()}～${movie.getOffDateString()}</li>
			<ol>
			<% for (int i = 0; i < showings.size(); i++) {
			%>
				<li><%= showings.get(i).getStartString() %>～<%= showings.get(i).getEndString() %></li>
			<% }
			%>
			</ol>
		</ul>
	</div>
	<form action="/NCU_MIS_SA/member-pages/booking?m=${movie.getId()}" method="get">
	<input type="submit" value="馬上訂票">
	</form>
</body>

</html>