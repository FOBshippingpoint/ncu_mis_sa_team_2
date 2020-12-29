package ncu.im3069.demo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import ncu.im3069.demo.app.LoginHelper;
import ncu.im3069.demo.app.Member;
import ncu.im3069.tools.JsonReader;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LoginHelper lh = (LoginHelper) LoginHelper.getHelper();
	 /**
     * 處理Http Method請求GET方法（取得資料）
     *
     * @param request Servlet請求之HttpServletRequest之Request物件（前端到後端）
     * @param response Servlet回傳之HttpServletResponse之Response物件（後端到前端）
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    		
    	 HttpSession session = request.getSession(false);
         if (session != null) {
             session.removeAttribute("member");
             request.getRequestDispatcher("/home.jsp").forward(request, response);;
         }
    }
}
