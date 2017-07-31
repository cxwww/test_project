package com.example.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Paginator;
import com.example.demo.domain.ShopReferralResponseInfo;
import com.example.demo.token.component.TokenAuthorization;
import com.example.demo.util.ReferralSourceCreateUtil;

@RestController
public class DataController {
	
	
	//youzan.ebiz.stats.referral.pages.overview
	@TokenAuthorization
	@RequestMapping(value="/data/shop/pageOverview/{size}/{page_id}/{page}", method=RequestMethod.GET)
	public ShopReferralResponseInfo pageOverview(@PathVariable(required=true) int size, @PathVariable(required=true)  int page_id
			, @PathVariable(required=true) int page) {
		ShopReferralResponseInfo ri = new ShopReferralResponseInfo();
		Paginator p = new Paginator();
		ri.setPaginator(p);
		ri.setItems(ReferralSourceCreateUtil.getItems());
		return ri;
	}
	
	
	
	
}
