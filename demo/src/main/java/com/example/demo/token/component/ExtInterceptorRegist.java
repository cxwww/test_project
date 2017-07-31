package com.example.demo.token.component;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.demo.token.interceptor.TokenInterceptor;

@Configuration
public class ExtInterceptorRegist extends WebMvcConfigurerAdapter{
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TokenInterceptor()).excludePathPatterns("/data/tokenC");
//		registry.addInterceptor(new TokenInterceptor());
	}
}
