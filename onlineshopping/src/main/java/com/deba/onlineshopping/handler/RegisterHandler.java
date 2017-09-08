package com.deba.onlineshopping.handler;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.deba.onlineshopping.model.RegisterModel;
import com.deba.shoppingbackend.DTO.Address;
import com.deba.shoppingbackend.DTO.Cart;
import com.deba.shoppingbackend.DTO.User;
import com.deba.shoppingbackend.dao.UserDAO;

@Component
public class RegisterHandler implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public RegisterModel init()
	{
		return new RegisterModel();
	}
	
	public void addUser(RegisterModel registerModel,User user)
	{
		registerModel.setUser(user);
	}
	public void addAddress(RegisterModel registerModel,Address address)
	{
		registerModel.setBilling(address);
	}
	public String saveUserDetails(RegisterModel registerModel)
	{
		String transaction="success";
		User user=registerModel.getUser();
		if(user.getRole().equals("USER"))
		{
			Cart cart=new Cart();
			user.setCart(cart);
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDAO.addUser(user);
		
		Address address=registerModel.getBilling();
		address.setBilling(true);
		address.setUser(user);
		
		userDAO.addAddress(address);
		
		return transaction;
	}
	
	public String validateUser(User user,MessageContext messageContext)
	{
		String transition="success";
		if(!(user.getPassword().equals(user.getConfirmPassword())))
		{
			transition="failure";
			messageContext.addMessage(new MessageBuilder().error()
					.source("confirmPassword").
					defaultText("password and confirme password mismatch").build());
		}
		String email = "";
		try {
			email =	userDAO.getUser(user.getEmail()).getEmail();
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		if(!(email.isEmpty()) && email.equals(user.getEmail()))
		{
			messageContext.addMessage(new MessageBuilder().error()
					.source("email").defaultText("Email is in Use").build());
			transition="failure";
		}
		
		return transition;
	}
}
