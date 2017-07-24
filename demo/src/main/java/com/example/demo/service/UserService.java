package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserRepository;
import com.example.demo.domain.User;
import com.example.demo.page.PageModel;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	
	public User getUser() {
		return userRepository.findById(1);
	}
	
	public Page<User> findAll() {
//		int page=1,size=10;
//	    Sort sort = new Sort(Direction.DESC, "id");
//	    Pageable pageable = new PageRequest(page, size, sort);
		return userRepository.findAll(new PageModel());
	}
	
}
