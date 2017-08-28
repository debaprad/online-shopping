package com.deba.onlineshopping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
