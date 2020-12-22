package ncu.im3069.demo.app;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import ncu.im3069.demo.util.DBMgr;

public class AddMovieHelper extends Helper {

	private AddMovieHelper() {

	}

	public static AddMovieHelper getHelper() {
		/** Singleton檢查是否已經有Helper物件，若無則new一個，若有則直接回傳 */
		if (helper == null)
			helper = new AddMovieHelper();

		return (AddMovieHelper) helper;
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

}
