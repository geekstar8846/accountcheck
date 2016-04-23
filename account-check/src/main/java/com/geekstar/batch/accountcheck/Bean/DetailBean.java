package com.geekstar.batch.accountcheck.Bean;

import java.math.BigDecimal;

public class DetailBean {
	final String date;
	final String status;
	final BigDecimal amount;
	
	public DetailBean(final String date, final String status, final BigDecimal amount){
		this.date = date;
		this.status = status;
		this.amount = amount;
	}

	
	public String getDate() {
		return date;
	}
	public String getStatus() {
		return status;
	}
	public BigDecimal getAmount() {
		return amount;
	}
}
