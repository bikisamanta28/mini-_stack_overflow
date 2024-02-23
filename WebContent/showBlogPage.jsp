<%@page import="com.tech.blog.dao.LikeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="com.tech.blog.entities.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tech.blog.helper.ConncetionProvider"%>
    <%@page import="com.tech.blog.entities.User"%>
    <%@page import ="com.tech.blog.entities.Message" %>
    <%@page import ="com.tech.blog.dao.PostDao" %>
    <%@page import="com.tech.blog.entities.Post"%>
    <%@page import="com.tech.blog.dao.UserDao"%>
    <%@page errorPage="error_page.jsp" %>
  
    
    <%
 User user=(User)session.getAttribute("currentUser");
if(user==null)
{
	response.sendRedirect("login_page.jsp");
	return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>Insert title here</title>
</head>
<body>

<!-- navbar start  -->
<nav class="navbar navbar-expand-lg navbar-dark primary-color">
  <a class="navbar-brand ms-3" href="index.jsp"><span class="fa fa-bandcamp"></span>TechIndia</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="profile.jsp">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="#">Link</a>
      </li>
      
       <li class="nav-item active">
        <a class="nav-link" href="#!" " data-toggle="modal" data-target="#addpostModal"><span class="fas fa-campground "></span>Do Post</a>
      </li>
   </ul>
    <ul class="navbar-nav ms-auto mr-3">
            <li class="nav-item active">
                <a class="nav-link " href="#!" data-toggle="modal" data-target="#profileeModal"><span class="fa fa-user-circle"></span><%=user.getName() %></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link me-3" href="logout"><span class="fa fa-user-plus"></span>Logout</a>
            </li>
        </ul>
   
  </div>
</nav>
<%
Message mess=(Message)session.getAttribute("postblog");
if(mess!=null){
%>
<div class="alert <%=mess.getCss() %>">
  <%=mess.getMessage() %>
</div>
<%
session.removeAttribute("postblog");
}
%>

<%
Message message=(Message)session.getAttribute("msg");
if(message!=null){
%>
<div class="alert <%=message.getCss() %>">
  <%=message.getMessage() %>
</div>
<%
session.removeAttribute("msg");
}

%>
<!--navbar end  -->

<!-- main view start here -->
<%
int pid= Integer.parseInt(request.getParameter("pid"));

%>
<%
 PostDao postDao=new PostDao(ConncetionProvider.getConnection());
UserDao userdao=new UserDao(ConncetionProvider.getConnection());

 Post post=postDao.getPostByPostId(pid);
 
 User u=userdao.getUserDetails(post.getUserId());

%>



  <div class="container mt-4">
  
    <div class="card">
    
    <img class="card-img-top" src="blogPics/<%=post.getpPic() %>" alt="Card image cap">
    <p class="m-2"><a href=#!><%=u.getName() %></a></p>
    <div class="card-header">
    
    <h3 class="card-title"><%=post.getpTitle() %></h3>
    </div>
    <div class="card-body">
    
     <p class="card-text"><%=post.getpContent() %></p>
    
    
    
    </div>
    <%
    
    LikeDao like=new LikeDao(ConncetionProvider.getConnection());
    
    
    
    
    %>
    
    <div class="card-footer">
    
      <a href="#!" onclick="doLike(<%=post.getUserId()%>,<%=post.getpId()%>)" class="btn btn-outline-primary btn-sm"><i class="fa fa-thumbs-o-up"></i><span class="like-counter"><%=like.getCountLike(post.getpId()) %></span></a>
       <a href="#!" class="btn btn-outline-primary btn-sm"><i class="fa fa-commenting-o"></i><span>20</span></a>
    
    
    
    </div>
    
    
    
    </div>
  
  </div>


<!-- main view end here -->


<!--modal profile details start -->

<div class="modal fade" id="profileeModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Profile Details</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body container text-center"">
         <img src="pics/<%=user.getProfile() %>"  class="img-fluid" style="border-radius:50%;max-width: 150px;">
         <h4><%=user.getName() %></h4>
         
         <div id="profile-details">
  <table class="table">
    <tbody>
    <tr>
      <th scope="row">Id :</th>
      <td><%=user.getId() %></td>
    </tr>
    <tr>
      <th scope="row">Email :</th>
      <td><%=user.getEmail() %></td>
    </tr>
     <tr>
      <th scope="row">password :</th>
      <td><%=user.getPassword() %></td>
    </tr>
    <tr>
      <th scope="row">Gender :</th>
      <td><%=user.getGender() %></td>
   </tr>
      <tr>
      <th scope="row">Massage</th>
      <td><%=user.getAbout() %></td>
   </tr>
     <tr>
      <th scope="row">Date </th>
      <td><%=user.getRdate() %></td>
   </tr>
  </tbody>
</table>
</div>

<div id="profile-edit" style="display:none">

<form class="form-control" action="EditServlet" method="post" enctype="multipart/form-data">
<h2>Edit You Form Carefully</h2>
     <table class="table">
     
     <tr>
          <th>Id :</th>
          <th><%=user.getId() %></th>
     </tr>
     <tr>
     <th>Email :</th>
     <td><input type="email" name="user_email" class="form-control" value="<%=user.getEmail() %>"></td>
     </tr>
     <tr>
     <th>Name :</th>
     <td><input type="text" name="user_name" class="form-control" value="<%=user.getName() %>"></td>
     </tr>
     <tr>
     <th>Password :</th>
     <td><input type="text" name="user_password" class="form-control" value="<%=user.getPassword()%>"></td>
     </tr>
       <th>Gender :</th>
     <td><%=user.getGender() %></td>
     </tr>
    <tr>
      <th>Massage :</th>
     <td>
    <textarea rows="3" class="form-control" name="user_about"><%=user.getAbout() %></textarea>
     </td>
    </tr>
    
    <tr>
     <th>Profile Picture:</th>
     <td>
     
     <input type="file" name="user_profile" class="form-control" value="blogpost.png" ></td>
     </tr>
     </table>
     
     <div class="container">
     <button class="btn btn-primary">Save</button>
     </div>
   </form>
</div>
      </div>
      <div class="modal-footer ">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="edit-btn">EDIT</button>
      </div>
    </div>
  </div>
</div>

<!-- profile details end -->

<!-- postblog modal start  -->

<div class="modal fade" id="addpostModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Provide the post details</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      
      <!-- post form here.............................................. -->
      <form id="postId" action="AddPostServlet" method="post" enctype="multipart/form-data">
      
      <div class="form-group">
      <select class="form-control" name="pselect">
      <option selected disabled>---select catagories---</option>
      
      <% PostDao p=new PostDao(ConncetionProvider.getConnection());
       ArrayList<Category>list=p.getAllCatagories();
       for(Category c:list)
       {
       %>
       <option value="<%=c.getCid()%>"><%=c.getcName()%></option>
        
         <%
       }
         %>
      </select>
      </div>
      
      <div class="form-group">
      <input type="text" name="pname" placeholder="Enter post title" class="form-control"/>
      </div>
      <div class="form-group">
     <textarea class="form-control" name="pcontent" style="height:200px" placeholder="Enter your content"></textarea>
      </div>
      <div class="form-group">
     <textarea class="form-control" name="pcode" style="height:200px" placeholder="Enter your programe(if any)"></textarea>
      </div>
      <div class="form-group">
      <label>Select your pic:</label><br>
    <input type="file" name="pfile" placeholder="enter your pic"/>
      </div>
       <div class="container text-center ">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary">Post</button>
      </div>
      
      </form>
      </div>
     
    </div>
  </div>
</div>

<!-- postblog modal end here -->

<script src="https://code.jquery.com/jquery-3.7.1.min.js"
  integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
  crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
	




<script type="text/javascript">


$(document).ready(function(){
	
	let editValue=false;
	
	$("#edit-btn").click(function(){
		
		if(editValue==false){
			
			$("#profile-details").hide();
			$("#profile-edit").show();
			
			editValue=true;
			$(this).text("Back")
		}
		else{
			
			$("#profile-details").show();
			$("#profile-edit").hide();
			editValue=false;
			$(this).text("Edit")
		}
	});
	
});
</script>

<!-- data post -->

<script type="text/javascript">
 $(document).ready(function(){
	$('#postId').on('submit',function(event){
		event.preventDefault();
		
		 let form = new FormData(this);
		$.ajax({
			url:'AddPostServlet',
			data:form,
			type:'post',
			success:function(data){
				if(data.trim()=='done'){
					 swal("post succesfully! ")
				}
				else{
					 swal("error is blog posting! ")
				}
			},
			error:function(data){
				console.log("error in post submission");
			},
			 processData:false,
	         contentType:false
		})
	}); 
 })
</script>
<script type="text/javascript" src="js/myjs.js"></script>

</body>
</html>