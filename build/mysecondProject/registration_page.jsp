<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>REGISTRATION || PAGE</title>
</head>
<body>
	<%@include file="nav_bar.jsp"%>

	<main style=" padding-top:5rem ">

	<div class="container ">



		<div class="row">

			<div class="col-md-4 offset-4 "
				style="background: #f5f5f5; padding: 2rem;">

				<div class="header" style="text-align: center; margin-bottom: 2rem;">
					<h3>Registration here</h3>
				</div>

				<div id="body-massage" style="text-align:center;text-color:green;"></div>

				<form action="Registration" method="post" class="form" id="myform">

					<div class="form-group">
						<label for="nameId">User name</label> <input type="text"
							name="user_name" class="form-control" id="nameId"
							placeholder="Enter user name">

					</div>
					<div class="form-group">
						<label for="emailId">Email address</label> <input type="email"
							name="user_email" class="form-control" id="emailId"
							placeholder="Enter email">

					</div>
					<div class="form-group">
						<label for="passwordId">Password</label> <input type="password"
							name="user_password" class="form-control" id="passwordId"
							placeholder="Password">
					</div>

					<div class="form-group">
						<label for="exampleInputPassword1">Gender</label> <input
							type="radio" id="exampleInputPassword1" name="gender"
							value="male">male <input type="radio"
							id="exampleInputPassword1" name="gender" value="female">female
					</div>
					<div class="form-group">
						<label for="exampleFormControlTextarea1">Example textarea</label>
						<textarea class="form-control" name="about"
							id="exampleFormControlTextarea1" rows="3"></textarea>
					</div>
					<div class=form-check>
						<input type="checkbox" name="check" class="form-check-input"
							id="examplecheck1"> <label class="form-check-label1"
							for="examplecheck1">agree terms and conditions</label>
					</div>
				<div class="container" style="text-align:center;display:none;" id="spin">
				<span class="fa fa-refresh fa-spin fa-3x"></span>
				<h4>please wait...</h4>
				
				</div>


					<button id="submit-btn" type="submit" class="btn btn-outline-dark ">Submit</button>
				</form>
			</div>
		</div>
	</div>

	</main>

	<script src="https://code.jquery.com/jquery-3.7.1.min.js"
		integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>

<!--swert alert cdn-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
	
	
	
	
	<script type="text/javascript">

$(document).ready(function(){
	console.log("loaded.....");
	
	$("#myform").on('submit',function(event){
		
		
		 $("#spin").show();
         $("#submit-btn").hide();
    	
    	event.preventDefault();
    	
    	console.log("submitted form....");
    	
    	
    	 let form = new FormData(this);
  
    	

    	//snt register servlet
    	
    	$.ajax({
    		url: "Registration",
    		data:form,
    		 type:'POST',
    		
    		 success:function(data){
                 console.log(data);
                 console.log("success...");
                 $("#spin").hide();
            	 $("#submit-btn").show();
                 if(data.trim()=='done'){
                	//sweetalert cdn and sweetAlert
                	 swal("successfully Register...please go to login page")
                	 .then((value) => {
                	   window.location="login_page.jsp";
                	 });
                }
                 else{
                	 swal("error in register...please check...");
                	 swal(data);
                 }
              
    		},
    	error:function(data){
    		 $("#spin").hide();
        	 $("#submit-btn").show();
        	 swal("error in register...please check...");
    	},
    	 processData:false,
         contentType:false
    		
    	});
    	
    });
});


</script>

</body>
</html>