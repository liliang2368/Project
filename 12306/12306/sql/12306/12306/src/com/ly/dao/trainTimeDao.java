package com.ly.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ly.comms.DbHelper;

public class trainTimeDao {
	DbHelper db=new DbHelper();
	//1.将值插入到列车时刻表
	public int update(String tid,String start,String arrives,String sleeperp,String softseatp,String hardseatp,String gotime,String arrivetime) throws SQLException{
		//1.编写sql语句
		String sql="insert into trainTime values(?,?,?,?,?,?,?,?)";
		return db.update(sql, tid,start,arrives,sleeperp,softseatp,hardseatp,gotime,arrivetime);
	}
	//2.查询列车时刻表的全部信息
	public List<Map<String, Object>> SingleFind() throws SQLException{
			String sql="select *from trainTime";
			List<Map<String, Object>> list=db.findMutil(sql, null);
			return list;
			
		}
	//根据车次来删除所有的信息
	public Map<String, Object> deleteBy(String tid) throws SQLException{
		String sql="delete from trainTime where tid=?";
		return db.findSingle(sql, tid);
	}
	
	
	
	
}
