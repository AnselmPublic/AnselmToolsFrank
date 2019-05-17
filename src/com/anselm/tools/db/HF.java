package com.anselm.tools.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Properties;

import com.anselm.tools.record.Log;

public class HF {
	public static Connection getConnection(String username, String password, String url, Log log) {
		Connection conn = null;
		Properties connectionProps = new Properties();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connectionProps.put("user", username);
			connectionProps.put("password", password);
			conn = DriverManager.getConnection(url, connectionProps);

			log.logger("Connection established");
		} catch (ClassNotFoundException e) {
			log.logger("Connection Failed");
			e.printStackTrace();
		} catch (SQLException e) {
			log.logger("Connection Failed");
			e.printStackTrace();
		} finally {
			// Close connection and release memory
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}

			// Release properties memory
			if (connectionProps != null)
				connectionProps = null;
		}

		return conn;
	}

	public static Connection getMySQLConnection(String username, String password, String url, Log log) {
		Connection conn = null;
		Properties connectionProps = new Properties();

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connectionProps.put("user", username);
			connectionProps.put("password", password);
			conn = DriverManager.getConnection(url, connectionProps);

			log.logger("Connection established");
		} catch (ClassNotFoundException e) {
			log.logger("Connection Failed");
			e.printStackTrace();
		} catch (SQLException e) {
			log.logger("Connection Failed");
			e.printStackTrace();
		} finally {
			// Close connection and release memory
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}

			// Release properties memory
			if (connectionProps != null)
				connectionProps = null;
		}

		return conn;
	}

	public static boolean executeStatement(Connection conn, String sql, Log log) {
		Statement stmt = null;
		boolean success = false;
		try {
			stmt = conn.createStatement();
			log.logger("Executing statement...");
			success = stmt.execute(sql);
		} catch (SQLException e) {
			log.logger("Connection Failed");
			e.printStackTrace();
		} catch (Exception e) {
			log.logger("Connection Failed");
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				log.logger("Statement close error");
				e.printStackTrace();
			}
		}

		return success;
	}

	public static LinkedList<String> executeSQL(Connection conn, String sql, Log log) throws SQLException {
		LinkedList<String> list = new LinkedList<String>();
		String result = "";
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			log.logger("Executing statement...");
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				result = rs.getString(1);
				list.push(result);
				log.logger("Got resultset: " + result);
			}
			log.logger("ResultSet exhausted.");
		} catch (Exception e) {
			throw e;
		} finally {
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
		}

		return list;
	}

	public static void closeConn(Connection conn) throws SQLException {
		conn.close();
		conn = null;
	}
}
