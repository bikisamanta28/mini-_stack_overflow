package com.tech.blog.servlets;

import com.tech.blog.dao.UserDao;
import com.tech.blog.entities.Message;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConncetionProvider;
import com.tech.blog.helper.Helper;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig
public class EditServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter out = resp.getWriter();
		try {
		    HttpSession session = req.getSession();
		    User user = (User) session.getAttribute("currentUser");

		    String name = req.getParameter("user_name");
		    String email = req.getParameter("user_email");
		    String about = req.getParameter("user_about");
		    String password = req.getParameter("user_password");
		    
		    Part part = req.getPart("user_profile");

		    String profileName = null; // Initialize to null

		    // Check if a file is uploaded
		    if (part != null && part.getSize() > 0) {
		        profileName = part.getSubmittedFileName();
		        String path = req.getRealPath("/") + "pics" + java.io.File.separator + profileName;

		        // Save the new file
		     if(Helper.saveFile(part.getInputStream(), path))
		     {
		    	 System.out.println("pic successfully updated to folder");
		     }
		     else {
		    	 System.out.println("error in photo upload in folder");
		     }

		        // Delete the old file if it's not the default
		        String oldFile = user.getProfile();
		        if (oldFile != null && !oldFile.equals("default.jpg")) {
		            String oldPath = req.getRealPath("/") + "pics" + java.io.File.separator + oldFile;
		            Helper.deleteFile(oldPath);
		        }
		        
		        // Update user profile only if new file is uploaded
		        user.setProfile(profileName);
		    }

		    // Update other user details
		    user.setName(name);
		    user.setEmail(email);
		    user.setAbout(about);
		    user.setPassword(password);

		    UserDao userDao = new UserDao(ConncetionProvider.getConnection());
		    boolean ans = userDao.getEdit(user);

		    if (ans) {
		        
		        Message msg = new Message("updated successfully", "success", "alert-success");
		        session.setAttribute("msg", msg);
		       
		    } else {
		       
		        Message msg = new Message("Error in profile updation", "error", "alert-danger");
		        session.setAttribute("msg", msg);
		    }

		    resp.sendRedirect("profile.jsp");
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

}
