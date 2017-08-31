package com.deba.shoppingbackend.dao;

import java.util.List;

import com.deba.shoppingbackend.DTO.Product;

public interface ProductDAO {

	List<Product> list();
	Product getProduct(int id);
	boolean addProduct(Product category);
	boolean update(Product category);
	boolean delete(Product category);
	
	List<Product> listActiveProduct();
	List<Product> listActiveProductByCategory(int categoryId);
	List<Product> getLatestActiveProduct(int count);
	
}
