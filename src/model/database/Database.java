package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	private String server, username, password,database;
	private Connection conn;

	public Database(String server, String database, String username, String password) {
		this.server = server;
		this.database = database;
		this.username = username;
		this.password = password;
	}
	
	public boolean connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://" + server + "/"
					+ database, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return true;
	}

	public void close() {

	}

	public static void query(String sql, Object... o) {
		for (int i = 0; i < o.length; i++) {
			switch (o[i].getClass().toString()) {
			case "class java.lang.Double":
				System.out.println("Double: " + (double) o[i]);
				break;
			case "class java.lang.Integer":
				System.out.println("Integer: " + (int) o[i]);
				break;
			default:
				System.out.println("String: " + o[i].toString());
			}
		}
	}

}
