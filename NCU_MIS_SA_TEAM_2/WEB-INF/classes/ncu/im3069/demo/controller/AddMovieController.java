package ncu.im3069.demo.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ncu.im3069.demo.app.MovieHelper;
import ncu.im3069.demo.app.Showing;
import ncu.im3069.demo.app.ShowingHelper;
import ncu.im3069.demo.app.Hall;
import ncu.im3069.demo.app.Movie;

@WebServlet("/admin-pages/add-movie")
@MultipartConfig
public class AddMovieController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/admin-pages/add-movie.jsp").forward(request, response);
	}

	private MovieHelper amh = (MovieHelper) MovieHelper.getHelper();

	public ArrayList<String> getHallNames() {
		return amh.getHallNames();
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
		request.setCharacterEncoding("UTF-8");

		String title = request.getParameter("title");
		String introduction = request.getParameter("introduction");
		int rating = Integer.parseInt(request.getParameter("rating"));
		String version = request.getParameter("version");
		int price = Integer.parseInt(request.getParameter("price"));
		int length = Integer.parseInt(request.getParameter("length"));
		LocalDate onDate = LocalDate.parse(request.getParameter("on-date"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate offDate = LocalDate.parse(request.getParameter("off-date"),
				DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		Movie movie = new Movie(title, introduction, rating, version, price, length, onDate, offDate);
		int movieId = amh.create(movie);
		movie.setId(movieId);

		/** WEB-INF/web.xml param: NCU_MIS_SA */
		amh.saveImage(request, "cover",
				new File(getServletContext().getInitParameter("NCU_MIS_SA")).getAbsolutePath() + "/images/", movieId);

		String hallName = request.getParameter("hall");
		Hall hall = new Hall(hallName);
		
		amh.setShowingOfMovie(movie, hall);
		

		/** 動作結束後跳轉網頁 */
		String destPage = "/admin-pages/add-movie.jsp";
		request.setAttribute("message", "已新增…");

		RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
		dispatcher.forward(request, response);
	}

}
