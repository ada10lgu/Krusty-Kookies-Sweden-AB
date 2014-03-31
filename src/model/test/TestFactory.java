package model.test;

import java.util.ArrayList;

import model.Factory;
import model.Triplet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class TestFactory {
	
	@Test
	public void createFactory() {
		
		Factory f = new Factory(Factory.DATABASE_CONNECTION);
		
		
	}
	
	@Test
	public void testGetAllIngrediense() {
		Factory f = new Factory(Factory.DATABASE_CONNECTION);
		
		ArrayList<Triplet<Integer,String,Integer>> data = f.getAllRawMaterials();
		
		
		
		
		System.out.println(data);
	}
	
}
