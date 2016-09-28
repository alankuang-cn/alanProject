package com.example.model;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3868269731826822792L;
	
	private Integer id;
	
	private String name;
	
	private String password;
	
	public User(){
		
	}
	
	public User(Integer id ,String name,String password){
		this.id = id;
		this.name = name;
		this.password = password;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
