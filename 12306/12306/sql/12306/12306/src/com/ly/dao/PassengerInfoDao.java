package com.ly.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ly.comms.DbHelper;


public class PassengerInfoDao {

DbHelper db=new DbHelper();

	
	public int add(Object...params) throws SQLException{
		String sql="insert into passengerInfo1 values(seq_passenge.nextval,?,?,?,?,null)";
		return db.update(sql, params);
	}
	//查看所有的乘客信息表
	public List<Map<String, Object>> FindaLL() throws SQLException{
		String sql="select pid, pname,sex,carid,tel,ttl from passengerInfo1";
		return db.findMutil(sql, null);
	}
}
