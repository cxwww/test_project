package com.example.demo.token.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.token.component.TokenAuthorization;
import com.example.demo.token.domain.TokenModel;
import com.example.demo.token.domain.TokenRequestParam;
import com.example.demo.token.domain.TokenResponseInfo;
import com.example.demo.token.inter.TokenManager;

@RestController
public class TokenController {
	
	@Autowired
	private RestTemplate rest;
	
	@Autowired
	private TokenManager tokenManager;
	
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
	
	@TokenAuthorization
	@RequestMapping(value="/data/tokenC", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getTokenC(@RequestBody(required=true) TokenRequestParam rp,HttpSession httpSession) {
		String key = rp.getUsername()+"_"+httpSession.getId();
        TokenModel model = tokenManager.createToken(key);
        return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	
	
	
}
