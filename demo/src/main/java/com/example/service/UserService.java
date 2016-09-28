package com.example.service;

import java.util.List;

import com.example.model.User;

public interface UserService {

	public List<User> getAllUser();
	
	public Integer insertUser(String name,String password);
	
	public User tt();
	
 }
