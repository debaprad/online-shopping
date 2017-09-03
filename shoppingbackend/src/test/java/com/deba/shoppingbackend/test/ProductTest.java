package com.deba.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.deba.shoppingbackend.DTO.Category;
import com.deba.shoppingbackend.DTO.Product;
import com.deba.shoppingbackend.dao.CategoryDAO;
import com.deba.shoppingbackend.dao.ProductDAO;

public class ProductTest {

private static AnnotationConfigApplicationContext applicationContext;
	
	private static ProductDAO productDAO;
	private static CategoryDAO categoryDAO;
	
	
	@BeforeClass
	public static void init()
	{
		applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.scan("com.deba.shoppingbackend.config");
		applicationContext.scan("com.deba.shoppingbackend.daoimpl");
		applicationContext.refresh();
		System.out.println(applicationContext);
		productDAO =(ProductDAO)applicationContext.getBean("productDAO");
		categoryDAO= (CategoryDAO)applicationContext.getBean("categoryDAO");
		
	}
	
	@Ignore
	@Test
	public void testAddProduct()
	{
		Category category=categoryDAO.getCategory(2);
		Product product1=new Product();
		product1.setActive(true);
		product1.setBrand("SamSung");
		product1.setCatg(category);
		product1.setDescription("Samsung on8 mobile  2gb ram 16gb hdd");
		product1.setName("Samsung ON8");
		assertEquals(true, productDAO.addProduct(product1));
	}
	
	@Ignore
	@Test
	public void testGetCategory()
	{
		assertEquals("Dell Inspiron", productDAO.getProduct(1).getName());
	}
	
	//@Ignore
	@Test
	public void testUpdateProduct()
	{
		Product product=productDAO.getProduct(12);
	/*	product.setUnitPrice(14000);
		product.setQuantity(0);*/
		product.setQuantity(6);
		assertEquals(true, productDAO.update(product));
	}
	@Ignore
	@Test
	public void testDeleteCategory()
	{
		Product category=productDAO.getProduct(5);
		assertEquals(true, productDAO.delete(category));
	}
	
	@Ignore
	@Test
	public void testListProduct()
	{
		assertEquals(2, productDAO.list().size());
	}
	@Ignore
	@Test
	public void testListActiveProduct()
	{
		assertEquals(2, productDAO.listActiveProduct().size());
	}
	@Ignore
	@Test
	public void testListActiveProductByCategory()
	{
		assertEquals(2, productDAO.listActiveProductByCategory(1).size());
	}
	
	@Ignore
	@Test
	public void testgetLatestActiveProduct()
	{
		String s=productDAO.getLatestActiveProduct(1)
				.stream().findFirst().get().getName();
		System.out.println(productDAO.getLatestActiveProduct(1));
		assertEquals(2, productDAO.getLatestActiveProduct(2).size());
	}
}
