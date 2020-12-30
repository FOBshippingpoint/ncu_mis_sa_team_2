package ncu.im3069.demo.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ncu.im3069.demo.app.Member;
import ncu.im3069.demo.app.Movie;
import ncu.im3069.demo.app.MovieHelper;
import ncu.im3069.demo.app.Order;
import ncu.im3069.demo.app.OrderHelper;

@WebServlet("/search")
public class SearchController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		String search = request.getParameter("search");
		
		ArrayList<Movie> movies = MovieHelper.getHelper().getMovies();
		movies.removeIf(m->!m.getTitle().contains(search));
		
		request.setAttribute("movies", movies);
		
		request.getRequestDispatcher("movie-list.jsp").forward(request, response);
	}

}
