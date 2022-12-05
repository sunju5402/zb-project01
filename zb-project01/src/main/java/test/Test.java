package test;
import java.sql.*;
import java.util.*;

public class Test {

	// test용
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = null;
//		Statement stmt = null;
		PreparedStatement ps = null;
//		ResultSet rs = null;
		
		String dbFileUrl = "jdbc:sqlite:sqlite.db";
		
		try {
			Class.forName("org.sqlite.JDBC");
//			con = DriverManager.getConnection(dbFileUrl);
			System.out.println("SQLite DB conneted");
//			
//			stmt = con.createStatement();
////			rs = stmt.executeQuery("select * from test;");
//			String sql = " INSERT INTO wifi_info(X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, "
//					+  "X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, "
//					+  "X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, "
//					+  "X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, "
//					+  "X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) "
//					+  " VALUES "
//					+  " ( "
//					+  " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
//					+  " ) ";
//			
//			ps = con.prepareStatement(sql);
//			ps.setString(1, "d2");
//			ps.setString(2, "dd");
//			ps.setString(3, "dd");
//			ps.setString(4, "dd");
//			ps.setString(5, "dd");
//			ps.setString(6, "dd");
//			ps.setString(7, "dd");
//			ps.setString(8, "dd");
//			ps.setString(9, "dd");
//			ps.setString(10, "dd");
//			ps.setString(11, "dd");
//			ps.setString(12, "dd");
//			ps.setString(13, "dd");
//			ps.setDouble(14, 22.2);
//			ps.setDouble(15, 22.2);
//			ps.setString(16, "dd");
//			ps.executeUpdate();
//			stmt.executeUpdate("insert into wifi_info(LAT, LNT) values(22.9, 22.9)");
		} catch(Exception e) {
			e.getStackTrace();
		}
		
		try {
			con = DriverManager.getConnection(dbFileUrl);
			
//			stmt = con.createStatement();
//			rs = stmt.executeQuery("select * from test;");
			String sql = " INSERT INTO wifi_info(X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, "
					+  "X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, "
					+  "X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, "
					+  "X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, "
					+  "X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) "
					+  " VALUES "
					+  " ( "
					+  " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
					+  " ) ";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, "d4");
			ps.setString(2, "dd");
			ps.setString(3, "dd");
			ps.setString(4, "dd");
			ps.setString(5, "dd");
			ps.setString(6, "dd");
			ps.setString(7, "dd");
			ps.setString(8, "dd");
			ps.setString(9, "dd");
			ps.setString(10, "dd");
			ps.setString(11, "dd");
			ps.setString(12, "dd");
			ps.setString(13, "dd");
			ps.setDouble(14, 22.2);
			ps.setDouble(15, 22.2);
			ps.setString(16, "dd");
			ps.executeUpdate();

		} catch (Exception e) {
//			cs.rollback(conn);
			System.out.println("저장 오류");
			e.getStackTrace();
		} finally {
			try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
			
			try {
                if (con != null && !con.isClosed()) {
                	con.isClosed();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
		}
		System.out.println("dkdkdk");
	}

}