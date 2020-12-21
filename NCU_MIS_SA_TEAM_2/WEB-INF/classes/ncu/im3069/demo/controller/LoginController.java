package ncu.im3069.demo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ncu.im3069.demo.app.LoginHelper;
import ncu.im3069.demo.app.Member;

@WebServlet("/api/login.do")
public class LoginController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LoginHelper lh = (LoginHelper) LoginHelper.getHelper();

	/**
     * 處理 Http Method 請求 POST 方法（新增資料）
     *
     * @param request Servlet 請求之 HttpServletRequest 之 Request 物件（前端到後端）
     * @param response Servlet 回傳之 HttpServletResponse 之 Response 物件（後端到前端）
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		/** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
//		JsonReader jsr = new JsonReader(request);
//		JSONObject jso = jsr.getObject();
//
//		/** 取出經解析到JSONObject之Request參數 */
//		String email = jso.getString("email");
//		String password = jso.getString("password");
		
		/** 使用request取得email&password */
		String email = request.getParameter("email");
		String password = request.getParameter("password");

			/** 驗證使用者，詳見LoginHelper#validate */
			Member member = lh.validate(email, password);
			/** 動作結束後跳轉網頁 */
			String destPage;
			/** 登入成功 */
			if (member != null) {
				/** 設定session */
				HttpSession session = request.getSession();
				session.setAttribute("member", member);
				request.setAttribute("memberIsAdmin", member.isAdmin());
				destPage = "/home.jsp";
			}
			/** 登入失敗 */
			else {
				String messageString = "登入失敗！帳號密碼錯誤";
				request.setAttribute("message", messageString);
				destPage = "/login.jsp";
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
			dispatcher.forward(request, response);
//		/** 後端檢查是否有欄位為空值，若有則回傳錯誤訊息 */
//		if (email.isEmpty() || password.isEmpty()) {
//			/** 以字串組出JSON格式之資料 */
//			String resp = "{\"status\": \'400\', \"message\": \'欄位不能有空值\', \'response\': \'\'}";
//			/** 透過JsonReader物件回傳到前端（以字串方式） */
//			jsr.response(resp, response);
//		} else {
//			/** 驗證使用者 */
//			Member member = lh.validate(email, password);
//			/** 登入成功 */
//			if (member != null) {
//				Cookie cookie = new Cookie("memberName", member.getName());
//				cookie.setMaxAge(60 * 60 * 24);
//				cookie.setPath("/");
//				response.addCookie(cookie);
//				
//				System.out.println(member.isAdmin());
//				Cookie cookie1 = new Cookie("memberIsAdmin", Boolean.toString(member.isAdmin()));
//				cookie1.setMaxAge(60 * 60 * 24);
//				cookie1.setPath("/");
//				response.addCookie(cookie1);
//				
//				String resp = "{status: '200', " + "message: '登入成功', " + "data: {email:" + member.getEmail() + "}}";
//				jsr.response(resp, response);
//			}
//			/** 登入失敗 */
//			else {
//				String resp = "{status: '401', " + "message: '登入失敗', }";
//				
//				/** 透過JsonReader物件回傳到前端（以JSONObject方式） */
//				jsr.response(resp, response);
//			}
//		}

	}
}
