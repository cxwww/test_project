package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.domain.TokenRequestParam;
import com.example.demo.domain.TokenResponseInfo;

@RestController
@EnableAutoConfiguration
public class DataController {
	
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
	
	//youzan.ebiz.stats.referral.pages.overview
	@RequestMapping(value="/data/pageOverview/${size}/${page_id}/${page}/${token}", method=RequestMethod.GET)
	public void pageOverview(@RequestParam(required=true) Integer size, @RequestParam(required=true) Integer page_id
			,@RequestParam(required=true) Integer page,@RequestParam(required=true) String token) {
		//token check redis
		
	}
	
	@RequestMapping(value="/data/tokenC", method=RequestMethod.POST)
	public String getTokenC(@RequestBody TokenRequestParam rp) {
//		if(rp == null || rp.isNull()) {
//			return null;
//		}
		String codeStr = rp.getClient_id()+"."+rp.getClient_secret()+"."+rp.getGrant_type()+"."+rp.getKdt_id();
		srt.opsForValue().set(codeStr, codeStr);
		return codeStr;
	}
	
	
	
	
}
