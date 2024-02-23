package com.tech.blog.servlets;





import com.tech.blog.dao.PostDao;
import com.tech.blog.entities.Message;
import com.tech.blog.entities.Post;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConncetionProvider;
import com.tech.blog.helper.Helper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


@MultipartConfig
public class AddPostServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		HttpSession s=req.getSession();
		PrintWriter out=resp.getWriter();
		
		try {
			Thread.sleep(2000);
			int cid=Integer.parseInt(req.getParameter("pselect"));
			
			String ptitle=req.getParameter("pname");
			String pcontent=req.getParameter("pcontent");
			String pcode=req.getParameter("pcode");
		    Part part =req.getPart("pfile");
		    String ppic=part.getSubmittedFileName();
			Date currentDate = new Date();
			HttpSession session=req.getSession();
			User user=(User)session.getAttribute("currentUser");
			
			System.out.println("profile pic in addPostServlet is "+ppic);
		
			
			 if (ppic == null || ppic.isEmpty()) {
			        // If no file was uploaded, set to default or retain existing picture
			        ppic = "blog.png"; // Or any other logic you prefer
			    }
			
			Post post=new Post(ptitle,pcontent,pcode,ppic,currentDate,user.getId(),cid);
			
		
			
			PostDao postDao=new PostDao(ConncetionProvider.getConnection());
			
			if(postDao.savePost(post)) {
				if(part.getSize()!=0) {
					String path=req.getRealPath("/") + "blogPics" + java.io.File.separator + ppic;
					Helper.saveFile(part.getInputStream(), path);
					
				}
				out.print("done");
				
			}
			else {
				out.println("error");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
