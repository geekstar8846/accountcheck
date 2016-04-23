package com.geekstar.batch.accountcheck.Implement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.geekstar.batch.accountcheck.Bean.DetailBean;
import com.geekstar.batch.accountcheck.Interface.IDetailFile;

public class MyDetailFile implements IDetailFile {

	public List<DetailBean> getDetailBeanList(String file){
		DetailBean b0 = new DetailBean("20160101", "B0", new BigDecimal("66600000000.66"));
		DetailBean b1 = new DetailBean("20160101", "B1", new BigDecimal("99900000000.00"));
		DetailBean b2 = new DetailBean("20160101", "B2", new BigDecimal("22200000000.22"));
		
		List<DetailBean> list = new ArrayList<DetailBean>();
		list.add(b0);
		list.add(b1);
		list.add(b2);
		
		return list;
	}

}
