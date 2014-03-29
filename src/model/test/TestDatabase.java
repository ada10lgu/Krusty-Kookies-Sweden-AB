package model.test;

import static org.junit.Assert.*;
import model.database.Database;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestDatabase {

	private String server = "puccini.cs.lth.se";
	private String database = "db50";
	private String username = "db50";
	private String password = "ntx184tr";

	@Test
	public void testCreateDatabaseAndConnect() {
		Database db = new Database(server, database, username, password);
		
		try {
			db.connect();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
