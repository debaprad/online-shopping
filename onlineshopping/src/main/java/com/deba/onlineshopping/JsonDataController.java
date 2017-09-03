package com.deba.onlineshopping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deba.shoppingbackend.DTO.Product;
import com.deba.shoppingbackend.dao.ProductDAO;

@Controller
@RequestMapping("/json/data")
public class JsonDataController {

	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping("/All/Product")
	@ResponseBody
	public List<Product> getListOfActiveAllProducts()
	{
		//System.out.println("List of products "+productDAO.listActiveProduct());
		return productDAO.listActiveProduct();
	}
	
	@RequestMapping("/All/Admin/Product")
	@ResponseBody
	public List<Product> getListOfAllProducts1()
	{
		//System.out.println("List of products "+productDAO.listActiveProduct());
		return productDAO.list();
	}
	
	@RequestMapping("/category/{id}/Product")
	@ResponseBody
	public List<Product> getListOfActiveProducts(@PathVariable int id)
	{
		return productDAO.listActiveProductByCategory(id);
	}
}
