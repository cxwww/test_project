package com.example.demo.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.stereotype.Component;


@Component
public class JsonWebTokenAuthenticationFilter extends RequestHeaderAuthenticationFilter {
	
	public JsonWebTokenAuthenticationFilter() {
		//Dont't throw exceptions if the header is missing
		this.setExceptionIfHeaderMissing(false);
		
		//This is the request header it will look for
		this.setPrincipalRequestHeader("Authroization");
	}
	
	/**
	 * 在这里，头部信息将被转换为Spring Authentication 对象，名称为PreAuthenticatedAuthenticationToken
	 */
	@Override
	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}
}
