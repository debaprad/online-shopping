package com.deba.onlineshopping;

import javax.resource.spi.work.SecurityContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.deba.onlineshopping.model.UserModel;
import com.deba.shoppingbackend.DTO.User;
import com.deba.shoppingbackend.dao.UserDAO;

@ControllerAdvice
public class GlobalController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserDAO userDAO;
	
	private UserModel userModel=null;
	
	@ModelAttribute("userModel")
	public UserModel getUserModel()
	{
		if (session.getAttribute("userModel") == null) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String email = authentication.getName();
			User user =null;
			if(email != "anonymousUser")
			{
				user = userDAO.getUser(email);
			}
			if (user != null) {
				userModel = new UserModel();
				userModel.setId(user.getId());
				userModel.setFullName(user.getFirstName() + " " + user.getLastName());
				userModel.setRole(user.getRole());
				if (userModel.getRole() == "USER") {
					userModel.setCart(user.getCart());
				}
				session.setAttribute("userModel", userModel);
				return userModel;
			}
		}
		return (UserModel)session.getAttribute("userModel");
	}
}
