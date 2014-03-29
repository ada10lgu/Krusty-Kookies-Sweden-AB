package model.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import model.database.Database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestDatabase {

	private String server = "puccini.cs.lth.se";
	private String database = "db50";
	private String username = "db50";
	private String password = "ntx184tr";
	private Database db;
	
	@Before
	public void before() {
		db = new Database(server, database, username, password);
		try {
			db.connect();
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
	
	@After
	public void after() {
		try {
		db.close();
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
	
	
	@Test
	public void testCreateDatabaseAndConnectAndClose() {
		Database db = new Database(server, database, username, password);
		
		try {
			db.connect();
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
		try {
			db.close();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	

	

}
