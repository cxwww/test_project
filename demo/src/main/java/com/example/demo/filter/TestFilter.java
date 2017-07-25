package com.example.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;


@Order(1)
@WebFilter(filterName="testFilter", urlPatterns="/data/shop/*")
public class TestFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest srequest, ServletResponse sresponse,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) srequest;
		HttpServletResponse response = (HttpServletResponse) sresponse;
		HttpSession session = request.getSession(true);
		
		System.out.println("this is MyFilter, uri : " + request.getRequestURI());
		System.out.println("this is MyFilter, url : " + request.getRequestURL());
		
		//checkToken
		String username = (String) session.getAttribute("username");
		String sessionId = session.getId();
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(sessionId)) {
			response.getWriter().write("username or sessionId is null");
			response.sendRedirect("/login.jsp");
			if(!response.isCommitted())
				filterChain.doFilter(srequest, sresponse);
			return;
		}
		String tokenP = request.getParameter("token");
//		String token = RedisUtils.getTokenFromRedis(username+"_"+sessionId);
		//for test
		StringRedisTemplate srt = (StringRedisTemplate)session.getAttribute("redis");
		String token = srt.opsForValue().get(username+"_"+sessionId);
		if(!token.equals(tokenP)) {
			response.getWriter().write("no authorization");
			response.sendRedirect("/authorization_error.jsp");
			if(!response.isCommitted())
				filterChain.doFilter(srequest, sresponse);
			return;
		}
		
		
		filterChain.doFilter(srequest, sresponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
