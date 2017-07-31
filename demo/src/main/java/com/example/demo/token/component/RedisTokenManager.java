package com.example.demo.token.component;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.example.demo.token.domain.TokenModel;
import com.example.demo.token.domain.TokenRequestParam;
import com.example.demo.token.inter.TokenManager;
import com.example.demo.token.util.JsonWebTokenUtil;

@Component("redisTokenManager")
public class RedisTokenManager implements TokenManager{
	
	@Autowired
	private RedisTemplate redis;

	@Override
	public TokenModel createToken(String key) {
        String token = JsonWebTokenUtil.createJsonWebToken(new TokenRequestParam());
        TokenModel model = new TokenModel(key, token);
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
        redis.boundValueOps(key).expire(60, TimeUnit.SECONDS);
        return true;
	}


	@Override
	public void deleteToken(String key) {
		redis.delete(key);
	}

}
