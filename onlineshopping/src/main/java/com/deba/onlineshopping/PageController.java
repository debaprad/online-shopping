package com.deba.onlineshopping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

	@RequestMapping(value ={"/","/home","/index"})
	public ModelAndView index()
	{
		System.out.println("hello controller");
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("greeting","Welcome to OnlineShopping !");
		return mv;
	}
}
