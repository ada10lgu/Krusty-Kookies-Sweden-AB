package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

	private String server, username, password, database;
	private Connection conn;

	public Database(String server, String database, String username,
			String password) {
		this.server = server;
		this.database = database;
		this.username = username;
		this.password = password;
	}

	public void connect() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://" + server + "/"
					+ database, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void close() throws SQLException {
		if (conn != null) {
			conn.close();
		}
		conn = null;
	}

	public int update(String sql, Object... o) throws SQLException {
		PreparedStatement stmt = createStatement(sql, o);

		return stmt.executeUpdate();
	}

	public ResultSet query(String sql, Object... o) throws SQLException {
		PreparedStatement stmt = createStatement(sql, o);

		ResultSet set = stmt.executeQuery();

		return set;
	}

	private PreparedStatement createStatement(String sql, Object... o)
			throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(sql);

		for (int i = 0; i < o.length; i++) {
			switch (o[i].getClass().toString()) {
			case "class java.lang.Integer":
				stmt.setInt(i + 1, (int) o[i]);
				break;
			default:
				stmt.setString(i + 1, o[i].toString());
			}
		}

		return stmt;
	}

}
