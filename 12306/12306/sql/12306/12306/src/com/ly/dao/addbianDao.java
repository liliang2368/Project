package com.ly.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ly.comms.DbHelper;

public class addbianDao {
	DbHelper db=new DbHelper();
	//这个方法是根据列车名查询出所有的升序还是降序信息
	public Map<String, Object> SingleFindAsc(String tid) throws SQLException{
		String sql="select basc from addbian where tid=?";
		return db.findSingle(sql, tid);
	}
	//获取到所有的车次信息
	public Map<String, Object> GetLine(String tid) throws SQLException{
		String sql="select tid,basc from addbian where tid=?";
		//获取到编号信息就又有根据是否为
		return db.findSingle(sql, tid);
	}
	
	
	public List<Map<String, Object>> FindName() throws SQLException{
		String sql="select railway_name from railway union  select railway_name from railway";
		return db.findMutil(sql, null);
	}
	public List<Map<String, Object>> FindALL() throws SQLException{
		String sql="select tid,basc from addbian";
		return db.findMutil(sql, null);
	}
	public int delete(String railway_name) throws SQLException{
		String sql="delete from railway where railway_name=?";
		return db.update(sql, railway_name);
	}
	public int delete2(String tid) throws SQLException{
		String sql="delete from addbian where railway_name=?";
		return db.update(sql, tid);
	}
	
	//将列车添加到运行的铁路线路
	public int insert(String raily_name,String tid,String basc) throws SQLException{
		String sql="insert into addbian values(?,?,?)";
		return db.update(sql, raily_name,tid,basc);

	}
}
