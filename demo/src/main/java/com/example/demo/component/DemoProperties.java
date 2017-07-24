package com.example.demo.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DemoProperties {
	
	@Value("${com.example.title}")
	private String title;
	
	@Value("${com.example.descr}")
	private String descr;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String description) {
		this.descr = description;
	}
	
}
