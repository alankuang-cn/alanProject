package com.example.mapper.master;

import java.util.List;

import com.example.model.User;

public interface UserMapper {
	
	
	List<User> getAllUser();
	
	Integer insertUser(User user);
	
	Boolean batchInsert(List<User> list);

}
