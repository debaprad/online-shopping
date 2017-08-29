package com.deba.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.deba.shoppingbackend.DTO.Category;
import com.deba.shoppingbackend.dao.CategoryDAO;

@Repository("categoryDAO")
public class CategoryDaoImpl implements CategoryDAO{

	private static List<Category> categoryList=new ArrayList<>
				(Arrays.asList(new Category(1, "Television", "Television Description", "tel.png"),
						new Category(2, "Mobile", "Mobile Description", "mob.png"),
						new Category(3, "Laptop", "Laptop Description", "lap.png")));
	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return categoryList;
	}
	@Override
	public Category get(int id) {
		// TODO Auto-generated method stub
		return categoryList.stream().filter(c -> c.getId()==id).findFirst().get();
	}

	
}
