package ncu.im3069.demo.app;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

import ncu.im3069.demo.util.DBMgr;

public class LoginHelper extends Helper{
	
	private LoginHelper() {
		
	}
	
	public static LoginHelper getHelper() {
      /** Singleton檢查是否已經有Helper物件，若無則new一個，若有則直接回傳 */
      if(helper == null) helper = new LoginHelper();
      
      return (LoginHelper) helper;
	}
	
	public Member validate(String email, String password) {
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        ResultSet rs = null;
        
        boolean status = false;
        
        Member member = null;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            
            /** SQL指令 */
            String sql = "SELECT * FROM `members` WHERE `email` = ? AND `password` = ? LIMIT 1";
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setString(1, email);
            pres.setString(2, password);
            /** 執行刪除之SQL指令並記錄影響之行數 */
            rs = pres.executeQuery();
            status = rs.next();
            if(status) {
            	String nameString = rs.getString("name");
            	String emailString = rs.getString("email");
            	String passwordString = rs.getString("password");
            	
            	member = new Member(emailString, passwordString, nameString);
            }

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(rs, pres, conn);
        }

        return member;
	}
}
