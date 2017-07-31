package com.example.demo.token.domain;

public class TokenRequestParam {
	
	private String username;
	
	//有赞云颁发给开发者的应用ID
	private String client_id = "69e31461b888bfff24";
	
	//有赞云颁发给开发者的应用secret
	private String client_secret = "3924fc399dffdfdb9fcdea7f2b88b1d1";
	
	//授与方式（固定为 “silent”）
	private String grant_type = "silent";
	
	//授权给该应用的店铺id
	private String kdt_id = "19165004";

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getClient_secret() {
		return client_secret;
	}

	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public String getKdt_id() {
		return kdt_id;
	}

	public void setKdt_id(String kdt_id) {
		this.kdt_id = kdt_id;
	}
	
}
