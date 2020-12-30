package ncu.im3069.demo.app;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import ncu.im3069.demo.util.DBMgr;
import ncu.im3069.demo.util.TimeUtil;

public class MovieHelper extends Helper {

	private static MovieHelper addMovieHelper = null;

	private MovieHelper() {

	}

	public static MovieHelper getHelper() {
		/** Singleton檢查是否已經有Helper物件，若無則new一個，若有則直接回傳 */
		if (addMovieHelper == null)
			addMovieHelper = new MovieHelper();

		return addMovieHelper;
	}

	public Movie getMovieById(int searchId) {
		Movie movie = null;

		String exexcute_sql = "";
		ResultSet rs = null;

		try {
			/** 取得資料庫之連線 */
			conn = DBMgr.getConnection();
			/** SQL指令 */
			String sql = "SELECT * FROM `movies` WHERE `id` = ? LIMIT 1";

			/** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
			pres = conn.prepareStatement(sql);
			/** 執行查詢之SQL指令並記錄其回傳之資料 */
			pres.setInt(1, searchId);
			rs = pres.executeQuery();

			/** 紀錄真實執行的SQL指令，並印出 **/
			exexcute_sql = pres.toString();
			System.out.println(Thread.currentThread().getStackTrace() + " " + exexcute_sql);

			if (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String introduction = rs.getString("introduction");
				int rating = rs.getInt("rating");
				String version = rs.getString("version");
				int price = rs.getInt("price");
				int length = rs.getInt("length");
				LocalDate onDate = rs.getDate("on_date").toLocalDate();
				LocalDate offDate = rs.getDate("off_date").toLocalDate();

				movie = new Movie(id, title, introduction, rating, version, price, length, onDate, offDate);
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

		return movie;
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
			System.out.println("from AddMovieHelper#getHallNames: " + exexcute_sql);

			/** 透過 while 迴圈移動pointer，取得每一筆回傳資料 */
			while (rs.next()) {
				/** 每執行一次迴圈表示有一筆資料 */
				row += 1;

				String name = rs.getString("name");
				hallNames.add(name);

//				/** 將 ResultSet 之資料取出 */
//				int id = rs.getInt("id");
//				String title = rs.getString("title");
//				String introduction = rs.getString("introduction");
//				int rating = rs.getInt("rating");
//				String version = rs.getString("version");
//				int price = rs.getInt("price");
//				int length = rs.getInt("length");
//				LocalDate onDate = rs.getDate("on-date").toLocalDate();
//				LocalDate offDate = rs.getDate("off-date").toLocalDate();
//
//				Movie movie = new Movie(id, title, introduction, rating, version, price, length, onDate, offDate);

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

//	https://happycoding.io/tutorials/java-server/uploading-files
	public void saveImage(HttpServletRequest request, String imgTagName, String destinationPath, int movieId)
			throws IOException {
		Part filePart = null;
		try {
			filePart = request.getPart(imgTagName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		filePart.write(destinationPath + movieId + getFileExtension(filePart.getSubmittedFileName()));
	}

	private String getFileExtension(String name) {
		int lastIndexOf = name.lastIndexOf(".");
		if (lastIndexOf == -1) {
			return ""; // empty extension
		}
		return name.substring(lastIndexOf);
	}

	/**
	 * 建立該名會員至資料庫
	 *
	 * @param m 一名會員之Member物件
	 * @return the JSON object 回傳SQL指令執行之結果
	 */
	public int create(Movie movie) {
		/** 記錄實際執行之SQL指令 */
		String exexcute_sql = "";
		int resultId = -1;

		try {
			/** 取得資料庫之連線 */
			conn = DBMgr.getConnection();
			/** SQL指令 */
			String sql = "INSERT INTO `movies` "
					+ "(`id`, `title`, `introduction`, `rating`, `version`, `price`, `length`, `on_date`, `off_date`) "
					+ "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?);";

			/** 取得所需之參數 */
			String title = movie.getTitle();
			String introduction = movie.getIntroduction();
			int rating = movie.getRating();
			String version = movie.getVersion();
			int price = movie.getPrice();
			int length = movie.getLength();
			LocalDate onDate = movie.getOnDate();
			LocalDate offDate = movie.getOffDate();

			/** 將參數回填至SQL指令當中 */
			pres = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pres.setString(1, title);
			pres.setString(2, introduction);
			pres.setInt(3, rating);
			pres.setString(4, version);
			pres.setInt(5, price);
			pres.setInt(6, length);
			pres.setObject(7, onDate);
			pres.setObject(8, offDate);

			int affectedRows = pres.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating user failed, no rows affected.");
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
			System.out.println(pres);

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
	
	public void update(Movie movie) {
		/** 記錄實際執行之SQL指令 */
		String exexcute_sql = "";

		try {
			/** 取得資料庫之連線 */
			conn = DBMgr.getConnection();
			/** SQL指令 */
			String sql = "UPDATE `movies` SET "
					+ "`title` = ?, `introduction` = ?, `rating` = ?, "
					+ "`version` = ?, `price` = ?, `length` = ?, "
					+ "`on_date` = ?, `off_date` = ? WHERE `movies`.`id` = ?;";

			/** 取得所需之參數 */
			String title = movie.getTitle();
			String introduction = movie.getIntroduction();
			int rating = movie.getRating();
			String version = movie.getVersion();
			int price = movie.getPrice();
			int length = movie.getLength();
			LocalDate onDate = movie.getOnDate();
			LocalDate offDate = movie.getOffDate();

			/** 將參數回填至SQL指令當中 */
			pres = conn.prepareStatement(sql);
			pres.setString(1, title);
			pres.setString(2, introduction);
			pres.setInt(3, rating);
			pres.setString(4, version);
			pres.setInt(5, price);
			pres.setInt(6, length);
			pres.setObject(7, onDate);
			pres.setObject(8, offDate);
			pres.setInt(9, movie.getId());

			int affectedRows = pres.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating user failed, no rows affected.");
			}

			/** 紀錄真實執行的SQL指令，並印出 **/
			exexcute_sql = pres.toString();
			System.out.println(pres);

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
	
	public void delete(int movieId) {
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "DELETE FROM `movies` WHERE `movies`.`id` = ?";
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, movieId);
            
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

	public void setShowingOfMovie(Movie movie, Hall hall) {
		int length = movie.getLength();
		int movieId = movie.getId();
		int hallId = hall.getId();
		Showing showing = new Showing(movieId, hallId, LocalDateTime.now(), LocalDateTime.now());

		LocalDate onDate = movie.getOnDate();
		LocalDate offDate = movie.getOffDate();
		LocalDate processDate = onDate;
		final int breakTimeMinutes = 30;
		for (int i = 0; i < ChronoUnit.DAYS.between(onDate, offDate); i++) {
			LocalDateTime dateTime = LocalDateTime.of(processDate, LocalTime.of(10, 0));
			while (dateTime.plusMinutes(length + breakTimeMinutes)
					.isBefore(LocalDateTime.of(processDate, LocalTime.of(23, 59, 59)))) {
				showing.setStart(dateTime);
				dateTime = dateTime.plusMinutes(length);
				showing.setEnd(dateTime);
				dateTime = dateTime.plusMinutes(breakTimeMinutes);
//				System.out.println(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				ShowingHelper.getHelper().create(showing);
			}
			processDate = processDate.plusDays(1);
		}

	}

	public ArrayList<Movie> getMovies() {
		ArrayList<Movie> movies = new ArrayList<>();

		/** 記錄實際執行之SQL指令 */
		String exexcute_sql = "";
		ResultSet rs;

		try {
			/** 取得資料庫之連線 */
			conn = DBMgr.getConnection();
			/** SQL指令 */
			String sql = "SELECT * FROM `movies`";

			/** 將參數回填至SQL指令當中 */
			pres = conn.prepareStatement(sql);

			rs = pres.executeQuery();

			/** 紀錄真實執行的SQL指令，並印出 **/
			exexcute_sql = pres.toString();
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ": " + exexcute_sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String introduction = rs.getString("introduction");
				int rating = rs.getInt("rating");
				String version = rs.getString("version");
				int price = rs.getInt("price");
				int length = rs.getInt("length");
				LocalDate onDate = rs.getDate("on_date").toLocalDate();
				LocalDate offDate = rs.getDate("off_date").toLocalDate();

				Movie movie = new Movie(id, title, introduction, rating, version, price, length, onDate, offDate);
				movies.add(movie);
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

		return movies;
	}

}
