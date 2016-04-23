package com.geekstar.batch.accountcheck.Interface;

import java.util.List;

import com.geekstar.batch.accountcheck.Bean.DetailBean;

public interface IDetailFile {
	//获取对账文件中的所有记录
	public List<DetailBean> getDetailBeanList(String file);
}
