package service;

import dto.WifiInfo;
import java.sql.*;
import java.util.*;

public class WifiInfoService {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private ConnectionService cs = new ConnectionService();
		
	 public List<WifiInfo> nearWifiInfo(double x, double y) {
		WifiInfo wifi = null;
		List<WifiInfo> list = new ArrayList<>();
		
		try {
			conn = cs.getConnection(conn);
			
			String sql = " SELECT *, " 
					  + " round(6371*acos(cos(radians(?))*cos(radians(LAT))*cos(radians(LNT)-radians(?))+sin(radians(?))*sin(radians(LAT))),4) as DISTANCE " 
					  + " FROM wifi_info " 
					  + " ORDER BY DISTANCE ASC "
					  + " LIMIT 20 ";
			
			ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, x);
			ps.setDouble(2, y);
			ps.setDouble(3, x);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				wifi = new WifiInfo();
				wifi.setDistance(rs.getDouble("DISTANCE"));
				wifi.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
				wifi.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
				wifi.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
				wifi.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
				wifi.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
				wifi.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
				wifi.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
				wifi.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
				wifi.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
				wifi.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
				wifi.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
				wifi.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
				wifi.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
				wifi.setLAT(rs.getDouble("LAT"));
				wifi.setLNT(rs.getDouble("LNT"));
				wifi.setWORK_DTTM(rs.getString("WORK_DTTM"));
				
				list.add(wifi);
			}
		} catch (SQLException e) {
			cs.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            
            cs.closeConnection(conn);
		}
		
		return list;
	}
	
	public void saveWifiInfo(WifiInfo wifi) {
		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println("SQLite DB conneted");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			String sql = " INSERT INTO wifi_info(X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, "
					+  "X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, "
					+  "X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, "
					+  "X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, "
					+  "X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) "
					+  " VALUES "
					+  " ( "
					+  " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
					+  " ) ";
	
			conn = cs.getConnection(conn);

			ps = conn.prepareStatement(sql);
			ps.setString(1, wifi.getX_SWIFI_MGR_NO());
			ps.setString(2, wifi.getX_SWIFI_WRDOFC());
			ps.setString(3, wifi.getX_SWIFI_MAIN_NM());
			ps.setString(4, wifi.getX_SWIFI_ADRES1());
			ps.setString(5, wifi.getX_SWIFI_ADRES2());
			ps.setString(6, wifi.getX_SWIFI_INSTL_FLOOR());
			ps.setString(7, wifi.getX_SWIFI_INSTL_TY());
			ps.setString(8, wifi.getX_SWIFI_INSTL_MBY());
			ps.setString(9, wifi.getX_SWIFI_SVC_SE());
			ps.setString(10, wifi.getX_SWIFI_CMCWR());
			ps.setString(11, wifi.getX_SWIFI_CNSTC_YEAR());
			ps.setString(12, wifi.getX_SWIFI_INOUT_DOOR());
			ps.setString(13, wifi.getX_SWIFI_REMARS3());
			ps.setDouble(14, wifi.getLNT());
			ps.setDouble(15, wifi.getLAT());
			ps.setString(16, wifi.getWORK_DTTM());
			
			int affected = ps.executeUpdate();
			
			if (affected > 0) {
                System.out.println(" 저장 성공 ");
            } else {
                System.out.println(" 저장 실패 ");
            }
		} catch (Exception e) {
			System.out.println("저장 오류");
			System.out.println(e.getMessage());
		} finally {
			try {
                if (ps != null && !ps.isClosed()) {
                	ps.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
			
			cs.closeConnection(conn);
		}
	}
	
	// wifi 삭제 코드
/*
	public void withrawWifiInfo (WifiInfo wifi) {
		try {
//			conn = null;
            conn = cs.getConnection(conn);
            
            String sql = "DELETE FROM wifi_info " +
                    " WHERE X_SWIFI_MGR_NO = ? ";

            ps = conn.prepareStatement(sql);
            ps.setString(1, wifi.getX_SWIFI_MGR_NO());

            int affected = ps.executeUpdate();

            if (affected > 0) {
                System.out.println(" 삭제 성공 ");
            } else {
                System.out.println(" 삭제 실패 ");
            }
        } catch (SQLException e) {
        	cs.rollback(conn);
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            cs.closeConnection(conn);
        }
	} */
}
