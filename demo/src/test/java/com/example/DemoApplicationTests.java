package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.util.RedisApi;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	
	@Test
	public void contextLoads() {
		
		RedisApi r = new RedisApi();
		
		System.out.println("不存在key过期时间 :" +r.getExpire("123123"));
	}

}
