package ncu.im3069.demo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ncu.im3069.demo.app.Member;
import ncu.im3069.demo.app.MemberHelper;

@WebServlet("/admin-pages/member-list")
public class MemberListController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (null == request.getSession().getAttribute("member")) {
			request.getRequestDispatcher("/home.jsp").forward(request, response);
			return;
		}

		if (!((Member) request.getSession().getAttribute("member")).isAdmin()) {
			request.getRequestDispatcher("/home.jsp").forward(request, response);
			return;
		}

		request.setAttribute("members", MemberHelper.getHelper().getMembers());

		request.getRequestDispatcher("/admin-pages/member-list.jsp").forward(request, response);
	}

}
