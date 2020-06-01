package com.ly.biz;

import java.sql.SQLException;
import java.util.List;

import com.ly.bean.Addr;
import com.ly.dao.AddrDao;


public class AddrBiz {
	AddrDao addr=new AddrDao();
	public List<Addr> query(String userid) throws SQLException {
	
		
		return addr.query(userid);
	}



}
