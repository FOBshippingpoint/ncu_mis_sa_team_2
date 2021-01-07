<%@page import="ncu.im3069.demo.app.FoodType"%>
<%@page import="ncu.im3069.demo.app.Seat"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.LocalDate"%>
<%@page import="ncu.im3069.demo.app.ShowingHelper"%>
<%@page import="ncu.im3069.demo.app.Showing"%>
<%@page import="ncu.im3069.demo.app.MovieHelper"%>
<%@page import="ncu.im3069.demo.app.Movie"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
ArrayList<Seat> seats = (ArrayList<Seat>)request.getAttribute("seatsAvailable");
int rowNum = (int)request.getAttribute("rowNum");
int colNum = (int)request.getAttribute("colNum");

ArrayList<FoodType> foodTypes = (ArrayList<FoodType>)request.getAttribute("foodTypes");
%>

<html>

<head>
<%@ include file="../theater-header.jsp"%>
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
</style>

</head>

<body>
<div class="main">
<h1>選擇座位與餐點</h1>
	<form action="/NCU_MIS_SA/member-pages/seat-and-food" method="post">
		<input style="display: none;" name="showingId" value="<%= request.getAttribute("showingId") %>">
		<input style="display: none;" name="movieId" value="<%= request.getAttribute("movieId") %>">
		<table>
			<tr>
			<td><b>  </b></td>
			<% for(int c=1; c<=colNum; c++) { 
				%>
				<td><b><%= c %></b></td>
				<%
			 } %>
			<% for(int r=1; r<=rowNum; r++) { %>
				</tr>
				<tr>
				<td><b><%= r %></b></td>
				<% for(int c=1; c<=colNum; c++) { 
					boolean available = false;
					for(Seat seat: seats){
						if(r == seat.getRowNum() && c == seat.getColNum()){
							available = true;
						}
					}
					%>
					<td><input type="checkbox" name="<%= r + "-" + c %>" <%= available?"":"disabled" %>></td>
					<%
				 } %>
				</tr>
			<% } %>
		</table>
		<% for(FoodType foodType: foodTypes) { %>
			<label for="<%= foodType.getId() %>-num"><%= foodType.getName() %>（<%= foodType.getPrice() %>元）</label>
			<input type="number" min="0" max="10" step="1" name="<%= foodType.getId() %>-num"  value="0"><br>
		<% } %>
		<input type="submit">
	</form>
</div>
</body>

</html>