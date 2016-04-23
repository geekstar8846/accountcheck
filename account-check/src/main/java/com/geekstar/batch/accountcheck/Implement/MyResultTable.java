package com.geekstar.batch.accountcheck.Implement;

import java.util.ArrayList;
import java.util.List;

import com.geekstar.batch.accountcheck.Bean.ResultBean;
import com.geekstar.batch.accountcheck.Interface.IResultTable;

public class MyResultTable implements IResultTable {

List<ResultBean> list = new ArrayList<ResultBean>();
	
	public void addResultBean(ResultBean bean){
		list.add(bean);
	}
	
	public List<ResultBean> getResultBeanList(){
		return list;
	}
	
	public void saveToDB(String date){
		//delete相同“账务日期”的对账结果记录
		
		//insert新的对账结果记录
		for(int i=0; i<list.size(); i++){
			ResultBean bean = list.get(i);
			
			StringBuffer sb = new StringBuffer();
			sb.append(bean.getDate()).append("|");
			sb.append(bean.getCode()).append("|");
			sb.append(bean.getFlag());
			
			System.out.println(sb.toString());
		}
	}

}
