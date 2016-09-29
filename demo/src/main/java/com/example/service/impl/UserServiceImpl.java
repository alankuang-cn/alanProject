package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.mapper.master.UserMapper;
import com.example.model.User;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> getAllUser() {
		return userMapper.getAllUser();
	}

	@Override
	public Integer insertUser(String name,String password) {
		
		User u = new User();
		u.setName(name);
		u.setPassword(password);
		
		userMapper.insertUser(u);
		
		return u.getId();
	}

	@Override
	@Cacheable(value = "usercache",keyGenerator = "wiselyKeyGenerator")  
    public User tt(){  
        System.out.println("无缓存的时候调用这里");  
        
        return new User(1,"123123","333");  
        		
    }  


}
