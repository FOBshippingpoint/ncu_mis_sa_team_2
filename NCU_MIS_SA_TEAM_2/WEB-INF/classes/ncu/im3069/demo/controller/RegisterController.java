package ncu.im3069.demo.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.tools.javac.comp.MemberEnter;

import ncu.im3069.demo.app.Food;
import ncu.im3069.demo.app.FoodType;
import ncu.im3069.demo.app.FoodTypeHelper;
import ncu.im3069.demo.app.HallHelper;
import ncu.im3069.demo.app.Member;
import ncu.im3069.demo.app.MemberHelper;
import ncu.im3069.demo.app.Movie;
import ncu.im3069.demo.app.Order;
import ncu.im3069.demo.app.OrderHelper;
import ncu.im3069.demo.app.Seat;
import ncu.im3069.demo.app.SeatHelper;
import ncu.im3069.demo.app.Showing;
import ncu.im3069.demo.app.Ticket;
import ncu.im3069.demo.app.TicketHelper;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (null != request.getSession().getAttribute("member")) {
			Member member = (Member) request.getSession().getAttribute("member");
			if (member.isAdmin()) {
				request.setAttribute("isAdmin", "");
			} else {
				request.getRequestDispatcher("/home.jsp").forward(request, response);

				return;
			}
		}

		request.getRequestDispatcher("/register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		boolean isAdmin = false;
		if (null != request.getParameter("is-admin")) {
			isAdmin = "on".equals(request.getParameter("is-admin"));
		}
		
		if(!MemberHelper.getHelper().checkEmailisFine(email)) {
			request.setAttribute("message", "伊媚兒已註冊，註冊失敗");
			request.getRequestDispatcher("/home.jsp").forward(request, response);
			return;
		}
		
		Member member = new Member(name, email, password, isAdmin);
		MemberHelper.getHelper().create(member);
		
		request.setAttribute("message", "註冊成功");

		/** 轉址 */
		String destPage = "/home.jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
		dispatcher.forward(request, response);
	}

}
