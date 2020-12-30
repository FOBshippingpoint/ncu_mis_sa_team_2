package ncu.im3069.demo.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import ncu.im3069.demo.util.DBMgr;
import ncu.im3069.demo.util.TimeUtil;

public class OrderHelper extends Helper {

	private static OrderHelper ticketHelper = null;

	private OrderHelper() {

	}

	public static OrderHelper getHelper() {
		/** Singleton檢查是否已經有Helper物件，若無則new一個，若有則直接回傳 */
		if (ticketHelper == null)
			ticketHelper = new OrderHelper();

		return ticketHelper;
	}

	public int create(Order order) {
		/** 記錄實際執行之SQL指令 */
		String exexcute_sql = "";
		int resultId = -1;

		try {
			/** 取得資料庫之連線 */
			conn = DBMgr.getConnection();
			/** SQL指令 */
			String sql = "INSERT INTO `orders` (`id`, `member_id`, `purchased`, `canceled`) "
					+ "VALUES (NULL, ?, ?, NULL);";

			/** 取得所需之參數 */
			int memberId = order.getMemberId();
			LocalDateTime purchased = order.getPurchased();

			/** 將參數回填至SQL指令當中 */
			pres = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pres.setInt(1, memberId);
			pres.setString(2, TimeUtil.format(purchased));

			int affectedRows = pres.executeUpdate();
			
			if (affectedRows == 0) {
				throw new SQLException("Creating order failed, no rows affected.");
			}
			
			try (ResultSet generatedKeys = pres.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					resultId = generatedKeys.getInt(1);
				} else {
					throw new SQLException("Creating user failed, no ID obtained.");
				}
			}

			/** 紀錄真實執行的SQL指令，並印出 **/
			exexcute_sql = pres.toString();
			System.out.println(Thread.currentThread().getStackTrace() + ": " + pres);

		} catch (SQLException e) {
			/** 印出JDBC SQL指令錯誤 **/
			System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			/** 若錯誤則印出錯誤訊息 */
			e.printStackTrace();
		} finally {
			/** 關閉連線並釋放所有資料庫相關之資源 **/
			DBMgr.close(pres, conn);
		}
		
		return resultId;
	}
	
	public ArrayList<Order> getOrders() {
		ArrayList<Order> orders = new ArrayList<>();

		/** 記錄實際執行之SQL指令 */
		String exexcute_sql = "";
		ResultSet rs;

		try {
			/** 取得資料庫之連線 */
			conn = DBMgr.getConnection();
			/** SQL指令 */
			String sql = "SELECT * FROM `orders`";

			/** 將參數回填至SQL指令當中 */
			pres = conn.prepareStatement(sql);

			rs = pres.executeQuery();

			/** 紀錄真實執行的SQL指令，並印出 **/
			exexcute_sql = pres.toString();
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ": " + exexcute_sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				int memberId = rs.getInt("member_id");
				LocalDateTime purchased = TimeUtil.toLocalDateTimeFromString(rs.getString("purchased"));
				LocalDateTime canceled = TimeUtil.toLocalDateTimeFromString(rs.getString("canceled"));

				Order order = new Order(id, memberId, purchased, canceled);
				orders.add(order);
			}

		} catch (SQLException e) {
			/** 印出JDBC SQL指令錯誤 **/
			System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			/** 若錯誤則印出錯誤訊息 */
			e.printStackTrace();
		} finally {
			/** 關閉連線並釋放所有資料庫相關之資源 **/
			DBMgr.close(pres, conn);
		}

		return orders;
	}
	
	public void cancel(int orderId) {
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "UPDATE `orders` SET `canceled` = ? WHERE `orders`.`id` = ?;";
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setString(1, TimeUtil.format(LocalDateTime.now()));
            pres.setInt(2, orderId);
            
            pres.executeUpdate();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ": " + exexcute_sql);

        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(pres, conn);
        }
    }

}
