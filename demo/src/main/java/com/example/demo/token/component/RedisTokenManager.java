package com.example.demo.token.component;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.example.demo.token.domain.TokenModel;
import com.example.demo.token.domain.TokenRequestParam;
import com.example.demo.token.inter.TokenManager;
import com.example.demo.token.util.JsonWebTokenUtil;

@Component
public class RedisTokenManager implements TokenManager{
	
	@Autowired
	private RedisTemplate redis;

	@Override
	public TokenModel createToken(String key) {
		//使用uuid作为源token
        String token = JsonWebTokenUtil.createJsonWebToken(new TokenRequestParam());
        TokenModel model = new TokenModel(key, token);
        //存储到redis并设置过期时间
        redis.boundValueOps(key).set(token, 60, TimeUnit.SECONDS);
        return model;
	}

	@Override
	public boolean checkToken(String key, String value) {
		if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return false;
        }
        String token = (String) redis.boundValueOps(key).get();
        if (token == null || !token.equals(value)) {
            return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        redis.boundValueOps(key).expire(60, TimeUnit.SECONDS);
        return true;
	}


	@Override
	public void deleteToken(String key) {
		redis.delete(key);
	}

}
