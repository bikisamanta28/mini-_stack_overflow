<%@page import="com.tech.blog.entities.Message"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>Insert title here</title>
</head>
<body>
<%@include file="nav_bar.jsp" %>

<main style=" padding-top:5rem ">

<div class="container ">



  <div class="row" >
  
   <div class="col-md-4 offset-4 " style="background:#f5f5f5 ; padding:2rem; ">
   
   <div class="header" style="text-align:center; margin-bottom:2rem;">
    <h3>LogIn here</h3>
   </div>
   
   <%
   Message massage=(Message)session.getAttribute("msgSession");
   
   if(massage!=null){
	   %>
	   <div class="alert <%=massage.getCss() %>" role="alert">
         <%=massage.getMessage() %>.
         </div>
	   
	   <% 
	   session.removeAttribute("msgSession");
	  
   }
  %>
     <%
   Message massageLogout=(Message)session.getAttribute("logout");
   
   if(massageLogout!=null){
	   %>
	   <div class="alert <%=massageLogout.getCss() %>" role="alert">
         <%=massageLogout.getMessage() %>.
         </div>
	   
	   <% 
	   session.removeAttribute("logout");
	  
   }
  %>
   
   <div class=body></div>
  
  <form action="login" method="post">
  <div class="form-group">
    <label for="emailId">Email address</label>
    <input type="email" class="form-control" name="user_email" id="emailId" aria-describedby="emailHelp" placeholder="Enter email">
   
  </div>
  <div class="form-group">
    <label for="passwordId">Password</label>
    <input type="text" name="user_password" class="form-control" id="passwordId" placeholder="Password">
  </div>
  
  <button type="submit" class="btn btn-outline-dark ">Submit</button>
</form>
  </div>
</div>
</div>

</main>

<script
  src="https://code.jquery.com/jquery-3.7.1.min.js"
  integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
  crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>