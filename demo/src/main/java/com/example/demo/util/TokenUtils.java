package com.example.demo.util;


import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.domain.TokenRequestParam;



public class TokenUtils {
	
	 public static String createToken(Map<String, Object> header, TokenRequestParam rp) throws Exception{
		 Map<String, Object> map = null;
		 if(null == header) {
			 map = new HashMap<String, Object>();  
			 map.put("alg", "HS256");  
			 map.put("typ", "JWT");  
		 }else {
			 map = header;
		 }
         String token = JWT.create()  
                .withHeader(map)//header  
                .withClaim("client_id", rp.getClient_id())//payload 
                .withClaim("client_secret", rp.getClient_secret()) 
                .withClaim("grant_type", rp.getGrant_type()) 
                .withClaim("kdt_id", rp.getKdt_id())
                .sign(Algorithm.HMAC256("secret"));//加密  
         return token;  
	 }  
	 
	 public static void verifyToken(String token,String key) throws Exception{  
		 JWTVerifier verifier = JWT.require(Algorithm.HMAC256(key))  
				 .build();   
		 DecodedJWT jwt = verifier.verify(token);  
		 Map<String, Claim> claims = jwt.getClaims();  
		 System.out.println(claims.get("name").asString());  
	 }  
	 
	 public static void main(String[] args) throws Exception {
		System.out.println(TokenUtils.createToken(null, new TokenRequestParam()));
	}
}
