package model;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;
import java.util.TreeMap;

import model.database.Database;

public class Factory extends Observable {

	private Database db;
	public static final String DATABASE_CONNECTION = "sql/databaseconnection";
	private final int FACTOR = 15 * 10 * 36;

	public Factory(String database) {
		try {
			Scanner s = new Scanner(new File(database));

			String databse = s.nextLine();
			String username = s.nextLine();
			String password = s.nextLine();

			db = new Database(databse, username, username, password);
			db.connect();
			s.close();
		} catch (Exception e) {
			System.err
					.println("Could not read file and create databaseconnection...");
			System.err.println("Terminating system!");
			System.exit(1);
		}
	}

	public ArrayList<Article> getAllRawMaterials() {
		ArrayList<Article> data = new ArrayList<>();

		String sql = "SELECT id, name, ammount,prefix FROM article ORDER BY name;";
		try {
			ResultSet result = db.query(sql);

			while (result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				int ammount = result.getInt("ammount");
				String prefix = result.getString("prefix");
				data.add(new Article(id, name, ammount, prefix));
			}

		} catch (SQLException e) {
			System.err
					.println("Could not fetch any ingrediens from the database.");
			System.err.println("Query: " + sql);
			System.err.println("Terminating system!");
			System.exit(1);
		}

		return data;
	}

	public synchronized void addArticle(Article article, int ammount) {
		if (article.getId() == 0)
			addNewArticle(article.getName(), ammount, article.getPrefix());
		else
			increseArticle(article.getId(), ammount);
		setChanged();
		notifyObservers();
	}

	private void increseArticle(int id, int ammount) {
		String sql = "UPDATE article SET ammount = ammount + ? WHERE id = ? LIMIT 1";
		try {
			db.update(sql, ammount, id);

		} catch (SQLException e) {
			System.err.println("Could not update row in database.");
			System.err.println("Query: " + sql);
			System.err.println("Terminating system!");
			System.exit(1);
		}

	}

	private void addNewArticle(String name, int ammount, String prefix) {
		String sql = "INSERT INTO article(name,ammount,prefix) VALUES (?,?,?)";
		try {
			db.update(sql, name, ammount, prefix);

		} catch (SQLException e) {
			terminate("Could not add new article to the database.", sql);
		}

	}

	public ArrayList<Product> getAllProducts() {
		ArrayList<Product> products = new ArrayList<>();

		String sql = "SELECT name FROM product";

		try {
			ResultSet result = db.query(sql);

			while (result.next()) {
				String name = result.getString("name");
				TreeMap<Article, Double> data = getTngridients(name);

				Product p = new Product(name, data);

				products.add(p);

			}

		} catch (SQLException e) {
			terminate("Could get products from the database.", sql);
		}

		return products;
	}

	private TreeMap<Article, Double> getTngridients(String name) {
		TreeMap<Article, Double> data = new TreeMap<>();

		String sql = "SELECT a.id AS id, i.ammount AS ammount, a.name AS name, a.prefix AS prefix FROM ingridient AS i LEFT JOIN article AS a ON i.article = a.id WHERE product = ?";
		;

		try {
			ResultSet result = db.query(sql, name);

			while (result.next()) {
				double ammount = result.getDouble("ammount");
				String aName = result.getString("Name");
				String prefix = result.getString("prefix");
				int id = result.getInt("id");
				Article a = new Article(id, aName, 0, prefix);
				data.put(a, ammount);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			terminate("Could not get ingridients from database.", sql);

		}

		return data;
	}

	public synchronized boolean produceProduct(Product p, int ammount) {
		TreeMap<Integer, Integer> articlesNeeded = new TreeMap<>();

		for (Article a : p.getIngirdients().keySet()) {
			int id = a.getId();
			int n = (int) (p.getIngirdients().get(a) * FACTOR * ammount);
			articlesNeeded.put(id, n);
			if (!hasArticle(id, n))
				return false;
		}

		for (int id : articlesNeeded.keySet()) {
			int n = articlesNeeded.get(id);

			reduceArticle(id, n);
		}

		createPallet(p, ammount);

		System.out.println(articlesNeeded);
		setChanged();
		notifyObservers();
		return true;
	}
	
	public synchronized ArrayList<Pallet> getPallets() {
		return null;
	}
	

	private void createPallet(Product p, int ammount) {
		for (int i = 0; i < ammount; i++) {
			String sql = "INSERT INTO pallet (bakingDate,product) VALUES (CURDATE(),?);";

			try {
				db.update(sql, p.getName());
			} catch (SQLException e) {
				terminate("Could not create a pallet", sql);
			}
		}

	}

	private void reduceArticle(int id, int n) {
		String sql = "UPDATE article SET ammount = ammount - ? WHERE id = ? ";
		try {
			db.update(sql, n, id);
		} catch (SQLException e) {
			terminate("Could not alter the ammount of an article.", sql);
		}
	}

	private boolean hasArticle(int id, int n) {
		String sql = "SELECT ammount FROM article WHERE id = ? LIMIT 1";
		try {
			ResultSet result = db.query(sql, id);
			if (result.next()) {
				int ammount = result.getInt("ammount");
				if (ammount >= n)
					return true;
			}
		} catch (SQLException e) {
			terminate("Could not calculate ammount of an article.", sql);
		}

		return false;
	}

	private void terminate(String message, String sql) {
		System.err.println(message);
		System.err.println("Query: " + sql);
		System.err.println("Terminating system!");
		System.exit(1);
	}

}
