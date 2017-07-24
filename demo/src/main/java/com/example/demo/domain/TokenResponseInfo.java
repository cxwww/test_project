package com.example.demo.domain;

public class TokenResponseInfo {
	
	private String access_token;
	
	private String expires_in;
	
	private String scope;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	
	public String toString() {
		return "TokenResponseInfo:[ access_token: " + this.getAccess_token()+"; expires_in: " + this.getExpires_in()+"; scope: " + this.getScope() +"]";
	}
}
