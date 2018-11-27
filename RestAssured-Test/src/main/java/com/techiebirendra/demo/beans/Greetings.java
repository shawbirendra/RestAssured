package com.techiebirendra.demo.beans;

public class Greetings {
	String name;
	int id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Greetings() {
		// TODO Auto-generated constructor stub
	}
	public Greetings(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}
	
}
