package com.tech.blog.helper;
import java.sql.*;

public class ConncetionProvider {

	private static Connection con;
	
	public static Connection getConnection() {
		
		try {
			if(con==null) {
				//driver class load
				Class.forName("com.mysql.cj.jdbc.Driver");
				//create a conncetion
				con=DriverManager.getConnection("jdbc:mysql://localhost:3300/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false","root","test");
		     }
	   }catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return con;
	}
}
