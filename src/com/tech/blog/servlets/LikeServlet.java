package com.tech.blog.servlets;

import com.tech.blog.dao.LikeDao;
import com.tech.blog.helper.ConncetionProvider;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





public class LikeServlet extends HttpServlet{
	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		try {
			int userId=Integer.parseInt(req.getParameter("var1"));
					
			int pid=Integer.parseInt(req.getParameter("var2"));		
					
			LikeDao likeDao=new LikeDao(ConncetionProvider.getConnection());
			if(likeDao.postLike(userId, pid)) {
				
			out.print("done");
			}
			else {
				
				out.print("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
