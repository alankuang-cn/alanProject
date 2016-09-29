package com.example.model;

import java.io.Serializable;

public class Test implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1344668215100864152L;

	private Integer id;
	
	private String name;

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
	
	
}
