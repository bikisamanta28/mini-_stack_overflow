package com.tech.blog.entities;

import java.util.Date;

import org.omg.CORBA.PRIVATE_MEMBER;

public class Post {

	private int pId;
	private String pTitle;
	private String pContent;
	private String pCode;
	private String pPic;
	private Date pDate;
	private int catId;
	private int userId;
	public Post() {
		super();
	}
	public Post(int pId, String pTitle, String pContent, String pCode, String pPic, Date pDate,int userId, int catId) {
		super();
		this.pId = pId;
		this.pTitle = pTitle;
		this.pContent = pContent;
		this.pCode = pCode;
		this.pPic = pPic;
		this.pDate = pDate;
		this.userId=userId;
		this.catId = catId;
		
	}
	public Post(String pTitle, String pContent, String pCode, Date pDate,int userId, int catId) {
		super();
		
		this.pTitle = pTitle;
		this.pContent = pContent;
		this.pCode = pCode;
		this.pDate = pDate;
		this.userId=userId;
		this.catId = catId;
		
	}
	public Post(String pTitle, String pContent, String pCode, String pPic, Date pDate,int userId,int catId) {
		super();
		this.pTitle = pTitle;
		this.pContent = pContent;
		this.pCode = pCode;
		this.pPic = pPic;
		this.pDate = pDate;
		this.userId=userId;
		this.catId = catId;
		
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getpTitle() {
		return pTitle;
	}
	public void setpTitle(String pTitle) {
		this.pTitle = pTitle;
	}
	public String getpContent() {
		return pContent;
	}
	public void setpContent(String pContent) {
		this.pContent = pContent;
	}
	public String getpCode() {
		return pCode;
	}
	public void setpCode(String pCode) {
		this.pCode = pCode;
	}
	public String getpPic() {
		return pPic;
	}
	public void setpPic(String pPic) {
		this.pPic = pPic;
	}
	public Date getpDate() {
		return pDate;
	}
	public void setpDate(Date pDate) {
		this.pDate = pDate;
	}
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	
	
	
}
