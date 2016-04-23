package com.geekstar.batch.accountcheck.Service;

import java.math.BigDecimal;
import java.util.List;

import com.geekstar.batch.accountcheck.Bean.*;
import com.geekstar.batch.accountcheck.Implement.*;
import com.geekstar.batch.accountcheck.Interface.*;

public class CheckLogic {


	public static final String ACCOUNT_STATUS_B0 = "B0";
	public static final String ACCOUNT_STATUS_B1 = "B1";
	public static final String ACCOUNT_STATUS_B2 = "B2";
	public static final String ACCOUNT_STATUS_B3 = "B3";
	
	public static final String CHECK_CODE_501 = "501";
	public static final String CHECK_CODE_511 = "511";
	
	public static final String CHECK_FLAG_YES = "Y";
	public static final String CHECK_FLAG_NO = "N";

	
	//“搭积木”般的主逻辑
	public void check(String date, String file, IBalanceTable balanceTable, IDetailFile detailFile, IResultTable resultTable) {
		
		//step1: 从database中获取“科目余额”到内存对象balanceTable中
		balanceTable.loadFromDB(date);
			
		//step2: 从对账文件中获取所有记录到内存detailBeanList中
		List<DetailBean> detailBeanList = detailFile.getDetailBeanList(file);	
		
		//step3: 遍历detailBeanList，计算来自对账文件的余额amount501FromFile、amount511FromFile
		BigDecimal amount501FromFile = new BigDecimal("0.00");
		BigDecimal amount511FromFile = new BigDecimal("0.00");
		for(int i=0; i<detailBeanList.size(); i++){
			DetailBean bean = detailBeanList.get(i);
			
			if(ACCOUNT_STATUS_B0.equals(bean.getStatus()) || ACCOUNT_STATUS_B2.equals(bean.getStatus())){
				//B0+B2 > 501
				amount501FromFile = amount501FromFile.add(bean.getAmount());
			}
			else if(ACCOUNT_STATUS_B1.equals(bean.getStatus()) || ACCOUNT_STATUS_B3.equals(bean.getStatus())){
				//B1+B3 > 511
				amount511FromFile = amount511FromFile.add(bean.getAmount());
			}
		}
			
		//step4: 将amount501FromFile、amount511FromFile与内存对象balanceTable中科目余额对比，并将结果保存在内存对象resultTable中
		if(amount501FromFile.equals(balanceTable.getBalanceAmount(date, CHECK_CODE_501))){
			resultTable.addResultBean(new ResultBean(date, CHECK_CODE_501, CHECK_FLAG_YES));
		}
		else{
			resultTable.addResultBean(new ResultBean(date, CHECK_CODE_501, CHECK_FLAG_NO));
		}
		
		if(amount511FromFile.equals(balanceTable.getBalanceAmount(date, CHECK_CODE_511))){
			resultTable.addResultBean(new ResultBean(date, CHECK_CODE_511, CHECK_FLAG_YES));
		}
		else{
			resultTable.addResultBean(new ResultBean(date, CHECK_CODE_511, CHECK_FLAG_NO));
		}
			
		//step5: 内存对象resultTable中的对账结果保存到database中
		resultTable.saveToDB(date);
	}

	
	public static void main(String[] args) {
		String file = "/Users/wuyg/Documents/workspace_mars2/account-check/detail.20160101";
		CheckLogic cl = new CheckLogic();
		
		//不等具体实现，即可并行开发:BalanceTable>MyBalanceTable, DetailFile>MyDetailFile, ResultTable>MyResultTable
		//cl.check("20160101", file, new MyBalanceTable(), new MyDetailFile(), new MyResultTable());
		cl.check("20160101", file, new BalanceTable(), new DetailFile(), new ResultTable());
		
		System.out.println("batch processing is done.");
	}
}
