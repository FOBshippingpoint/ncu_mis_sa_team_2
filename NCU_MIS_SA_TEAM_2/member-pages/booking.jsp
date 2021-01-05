<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.LocalDate"%>
<%@page import="ncu.im3069.demo.app.ShowingHelper"%>
<%@page import="ncu.im3069.demo.app.Showing"%>
<%@page import="ncu.im3069.demo.app.MovieHelper"%>
<%@page import="ncu.im3069.demo.app.Movie"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<html>

<head>
<%@ include file="../theater-header.jsp"%>

<% 
Movie movie = (Movie)request.getSession().getAttribute("movie"); 
ArrayList<Showing> showings = (ArrayList<Showing>)request.getAttribute("showings");
%>

<title>線上電影訂票系統</title>
<h1>訂票</h1>

<script type="text/javascript">
	Date.prototype.toDateInputValue = (function() {
		var local = new Date(this);
		local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
		return local.toJSON().slice(0, 10);
	});

	$(document).ready(function() {
		$('#date').on('change', function() {
			alert($(this).val());
		});
	});

</script>

<style>
.my-row {
	display: flex;
	flex-direction: row;
}

.my-column {
	display: flex;
	flex-direction: column;
	margin-right: 50px;
}

.my-column * {
	margin-top: 10px;
}

label {
	display: block;
	float: left;
}

body {
	background: linear-gradient(to top, #595959, 20%, #80ffff);
}

h1 {
	text-align: center;
	color: white;
	background-color: #EE82EE;
	padding: 5px;
	border: 1px solid gray;
	box-shadow: 2px 2px 2px 1px gray;
	text-shadow: 2px 2px 1px gray;
	border-radius: 50px;
}

.movie-container {
	dipslay: inline-block;
	border: 5px outset gray;
	margin: 10px;
	padding: 10px;
}

.movie-container>* {
	display: block;
}

.movie-container p {
	margin: 5px;
}

#title {
	font-style: italic;
	font-size: 30px;
	text-shadow: 0 0 0.2em #87F, 0 0 0.2em #87F, 0 0 0.2em #87F;
}

#introduction {
	margin-left: 30px;
}

.cover-holder {
	box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
	max-width: 300px;
	background-color: gray;
}

.cover-holder img{
	width: 100%; 
}
</style>

</head>

<body>
<div class="main">
<h1>訂票</h1>
	<div>
		<div class="movie-container">
			<b id="title">～${movie.getTitle()}～</b>
			<div class="cover-holder">
			<%
			out.print("<img src=\"/NCU_MIS_SA/images/" + movie.getId() + ".png\">");
			%>
			</div>
			<p>
				<b>評分：</b>
				<%
				for (int i = 0; i < movie.getRating(); i++) {
				%>
				<img src="https://i.imgur.com/fHvZA5R.png">
				<%
				}
				%>
			</p>
			<p>
				<b>簡介：</b>
			</p>
			<p id="introduction"><%=escape(movie.getIntroduction())%></p>
			<p>
				<b>版本：</b><%=movie.getVersion()%></p>
			<p>
				<b>價格：</b><%=movie.getPrice()%></p>
			<p>
				<b>時長：</b><%=movie.getLength()%></p>
			<p>
				<b>上映時間：</b><%=movie.getOnDateString()%>～<%=movie.getOffDateString()%></p>
		</div>
	</div>
	<form action="#" method="post">
		<label for="showing-id">場次</label> <select name="showing-id"
			id="showing-id">
			<%
			for (Showing showing : showings) {
			%>
			<option value="<%=showing.getId()%>">
				<%=showing.getStartString()%>
			</option>
			<%
			}
			%>
		</select>
		<p>
			<input type="submit" value="送出" id="submit-button">
		</p>
	</form>
	</div>
</body>

</html>