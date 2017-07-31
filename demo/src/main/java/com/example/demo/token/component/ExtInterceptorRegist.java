package com.example.demo.token.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.demo.token.interceptor.TokenInterceptor;

@Configuration
public class ExtInterceptorRegist extends WebMvcConfigurerAdapter{
	
	@Autowired
	TokenInterceptor tokenInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(tokenInterceptor).excludePathPatterns("/data/tokenC");
//		registry.addInterceptor(new TokenInterceptor());// 新建一个对象，对象中的TokenManager不会自动注入
	}
}
