package com.tech.blog.entities;

public class Message {
private String message;
private String type;
private String css;
public Message(String message, String type, String css) {
	super();
	this.message = message;
	this.type = type;
	this.css = css;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getCss() {
	return css;
}
public void setCss(String css) {
	this.css = css;
}

}
