<%@page import="com.tech.blog.dao.UserDao"%>
<%@page import="com.tech.blog.helper.ConncetionProvider"%>
<%@page import="com.tech.blog.dao.PostDao"%>
<%@page import="java.util.List"%>
<%@page import="com.tech.blog.entities.Post"%>
<%@page import="com.tech.blog.entities.User"%>
<%@page errorPage="error_page.jsp" %>

<%
 User user=(User)session.getAttribute("currentUser");
if(user==null)
{
	response.sendRedirect("login_page.jsp");
	return;
}
%>
<%
PostDao post=new PostDao(ConncetionProvider.getConnection());
UserDao userdao=new UserDao(ConncetionProvider.getConnection());
//usedao.getUserName();

  List<Post>list=null;
  int cid=Integer.parseInt(request.getParameter("var1"));
  
  if(cid==0){
	  
	  list=post.getAllBlogPost();
  }else{
	  
	  list=post.getBlogPostByCatId(cid);
  }
  
  if(list.size()==0){
	  out.println("<h3 class='display-3 text-center'>No Posts in this category...</h3>");
      return;
  }
   for(Post p:list)
 {

	 %>
	 
	<div class="col-md-5 d-inline-block mt-4">
	<div class="card">
	 
	
	 <img class="card-img-top" src="blogPics/<%=p.getpPic()%>" alt="Card image cap">
  <div class="card-body">
    <h5 class="card-title"><%=p.getpTitle() %></h5>
     <p class="card-text"><%=p.getpContent() %></p>
   </div>
   <div class="card-footer primary-background text-center">
   <a href="#!" class="btn btn-outline-primary btn-sm"><i class="fa fa-thumbs-o-up"></i><span class="like-counter">20</span></a>
   <a href="showBlogPage.jsp?pid=<%=p.getpId() %>" class="btn btn-outline-primary btn-sm">Read More...</a>
    <a href="#!" class="btn btn-outline-primary btn-sm"><i class="fa fa-commenting-o"></i><span>20</span></a>
   
   </div>
</div>
</div>
	 <% 
 }
   
 
%>

