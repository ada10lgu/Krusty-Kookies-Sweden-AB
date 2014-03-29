package model.test;

import model.database.Database;

public class DB {
	public static void main(String[] args) {
		
		 String server = "puccini.cs.lth.se";
		 String database = "db50";
		 String username = "db50";
		 String password = "ntx184tr";
		
		 Database db = new Database(server, database, username, password);
		 
		 db.connect();
		
	}
}
