package com.geekstar.batch.accountcheck.Implement;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.geekstar.batch.accountcheck.Interface.IBalanceTable;

public class TestBalanceTable {

	@Test
	public void testMyGetBalanceAmount() {
		//只要接口不变，不等你的具体实现，我就可以并行开发了
		getBalanceAmountTest( new MyBalanceTable() );
	}

	
	private void getBalanceAmountTest(IBalanceTable bt) {
		bt.loadFromDB("20160101");
		
		BigDecimal amount501 = bt.getBalanceAmount("20160101", "501");
		BigDecimal amount511 = bt.getBalanceAmount("20160101", "511");
		BigDecimal amount127 = bt.getBalanceAmount("20160101", "127");
		
		assertEquals(new BigDecimal("88800000000.88"), amount501);
		assertEquals(new BigDecimal("99900000000.99"), amount511);
		assertEquals(new BigDecimal("44400000000.44"), amount127);
	}

}
