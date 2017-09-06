package com.deba.onlineshopping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.engine.jdbc.spi.ResultSetReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.deba.onlineshopping.util.FileUploadUtil;
import com.deba.onlineshopping.util.ProductValidator;
import com.deba.shoppingbackend.DTO.Category;
import com.deba.shoppingbackend.DTO.Product;
import com.deba.shoppingbackend.dao.CategoryDAO;
import com.deba.shoppingbackend.dao.ProductDAO;

@Controller
@RequestMapping("/manage")
public class ManageController {
	  //jhh  
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping("/product")
	public ModelAndView showManageProducts(@RequestParam(value="message",required=false) String message)
	{
		Product product=new Product();
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title","Manage Product");
		mv.addObject("manageproduct",true);
		mv.addObject("product",product);
		if(message!=null &&!message.isEmpty())
		mv.addObject("message",message+" Added Successfully! ");

		
		return mv;
	}
	
	@RequestMapping("{id}/product")
	public ModelAndView editManageProducts(@PathVariable int id)
	{
		
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title","Manage Product");
		mv.addObject("manageproduct",true);
		
		mv.addObject("product",productDAO.getProduct(id));
		
		return mv;
	}
	
	
	@RequestMapping(value="/product",method=RequestMethod.POST)
	public String addManageProducts(@Valid @ModelAttribute Product product,
							BindingResult bindingResult,Model model,HttpServletRequest request)
	{
		if(product.getId()==0)
		new ProductValidator().validate(product, bindingResult);
		else
		{
			if(!(product.getFile().getOriginalFilename().equals("")))
			{
				new ProductValidator().validate(product, bindingResult);
			}
		}
		if(bindingResult.hasErrors())
		{
			model.addAttribute("title","Manage Product");
			model.addAttribute("manageproduct",true);
			model.addAttribute("message","Form validation Failed!");
			return "page";
		}
		Category category=categoryDAO.getCategory(product.getProductCatId());
		product.setCatg(category);
		product.setActive(true);
		if(product.getId()==0)
		productDAO.addProduct(product);
		if(product.getId()!=0)
		productDAO.update(product);
		
		if(!product.getFile().getOriginalFilename().equals(""))
		{
			FileUploadUtil.fileUpload(request, product.getFile(), product.getCode());
		}
		
		return "redirect:/manage/product?message=Product";
	}
	
	@ModelAttribute("categories")
	public List<Category> listOfCategory()
	{
		return categoryDAO.list();
	} 
	
	@ModelAttribute("addCategory")
	public Category addCategory()
	{
		return new Category();
	} 
	
	@RequestMapping(value="/category",method=RequestMethod.POST)
	public String addCtegory(@ModelAttribute Category addCategory,
			BindingResult bindingResult,Model model,HttpServletRequest request)
	{
		//addCategory.setActive(true);
		categoryDAO.addCategory(addCategory);
		return "redirect:/manage/product?message=category";
	}
	@RequestMapping(value="/product/{id}/activation")
	public String activatrDeactivateProduct(@PathVariable int id)
	{
		Product product=productDAO.getProduct(id);
		product.setActive(!product.isActive());
		productDAO.update(product);
		/*return (product.isActive())?"product "+product.getName()+"successfully Activeted":
			"product "+product.getName()+"successfully DeActiveted";*/
		return "redirectff";
	}
	
}
