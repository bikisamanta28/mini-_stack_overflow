package com.tech.blog.servlets;

import com.tech.blog.dao.UserDao;
import com.tech.blog.entities.Message;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConncetionProvider;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login_page extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		UserDao userDao = new UserDao(ConncetionProvider.getConnection());
		try {
			String email = req.getParameter("user_email");
			String password = req.getParameter("user_password");
			
			User users = userDao.getEmailAndPassword(email, password);

			// out.print(users);

			if (users == null) {

				Message msg = new Message("credial is wrong", "error-maggage", "alert-danger");

				HttpSession msgSession = req.getSession();

				msgSession.setAttribute("msgSession", msg);

				resp.sendRedirect("login_page.jsp");

			} else {

				HttpSession session = req.getSession();
				session.setAttribute("currentUser", users);

				resp.sendRedirect("profile.jsp");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
