package com.example.demo.domain;

import java.util.Date;


public class ReferralSource {

	//来源 id
	private Integer source_id;
	
	//创建时间
	private Date create_time;
	
	//来源名称
	private String source_name;

	public Integer getSource_id() {
		return source_id;
	}

	public void setSource_id(Integer source_id) {
		this.source_id = source_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getSource_name() {
		return source_name;
	}

	public void setSource_name(String source_name) {
		this.source_name = source_name;
	}
	
}
