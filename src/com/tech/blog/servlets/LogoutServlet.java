package com.tech.blog.servlets;

import com.tech.blog.entities.Message;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		
		session.removeAttribute("currentUser");
		 Message massage=new Message("Successfully log out", "success", "alert-success");
		 
		 session.setAttribute("logout", massage);
		 
		 resp.sendRedirect("login_page.jsp");
	}

	
	
	
}
