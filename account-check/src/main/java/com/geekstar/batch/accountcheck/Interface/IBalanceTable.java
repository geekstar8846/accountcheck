package com.geekstar.batch.accountcheck.Interface;

import java.math.BigDecimal;

public interface IBalanceTable {
	//从database装载"账务日期"的余额表信息
	public void loadFromDB(String date);
	
	//通过“账务日期”和“科目”获取余额
	public BigDecimal getBalanceAmount(String date, String code);
}
