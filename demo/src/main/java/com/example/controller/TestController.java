package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.mapper.slave.TestMapper;
import com.example.model.Test;

@RestController
public class TestController {
	
	@Autowired
	private TestMapper testMapper;
	
	@RequestMapping("/json")
	public String json() throws Exception {
		
		throw new RuntimeException("发生错误2");
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public List<Test> getAll(){
		return testMapper.getAll();
		
	}
}
