package com.deba.shoppingbackend.dao;

import java.util.List;

import com.deba.shoppingbackend.DTO.Category;

public interface CategoryDAO {

	List<Category> list();
	Category getCategory(int id);
	boolean addCategory(Category category);
	boolean update(Category category);
	boolean delete(Category category);
}
