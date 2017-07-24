package com.example.demo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public User(){}
	
	public User(String name, String password, int status) {
		this.setName(name);
		this.setPassword(password);
		this.setStatus(status);
	}
	
	@Id
	@GeneratedValue
	private int id;

	@Column(nullable = false,unique = true)
	private String name;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private int status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
