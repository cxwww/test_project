package com.example.demo.component;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import com.example.demo.domain.AuthTokenDetailsDTO;
import com.example.demo.util.JsonWebTokenUtility;

/**
 * 需要一个授权提供者读取这个记号，然偶验证它，然后转换为我们自己的定制授权对象
 *
 */
public class JsonWebTokenAuthenticationProvider implements AuthenticationProvider{
	
	private JsonWebTokenUtility tokenService = new JsonWebTokenUtility();

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Authentication authenticationUser = null;
		//Only process the PreAuthenticatedAuthenticationToken
		//authentication.getPrincipal() The identity of the principal being authenticated  被认证的身份主体
		if(authentication.getClass().isAssignableFrom(PreAuthenticatedAuthenticationToken.class) && authentication.getPrincipal() != null) {
			String tokenHeader = (String) authentication.getPrincipal();
			UserDetails userDetails = parseToken(tokenHeader);
			if(userDetails != null) {
				authenticationUser = new JsonWebTokenAuthentication(userDetails, tokenHeader);
			}
		}
		return null;
	}
	
	private UserDetails parseToken(String tokenHeader) {
		UserDetails principal = null;
		AuthTokenDetailsDTO authTokenDetailsDTO = tokenService.parseAndValidate(tokenHeader);
		if(authTokenDetailsDTO != null) {
			List<GrantedAuthority> authorities = authTokenDetailsDTO.getRoleNames()
					.stream()
					.map(roleName -> new SimpleGrantedAuthority(roleName))  // every roleName turn to  SimpleGrantedAuthority object
					.collect(Collectors.toList());
			principal = new User(authTokenDetailsDTO.getEmail(), "", authorities);
		}
		return principal;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.isAssignableFrom(PreAuthenticatedAuthenticationToken.class) 
				|| authentication.isAssignableFrom(JsonWebTokenAuthentication.class);
	}

}
