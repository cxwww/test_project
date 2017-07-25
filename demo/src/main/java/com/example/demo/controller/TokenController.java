package com.example.demo.controller;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.domain.TokenRequestParam;
import com.example.demo.domain.TokenResponseInfo;

@RestController
public class TokenController {
	
	@Autowired
	private RestTemplate rest;
	
	@Autowired
	private StringRedisTemplate srt;
	
	private static final String REMOVE_URL = "https://open.youzan.com/oauth/token";
	
	/**
	 * curl -X POST http://open.youzan.com/oauth/token 
	 * -H 'content-type: application/x-www-form-urlencoded' 
	 * -d 'client_id=testclient&client_secret=testclientsecret&grant_type=silent&kdt_id=88888'
	 */
	@RequestMapping(value="/data/token", method=RequestMethod.GET)
	public String getToken() {
		TokenRequestParam requestParam = new TokenRequestParam();
		ResponseEntity responseEntity = this.rest.postForEntity(REMOVE_URL, requestParam, TokenResponseInfo.class);
		TokenResponseInfo responseInfo = (TokenResponseInfo) responseEntity.getBody();
		System.out.println(responseInfo.toString());
		return responseInfo.toString();
	}
	
	@RequestMapping(value="/data/tokenC", method={RequestMethod.GET, RequestMethod.POST})
	public String getTokenC(@RequestBody(required=true) TokenRequestParam rp, @RequestParam(required=true) String username,HttpSession httpSession) {
		if(httpSession==null) return null;
		String value = rp.getClient_id()+"."+rp.getClient_secret()+"."+rp.getGrant_type()+"."+rp.getKdt_id();
		String key = username+"_"+httpSession.getId();
		srt.opsForValue().set(key, value,6,TimeUnit.SECONDS);
		System.out.println("value:"+srt.opsForValue().get(key));
		return key;
	}
	
	
	
	
}
