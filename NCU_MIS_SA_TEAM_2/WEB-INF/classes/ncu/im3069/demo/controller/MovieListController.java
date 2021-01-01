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

@WebServlet("/movie-list")
public class MovieListController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("movies", MovieHelper.getHelper().getMovies());
		
		request.getRequestDispatcher("movie-list.jsp").forward(request, response);
	}

}
