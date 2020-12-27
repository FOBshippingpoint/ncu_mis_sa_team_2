package ncu.im3069.demo.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ncu.im3069.demo.util.DBMgr;

public class SeatHelper extends Helper {
	
	private static SeatHelper seatHelper = null;

	private SeatHelper() {

	}

	public static SeatHelper getHelper() {
		/** Singleton檢查是否已經有Helper物件，若無則new一個，若有則直接回傳 */
		if (seatHelper == null)
			seatHelper = new SeatHelper();
		return seatHelper;
	}
	
	public void getSeatsAvailable() {
		
	}
	
	
	public ArrayList<String> getHallNames() {
		ArrayList<String> hallNames = new ArrayList<>();

		String exexcute_sql = "";
		ResultSet rs = null;
		int row = -1;
		try {
			/** 取得資料庫之連線 */
			conn = DBMgr.getConnection();
			/** SQL指令 */
			String sql = "SELECT * FROM `halls`";

			/** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
			pres = conn.prepareStatement(sql);
			/** 執行查詢之SQL指令並記錄其回傳之資料 */
			rs = pres.executeQuery();

			/** 紀錄真實執行的SQL指令，並印出 **/
			exexcute_sql = pres.toString();
			System.out.println("from HallHelper#getHallNames: " + exexcute_sql);

			/** 透過 while 迴圈移動pointer，取得每一筆回傳資料 */
			while (rs.next()) {
				/** 每執行一次迴圈表示有一筆資料 */
				row += 1;

				String name = rs.getString("name");
				hallNames.add(name);

			}

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
		return hallNames;
	}
	
	public Hall getHallByName(String name) {
		Hall result = null;
		
		String exexcute_sql = "";
		ResultSet rs = null;
		try {
			/** 取得資料庫之連線 */
			conn = DBMgr.getConnection();
			/** SQL指令 */
			String sql = "SELECT * FROM `halls` WHERE `name` = ? LIMIT 1";

			/** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
			pres = conn.prepareStatement(sql);
			/** 執行查詢之SQL指令並記錄其回傳之資料 */
			pres.setString(1, name);
			rs = pres.executeQuery();

			/** 紀錄真實執行的SQL指令，並印出 **/
			exexcute_sql = pres.toString();
			System.out.println("HallHelper#getHallByName: " + exexcute_sql);

            if(rs.next()) {
            	int hallId = rs.getInt("id");
            	String hallName = rs.getString("name");
            	
            	result = new Hall(hallId, hallName);
            }

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
		
		return result;
	}

}
