package com.example.util;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisApi implements Serializable{

	private static final long serialVersionUID = -5834577753356099467L;
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	/**
	 * 保存一个 key-value形式的key
	 * @param key
	 * @param value
	 */
	public void setString(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}
	/**
	 * 	保存一个 key-value形式的key 带过期时间
	 * @param key
	 * @param value
	 * @param timeOut
	 * @param time
	 */
	public void setStringExpireKey(String key, String value, Long timeOut, TimeUnit time) {
		// TimeUnit default Second
		if (null == time) {
			time = TimeUnit.SECONDS;
		}
		redisTemplate.opsForValue().set(key, value, timeOut, time);
	}
	/**
	 * 获取Redis String value
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		if (null == redisTemplate.opsForValue().get(key)) {
			return null;
		}
		return redisTemplate.opsForValue().get(key);
	}
	
	/**
	 * 获取 redis key 过期时间， 不存在/返回-1
	 * @param key
	 * @return
	 */
	public Integer getExpire(String key) {
		int timeOut = -1;
		if (redisTemplate.getExpire(key) > 0) {
			timeOut = redisTemplate.getExpire(key).intValue();
		}
		return timeOut;
	}

}
