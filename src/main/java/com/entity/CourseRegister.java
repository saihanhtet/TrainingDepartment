package com.entity;

public class CourseRegister {
	private String username;
	private String email;
	private int course_id;
	private String course_name;
	private double course_price;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public double getCourse_price() {
		return course_price;
	}
	public void setCourse_price(String course_price) {
		this.course_price = Double.parseDouble(course_price);
	}
}
