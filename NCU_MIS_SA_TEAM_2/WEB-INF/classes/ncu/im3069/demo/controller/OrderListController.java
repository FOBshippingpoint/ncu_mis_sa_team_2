package ncu.im3069.demo.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ncu.im3069.demo.app.Member;
import ncu.im3069.demo.app.Order;
import ncu.im3069.demo.app.OrderHelper;

@WebServlet("/member-pages/order-list")
public class OrderListController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Member member = (Member) request.getSession().getAttribute("member");
		if (member == null) {
			request.getRequestDispatcher("/home.jsp").forward(request, response);
			return;
		}

		ArrayList<Order> orders = OrderHelper.getHelper().getOrders();
		orders.removeIf(o -> o.getMemberId() != member.getID());

		request.setAttribute("orders", orders);
		
		request.getRequestDispatcher("order-list.jsp").forward(request, response);
	}

}
