package com.deba.shoppingbackend.test;


import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.deba.shoppingbackend.DTO.Category;
import com.deba.shoppingbackend.DTO.Product;
import com.deba.shoppingbackend.dao.CategoryDAO;

public class CategoryTest {

	private static AnnotationConfigApplicationContext applicationContext;
	
	private static CategoryDAO categoryDAO;
	
	private Category category;
	
	@BeforeClass
	public static void init()
	{
		applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.scan("com.deba.shoppingbackend.config");
		applicationContext.scan("com.deba.shoppingbackend.daoimpl");
		applicationContext.refresh();
		System.out.println(applicationContext);
		categoryDAO =(CategoryDAO)applicationContext.getBean("categoryDAO");
		
	}
	
	//@Ignore
	@Test
	public void testAddCategory()
	{
		category=new Category();
		category.setActive(true);
		category.setDescription("Mobile Description");
		category.setImageUrl("lap.png");
		category.setName("Mobile");
		Product product1=new Product();
		product1.setActive(true);
		product1.setBrand("Samsung");
		//product1.setCategory(category);
		product1.setDescription("Samsung on8 dual cam mobile");
		product1.setName("Samsung on8");
		Set<Product> products=new HashSet<>();
		products.add(product1);
		//category.setProducts(products);
		assertEquals(true, categoryDAO.addCategory(category));
	}
	@Ignore
	@Test
	public void testGetCategory()
	{

		assertEquals("14 Series Laptop", categoryDAO.getCategory(1).getProducts().stream().map(Product::getName).findFirst().get());
	}
	@Ignore
	@Test
	public void testupdateCategory()
	{
		Category category=categoryDAO.getCategory(1);
		category.setName("Televison");
		category.setActive(true);
		assertEquals(true, categoryDAO.update(category));
	}
	@Ignore
	@Test
	public void testDeleteCategory()
	{
		Category category=categoryDAO.getCategory(1);
		assertEquals(true, categoryDAO.delete(category));
	}
	@Ignore
	@Test
	public void testListCategories()
	{
		assertEquals(3, categoryDAO.list().size());
	}
}
