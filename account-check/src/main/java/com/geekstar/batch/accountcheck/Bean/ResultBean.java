package com.geekstar.batch.accountcheck.Bean;

public class ResultBean {
	final String date;
	final String code;
	final String flag;
	
	public ResultBean(final String date, final String code, final String flag){
		this.date = date;
		this.code = code;
		this.flag = flag;
	}

	
	public String getDate() {
		return date;
	}
	public String getCode() {
		return code;
	}
	public String getFlag() {
		return flag;
	}
}
