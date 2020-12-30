package ncu.im3069.demo.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import ncu.im3069.demo.util.DBMgr;

public class TicketHelper extends Helper {

	private static TicketHelper ticketHelper = null;

	private TicketHelper() {

	}

	public static TicketHelper getHelper() {
		/** Singleton檢查是否已經有Helper物件，若無則new一個，若有則直接回傳 */
		if (ticketHelper == null)
			ticketHelper = new TicketHelper();

		return ticketHelper;
	}

	public void create(Ticket ticket) {
		/** 記錄實際執行之SQL指令 */
		String exexcute_sql = "";
		int resultId = -1;

		try {
			/** 取得資料庫之連線 */
			conn = DBMgr.getConnection();
			/** SQL指令 */
			String sql = "INSERT INTO `tickets` (`id`, `seat_id`, `showing_id`, `order_id`) "
					+ "VALUES (NULL, ?, ?, ?);";

			/** 取得所需之參數 */
			int seatId = ticket.getSeatId();
			int showingId = ticket.getShowingId();
			int orderId = ticket.getOrderId();

			/** 將參數回填至SQL指令當中 */
			pres = conn.prepareStatement(sql);
			pres.setInt(1, seatId);
			pres.setInt(2, showingId);
			pres.setInt(3, orderId);

			int affectedRows = pres.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating user failed, no rows affected.");
			}

			/** 紀錄真實執行的SQL指令，並印出 **/
			exexcute_sql = pres.toString();
			System.out.println(Thread.currentThread().getStackTrace() + ": " + exexcute_sql);

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
	
	public ArrayList<Seat> getSeatsAvailableByShowing(Showing showing){
		return getSeatsAvailableByShowingId(showing.getId());
	}
	
	public ArrayList<Seat> getSeatsAvailableByShowingId(int showingId){
		ArrayList<Seat> seats = HallHelper.getHelper().getSeats();
		
		/** 記錄實際執行之SQL指令 */
		String exexcute_sql = "";
		ResultSet rs;

		try {
			/** 取得資料庫之連線 */
			conn = DBMgr.getConnection();
			/** SQL指令 */
			String sql = "SELECT * FROM `tickets` WHERE `showing_id` = ?";

			/** 將參數回填至SQL指令當中 */
			pres = conn.prepareStatement(sql);
			pres.setInt(1, showingId);

			rs = pres.executeQuery();
			
			/** 紀錄真實執行的SQL指令，並印出 **/
			exexcute_sql = pres.toString();
			System.out.println(Thread.currentThread().getStackTrace() + ": " + exexcute_sql);
			
			while(rs.next()) {
				int seatId = rs.getInt("seat_id");
				seats.removeIf(seat -> seat.getId() == seatId);
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
		
		return seats;
	}
	
	public ArrayList<Ticket> getTicketsByOrderId(int orderId) {
		ArrayList<Ticket> tickets = new ArrayList<>();

		String exexcute_sql = "";
		ResultSet rs = null;

		try {
			/** 取得資料庫之連線 */
			conn = DBMgr.getConnection();
			/** SQL指令 */
			String sql = "SELECT * FROM `tickets` WHERE `order_id` = ?";

			/** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
			pres = conn.prepareStatement(sql);
			/** 執行查詢之SQL指令並記錄其回傳之資料 */
			pres.setInt(1, orderId);
			rs = pres.executeQuery();

			/** 紀錄真實執行的SQL指令，並印出 **/
			exexcute_sql = pres.toString();
			System.out.println(Thread.currentThread().getStackTrace() + " " + exexcute_sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				int seatId = rs.getInt("seat_id");
				int showingId = rs.getInt("showing_id");

				Ticket ticket = new Ticket(id, seatId, showingId, orderId);
				tickets.add(ticket);
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

		return tickets;
	}
	
	public ArrayList<Seat> getSeatsByTickets(ArrayList<Ticket> tickets) {
		ArrayList<Seat> seats = new ArrayList<>();
		
		for(Ticket t: tickets) {
			seats.add(SeatHelper.getHelper().getSeatById(t.getSeatId()));
		}
		
		return seats;
	}

}
