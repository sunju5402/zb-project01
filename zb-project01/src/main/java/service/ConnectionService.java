package service;

import java.sql.*;

public class ConnectionService {
	private String dbFileUrl = "jdbc:sqlite:/Users/sj/git/zb-project01/zb-project01/sqlite.db";
	
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
