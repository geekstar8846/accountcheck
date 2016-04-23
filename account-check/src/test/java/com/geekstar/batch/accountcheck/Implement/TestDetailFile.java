package com.geekstar.batch.accountcheck.Implement;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import com.geekstar.batch.accountcheck.Bean.DetailBean;
import com.geekstar.batch.accountcheck.Interface.IDetailFile;

public class TestDetailFile {

	@Test
	public void testGetDetailBeanList() {
		//只要接口不变，不等你的具体实现，我就可以并行开发了
		getDetailBeanListTest( new DetailFile() );
	}

	
	private void getDetailBeanListTest(IDetailFile df) {
		String file = "/Users/wuyg/Documents/workspace_mars2/account-check/detail.20160101";
		List<DetailBean> list = df.getDetailBeanList(file);
		
		BigDecimal bd = new BigDecimal("0.00");
		for(int i=0; i<list.size(); i++){
			DetailBean bean = list.get(i);
			bd = bd.add(bean.getAmount());
		}
		
		BigDecimal bdExpected = (new BigDecimal("66600000000.66")).
							add(new BigDecimal("99900000000.00")).
							add(new BigDecimal("22200000000.22"));
		assertEquals(bdExpected, bd);
	}

}
