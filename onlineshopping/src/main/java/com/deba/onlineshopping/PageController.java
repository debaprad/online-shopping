package com.deba.onlineshopping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.deba.shoppingbackend.DTO.Category;
import com.deba.shoppingbackend.DTO.Product;
import com.deba.shoppingbackend.dao.CategoryDAO;
import com.deba.shoppingbackend.dao.ProductDAO;

@Controller
public class PageController {

	@Autowired
	public CategoryDAO categoryDAO;
	@Autowired
	public ProductDAO productDAO;
	
	@RequestMapping(value ={"/","/home","/index"})
	public ModelAndView index()
	{
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title","home");
		mv.addObject("HomePage",true);
		mv.addObject("categories",categoryDAO.list());
		return mv;
	}
	
	@RequestMapping("/show/all/products")
	public ModelAndView allProducts()
	{
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title","All Products");
		mv.addObject("AllProducts",true);
		mv.addObject("categories",categoryDAO.list());
		return mv;
	}
	
	@RequestMapping("/show/category/{categoryId}/product")
	public ModelAndView prodcutsByCategoryId(@PathVariable("categoryId") int id)
	{
		Category category=categoryDAO.getCategory(id);
		System.out.println("category : "+category);
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title",category.getName());
		mv.addObject("categories",categoryDAO.list());
		mv.addObject("category",category);
		mv.addObject("CategoryProducts",true);
		return mv;
	}
	
	@RequestMapping("/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id)
	{
		Product product=productDAO.getProduct(id);
		
		product.setViews(product.getViews()+1);
		productDAO.update(product);
		
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title",product.getName());
		mv.addObject("showProductClicked",true);
		mv.addObject("product",product);
		return mv;
	}
	
	@RequestMapping("/aboutus")
	public ModelAndView aboutUs()
	{
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title","About Us");
		mv.addObject("AboutUsPage",true);
		return mv;
	}
	@RequestMapping("/contactus")
	public ModelAndView contactUs()
	{
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title","contact Us");
		mv.addObject("contactUsPage",true);
		return mv;
	}
	
	/*---------------diff between @RequestParam @PathVariable  starts  Not part of the project -------------------*/
	
	/*http://localhost:8989/onlineshopping/testRequestParam
	 * required=false if we remove then the url should be contain query string http://localhost:8989/onlineshopping/testRequestParam?greeting=Welcome
	*/	
	@RequestMapping(value ="/testRequestParam")
	public ModelAndView testRequestParam(@RequestParam(value="greeting",required=false) String greating)
	{
		if(greating== null || greating.isEmpty())
		{
			greating="Hello";
		}
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("greeting",greating);
		return mv;
	}
	
	/*http://localhost:8989/onlineshopping/testPathVariable/Welcome
	 * 
	*/
	@RequestMapping(value ="/testPathVariable/{greating}")
	public ModelAndView testPathVariable(@PathVariable(value="greating",required=false) String greating)
	{
		if(greating== null || greating.isEmpty())
		{
			greating="Hello";
		}
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("greeting",greating);
		return mv;
	}
	
	/*---------------diff between @RequestParam @PathVariable  ends -------------------*/
}
