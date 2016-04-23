package com.geekstar.batch.accountcheck.Implement;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

import com.geekstar.batch.accountcheck.Interface.IBalanceTable;
import com.geekstar.batch.accountcheck.Service.DBHelper;

public class BalanceTable implements IBalanceTable {

	private Hashtable<String, BigDecimal> table = new Hashtable<String, BigDecimal>();
	
	public BigDecimal getBalanceAmount(String date, String code) {
		String key = date + "-" + code;
		return table.get(key);
	}

	public void loadFromDB(String date) {
		DBHelper db = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try{
			db = new DBHelper();
			Connection conn = db.getConnection();
			pst = conn.prepareStatement("select date,code,amount from balance where date=?");//由于一天的记录数不大，可以一次性取出
			pst.setString(1, date);
			
			rs = pst.executeQuery();
			while(rs.next()) {
				String key = rs.getString(1) + "-" + rs.getString(2);
				table.put(key, new BigDecimal(rs.getString(3)));
			}
		}
		catch(Exception e){
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
