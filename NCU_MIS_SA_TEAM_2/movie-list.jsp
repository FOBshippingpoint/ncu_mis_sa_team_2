<%@page import="java.util.ArrayList"%>
<%@page import="ncu.im3069.demo.app.Movie"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

<head>
<%@ include file = "theater-header.jsp"%> 

<%
ArrayList<Movie> movies = (ArrayList<Movie>) request.getAttribute("movies");
%>
<title>線上電影訂票系統</title>

<style>
	.wrapper {
	   display: grid;
	   grid-template-columns: repeat(5, 1fr);
	   grid-auto-rows: 500px;
	   grid-column-gap: 10px;
	   grid-row-gap: 1em;
	}
	
	.wrapper a{
		text-decoration: inherit;
		color: inherit;
	}
	
	.cover-holder {
		height: 70%;
		background-color: gray;
	}
	
	.cover-holder img{
		height: 100%; 
		width: 100%; 
	}
	
</style>

</head>

<body>
<h1>Welcome Home</h1>
	<div class="wrapper">
		<% 
		for (Movie m: movies) {
		%>
			<div>
				<div class="movie-block">
					<a href="/NCU_MIS_SA/browse?m=<%=m.getId()%>">
					<div class="cover-holder">
						<img alt="" src="/NCU_MIS_SA/images/<%=m.getId()%>.png">
					</div>
					</a>
					<a href="/NCU_MIS_SA/browse?m=<%=m.getId()%>">
						<h3"><%=m.getTitle()%></h3>
					</a>
				</div>
			</div>
		<%
		}
		%>
	</div>
</body>

</html>