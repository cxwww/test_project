package com.example.demo.redis;

import org.springframework.context.annotation.Configuration;

@Configuration
//maxInactiveIntervalInSeconds: 设置Session失效时间，使用Redis Session之后，原Boot的server.session.timeout属性不再生效s
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 2)//seconds
public class SessionConfig {

}
