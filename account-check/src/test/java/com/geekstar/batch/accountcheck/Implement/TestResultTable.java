package com.geekstar.batch.accountcheck.Implement;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.geekstar.batch.accountcheck.Bean.ResultBean;
import com.geekstar.batch.accountcheck.Interface.IResultTable;

public class TestResultTable {
	
	@Test
	public void testAddResultBean() {
		addResultBeanTest( new ResultTable() );
	}
	
	
	private void addResultBeanTest(IResultTable rt) {
		ResultBean b501 = new ResultBean("20160101", "501", "Y");
		ResultBean b511 = new ResultBean("20160101", "511", "N");
		rt.addResultBean(b501);
		rt.addResultBean(b511);
		
		List<ResultBean> list = rt.getResultBeanList();
		
		HashMap<String, String> map = new HashMap<String, String>();
		for(int i=0; i<list.size(); i++){
			ResultBean bean = list.get(i);
			map.put(bean.getCode(), bean.getFlag());
		}
		
		assertEquals("Y", (String)map.get("501"));
		assertEquals("N", (String)map.get("511"));
	}

}
