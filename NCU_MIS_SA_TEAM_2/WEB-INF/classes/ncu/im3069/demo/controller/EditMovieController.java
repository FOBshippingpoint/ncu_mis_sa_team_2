package ncu.im3069.demo.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import ncu.im3069.demo.app.Hall;
import ncu.im3069.demo.app.HallHelper;
import ncu.im3069.demo.app.Member;
import ncu.im3069.demo.app.Movie;
import ncu.im3069.demo.app.MovieHelper;
import ncu.im3069.demo.app.Seat;
import ncu.im3069.demo.app.Showing;
import ncu.im3069.demo.app.ShowingHelper;
import ncu.im3069.demo.app.TicketHelper;

@WebServlet("/admin-pages/edit-movie")
public class EditMovieController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		if (null != request.getParameter("delete")) {
			doDelete(request, response);
			return;
		}


		Movie movie = (Movie) request.getSession().getAttribute("movie");
		String title = request.getParameter("title");
		String introduction = request.getParameter("introduction");
		int rating = Integer.parseInt(request.getParameter("rating"));
		String version = request.getParameter("version");
		int price = Integer.parseInt(request.getParameter("price"));
		int length = Integer.parseInt(request.getParameter("length"));
		LocalDate onDate = LocalDate.parse(request.getParameter("on-date"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate offDate = LocalDate.parse(request.getParameter("off-date"),
				DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		
		ShowingHelper.getHelper().deleteShowingsOfMovie(movie);
		
		
		movie.setTitle(title);
		movie.setIntroduction(introduction);
		movie.setRating(rating);
		movie.setVersion(version);
		movie.setPrice(price);
		movie.setLength(length);
		movie.setOnDate(onDate);
		movie.setOffDate(offDate);

		MovieHelper.getHelper().update(movie);

		/** WEB-INF/web.xml param: NCU_MIS_SA */
		MovieHelper.getHelper().saveImage(request, "cover",
				new File(getServletContext().getInitParameter("NCU_MIS_SA")).getAbsolutePath() + "/images/",
				movie.getId());

		String hallName = request.getParameter("hall");
		Hall hall = new Hall(hallName);

		MovieHelper.getHelper().setShowingOfMovie(movie, hall);

		request.setAttribute("message", "更新成功");
		/** 動作結束後跳轉網頁 */
		String destPage = "/admin-pages/edit-movie?m=" + movie.getId();

		RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
		dispatcher.forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int movieId = Integer.parseInt(request.getParameter("m"));

		Movie movie = MovieHelper.getHelper().getMovieById(movieId);
		String hallName = HallHelper.getHelper()
				.getHallById(ShowingHelper.getHelper().getShowingsByMovie(movieId).get(0).getHallId()).getName();
		request.getSession().setAttribute("movie", movie);
		request.setAttribute("hallName", hallName);
		request.getRequestDispatcher("/admin-pages/edit-movie.jsp").forward(request, response);
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int movieId = Integer.parseInt(request.getParameter("m"));
		MovieHelper.getHelper().delete(movieId);

		request.getRequestDispatcher("/browse").forward(request, response);
	}

}
