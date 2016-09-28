package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@RequestMapping("/json")
	public String json() throws Exception {
		
		throw new RuntimeException("发生错误2");
	}
}
