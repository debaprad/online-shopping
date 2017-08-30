package com.deba.shoppingbackend.dao;

import java.util.List;

import com.deba.shoppingbackend.DTO.Product;

public interface ProductDAO {

	List<Product> list();
	Product getCategory(int id);
	boolean addProduct(Product category);
	boolean update(Product category);
	boolean delete(Product category);
}
