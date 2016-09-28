package com.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.UserService;
import com.example.view.ResultUtil;

@RestController
public class HelloController {

	@Autowired
	private UserService userService;

	@RequestMapping("/hello")
	@ResponseBody
	public Map<String, Object> sayHello() {

		ResultUtil result = new ResultUtil();
		return result.getResult(userService.getAllUser());
	}

	@RequestMapping("/insert")
	@ResponseBody
	public Map<String, Object> insert(String name, String password) {

		Assert.hasText(name, "名字不能为空");
		Assert.hasText(password, "密码不能为空");

		ResultUtil result = new ResultUtil();

		return result.getResult(userService.insertUser(name, password));

	}

}
