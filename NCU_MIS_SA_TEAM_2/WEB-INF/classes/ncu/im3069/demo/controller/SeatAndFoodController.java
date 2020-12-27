package ncu.im3069.demo.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ncu.im3069.demo.app.HallHelper;
import ncu.im3069.demo.app.Seat;

@WebServlet("/member-pages/seat-and-food")
public class SeatAndFoodController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setAttribute("rowNum", HallHelper.getHelper().getSeatsRowNum());
//		req.setAttribute("colNum", HallHelper.getHelper().getSeatsColNum());
//
//		req.getRequestDispatcher("member-pages/select-seat.jsp").forward(req, resp);
//	}

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
		ArrayList<Seat> selectedSeats = new ArrayList<>();
		for(int r=1; r<=HallHelper.getHelper().getSeatsRowNum(); r++) {
			for(int c=1; c<=HallHelper.getHelper().getSeatsColNum(); c++) {
				System.out.println(request.getParameter(r+"-"+c));
				if(request.getParameter(r+"-"+c)!=null) {
					selectedSeats.add(new Seat(r, c));
				}
			}
		}
		
		
		

		/** 動作結束後跳轉網頁 */
//		String destPage = "/member-pages/select-seat";
//		request.setAttribute("message", "已新增…");
//
//		RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
//		dispatcher.forward(request, response);
	}

}
