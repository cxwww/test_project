package com.example.demo.util;



import java.security.Key;
import java.sql.Date;
import java.util.Base64;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.message.module.ClientAuthModule;

import org.springframework.security.authentication.AuthenticationManager;

import com.example.demo.domain.AuthTokenDetailsDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JsonWebTokenUtility {
	
	private SignatureAlgorithm signatureAlgorithm;
	private Key secretKey;
	
	public JsonWebTokenUtility() {
		// 这里不是真正安全的实践
       // 为了简单，我们存储一个静态key在这里，
       // 在真正微服务环境，这个key将会被保留在配置服务器
       signatureAlgorithm = SignatureAlgorithm.HS512;
       String encodedKey ="L7A/6zARSkK1j7Vd5SDD9pSSqZlqF7mAhiOgRbgv9Smce6tf4cJnvKOjtKPxNNnWQj+2lQEScm3XIUjhW+YVZg==";
       secretKey = deserializeKey(encodedKey);
	}
	
	public String createJsonWebToken(AuthTokenDetailsDTO authTokenDetailsDTO) {
		String token = Jwts.builder().setSubject(authTokenDetailsDTO.getUserId())
				.claim("email", authTokenDetailsDTO.getEmail())
				.claim("roles", authTokenDetailsDTO.getRoleNames())
				.setExpiration(authTokenDetailsDTO.getExpirationDate())
				.signWith(this.getSignatureAlgorithm(), getSecretKey()).compact();
		return token;
	}
	
	private Key deserializeKey(String encodeKey) {
		byte[] decodeKey = Base64.getDecoder().decode(encodeKey);
		Key key = new SecretKeySpec(decodeKey, getSignatureAlgorithm().getJcaName()); //Returns the name of the JCA algorithm used to compute the signature
		return key;
	} 
	

	public SignatureAlgorithm getSignatureAlgorithm() {
		return signatureAlgorithm;
	}

	public Key getSecretKey() {
		return secretKey;
	}

	public AuthTokenDetailsDTO parseAndValidate(String token) {
		AuthTokenDetailsDTO authTokenDetailsDTO = null;
		try {
			Claims claims = Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token).getBody();
			String userId = claims.getSubject();
			String email = (String) claims.get("email");
			List roleNames = (List) claims.get("roles");
			Date expirationDate = (Date) claims.getExpiration();
			
			authTokenDetailsDTO = new AuthTokenDetailsDTO();
			authTokenDetailsDTO.setUserId(userId);
			authTokenDetailsDTO.setEmail(email);
			authTokenDetailsDTO.setExpirationDate(expirationDate);
			authTokenDetailsDTO.setRoleNames(roleNames);
		} catch (JwtException e) {
			e.printStackTrace();
		}
		return authTokenDetailsDTO;
	}
	
	private String serializeKey(Key key) {
		String encodeKey = Base64.getEncoder().encodeToString(key.getEncoded());
		return encodeKey;
	}
	
	
}
