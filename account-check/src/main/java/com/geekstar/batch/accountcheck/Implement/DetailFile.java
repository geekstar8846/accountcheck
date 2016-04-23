package com.geekstar.batch.accountcheck.Implement;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

import com.geekstar.batch.accountcheck.Bean.DetailBean;
import com.geekstar.batch.accountcheck.Interface.IDetailFile;

public class DetailFile implements IDetailFile {

	public List<DetailBean> getDetailBeanList(String file) {
		BufferedReader br = null;
		List<DetailBean> list = new ArrayList<DetailBean>();
		
		try {
			br = new BufferedReader(
					new InputStreamReader(
						new FileInputStream(file), "UTF-8"));
			
			String line;
			while((line=br.readLine()) != null) {
				String[] fields = line.split(" ",3);
				DetailBean bean = new DetailBean(fields[0], fields[1], new BigDecimal(fields[2]));
				list.add(bean);
			}		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(null!=br) br.close();
			}
			catch(Exception e2){e2.printStackTrace();}
		}
		
		return list;
	}

}
