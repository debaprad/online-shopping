package com.deba.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deba.shoppingbackend.DTO.Product;
import com.deba.shoppingbackend.dao.ProductDAO;

//@Repository("productDAO")
//@Transactional
public class ProductDaoImpl implements ProductDAO{

	//@Autowired
	public SessionFactory sesionFactory;
	
	
	@Override
	public List<Product> list() {
		return sesionFactory.getCurrentSession()
				.createQuery("From Product",Product.class)
				.getResultList();
	}

	@Override
	public Product getCategory(int id) {
		// TODO Auto-generated method stub
		return sesionFactory.getCurrentSession()
				.get(Product.class, Integer.valueOf(id));
	}

	@Override
	public boolean addProduct(Product product) {
		// TODO Auto-generated method stub
		try {
			 sesionFactory.getCurrentSession()
				.save(product);
			 return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public boolean update(Product product) {
		// TODO Auto-generated method stub
		try {
			 sesionFactory.getCurrentSession()
				.update(product);
			 return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public boolean delete(Product product) {
		product.setActive(false);
		try {
			 sesionFactory.getCurrentSession()
				.save(product);
			 return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

}
