package com.ly.dao;

import java.sql.SQLException;
import java.util.Map;

import com.ly.comms.DbHelper;

public class adminDao {
	DbHelper db=new DbHelper();
//
	public Map<String, Object> resint(String username,String pwd) throws SQLException{
		String sql="select *from adminInfo where aname=? and apwd=?";
		return db.findSingle(sql, username,pwd);
	}
}
