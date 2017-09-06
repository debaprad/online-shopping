package com.deba.onlineshopping.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

	private final static String ABS_PATH="C:/Users/debasish/git/online-shopping/onlineshopping/src/main/webapp/assets/images/";
	private  static String REAL_PATH="";
	
	public static void fileUpload(HttpServletRequest request,
			MultipartFile file,String code)
	{
		REAL_PATH=request.getSession().getServletContext().getRealPath("/assets/images/");
		if(!new File(REAL_PATH).exists())
		{
			new File(REAL_PATH).mkdir();
		}
		
		if(!new File(ABS_PATH).exists())
		{
			new File(ABS_PATH).mkdir();
		}
		
		try {
			file.transferTo(new File(ABS_PATH+code+".jpg"));
			file.transferTo(new File(REAL_PATH+code+".jpg"));
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("fail to upload image");
		}
	}
}
