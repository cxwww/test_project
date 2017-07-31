package com.example.demo.token.domain;

public class TokenModel {
	
	private String key;
	
	private String token;
	
	public TokenModel(){}
	
	public TokenModel(String key, String token) {
        this.key = key;
        this.token = token;
    }

    public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
	
}
