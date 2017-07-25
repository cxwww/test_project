package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;

@EnableAutoConfiguration
public class RedisUtils {
	
	@Autowired
	private static StringRedisTemplate srt;
	
	public static String getTokenFromRedis(String key) {
		String value = srt.opsForValue().get(key);
		return value;
	}
	
}
