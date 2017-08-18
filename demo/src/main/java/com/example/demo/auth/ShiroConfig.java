package com.example.demo.auth;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.DefaultAdvisorChainFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
	
	@Bean
	public DemoShiroRealm demoShiroRealm() {
		return new DemoShiroRealm();
	}
	
	
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(demoShiroRealm());
		return securityManager;
	}
}
