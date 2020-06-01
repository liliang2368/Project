package com.ly.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ly.bean.foodtype;
import com.ly.util.DBHelper;
import com.ly.util.Utils;



public class TypeDao {
	DBHelper db=new DBHelper();
	public List<foodtype> query() throws SQLException {
		//编写sql语句信息
		String sql="select * from foodtype limit 3";
		List<Map<String, Object>> list=db.findMutil(sql);
		System.out.println(list);
		List<foodtype> foodlist=Utils.castElement(list, foodtype.class);
		return foodlist;
	}
	public List<foodtype> FindAll() throws SQLException {
		String sql="select *from foodtype";
		List<Map<String, Object>> list=db.findMutil(sql);
		List<foodtype> type=Utils.castElement(list, foodtype.class);
		return type;
	}
	public void add(foodtype fd) throws SQLException {
		String sql="insert into foodtype (type,info,head) values(?,?,?)";
		db.update(sql, fd.getType(),fd.getInfo(),fd.getHead());
	}
	public void update(foodtype fd) throws SQLException {
		String sql="update foodtype set type=? , info=?,head=? where id=?";
		db.update(sql, fd.getType(),fd.getInfo(),fd.getHead(),fd.getId());
	}
	public void del(String id) throws SQLException {
		String sql="delete from foodtype where id=?";
		db.update(sql, id);
	}


}
