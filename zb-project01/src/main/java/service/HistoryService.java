package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import dto.History;

public class HistoryService {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
//	public List<History> showHistory() {
//		History history = null;
//		List<History> list = new ArrayList<>();
//		
//		try {
//			conn = null;
//			getConnection(conn);
//			
//			String sql = " SELECT * "
//							+  " FROM history ";
//			
//			
//			
//			ps = conn.prepareStatement(sql);
//			
//			rs = ps.executeQuery();
//			
//			while (rs.next()) {
//				history = new History();
//				history.setId(rs.getLong("ID"));
//				history.setLat(rs.getDouble("LAT"));
//				history.setLnt(rs.getDouble("LNT"));
//				history.setHistoryDate(rs.getString("HISTORY_DATE"));
//				
//				list.add(history);
//			}
//			
//		} catch (Exception e) {
//			rollback(conn);
//			System.out.println("select 실패" + e);
//		} finally {
//			try {
//                if (rs != null && !rs.isClosed()) {
//                    rs.close();
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//
//            try {
//                if (ps != null && !ps.isClosed()) {
//                    ps.close();
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//            
//			closeConnection(conn);
//		}
//		
//		return list;
//	}
//	
//	//history 정보 db에 저장하기
//	public void saveHistory(History history) {
//		try {
//			conn = null;
//			getConnection(conn);
//			
//			String sql = " INSERT INTO history (LAT, LNT, HISTORY_DATE) "
//							+  " VALUES (?, ?, ?) ";
//			
//			ps = conn.prepareStatement(sql);
//			ps.setDouble(1, history.getLat());
//			ps.setDouble(2, history.getLnt());
//			ps.setString(3, history.getHistoryDate());
//		
//			int affected = ps.executeUpdate();
//			
//			if (affected > 0) {
//                System.out.println(" 저장 성공 ");
//            } else {
//                System.out.println(" 저장 실패 ");
//            }
//		} catch (Exception e) {
//			rollback(conn);
//			e.getStackTrace();
//		} finally {
//            try {
//                if (ps != null && !ps.isClosed()) {
//                    ps.close();
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//            
//			closeConnection(conn);
//		}
//	}
//	
//	public void withrawHistory(long id) {
//		try {
//			conn = null;
//			getConnection(conn);
//			
//			String sql = " DELETE FROM history "
//							+  " WHERE ID = ? ";
//			
//			ps = conn.prepareStatement(sql);
//	        ps.setInt(1, (int)id);
//
//	        int affected = ps.executeUpdate();
//
//	        if (affected > 0) {
//	            System.out.println(" 삭제 성공 ");
//	        } else {
//	            System.out.println(" 삭 실패 ");
//	        }
//		} catch (Exception e) {
//			rollback(conn);
//			e.getStackTrace();
//		} finally {
//			try {
//                if (ps != null && !ps.isClosed()) {
//                    ps.close();
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//			
//			closeConnection(conn);
//		}
//		
//	}
}
