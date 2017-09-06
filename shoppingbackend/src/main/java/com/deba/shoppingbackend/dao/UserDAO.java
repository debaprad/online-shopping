package com.deba.shoppingbackend.dao;

import java.util.List;

import com.deba.shoppingbackend.DTO.Address;
import com.deba.shoppingbackend.DTO.Cart;
import com.deba.shoppingbackend.DTO.User;

public interface UserDAO {

	boolean addUser(User user);
	
	User getUser(String email);
	
	List<Address> getShippingAddresses(User user);
	
	Address getBillingAddress(User user);
	
	boolean addAddress(Address address);
	
	boolean updateCart(Cart cart);
	
}
