package com.geekstar.batch.accountcheck.Implement;

import java.sql.*;
import java.util.*;

import com.geekstar.batch.accountcheck.Bean.ResultBean;
import com.geekstar.batch.accountcheck.Interface.IResultTable;
import com.geekstar.batch.accountcheck.Service.DBHelper;

public class ResultTable implements IResultTable {

	List<ResultBean> list = new ArrayList<ResultBean>();
	
	public void addResultBean(ResultBean bean){
		list.add(bean);
	}
	
	public List<ResultBean> getResultBeanList(){
		return list;
	}
	
	public void saveToDB(String date){
		DBHelper db = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try{
			db = new DBHelper();
			conn = db.getConnection();
			
			//开启事务
			conn.setAutoCommit(false);
			
			//delete相同“账务日期”的对账结果记录
			pst = conn.prepareStatement("delete from result where date=?");
			pst.setString(1, date);
			pst.executeUpdate();
			pst.close();
			
			//insert新的对账结果记录
			pst = conn.prepareStatement("insert into result (date,code,flag) values (?,?,?)");
			for(int i=0; i<list.size(); i++){
				ResultBean bean = list.get(i);
				pst.setString(1, bean.getDate());
				pst.setString(2, bean.getCode());
				pst.setString(3, bean.getFlag());
				pst.addBatch();
			}
			pst.executeBatch();
			pst.close();
			
			//提交事务
			conn.commit();
		}
		catch(Exception e){
			try{
				if (conn != null) conn.rollback();
			}
			catch(Exception e3){e3.printStackTrace();} 
			e.printStackTrace();
		}
		finally{
			try{
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				db.close();
			}
			catch(Exception e2){e2.printStackTrace();}
		}
	}

}
