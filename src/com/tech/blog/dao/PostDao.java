package com.tech.blog.dao;

import com.tech.blog.entities.Category;
import com.tech.blog.entities.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PostDao {

	private Connection con;

	public PostDao(Connection con) {

		this.con = con;
	}

	public ArrayList<Category> getAllCatagories() {

		ArrayList<Category> list = new ArrayList<>();

		try {

			String quary = "select * from categories";

			java.sql.Statement statement = this.con.createStatement();

			ResultSet resultSet = statement.executeQuery(quary);

			while (resultSet.next()) {

				int cid = resultSet.getInt("cid");
				String cName = resultSet.getString("cName");
				String cDescription = resultSet.getString("cDescription");

				Category category = new Category(cid, cName, cDescription);
				list.add(category);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public boolean savePost(Post post) {
		boolean f = false;
		try {
			String q = "insert into posts(pTitle,pContent,pCode,pPic,userId,catId) values(?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(q);
			pstmt.setString(1, post.getpTitle());
			pstmt.setString(2, post.getpContent());
			pstmt.setString(3, post.getpCode());
			pstmt.setString(4, post.getpPic());
			if (post.getpPic() != null) {
				pstmt.setInt(5, post.getUserId());
			}
			pstmt.setInt(6, post.getCatId());
			pstmt.executeUpdate();
			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;

	}

	// get all blog post
	public List<Post> getAllBlogPost() {

		List<Post> list = new ArrayList<>();

		try {

			String q = "select * from posts";
			java.sql.Statement statement = this.con.createStatement();

			ResultSet resultSet = statement.executeQuery(q);

			while (resultSet.next()) {

				int pid = resultSet.getInt("pid");
				String pTitle = resultSet.getString("pTitle");
				String pContent = resultSet.getString("pContent");
				String pCode = resultSet.getString("pCode");
				String pPic = resultSet.getString("pPic");
				int userId = resultSet.getInt("userId");
				int catId = resultSet.getInt("catId");

				Post post = new Post(pid, pTitle, pContent, pCode, pPic, null, userId, catId);
				list.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

//  get blog post by catagory id

	public List<Post> getBlogPostByCatId(int catId) {

		List<Post> list = new ArrayList<>();

		try {

			String q = "select * from posts where catId=?";

			PreparedStatement pstmt = con.prepareStatement(q);
			pstmt.setInt(1, catId);
			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				int pid = resultSet.getInt("pid");
				String pTitle = resultSet.getString("pTitle");
				String pContent = resultSet.getString("pContent");
				String pCode = resultSet.getString("pCode");
				String pPic = resultSet.getString("pPic");
				int userId = resultSet.getInt("userId");

				Post post = new Post(pid, pTitle, pContent, pCode, pPic, null, userId, catId);
				list.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	public Post getPostByPostId(int pid) {

		Post post = null;

		try {

			String q = "select * from posts where pid=?";
			PreparedStatement pstmt = con.prepareStatement(q);
			pstmt.setInt(1, pid);
			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				String pTitle = resultSet.getString("pTitle");
				String pContent = resultSet.getString("pContent");
				String pCode = resultSet.getString("pCode");
				String pPic = resultSet.getString("pPic");
				int userId = resultSet.getInt("userId");

				post = new Post(pid, pTitle, pContent, pCode, pPic, null, userId, pid);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return post;
	}

}
