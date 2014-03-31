package model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import model.Article;
import model.Factory;
import model.Product;
import model.database.DatabaseSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestFactory {

	private Factory f;
	
	@Before
	public void setUp() {
		try {
			new DatabaseSetup(DatabaseSetup.SQL_FILE_PATH);
			f = new Factory(Factory.DATABASE_CONNECTION);
		} catch (FileNotFoundException e) {
			fail("Could not reset databse");
		}
	}
	

	@Test
	public void testGetAllIngrediense() {

		ArrayList<Article> data = f.getAllRawMaterials();

		
		assertEquals(data.get(0).getName(), "New rawmaterial");
		assertEquals(data.get(1).getName(), "Flour");

		assertEquals(data.get(2).getName(), "Butter");

		assertEquals(data.get(3).getName(), "Icing sugar");

		assertEquals(data.get(4).getName(), "Roasted, chopped nuts");

		assertEquals(data.get(5).getName(), "Fine-ground nuts");

		for (int i = 1; i < data.size();i++ ) {
			int id = data.get(i).getId();
			int ammount = data.get(i).getAmmount();
			
			assertEquals(i, id);
			assertEquals(1000, ammount);
		}
		
	}

	@Test
	public void testAddExistingArticle() {
		ArrayList<Article> data = f.getAllRawMaterials();
		
		Article before = data.get(3);
		
		int beforeAmmount = before.getAmmount();
		int increese = 125;
		int afterAmmount = beforeAmmount+increese;
		
		f.addArticle(before, increese);
		
		data = f.getAllRawMaterials();
		
		Article after = data.get(3);
		
		assertEquals(before.getId(), after.getId());
		assertEquals(before.getName(), after.getName());
		assertEquals(afterAmmount, after.getAmmount());
		
	}
	
	@Test
	public void testAddNewArticle() {
		int id = 0;
		String name = "Tomato";
		String prefix = "dl";
		int ammount = 300;
		int wrongAmmount = 200;
		Article a = new Article(id,name,wrongAmmount,prefix);
		
		int numberOfArticles = f.getAllRawMaterials().size();
		
		f.addArticle(a, ammount);
		
		ArrayList<Article> data = f.getAllRawMaterials();
		
		Article last = data.get(data.size()-1);
		
		assertEquals(numberOfArticles+1, data.size());
		assertEquals(name, last.getName());
		assertEquals(ammount, last.getAmmount());
		assertEquals(prefix, last.getPrefix());
		assertFalse(id==last.getId());
	}
	
	@Test
	public void testGetProducts() {
		System.out.println(f.getAllProducts());
	}
	
	@Test
	public void testProduceProduct() {
		Product p = f.getAllProducts().get(0);
		f.produceProduct(p, 10);
	}
}
