package com.tech.blog.dao;

import java.sql.*;

public class LikeDao {

	Connection con;

	public LikeDao(Connection con) {
		super();
		this.con = con;
	}

	public boolean postLike(int userId, int postId) {
       boolean f = false;
		try {
			String q = "insert into lportal.liked(userId,pid) values(?,?)";
			PreparedStatement pre = con.prepareStatement(q);
			pre.setInt(1, userId);
			pre.setInt(2, postId);
            pre.executeUpdate();
			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
        return f;
	}
	
	public int getCountLike(int pid) {
		int f=0;
		try {
			String q="select count(*) from lportal.liked where pid=?";
			PreparedStatement pre = con.prepareStatement(q);
			pre.setInt(1, pid);
			ResultSet resultSet= pre.executeQuery();
			if(resultSet.next()) {
				
				f=resultSet.getInt("count(*)");
			}
		  } catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	public boolean getLikeOrNot(int userId , int pid) {
		
		boolean f=false;
		try {
			
			String q="select * from lportal.liked where userId=? and pid=?";
			PreparedStatement pre = con.prepareStatement(q);
			pre.setInt(1, userId);
			pre.setInt(2, pid);
			ResultSet resultSet= pre.executeQuery();
			if(resultSet.next()) {
				f=true;
				return f;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}

   public boolean getDeleteByPid(int pid) {
	   
	   boolean f=false;
	   
	   try {
		   String q="delete from lportal.liked where pid=?";
		   PreparedStatement pre = con.prepareStatement(q);
			pre.setInt(1, pid);
			pre.executeUpdate();
			f=true;
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	   return f;
   }
}
