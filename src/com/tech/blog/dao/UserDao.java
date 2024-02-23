package com.tech.blog.dao;


import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.tech.blog.entities.User;

import java.sql.*;



public class UserDao {

private Connection con;

public UserDao(Connection con) {
	super();
	this.con = con;
}

public boolean saveUser(User user) throws SQLException {
	
	boolean f=false;
	
	try {
	  String q="insert into user(name,email,password,gender,about,rDate) values(?,?,?,?,?,?)";
	  PreparedStatement pstmt = con.prepareStatement(q);
	  
	   pstmt.setString(1,user.getName());
       pstmt.setString(2,user.getEmail());
       pstmt.setString(3,user.getPassword());
       pstmt.setString(4,user.getGender());
       pstmt.setString(5,user.getAbout());
       pstmt.setString(6,user.getRdate());
      
       pstmt.executeUpdate();
       f=true;
	  
	}catch (Exception e) {
	e.printStackTrace();
	System.out.print("error: " + e.getMessage());
	}
	return f;
}
public User getEmailAndPassword(String email,String password)throws SQLException {
	
	
	//System.out.println("email is "+ email);
	//System.out.println("password is "+ password);
	User user=null;
	try {
		String q="SELECT * FROM user WHERE email=? AND password=?";
		
		
			     PreparedStatement pstmt = con.prepareStatement(q);
			     
			     pstmt.setString(1,email);
			     pstmt.setString(2,password);
			    
			     ResultSet rs = pstmt.executeQuery();
	
		
			     if(rs.next()) {
			    	    // Example: Retrieve data from the first column
			    	    String retrievedName = rs.getString("name"); // Or use column name
			    	    String retrievedEmail = rs.getString("email");
			    	    String retrievedGender = rs.getString("gender");
			    	    String retrievedAbout = rs.getString("about");
			    	    String retrieveDate=  rs.getString("rdate");
			    	    String retrieveProfile=  rs.getString("profile");
			    	    String retrivePassword=rs.getString("password");
			    	    int retrieveId=  rs.getInt("id");
			    	    
			    	 //   System.out.println("Name: " + retrievedName + ", Email: " + retrievedEmail + ", Password: " + retrievedGender+",abour:"+retrievedAbout);
			    	    // Process the retrieved data
			    	    user=new User();
			    	    user.setId(retrieveId);
			    	    user.setName(retrievedName);
			    	    user.setEmail(retrievedEmail);
			    	    user.setGender(retrievedGender);
			    	    user.setAbout(retrievedAbout);
			    	   user.setRdate(retrieveDate);
			    	   user.setProfile(retrieveProfile);
			    	   user.setPassword(retrivePassword);
			    	}
			     else {
			    	 System.out.println("error in userdao to login");
			     }
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	return user;
}
//get user details

public User getUserDetails(int userId){
	User user=null;
	try {
		
		String q="select * from user where id=?";
		 PreparedStatement pstmt = con.prepareStatement(q);
	     pstmt.setInt(1,userId);
	     ResultSet rs = pstmt.executeQuery();
	     if(rs.next()) {
	    	 String name=rs.getString("name");
	    	 String email=rs.getString("email");
	    	 String about=rs.getString("about");
	    	 user=new User();
	    	 user.setName(name);
	    	 user.setEmail(email);
	    	 user.setAbout(about);
	     }
	} catch (Exception e) {
		e.printStackTrace();
	}
return user;
}

public boolean getEdit(User user) {
	
	boolean req=false;
	try {
		
		String quary="update user set name=? , email=? , password=? , gender=? ,about=? , profile=? where  id =?";
		PreparedStatement pre=con.prepareStatement(quary);
		pre.setString(1, user.getName());
		pre.setString(2, user.getEmail());
		pre.setString(3,user.getPassword());
		pre.setString(4, user.getGender());
		pre.setString(5,user.getAbout());
		pre.setString(6, user.getProfile());
		pre.setInt(7,user.getId());
		
		pre.executeUpdate();
		req=true;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return req;
}
}
