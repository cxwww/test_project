package com.example.demo.util;

import java.util.Date;

import com.example.demo.domain.ReferralSource;

public class ReferralSourceCreateUtil {

	public static ReferralSource[] getItems() {
		ReferralSource[] items = new ReferralSource[5];
		for(int i=0; i<5; i++) {
			ReferralSource item = new ReferralSource();
			item.setCreate_time(new Date(System.currentTimeMillis()));
			item.setSource_id(400+i);
			item.setSource_name("test-"+item.getSource_id());
			items[i] = item;
		}
		return items;
	}
}
