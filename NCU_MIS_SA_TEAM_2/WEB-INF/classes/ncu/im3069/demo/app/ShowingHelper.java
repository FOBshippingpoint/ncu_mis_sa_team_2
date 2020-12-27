package ncu.im3069.demo.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import ncu.im3069.demo.util.DBMgr;
import ncu.im3069.demo.util.TimeUtil;

public class ShowingHelper extends Helper {

	private static ShowingHelper showingHelper = null;

	private ShowingHelper() {

	}

	public static ShowingHelper getHelper() {
		/** Singleton檢查是否已經有Helper物件，若無則new一個，若有則直接回傳 */
		if (showingHelper == null)
			showingHelper = new ShowingHelper();

		return showingHelper;
	}

	public ArrayList<Showing> getShowingsByMovie(Movie movie) {
		ArrayList<Showing> showings = new ArrayList<>();

		String exexcute_sql = "";
		ResultSet rs = null;

		try {
			/** 取得資料庫之連線 */
			conn = DBMgr.getConnection();
			/** SQL指令 */
			String sql = "SELECT * FROM `showings` WHERE `movie_id` = ? ORDER BY `showings`.`start` ASC";

			/** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
			pres = conn.prepareStatement(sql);
			/** 執行查詢之SQL指令並記錄其回傳之資料 */
			pres.setInt(1, movie.getId());
			rs = pres.executeQuery();

			/** 紀錄真實執行的SQL指令，並印出 **/
			exexcute_sql = pres.toString();
			System.out.println(Thread.currentThread().getStackTrace() + ": " + exexcute_sql);

			/** 透過 while 迴圈移動pointer，取得每一筆回傳資料 */
			while (rs.next()) {
				/** 每執行一次迴圈表示有一筆資料 */
				int id = rs.getInt("id");
				int movieId = rs.getInt("movie_id");
				int hallId = rs.getInt("hall_id");
				LocalDateTime start = TimeUtil.toLocalDateTimeFromString(rs.getString("start"));
				LocalDateTime end = TimeUtil.toLocalDateTimeFromString(rs.getString("end"));

				Showing showing = new Showing(id, movieId, hallId, start, end);
				showings.add(showing);
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

		return showings;
	}

	public ArrayList<Showing> filterShowingWithDate(ArrayList<Showing> showings, LocalDate date) {
		showings.removeIf(showing -> showing.getStart().toLocalDate().equals(date));
		return showings;
	}

	public void create(Showing showing) {
		/** 記錄實際執行之SQL指令 */
		String exexcute_sql = "";
		int resultId = -1;

		try {
			/** 取得資料庫之連線 */
			conn = DBMgr.getConnection();
			/** SQL指令 */
			String sql = "INSERT INTO `showings` " + "(`id`, `movie_id`, `hall_id`, `start`, `end`) "
					+ "VALUES (NULL, ?, ?, ?, ?);";

			/** 取得所需之參數 */
			int movieId = showing.getMovieId();
			int hallId = showing.getHallId();
			String start = showing.getStartString();
			String end = showing.getEndString();

			/** 將參數回填至SQL指令當中 */
			pres = conn.prepareStatement(sql);
			pres.setInt(1, movieId);
			pres.setInt(2, hallId);
			pres.setString(3, start);
			pres.setString(4, end);

			int affectedRows = pres.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating user failed, no rows affected.");
			}

			/** 紀錄真實執行的SQL指令，並印出 **/
			exexcute_sql = pres.toString();
			System.out.println("ShowingHelper#create: " + pres);

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
	
	public Showing getShowingById(int showingId) {
		Showing showing = null;

		String exexcute_sql = "";
		ResultSet rs = null;
		
		try {
			/** 取得資料庫之連線 */
			conn = DBMgr.getConnection();
			/** SQL指令 */
			String sql = "SELECT * FROM `showings` WHERE `id` = ? LIMIT 1";

			/** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
			pres = conn.prepareStatement(sql);
			/** 執行查詢之SQL指令並記錄其回傳之資料 */
			pres.setInt(1, showingId);
			rs = pres.executeQuery();

			/** 紀錄真實執行的SQL指令，並印出 **/
			exexcute_sql = pres.toString();
			System.out.println(Thread.currentThread().getStackTrace() + " " + exexcute_sql);

			if (rs.next()) {
				int id = rs.getInt("id");
				int movieId = rs.getInt("movie_id");
				int hallId = rs.getInt("hall_id");
				LocalDateTime start = TimeUtil.toLocalDateTimeFromString(rs.getString("start"));
				LocalDateTime end = TimeUtil.toLocalDateTimeFromString(rs.getString("end"));

				showing = new Showing(id, movieId, hallId, start, end);
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
		
		return showing;
	}
	
}
