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
		
		data.add(new Triplet<Integer,String,Integer>(0,"New rawmaterial",0));
		
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

	public void addArticle(int id, String name, int ammount) {
		if (id == 0)
			addNewArticle(name,ammount);
		else
			increseArticle(id,ammount);
		
		
	}

	private void increseArticle(int id, int ammount) {
		String sql = "UPDATE article SET ammount = ? WHERE id = ? LIMIT 1";
		try {
			db.update(sql,ammount,id);			
			
		} catch (SQLException e) {
			System.err.println("Could not update row in database.");
			System.err.println("Query: " + sql);
			System.err.println("Terminating system!");
			System.exit(1);
		}
		
	}

	private void addNewArticle(String name, int ammount) {
		String sql = "INSERT INTO article(name,ammount) VALUES (?,?)";
		try {
			db.update(sql,name,ammount);

			
		} catch (SQLException e) {
			System.err.println("Could not add new article to the database.");
			System.err.println("Query: " + sql);
			System.err.println("Terminating system!");
			System.exit(1);
		}
		
	}
	
}
