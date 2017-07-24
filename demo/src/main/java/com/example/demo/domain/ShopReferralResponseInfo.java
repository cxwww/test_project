package com.example.demo.domain;

public class ShopReferralResponseInfo {

	private Paginator paginator;
	
	private ReferralSource[] items;

	public Paginator getPaginator() {
		return paginator;
	}

	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}

	public ReferralSource[] getItems() {
		return items;
	}

	public void setItems(ReferralSource[] items) {
		this.items = items;
	}
	
	
}
