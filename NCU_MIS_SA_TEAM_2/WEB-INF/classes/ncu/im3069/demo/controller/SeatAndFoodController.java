package ncu.im3069.demo.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ncu.im3069.demo.app.Food;
import ncu.im3069.demo.app.FoodHelper;
import ncu.im3069.demo.app.FoodType;
import ncu.im3069.demo.app.FoodTypeHelper;
import ncu.im3069.demo.app.HallHelper;
import ncu.im3069.demo.app.Movie;
import ncu.im3069.demo.app.MovieHelper;
import ncu.im3069.demo.app.Seat;
import ncu.im3069.demo.app.SeatHelper;
import ncu.im3069.demo.app.Showing;
import ncu.im3069.demo.app.ShowingHelper;
import ncu.im3069.demo.app.Ticket;

@WebServlet("/member-pages/seat-and-food")
public class SeatAndFoodController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setAttribute("rowNum", HallHelper.getHelper().getSeatsRowNum());
//		req.setAttribute("colNum", HallHelper.getHelper().getSeatsColNum());
//
//		req.getRequestDispatcher("member-pages/select-seat.jsp").forward(req, resp);
//	}

	/**
	 * 處理 Http Method 請求 POST 方法（新增資料）
	 *
	 * @param request  Servlet 請求之 HttpServletRequest 之 Request 物件（前端到後端）
	 * @param response Servlet 回傳之 HttpServletResponse 之 Response 物件（後端到前端）
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/** 訂票list */
		ArrayList<Seat> seats = new ArrayList<>();
		ArrayList<Ticket> tickets = new ArrayList<>();
		Showing showing = (Showing) request.getSession().getAttribute("showing");
		Movie movie = (Movie) request.getSession().getAttribute("movie");

		for (int r = 1; r <= HallHelper.getHelper().getSeatsRowNum(); r++) {
			for (int c = 1; c <= HallHelper.getHelper().getSeatsColNum(); c++) {
				if (request.getParameter(r + "-" + c) != null) {
					int seatId = SeatHelper.getHelper().getSeatByRowColNum(r, c).getId();
					seats.add(new Seat(seatId, r, c));
					Ticket ticket = new Ticket(seatId, showing.getId(), -1);
					tickets.add(ticket);
				}
			}
		}

		/** 食物list */
		ArrayList<Food> foods = new ArrayList<>();
		/** 食物總價 */
		int foodTotal = 0;

		for (FoodType foodType : FoodTypeHelper.getHelper().getFoodTypes()) {
			int num = Integer.parseInt(request.getParameter(foodType.getId() + "-num"));
			foods.add(new Food(-1, foodType.getId(), num));
			foodTotal += foodType.getPrice() * num;
		}

		/** 電影票部份 */
		int ticketTotal = 0;
		ticketTotal += tickets.size() * movie.getPrice();

		/** ！！order_id尚未設定 */
		request.getSession().setAttribute("tickets", tickets);
		request.getSession().setAttribute("foods", foods);

		request.setAttribute("seats", seats);

		request.setAttribute("ticketTotal", ticketTotal);
		request.setAttribute("foodTotal", foodTotal);

		/** 轉址 */
		String destPage = "/member-pages/checkout.jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
		dispatcher.forward(request, response);
	}

}
