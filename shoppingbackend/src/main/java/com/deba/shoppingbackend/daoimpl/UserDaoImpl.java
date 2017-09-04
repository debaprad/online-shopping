package com.deba.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deba.shoppingbackend.DTO.Address;
import com.deba.shoppingbackend.DTO.Cart;
import com.deba.shoppingbackend.DTO.User;
import com.deba.shoppingbackend.dao.UserDAO;

@Repository("userDAO")
@Transactional
public class UserDaoImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean addUser(User user) {
		try {
			sessionFactory.getCurrentSession().save(user);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public boolean addAddress(Address address) {
		try {
			sessionFactory.getCurrentSession().save(address);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public boolean updateCart(Cart cart) {
		try {
			sessionFactory.getCurrentSession().update(cart);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public User getUser(String email) {
		return sessionFactory.getCurrentSession().
				createQuery("From User where email=:email",User.class)
				.setParameter("email", email).getSingleResult();
	}

	@Override
	public List<Address> getShippingAddresses(User user) {
		
		try {
			return sessionFactory.getCurrentSession()
						  .createQuery("From Address where shipping=:shipping and user=:user",Address.class)
						  .setParameter("shipping", true)
						  .setParameter("user", user)
						  .getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

	@Override
	public Address getBillingAddress(User user) {

		try {
			return sessionFactory.getCurrentSession()
						  .createQuery("From Address where billing=:billing and user=:user",Address.class)
						  .setParameter("billing", true)
						  .setParameter("user", user)
						  .getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

}
