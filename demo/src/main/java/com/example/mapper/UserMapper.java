package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.model.User;

@Mapper
public interface UserMapper {
	
	
	List<User> getAllUser();
	
	Integer insertUser(User user);
	
	Boolean batchInsert(List<User> list);

}
