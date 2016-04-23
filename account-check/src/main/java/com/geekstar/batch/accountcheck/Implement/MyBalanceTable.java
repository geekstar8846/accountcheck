package com.geekstar.batch.accountcheck.Implement;

import java.math.BigDecimal;
import java.util.Hashtable;

import com.geekstar.batch.accountcheck.Interface.IBalanceTable;

public class MyBalanceTable implements IBalanceTable {

private Hashtable<String, BigDecimal> table = new Hashtable<String, BigDecimal>();
	
	public BigDecimal getBalanceAmount(String date, String code) {
		String key = date + "-" + code;
		return table.get(key);
	}

	public void loadFromDB(String date) {
		table.put("20160101-501", new BigDecimal("88800000000.88"));
		table.put("20160101-511", new BigDecimal("99900000000.99"));
		table.put("20160101-127", new BigDecimal("44400000000.44"));
	}

}
