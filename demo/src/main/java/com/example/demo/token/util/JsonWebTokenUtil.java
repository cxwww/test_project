package com.example.demo.token.util;

import java.security.Key;
import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;

import com.example.demo.token.domain.TokenRequestParam;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JsonWebTokenUtil {
	private static SignatureAlgorithm signatureAlgorithm;
	private static Key secretKey;
	
	static {
		// 这里不是真正安全的实践
		// 为了简单，我们存储一个静态key在这里，
		// 在真正微服务环境，这个key将会被保留在配置服务器
		signatureAlgorithm = SignatureAlgorithm.HS512;
		String encodedKey ="L7A/6zARSkK1j7Vd5SDD9pSSqZlqF7mAhiOgRbgv9Smce6tf4cJnvKOjtKPxNNnWQj+2lQEScm3XIUjhW+YVZg==";
		secretKey = deserializeKey(encodedKey);
	}
	
	public static String createJsonWebToken(TokenRequestParam tRequestParam) {
		String token = Jwts.builder().setSubject(tRequestParam.getClient_id())
				.claim("client_secret", tRequestParam.getClient_secret())
				.claim("grant_type", tRequestParam.getGrant_type())
				.claim("kdt_id", tRequestParam.getKdt_id())
				.signWith(getSignatureAlgorithm(), getSecretKey()).compact();
		return token;
	}
	
	private static Key deserializeKey(String encodeKey) {
		byte[] decodeKey = Base64.getDecoder().decode(encodeKey);
		Key key = new SecretKeySpec(decodeKey, getSignatureAlgorithm().getJcaName()); //Returns the name of the JCA algorithm used to compute the signature
		return key;
	} 
	

	public static SignatureAlgorithm getSignatureAlgorithm() {
		return signatureAlgorithm;
	}

	public static Key getSecretKey() {
		return secretKey;
	}

	public TokenRequestParam parseAndValidate(String token) {
		TokenRequestParam tRequestParam = null;
		try {
			Claims claims = Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token).getBody();
			String client_id = claims.getSubject();
			String client_secret = (String) claims.get("client_secret");
			String grant_type = (String) claims.get("grant_type");
			String kdt_id = (String) claims.get("kdt_id");
			
			tRequestParam = new TokenRequestParam();
			tRequestParam.setClient_id(client_id);
			tRequestParam.setClient_secret(client_secret);
			tRequestParam.setGrant_type(grant_type);
			tRequestParam.setKdt_id(kdt_id);
			
		} catch (JwtException e) {
			e.printStackTrace();
		}
		return tRequestParam;
	}
	
	private String serializeKey(Key key) {
		String encodeKey = Base64.getEncoder().encodeToString(key.getEncoded());
		return encodeKey;
	}
}
