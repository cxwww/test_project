package com.example.demo;

import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.dao.UserRepository;
import com.example.demo.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void TestUser() throws Exception {
//		userRepository.save(new User("a1","p1",0));
//		userRepository.save(new User("a1","p11",0));
//		userRepository.save(new User("a2","p2",0));
		
		User u1 = userRepository.findByName("a1");
		User u2 = userRepository.findById(1);
		userRepository.updateUserStatus(1, 1);
		long count = userRepository.countByName("a1");
		
		Assert.assertEquals(count, 1);
		Assert.assertEquals(2, userRepository.findAll().size());
	}
	
}
