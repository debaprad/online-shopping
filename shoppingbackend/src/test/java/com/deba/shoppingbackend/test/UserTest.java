package com.deba.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.deba.shoppingbackend.DTO.Address;
import com.deba.shoppingbackend.DTO.Cart;
import com.deba.shoppingbackend.DTO.User;
import com.deba.shoppingbackend.dao.UserDAO;

public class UserTest {

	private static UserDAO userDAO;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void init()
	{
	
				
				AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
				context.scan("com.deba.shoppingbackend");
				context.refresh();
				userDAO=context	.getBean("userDAO",UserDAO.class);
	}
	@Ignore
	@Test
	public void testAddUser()
	{
		User user=new User();
		user.setRole("USER");
		user.setEmail("dp@gmail.com");
		user.setFirstName("debasish");
		user.setLastName("Pradhan");
		user.setPassword("pin123");
		user.setContactNumber("9543287283");
		Cart cart=null;
		if(user.getRole().equals("USER"))
		{
			cart=new Cart();
			cart.setUser(user);
		}
		user.setCart(cart);
		assertEquals(true, userDAO.addUser(user));
	}
	
	//@Test
	public void testGetUser()
	{
		assertEquals("Pradhan", userDAO.getUser("dp@gmail.com").getLastName());
	}
	
	//@Test
	public void testUpdateUser()
	{
		Cart cart=userDAO.getUser("dp@gmail.com").getCart();
				cart.setGrandTotal(100);
		assertEquals(true, userDAO.updateCart(cart));
	}
	
	@Test
		public void testgetUserAddress()
		{
			User user=userDAO.getUser("dp@gmail.com");
			assertEquals(true, userDAO.getBillingAddress(user).isBilling());
			
			assertEquals(3, userDAO.getShippingAddresses(user).size());
		}
	
	//@Test
	public void testAddAddress()
	{
		User user=new User();
		user.setRole("USER");
		user.setEmail("dp@gmail.com");
		user.setFirstName("debasish");
		user.setLastName("Pradhan");
		user.setPassword("pin123");
		user.setContactNumber("9543287283");
		assertEquals(true, userDAO.addUser(user));
		
		Address billing=new Address();
		billing.setAddressLineOne("Billing addres 1");
		billing.setAddressLineTwo("Billing Address1 ");
		billing.setBilling(true);
		billing.setCity("mumbai");
		billing.setCountry("India");
		billing.setPostalCode("407014");
		billing.setShipping(false);
		billing.setState("Maharastra");
		billing.setUser(user);
		assertEquals(true, userDAO.addAddress(billing));
		
		Address shipping=new Address();
		shipping.setAddressLineOne("shipping addres 1");
		shipping.setAddressLineTwo("shipping Address1 ");
		shipping.setBilling(false);
		shipping.setCity("mumbai");
		shipping.setCountry("India");
		shipping.setPostalCode("407014");
		shipping.setShipping(true);
		shipping.setState("Maharastra");
		shipping.setUser(user);
		assertEquals(true, userDAO.addAddress(shipping));
		
		
		
		
		//assertEquals(true, userDAO.updateCart(cart));
	}
	
	   // @Test
		public void testAddShipingAddress()
		{
			User user=userDAO.getUser("dp@gmail.com");
			
			
			Address shipping=new Address();
			shipping.setAddressLineOne("shipping addres 3");
			shipping.setAddressLineTwo("shipping Address 3 ");
			shipping.setBilling(false);
			shipping.setCity("mumbai");
			shipping.setCountry("India");
			shipping.setPostalCode("407014");
			shipping.setShipping(true);
			shipping.setState("Maharastra");
			shipping.setUser(user);
			assertEquals(true, userDAO.addAddress(shipping));
			
			
			
			
			//assertEquals(true, userDAO.updateCart(cart));
		}
}
