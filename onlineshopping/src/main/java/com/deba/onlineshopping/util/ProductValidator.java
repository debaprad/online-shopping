package com.deba.onlineshopping.util;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.deba.shoppingbackend.DTO.Product;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return Product.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors errors) {
		// TODO Auto-generated method stub
		Product product=(Product)arg0;
		if(product.getFile()==null || product.getFile().getOriginalFilename().equals(""))
		{
			errors.rejectValue("file", null,"please select and image");
			return;
		}
		 
		if(!(product.getFile().getContentType().equals("image/jpeg") ||
				product.getFile().getContentType().equals("image/gif") ||
				product.getFile().getContentType().equals("image/png") ||
				product.getFile().getContentType().equals("image/jpg")))
		{
			errors.rejectValue("file", null,"please select jpeg/gif/png/jpg fomatted file ");
			return;
		}

	}

}
