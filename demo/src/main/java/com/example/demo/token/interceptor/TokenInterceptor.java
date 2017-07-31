package com.example.demo.token.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.demo.token.component.TokenAuthorization;
import com.example.demo.token.inter.TokenManager;

@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {
	
	 @Autowired
	 private TokenManager manager;

	 @Override
	 public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
		 //如果不是映射到方法直接通过
		 if (!(handler instanceof HandlerMethod)) {
			 return true;
		 }
		 HandlerMethod handlerMethod = (HandlerMethod) handler;
		 Method method = handlerMethod.getMethod();
		 if (method.getAnnotation(TokenAuthorization.class) != null) {
			 //从header中得到token
			 HttpSession session = request.getSession();
			 String token = request.getHeader("Authorization");
			 String username = (String) request.getAttribute("username");
			 String session_id = session==null?null:session.getId();
			 if(StringUtils.isEmpty(username) || StringUtils.isEmpty(session_id)) {
				 response.getWriter().write("username or session_id is null");
				 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				 return false;
			 }
			 if (manager.checkToken(username+"_"+session_id, token)) {
				 return true;
			 }
			 
			 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			 return false;
		 }
		 
		 return true;
	 }
}
