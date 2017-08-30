package com.deba.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deba.shoppingbackend.DTO.Category;
import com.deba.shoppingbackend.dao.CategoryDAO;

@Repository("categoryDAO")
@Transactional
public class CategoryDaoImpl implements CategoryDAO{

	@Autowired
	public SessionFactory sessionFactory;
	
	
	@Override
	public List<Category> list() {
		String queryListCategory="From Category where active=:active";
		@SuppressWarnings("unchecked")
		Query<Category> query=sessionFactory.getCurrentSession().createQuery(queryListCategory);
		query.setParameter("active", true);
		return query.getResultList();
	}
	@Override
	public Category getCategory(int id) {
		try {
			return sessionFactory.getCurrentSession().get(Category.class,Integer.valueOf(id));
			//return true;
		} catch (Exception e) {
			System.out.println("Exception in get category :"+e);
		}
		return null;
	}
	@Override
	public boolean addCategory(Category category) {
		try {
			sessionFactory.getCurrentSession().save(category);
			return true;
		} catch (Exception e) {
			System.out.println("Exception in add category :"+e);
		}
		return false;
	}
	@Override
	public boolean update(Category category) {
		try {
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception e) {
			System.out.println("Exception in update category :"+e);
		}
		return false;
	}
	@Override
	public boolean delete(Category category) {
		category.setActive(false);
		try {
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception e) {
			System.out.println("Exception in update category :"+e);
		}
		return false;
	}

	
}
