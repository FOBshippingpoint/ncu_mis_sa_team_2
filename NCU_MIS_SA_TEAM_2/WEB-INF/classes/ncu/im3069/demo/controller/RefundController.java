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
import ncu.im3069.demo.app.FoodType;
import ncu.im3069.demo.app.FoodTypeHelper;
import ncu.im3069.demo.app.HallHelper;
import ncu.im3069.demo.app.Member;
import ncu.im3069.demo.app.Movie;
import ncu.im3069.demo.app.Order;
import ncu.im3069.demo.app.OrderHelper;
import ncu.im3069.demo.app.Seat;
import ncu.im3069.demo.app.SeatHelper;
import ncu.im3069.demo.app.Showing;
import ncu.im3069.demo.app.Ticket;
import ncu.im3069.demo.app.TicketHelper;

@WebServlet("/member-pages/refund")
public class RefundController extends HttpServlet {
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
		int orderId = Integer.parseInt(request.getParameter("order-id"));
		OrderHelper.getHelper().cancel(orderId);

		request.setAttribute("message", "刪除成功");

		/** 轉址 */
		String destPage = "/home.jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
		dispatcher.forward(request, response);
	}

}
