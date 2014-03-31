package model;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import model.database.Database;

public class Factory {

	private Database db;
	public static final String DATABASE_CONNECTION = "sql/databaseconnection";
	
	public Factory(String database) {
		try {
			Scanner s = new Scanner(new File(database));

			String databse = s.nextLine();
			String username = s.nextLine();
			String password = s.nextLine();
			
			db = new Database(databse, username, username, password);
			db.connect();
		} catch (Exception e) {
			System.err.println("Could not read file and create databaseconnection...");
			System.err.println("Terminating system!");
			System.exit(1);
		}
	}
	
	public ArrayList<Triplet<Integer,String,Integer>> getAllRawMaterials() {
		ArrayList<Triplet<Integer,String,Integer>> data = new ArrayList<>();
		
		String sql = "SELECT id, name, ammount FROM article;";
		try {
			ResultSet result = db.query(sql);
			
			while (result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				int ammount = result.getInt("ammount");
				data.add(new Triplet<Integer,String,Integer>(id,name,ammount));
			}
			
		} catch (SQLException e) {
			System.err.println("Could not fetch any ingrediens from the database.");
			System.err.println("Query: " + sql);
			System.err.println("Terminating system!");
			System.exit(1);
		}
		
		
		return data;
	}

}
