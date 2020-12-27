package ncu.im3069.demo.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ncu.im3069.demo.app.FoodTypeHelper;
import ncu.im3069.demo.app.HallHelper;
import ncu.im3069.demo.app.Member;
import ncu.im3069.demo.app.Order;
import ncu.im3069.demo.app.Seat;
import ncu.im3069.demo.app.TicketHelper;

@WebServlet("/member-pages/checkout")
public class CheckoutController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Movie movie = MovieHelper.getHelper().getMovieById(movieId);
//		ArrayList<Showing> showings = ShowingHelper.getHelper().getShowingsByMovie(movie);
//
//		req.setAttribute("movie", movie);
//		req.setAttribute("showings", showings);
//
//		req.getRequestDispatcher("booking.jsp").forward(req, resp);
	}

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
		int memberId = ((Member)request.getSession().getAttribute("member")).getID();
		Order order = new Order(request.getSession().getAttribute(getServletName()));
		
		int movieId = Integer.parseInt(request.getParameter("movie-id"));
		int showingId = Integer.parseInt(request.getParameter("showing-id"));
		
		ArrayList<Seat> seats = TicketHelper.getHelper().getSeatsAvailableByShowingId(showingId);
		request.setAttribute("movieId", movieId);
		request.setAttribute("showingId", showingId);
		request.setAttribute("seatsAvailable", seats);
		request.setAttribute("rowNum", HallHelper.getHelper().getSeatsRowNum());
		request.setAttribute("colNum", HallHelper.getHelper().getSeatsColNum());
		request.setAttribute("foodTypes", FoodTypeHelper.getHelper().getFoodTypes());

		/** 動作結束後跳轉網頁 */
		String destPage = "/member-pages/seat-and-food.jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
		dispatcher.forward(request, response);
	}

}
