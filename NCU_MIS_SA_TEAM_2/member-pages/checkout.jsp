<%@page import="ncu.im3069.demo.app.FoodTypeHelper"%>
<%@page import="ncu.im3069.demo.app.HallHelper"%>
<%@page import="ncu.im3069.demo.app.Food"%>
<%@page import="ncu.im3069.demo.app.Ticket"%>
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
Showing showing = (Showing) request.getSession().getAttribute("showing");
ArrayList<Ticket> tickets = (ArrayList<Ticket>) request.getSession().getAttribute("tickets");
ArrayList<Food> foods = (ArrayList<Food>) request.getSession().getAttribute("foods");
Movie movie = (Movie) request.getSession().getAttribute("movie");

ArrayList<Seat> seats = (ArrayList<Seat>) request.getAttribute("seats");
int ticketTotal = (int) request.getAttribute("ticketTotal");
int foodTotal = (int) request.getAttribute("foodTotal");

String start = showing.getStartString();
String end = showing.getEndString();
String hallName = HallHelper.getHelper().getHallById(showing.getHallId()).getName();
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
		<h1>結帳</h1>
		<div>
			電影：<%=movie.getTitle()%><br> 
			場次：<%=hallName%>廳；<%=start%>～<%=end%><br>
			座位：<%
		for (Seat seat : seats) {
			out.print(seat.getRowNum() + "排" + seat.getColNum() + ", ");
		}
		%><br>
			票價：<% out.print(movie.getPrice() + " x " + tickets.size() + " = " + ticketTotal); %>元<br>
			餐點：<%
		for (Food food : foods) {
			FoodType foodType = FoodTypeHelper.getHelper().getFoodTypeById(food.getFoodTypeId());
			out.print(foodType.getName() + food.getNum() + "份：" + foodType.getPrice() * food.getNum() + "元, ");
		}
		%><br>
			餐點價：<%=foodTotal %>元<br>
			<hr>
			總價：<%=ticketTotal + foodTotal %>元<br>
			<hr>
		</div>
		<form action="/NCU_MIS_SA/member-pages/checkout" method="post">
			<label for="credit-card">信用卡卡號</label> 
			<input type="text" placeholder="xxxx-xxxx-xxxx-xxxx" name="credit-card" required pattern="^4\d{3}([\ \-]?)\d{4}\1\d{4}\1\d{4}$">
			<input type="text" placeholder="到期月（01-12）" name="card-month" required pattern="0[1-9]|1[0-2]">
			<input type="text" placeholder="到期年（00-99）" name="card-year" required pattern="[0-9][0-9]"><br>
			<label for="CVV">信用卡驗證碼（位於卡片背面）</label>
			<input type="text" name="CVV" required pattern="\d{3}"><br>
			<input type="submit" value="送出">
		</form>
	</div>
</body>

</html>