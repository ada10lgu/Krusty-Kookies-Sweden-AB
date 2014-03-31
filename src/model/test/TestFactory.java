package model.test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import model.Article;
import model.Factory;
import model.database.DatabaseSetup;

import org.junit.*;
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

		assertEquals(data.get(0).getId(), 0);
		assertEquals(data.get(0).getName(), "New rawmaterial");
		assertEquals(data.get(0).getAmmount(), 0);

		assertEquals(data.get(1).getId(), 1);
		assertEquals(data.get(1).getName(), "smör");
		assertEquals(data.get(1).getAmmount(), 700);

		assertEquals(data.get(2).getId(), 2);
		assertEquals(data.get(2).getName(), "socker");
		assertEquals(data.get(2).getAmmount(), 800);

		assertEquals(data.get(3).getId(), 3);
		assertEquals(data.get(3).getName(), "choklad");
		assertEquals(data.get(3).getAmmount(), 650);

		assertEquals(data.get(4).getId(), 4);
		assertEquals(data.get(4).getName(), "salt");
		assertEquals(data.get(4).getAmmount(), 1000);

		assertEquals(data.get(5).getId(), 5);
		assertEquals(data.get(5).getName(), "bananer");
		assertEquals(data.get(5).getAmmount(), 0);

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
		String name = "Tomat";
		int ammount = 300;
		int wrongAmmount = 200;
		Article a = new Article(id,name,wrongAmmount);
		
		int numberOfArticles = f.getAllRawMaterials().size();
		
		f.addArticle(a, ammount);
		
		ArrayList<Article> data = f.getAllRawMaterials();
		
		Article last = data.get(data.size()-1);
		
		assertEquals(numberOfArticles+1, data.size());
		assertEquals(name, last.getName());
		assertEquals(ammount, last.getAmmount());
		assertFalse(id==last.getId());
		

	}
}
