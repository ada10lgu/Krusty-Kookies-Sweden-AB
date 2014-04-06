package model;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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

		setChanged();
		notifyObservers();
		return true;
	}

	public synchronized ArrayList<PalletGroup> getPallets() {
		ArrayList<PalletGroup> pallets = new ArrayList<>();
		String sql = "SELECT name as product, (SELECT COUNT(*) FROM pallet WHERE status = 'available' AND product = name) as ammount FROM product;";

		try {
			ResultSet result = db.query(sql);
			while (result.next()) {
				String name = result.getString("product");
				int ammount = result.getInt("ammount");
				Product p = new Product(name);
				PalletGroup pallet = new PalletGroup(p, ammount);
				pallets.add(pallet);
			}
		} catch (SQLException e) {
			terminate("Could not fetch pallets", sql);
		}
		return pallets;
	}

	private void createPallet(Product p, int ammount) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);

		for (int i = 0; i < ammount; i++) {
			String sql = "INSERT INTO pallet (bakingDate,product) VALUES (?,?);";
			try {
				db.update(sql, reportDate, p.getName());
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

	public synchronized ArrayList<Batch> getBatches() {
		ArrayList<Batch> batches = new ArrayList<>();
		String sql = "SELECT product, bakingDate, status FROM pallet GROUP BY product,bakingDate";

		try {
			ResultSet result = db.query(sql);

			while (result.next()) {
				String product = result.getString("product");
				String date = result.getString("bakingDate");
				String status = result.getString("status");
				Product p = new Product(product);
				batches.add(new Batch(p, date, status));
			}
		} catch (SQLException e) {
			terminate("Could not fetch batches.", sql);
		}

		return batches;
	}

	public synchronized void changeBatch(Batch batch) {
		String date = batch.getDate();
		String product = batch.getProduct();
		String status = (batch.getStatus().equals("available")) ? "blocked"
				: "available";

		String sql = "UPDATE pallet SET status = ? WHERE product = ? AND bakingDate = ?";
		try {
			db.update(sql, status, product, date);
		} catch (SQLException e) {
			terminate("Could not update pallet status.", sql);
		}
		setChanged();
		notifyObservers();

	}

	public ArrayList<Customer> getCustomers() {
		ArrayList<Customer> customers = new ArrayList<>();

		String sql = "SELECT id,name,address FROM customer ORDER BY name ASC";

		try {
			ResultSet result = db.query(sql);

			while (result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				String address = result.getString("address");
				Customer c = new Customer(id, name, address);
				customers.add(c);
			}

		} catch (SQLException e) {
			terminate("Could not fetch customers", sql);
		}

		return customers;
	}

	public synchronized int placeOrder(Customer c,
			HashMap<Product, Integer> list) {

		for (Product p : list.keySet())
			if (!hasProduct(p, list.get(p)))
				return 0;

		int order = createOrder(c);

		for (Product p : list.keySet())
			bindProduct(p, list.get(p), order);

		setChanged();
		notifyObservers();
		return order;
	}

	public boolean hasProduct(Product p, int ammount) {

		String sql = "SELECT COUNT(*) AS ammount FROM pallet WHERE product = ? AND status = 'available'";

		try {
			ResultSet result = db.query(sql, p.getName());

			if (result.next()) {
				int n = result.getInt("ammount");
				if (n >= ammount) {
					return true;
				}
			}

		} catch (SQLException e) {
			terminate("Could not check product ammount", sql);
		}

		return false;
	}

	public int createOrder(Customer c) {
		String sql = "INSERT INTO invoice (customer,latestDeliveryDate) VALUES (?,?)";

		try {
			return db.insert(sql, c.getId(), "2013-01-01 12:22:12");
		} catch (SQLException e) {
			terminate("Could not create invoice", sql);
		}
		return 0;

	}

	public void bindProduct(Product p, int ammount, int order) {
		String sql = "UPDATE pallet SET invoice = ?, status = 'reserved' WHERE product = ? AND status = 'available' LIMIT ?";
		try {
			db.update(sql, order, p.getName(), ammount);
		} catch (SQLException e) {
			terminate("Could not bind product to invoice", sql);
		}

	}

	public ArrayList<Order> getOrders() {
		ArrayList<Order> list = new ArrayList<>();

		String sql = "SELECT id, status, customer,latestDeliveryDate FROM invoice ORDER BY id ASC";

		try {
			ResultSet result = db.query(sql);
			while (result.next()) {
				int id = result.getInt("id");
				String status = result.getString("status");
				Customer customer = getCustomer(result.getInt("customer"));
				String date = result.getString("latestDeliveryDate");

				Order o = new Order(id, status, customer, date);
				list.add(o);

			}
		} catch (SQLException e) {
			terminate("Could not get pallets related to invoice", sql);
		}

		return list;
	}

	public model.OrderInfo getOrderInfo(Order o) {
		OrderInfo info = new OrderInfo(o);

		fillWithProducts(info);
		fillWithPallets(info);

		return info;
	}

	private void fillWithProducts(OrderInfo info) {
		int order = info.o.id;

		String sql = "SELECT product, COUNT(*) as ammount FROM pallet WHERE invoice = ? GROUP BY product";
		try {
			ResultSet result = db.query(sql, order);
			while (result.next()) {
				Product p = new Product(result.getString("product"));
				int ammount = result.getInt("ammount");
				info.products.put(p, ammount);
			}
		} catch (SQLException e) {
			terminate("Could not get pallets related to invoice", sql);
		}

	}

	private void fillWithPallets(OrderInfo info) {
		int order = info.o.id;

		String sql = "SELECT product, id FROM pallet WHERE invoice = ? ";
		try {
			ResultSet result = db.query(sql, order);
			while (result.next()) {
				Product p = new Product(result.getString("product"));
				int id = result.getInt("id");
				info.pallets.add(new Pallet(p, id));
			}
		} catch (SQLException e) {
			terminate("Could not get pallets related to invoice", sql);
		}
	}

	private Customer getCustomer(int id) {
		String sql = "SELECT name, address FROM customer WHERE id = ? LIMIT 1";

		try {
			ResultSet result = db.query(sql, id);
			if (result.next()) {
				String name = result.getString("name");
				String address = result.getString("address");

				Customer c = new Customer(id, name, address);
				return c;
			}
		} catch (SQLException e) {
			terminate("Could not get pallets related to invoice", sql);
		}

		return null;
	}

	public synchronized void sendOrder(Order o) {
		String sql = "UPDATE invoice SET status = 'delivered' WHERE id = ?";

		try {
			db.update(sql, o.id);
		} catch (SQLException e) {
			terminate("Could not update invoice status", sql);
		}
		setChanged();
		notifyObservers();
	}

	public synchronized void addRecepie(String name) {
		String sql = "INSERT INTO product (name) VALUES (?)";
		try {
			db.update(sql, name);
		} catch (SQLException e) {
			terminate("Could not create new product", sql);
		}
		setChanged();
		notifyObservers();

	}

	public void updateProduct(Product p,
			Map<Article, Double> articles) {
		System.out.println("Updating " + p.toString());
		for (Article a : articles.keySet()) {
			System.out.println(a);
			setProductArticle(p,a,articles.get(a)); 
		}
		setChanged();
		notifyObservers();
	}

	private void setProductArticle(Product p, Article a, Double ammount) {
		String sql = "SELECT ammount FROM ingridient WHERE product = ? AND article = ?";
		try {
			ResultSet result = db.query(sql, p.getName(),a.getId());
			
			if (result.next()) {
				double n = result.getDouble("ammount");
				if (n == ammount)
					return;
				sql = "UPDATE ingridient SET ammount = ? WHERE product = ? AND article = ?";
			} else {
				sql = "INSERT INTO ingridient (ammount,product,article) VALUES (?,?,?)";
			}
			db.update(sql, ammount,p.getName(),a.getId());
		} catch (SQLException e) {
			terminate("Could not update product", sql);
		}
		
		
	}
}
