package ncu.im3069.demo.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.tools.doclint.resources.doclint_ja;

import ncu.im3069.demo.app.Hall;
import ncu.im3069.demo.app.HallHelper;
import ncu.im3069.demo.app.Movie;
import ncu.im3069.demo.app.MovieHelper;
import ncu.im3069.demo.app.OrderHelper;
import ncu.im3069.demo.app.ShowingHelper;

@WebServlet("/admin-pages/edit-order")
public class EditOrderController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (null != request.getParameter("delete")) {
			doDelete(request, response);
			return;
		}
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int orderId = Integer.parseInt(request.getParameter("order-id"));
		OrderHelper.getHelper().delete(orderId);
		request.setAttribute("message", "刪除成功");

		request.getRequestDispatcher("/order-list").forward(request, response);
	}

}
