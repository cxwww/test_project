package com.example.demo.controller;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

	
	@RequestMapping("/uid")
	public String uid(HttpSession session) {
		UUID uid = (UUID) session.getAttribute("uid");
		if(uid == null) {
			uid = UUID.randomUUID();
		}
		session.setAttribute("uid", uid);
		return session.getId()+",uid:" + uid.toString();
	}
}
