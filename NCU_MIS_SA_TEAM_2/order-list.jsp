<%@page import="ncu.im3069.demo.app.FoodHelper"%>
<%@page import="ncu.im3069.demo.app.SeatHelper"%>
<%@page import="ncu.im3069.demo.app.TicketHelper"%>
<%@page import="ncu.im3069.demo.app.Order"%>
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
ArrayList<Order> orders = (ArrayList<Order>)request.getAttribute("orders");
%>

<html>

<head>
<%@ include file="../theater-header.jsp"%>
<title>線上電影訂票系統</title>
<h1>訂票紀錄</h1>

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


.order {
	border: 1px solid black;
	padding: 5px 10px;
}

.invalid {
	color: black;
	border-left: 3px solid crimson;
	background-color: tomato;
	padding: 1px;
}
</style>

</head>

<body>
	<%
	for (Order o: orders) {
		ArrayList<Ticket> tickets = TicketHelper.getHelper().getTicketsByOrderId(o.getId());
		ArrayList<Food> foods = FoodHelper.getHelper().getFoodByOrderId(o.getId());
		if(tickets.size() > 0 ){
			int showingId = tickets.get(0).getShowingId();
			Showing showing = ShowingHelper.getHelper().getShowingById(showingId);
			Movie movie = MovieHelper.getHelper().getMovieById(showing.getMovieId());
			String hallName = HallHelper.getHelper().getHallById(showing.getHallId()).getName();
			String start = showing.getStartString();
			String end = showing.getEndString();
	%>
	<div class="order">
		訂單標號：<%=o.getId()%><br>
		訂購時間：<%=o.getPurchasedString()%><br>
		<% 
		if(o.getCanceled() != null) { 
		%>
			取消時間：<%=o.getCanceledString()%><br>
		<%
		}
		%>
			<div>
				電影：<%=movie.getTitle()%><br> 
				場次：<%=hallName%>廳；<%=start%>～<%=end%><br>
				座位：<%
					for (Ticket t : tickets) {
						Seat seat = SeatHelper.getHelper().getSeatById(t.getSeatId());
						out.print(seat.getRowNum() + "排" + seat.getColNum() + ", ");
					}
					%><br>
						票價：<% out.print(movie.getPrice() + " x " + tickets.size() + " = " + movie.getPrice() * tickets.size()); %>元<br>
						餐點：<%
					int foodTotal = 0;
					for (Food f: foods) {
						FoodType foodType = FoodTypeHelper.getHelper().getFoodTypeById(f.getFoodTypeId());
						out.print(foodType.getName() + f.getNum() + "份：" + foodType.getPrice() * f.getNum() + "元, ");
						foodTotal += foodType.getPrice() * f.getNum();
					}
					%><br>
				餐點價：<%=foodTotal %>元<br>
				<hr>
				總價：<%=movie.getPrice() * tickets.size() + foodTotal %>元<br>
				<hr>
			</div>
			<form action="/NCU_MIS_SA/member-pages/refund" method="post">
				<input style="display: none;" type="text" value="<%=o.getId()%>" name="order-id">
				<input type="submit" value="退票">
			</form>	
	<%
		} else {
			%>
			<div class="order">
				<b class="invalid">訂單失效</b><br>
				訂單標號：<%=o.getId()%><br>
				訂購時間：<%=o.getPurchasedString()%><br>
				<% 
				if(o.getCanceled() != null) { 
				%>
					取消時間：<%=o.getCanceledString()%><br>
				<%
				}
		}
		if(null!=request.getAttribute("isAdmin")) {
		%>
			<form action="/NCU_MIS_SA/admin-pages/edit-order" method="post">
				<input style="display: none;" type="text" name="delete">
				<input style="display: none;" type="text" value="<%=o.getId()%>" name="order-id">
				<input type="submit" value="刪除">
			</form>		
		<%
		}
		%>
			</div>
		</div>
	<%
	} 
	%>
</body>

</html>