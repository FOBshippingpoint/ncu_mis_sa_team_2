package ncu.im3069.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ncu.im3069.demo.app.FoodType;
import ncu.im3069.demo.app.FoodTypeHelper;
import ncu.im3069.demo.app.HallHelper;
import ncu.im3069.demo.app.Movie;
import ncu.im3069.demo.app.MovieHelper;
import ncu.im3069.demo.app.Seat;
import ncu.im3069.demo.app.Showing;
import ncu.im3069.demo.app.ShowingHelper;
import ncu.im3069.demo.app.TicketHelper;

@WebServlet("/browse")
public class BrowseController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if (m == null) {
			String message = "未提供電影編號或電影編號有誤";
			
			request.setAttribute("message", message);
			request.getRequestDispatcher("/home.jsp").forward(request, response);
			
			return;
		}
		int movieId = Integer.parseInt(m);
		Movie movie = MovieHelper.getHelper().getMovieById(movieId);
		ArrayList<Showing> showings = ShowingHelper.getHelper().getShowingsByMovie(movie);

		request.setAttribute("showings", showings);
		request.setAttribute("movie", movie);
		
		request.getRequestDispatcher("browse.jsp").forward(request, response);
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
		int showingId = Integer.parseInt(request.getParameter("showing-id"));
		ArrayList<Seat> seats = TicketHelper.getHelper().getSeatsAvailableByShowingId(showingId);
		request.getSession().setAttribute("showing", ShowingHelper.getHelper().getShowingById(showingId));
		
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
