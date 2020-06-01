package com.ly.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ly.comms.DbHelper;

/**
 * 
 * @author 
 *
 */
public class traininfoDao {
	DbHelper db=new DbHelper();
	//1.线根据列车车次来查询出所有的座位席别
	public List<Map<String, Object>> FindByTid(String ttid) throws SQLException{
		//1.编写sql语句信息
		String sql="select softseatp  from traininfo where ttid=?";
		return db.findMutil(sql, ttid);
	}
	//插入列车的数据信息
	public int insert(String ttid,String softseatp,String soft1seatp) throws SQLException{
		String sql="insert into traininfo values(?,?,null,null,?)";
		return db.update(sql, ttid,softseatp,soft1seatp);
	}
	//根据铁路名和座位类型来获取到这趟车的总票数
	public Map<String, Object> Findvote(String softseatp,String ttid) throws SQLException{
		String sql="select soft1seatp from traininfo where softseatp=? and ttid=?";
		return db.findSingle(sql, softseatp,ttid);
	}
	//有于我设置的总票数肯定有有默认值，因此我必须要判断这趟车是否有票数
	public boolean FindTid(String ttid) throws SQLException{
		String sql="select *from traininfo where ttid=?";
		Map<String, Object> map=db.findSingle(sql, ttid);
		if(map==null){
			return false;
		}else {
			return true;
		}
	}
	
	
	
	
	
	
	
	
	
	

}
	
	
	
	
	


