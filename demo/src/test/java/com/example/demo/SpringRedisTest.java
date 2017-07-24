package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class SpringRedisTest {
	
	@Autowired
//	private StringRedisTemplate srt;
	
//	@Autowired
//	private RedisTemplate rt;
	
	@Test
	public void test() {
//		srt.opsForValue().set("k1", "v1");
//		Assert.assertEquals("v2", srt.opsForValue().get("k1"));
	}
	
}
