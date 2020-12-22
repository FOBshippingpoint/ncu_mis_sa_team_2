package ncu.im3069.demo.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ncu.im3069.demo.app.AddMovieHelper;

@WebServlet("/api/add-movie.do")
@MultipartConfig
public class AddMovieController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AddMovieHelper amh = (AddMovieHelper) AddMovieHelper.getHelper();

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
		int length = Integer.parseInt(request.getParameter("length"));
		String version = request.getParameter("version");
		Date startDate = null;
		Date endDate = null;

		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("start-date"));
			endDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("end-date"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String hall = request.getParameter("hall");
		
		System.out.println(title);
		System.out.println(introduction);
		System.out.println(length);
		System.out.println(version);
		System.out.println(startDate);
		System.out.println(endDate);
		System.out.println(hall);
		
		/** WEB-INF/web.xml param: NCU_MIS_SA */
		amh.saveImage(request, "cover", new File(getServletContext().getInitParameter("NCU_MIS_SA")).getAbsolutePath() + "/images/");

		/** 動作結束後跳轉網頁 */
		String destPage = "/NCU_MIS_SA/admin-pages/add-movie";
		

//		RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
//		dispatcher.forward(request, response);
	}
}
