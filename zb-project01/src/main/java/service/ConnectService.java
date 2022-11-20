package service;

import java.sql.*;

public class ConnectService {
//	public Connection conn = null;
	private String dbFileUrl = "jdbc:sqlite:sqlite.db";
	
	public Connection getConnection(Connection conn) {
		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println("SQLite DB conneted");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(dbFileUrl);
			System.out.println("connection 연결 성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public void rollback(Connection conn) {
		try {
			if (conn != null) {
				System.out.println("rollback 성공");
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.isClosed();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
