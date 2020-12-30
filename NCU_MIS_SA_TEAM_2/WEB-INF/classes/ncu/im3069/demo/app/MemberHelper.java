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

public class MemberHelper extends Helper {

	private static MemberHelper helper = null;

	private MemberHelper() {

	}

	public static MemberHelper getHelper() {
		if (helper == null)
			helper = new MemberHelper();

		return helper;
	}

	public Member getMemberById(int searchId) {
		Member member = null;

		String exexcute_sql = "";
		ResultSet rs = null;

		try {
			/** 取得資料庫之連線 */
			conn = DBMgr.getConnection();
			/** SQL指令 */
			String sql = "SELECT * FROM `members` WHERE `id` = ? LIMIT 1";

			/** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
			pres = conn.prepareStatement(sql);
			/** 執行查詢之SQL指令並記錄其回傳之資料 */
			pres.setInt(1, searchId);
			rs = pres.executeQuery();

			/** 紀錄真實執行的SQL指令，並印出 **/
			exexcute_sql = pres.toString();
			System.out.println(Thread.currentThread().getStackTrace() + " " + exexcute_sql);

			if (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				boolean isAdmin= rs.getBoolean("is_admin");
				
				member = new Member(searchId, name, email, password, isAdmin);
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

		return member;
	}

	/**
	 * 建立該名會員至資料庫
	 *
	 * @param m 一名會員之Member物件
	 * @return the JSON object 回傳SQL指令執行之結果
	 */
	public int create(Member member) {
		/** 記錄實際執行之SQL指令 */
		String exexcute_sql = "";
		int resultId = -1;

		try {
			/** 取得資料庫之連線 */
			conn = DBMgr.getConnection();
			/** SQL指令 */
			String sql = "INSERT INTO `members` (`id`, `name`, `email`, `password`, `is_admin`) "
					+ "VALUES (NULL, ?, ?, ?, ?);";

			/** 取得所需之參數 */
			String name = member.getName();
			String email = member.getEmail();
			String password = member.getPassword();
			boolean isAdmin = member.isAdmin();

			/** 將參數回填至SQL指令當中 */
			pres = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pres.setString(1, name);
			pres.setString(2, email);
			pres.setString(3, password);
			pres.setBoolean(4, isAdmin);

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
	
	public void update(Member member) {
		/** 記錄實際執行之SQL指令 */
		String exexcute_sql = "";

		try {
			/** 取得資料庫之連線 */
			conn = DBMgr.getConnection();
			/** SQL指令 */
			String sql = "UPDATE `members` SET "
					+ "`name` = ?, "
					+ "`email` = ?, "
					+ "`password` = ?, "
					+ "`is_admin` = ? "
					+ "WHERE `members`.`id` = ?;";

			String name = member.getName();
			String email = member.getEmail();
			String password = member.getPassword();
			boolean isAdmin = member.isAdmin();
			int id = member.getId();
			
			pres = conn.prepareStatement(sql);
			pres.setString(1, name);
			pres.setString(2, email);
			pres.setString(3, password);
			pres.setBoolean(4, isAdmin);
			pres.setInt(5, id);
			
			int affectedRows = pres.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating user failed, no rows affected.");
			}

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
	
	public void delete(int memberId) {
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "DELETE FROM `members` WHERE `members`.`id` = ?";
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, memberId);
            
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

	public ArrayList<Member> getMembers() {
		ArrayList<Member> members = new ArrayList<>();

		/** 記錄實際執行之SQL指令 */
		String exexcute_sql = "";
		ResultSet rs;

		try {
			/** 取得資料庫之連線 */
			conn = DBMgr.getConnection();
			/** SQL指令 */
			String sql = "SELECT * FROM `Members`";

			/** 將參數回填至SQL指令當中 */
			pres = conn.prepareStatement(sql);

			rs = pres.executeQuery();

			/** 紀錄真實執行的SQL指令，並印出 **/
			exexcute_sql = pres.toString();
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ": " + exexcute_sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				boolean isAdmin= rs.getBoolean("is_admin");
				
				Member member = new Member(id, name, email, password, isAdmin);
				members.add(member);
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

		return members;
	}

}
