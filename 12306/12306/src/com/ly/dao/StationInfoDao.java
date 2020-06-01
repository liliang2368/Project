package com.ly.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ly.comms.DbHelper;

public class StationInfoDao {
	DbHelper db=new DbHelper();
	//插入站台信息
	public int update(Map<String, Object> map) throws SQLException{
		String sql="insert into stationinfo values(?,?,?,?,?,?,?,null)";
		return db.update(sql, map.get("S_NAME"),map.get("S_STATNAME"),map.get("S_TIME1"),map.get("S_TIME2"),map.get("S_SPRICE"),map.get("S_LPRICE"),map.get("S_PRICE"));	
	}
	public List<Map<String, Object>> getAllStation(String s_name) throws SQLException{	
		String sql="select s_name,s_statname,s_time1,s_time2,s_sprice,s_lprice,s_price,s_add from stationinfo where s_name=?";
		return db.findMutil(sql, s_name);
		
	}
	//根据编号的表去到所有的列车次
	public List<Map<String, Object>> Allbianhao() throws SQLException{
		String sql="select tid,basc from addbian";
		return db.findMutil(sql, null);
	}
	//获取到出发的时间和价格
	public Map<String, Object> getTime(String start,String end,String ttd) throws SQLException{
		String sql="select  s.s_time2 ,d.s_time1   from stationinfo s inner join stationinfo d  on(s.s_name=d.s_name) where s.s_statname=? and d.s_statname=?  and s.s_name=?";
		return db.findSingle(sql, start,end,ttd);
	}
	public Map<String, Object> Findprice(String start,String end) throws SQLException{
		String sql="select   max(s.s_sprice-d.s_sprice) 价格  from stationinfo s inner join stationinfo d  on(s.s_name=d.s_name) where s.s_statname=?  and d.s_statname=?";
		return db.findSingle(sql, start,end);
	}

	//获取价格根据初始站和终点站和运行得车次信息来获取出来
	public Map<String, Object> GwtPrice(String tid,String start,String end) throws SQLException{
	String sql="select d.s_sprice-s.s_sprice 软卧,d.s_lprice-s.s_lprice 软座,d.s_price-s.s_price 硬座    "
			+ "from stationinfo s inner join stationinfo d on(s.s_name=d.s_name) where s.s_name=?"
			+ "and s.s_statname=? and d.s_statname=?;";	
	return db.findSingle(sql, tid,start,end);
	}
	
}
