package com.entity;

public class Course {
	private int id;
	private String name;
	private String price;

	public int getId() {
		return id;
	}

	public Course() {
	}

	public Course(String name, double price) {
		this.name = name;
		this.price = Double.toString(price);
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
