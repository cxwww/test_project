package com.example.demo.token.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.demo.token.component.TokenAuthorization;
import com.example.demo.token.inter.TokenManager;

@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {
	
	 @Autowired
	 @Qualifier("redisTokenManager")
	 private TokenManager manager;

	 @Override
	 public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
		 if (!(handler instanceof HandlerMethod)) {
			 return true;
		 }
		 HandlerMethod handlerMethod = (HandlerMethod) handler;
		 Method method = handlerMethod.getMethod();
		 if (method.getAnnotation(TokenAuthorization.class) != null) {
//			 HttpSession session = request.getSession();
			 String token = request.getHeader("Authorization");
			 String username = (String) request.getParameter("username");
//			 String session_id = session==null?null:session.getId();
//			 if(StringUtils.isEmpty(username) || StringUtils.isEmpty(session_id)) {
//				 response.getWriter().write("username or session_id is null");
//				 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//				 return false;
//			 }
//			 String key = username+"_"+session_id;
			 String key = username;
			 if (manager.checkToken(key, token)) {
				 return true;
			 }
			 response.getWriter().write("no authorization");
			 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			 return false;
		 }
		 
		 return true;
	 }
}
