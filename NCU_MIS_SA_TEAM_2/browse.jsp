<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="ncu.im3069.demo.app.Showing"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ncu.im3069.demo.app.Movie"%>
<html>

<head>
<%@ include file = "theater-header.jsp"%> 
<link rel=stylesheet type="text/css" href="/NCU_MIS_SA/statics/css/browse.css">

<%
Movie movie = (Movie) request.getAttribute("movie");
ArrayList<Showing> showings = (ArrayList<Showing>) request.getAttribute("showings");
%>
<title>
	線上電影訂票系統
</title>

</head>

<body>
	<div class="main">
		<div class="movie-content">
			<div class="img-holder">
				<img src="/NCU_MIS_SA/images/<%= movie.getId() %>.png">
			</div>
			<h1>${movie.getTitle()}</h1>
			<div class="wrapper">
				<div>
					<table class="detail">
						<tr>
							<td>評分</td>
							<td>
							<%	
							for (int i = 0; i < movie.getRating(); i++) {
							%>
								<img src="https://i.imgur.com/fHvZA5R.png">
							<%
							}
							%>
							</td>
						</tr>
						<tr>
							<td>版本</td>
							<td>${movie.getVersion()}</td>
						</tr>
						<tr>
							<td>片長</td>
							<td>${movie.getLength()}</td>
						</tr>
						<tr>
							<td>價格</td>
							<td>${movie.getPrice()}</td>
						</tr>
						<tr>
							<td>簡介</td>
							<td><%= escape(movie.getIntroduction()) %></td>
						</tr>
						<tr>
							<td>上映時間</td>
							<td><%=movie.getOnDateString() + "~" + movie.getOffDateString()%></td>
						</tr>
					</table>
					<%
					if(null!=authMember) {
						if(authMember.isAdmin()) {
					%>
						<a class="button" href="/NCU_MIS_SA/admin-pages/edit-movie?m=${movie.getId()}">
							<img src="https://i.imgur.com/jEmoZns.png"><br>編輯
						</a>
					<%
						}
					}
					%>
					<a class="button" href="/NCU_MIS_SA/member-pages/booking?m=${movie.getId()}">
						<img src="https://i.imgur.com/NJVJKO3.png"><br>馬上訂票
					</a>
				</div>
				<table>
					<tr>
						<td>場次</td>
						<td>
							<ul class="showings">
							<% 
							for (int i = 0; i < showings.size(); i++) {
							%>
								<li><%= showings.get(i).getStartString() %>～<%= showings.get(i).getEndString() %></li>
							<% 
							}
							%>
							</ul>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>

</html>