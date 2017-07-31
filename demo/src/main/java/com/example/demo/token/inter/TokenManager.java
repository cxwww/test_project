package com.example.demo.token.inter;

import com.example.demo.token.domain.TokenModel;

public interface TokenManager {
	
    public TokenModel createToken(String key);

    public boolean checkToken(String key, String value);

    public void deleteToken(String key);
}
