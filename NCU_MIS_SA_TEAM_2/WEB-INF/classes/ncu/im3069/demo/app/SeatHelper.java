package ncu.im3069.demo.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
	
	public Seat getSeatByRowColNum(int rowNum, int colNum) {
		Seat seat = null;

		String exexcute_sql = "";
		ResultSet rs = null;
		
		try {
			/** 取得資料庫之連線 */
			conn = DBMgr.getConnection();
			/** SQL指令 */
			String sql = "SELECT * FROM `seats` WHERE `row_num` = ? AND `col_num` = ?";

			/** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
			pres = conn.prepareStatement(sql);
			/** 執行查詢之SQL指令並記錄其回傳之資料 */
			pres.setInt(1, rowNum);
			pres.setInt(2, colNum);
			rs = pres.executeQuery();

			/** 紀錄真實執行的SQL指令，並印出 **/
			exexcute_sql = pres.toString();
			System.out.println(Thread.currentThread().getStackTrace() + " " + exexcute_sql);

			if (rs.next()) {
				seat = new Seat(rs.getInt("id"), rowNum, colNum);
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
		
		return seat;
	}
	
}
