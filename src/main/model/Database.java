package main.model;

import java.sql.*;

public class Database {
	
	private static Connection conn = null;
	private static Statement statement = null;
	
	public static void initConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://mysql.itu.dk", "jegp_bgpp", "ldo8tf6o");
			statement = conn.createStatement();
			System.out.println("Connection established!");
		} catch (Exception e) {
			System.err.println("Cannot connect to database: " + e);
		}
	}
	
}
