package com.deba.shoppingbackend.test;


import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import com.deba.shoppingbackend.DTO.Category;
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
	
	@Ignore
	@Test
	public void testAddCategory()
	{
		category=new Category();
		category.setActive(true);
		category.setDescription("Laptop Description");
		category.setImageUrl("lap.png");
		category.setName("Laptop");
		assertEquals(true, categoryDAO.addCategory(category));
	}
	
	@Test
	public void testGetCategory()
	{
		assertEquals("Televison", categoryDAO.getCategory(1).getName());
	}
	
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
	
	@Test
	public void testListCategories()
	{
		assertEquals(3, categoryDAO.list().size());
	}
}
