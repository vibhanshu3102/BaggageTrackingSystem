package com.gl.app.util;

import org.w3c.dom.css.Counter;

import java.sql.*;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

public class BaggageUtil {
	private static final String URL = "jdbc:postgresql://localhost:5432/baggagetrackingsystem?currentSchema=baggage";
	private static final String USER = "postgres";
	private static final String PASSWORD = "Pass@123";
	static AtomicInteger counter = new AtomicInteger();

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		Connection connection = DriverManager.getConnection(URL , USER , PASSWORD);
		return connection;
	}

	public int generateUniqueId(String columnName, String tableName, int initialValue) throws SQLException, ClassNotFoundException {
		Connection connection = BaggageUtil.getConnection();
		int uniqueId = initialValue;
		String query = "SELECT MAX(" + columnName + ") AS maxid FROM " + tableName;
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()){
		int maxid = resultSet.getInt("maxid");
		uniqueId = (maxid >= initialValue)? maxid + 1: initialValue;
		}
		counter.set(uniqueId);
		resultSet.close();
		statement.close();
		connection.close();
		return uniqueId;
	}

}
