package com.garaipenadev.loginapp.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet("/logout")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		if (request.getSession() != null){
		  
			request.getSession().invalidate();
			Cookie[] cookies = request.getCookies();
			if(cookies!=null){
				for(Cookie c: cookies){
					if(c.getName().equals("usuario")){
						c.setMaxAge(0);
					}
					
				}
			}
		}
		response.sendRedirect("index.jsp");
	}

}
