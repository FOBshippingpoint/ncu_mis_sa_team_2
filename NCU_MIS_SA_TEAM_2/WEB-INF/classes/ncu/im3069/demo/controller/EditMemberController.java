package ncu.im3069.demo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ncu.im3069.demo.app.Member;
import ncu.im3069.demo.app.MemberHelper;

@WebServlet("/edit-member")
public class EditMemberController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (null == request.getSession().getAttribute("member")) {
			request.getRequestDispatcher("/home.jsp").forward(request, response);

			return;
		}
		int memberId = -1;
		if (null == request.getParameter("m")) {
			memberId = ((Member) request.getSession().getAttribute("member")).getId();
		} else {
			memberId = Integer.parseInt(request.getParameter("m"));
		}

		Member editMember = MemberHelper.getHelper().getMemberById(memberId);
		request.getSession().setAttribute("editMember", editMember);
		request.getRequestDispatcher("/edit-member.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (null != request.getParameter("delete")) {
			doDelete(request, response);
			return;
		}
	
		Member member = (Member) request.getSession().getAttribute("member");
		if (null != request.getSession().getAttribute("editMember")) {
			member = (Member) request.getSession().getAttribute("editMember");
		}
		String name = request.getParameter("name");
		String email = request.getParameter("email");
	
		member.setName(name);
		member.setEmail(email);
	
		if (null != request.getParameter("password")) {
			String password = request.getParameter("password");
			member.setPassword(password);
		}
		if (null != request.getParameter("is-admin")) {
			boolean isAdmin = "on".equals(request.getParameter("is-admin"));
			member.setAdmin(isAdmin);
		}
	
		MemberHelper.getHelper().update(member);
		
		request.setAttribute("message", "更新成功");
		request.getRequestDispatcher("/admin-pages/member-list").forward(request, response);
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Member member = (Member) request.getSession().getAttribute("member");
		if (null != request.getSession().getAttribute("editMember")) {
			member = (Member) request.getSession().getAttribute("editMember");
		}
		MemberHelper.getHelper().delete(member.getId());
		
		request.setAttribute("message", "刪除成功");
		request.getRequestDispatcher("/admin-pages/member-list").forward(request, response);
	}

}
