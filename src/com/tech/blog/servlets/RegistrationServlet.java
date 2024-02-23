package com.tech.blog.servlets;

import com.tech.blog.dao.UserDao;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConncetionProvider;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@MultipartConfig
public class RegistrationServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Connection conr = ConncetionProvider.getConnection();

		PrintWriter out = resp.getWriter();

		String check = req.getParameter("check");
		if (check == null) {
			out.print("checkbox should be fill");
		} else {
			String name = req.getParameter("user_name");
			String email = req.getParameter("user_email");
			String password = req.getParameter("user_password");
			String gender = req.getParameter("gender");
			String about = req.getParameter("about");
			LocalDateTime currentDateTime = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String formattedDateTime = currentDateTime.format(formatter);

 
			User user = new User(name, email, password, about, gender,formattedDateTime);
			UserDao userDao = new UserDao(conr);

			try {
				
				if (userDao.saveUser(user)) {

					out.print("done");
				} else {
					out.print("error");
				}
				
			} catch (Exception e) {
				e.printStackTrace(); 
			}

		}
	}

	
	
	
}
