package com.ly.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ly.bean.A;
import com.ly.util.DBHelper;
import com.ly.util.Utils;



public class ADao {
	DBHelper db=new DBHelper();
	public List<A> query() throws SQLException {
		String sql="select *from text";
		List<Map<String , Object>> list=db.findMutil(sql);
		
		return Utils.castElement(list, A.class);
	}
	public void add(A a) throws SQLException {
		//新增操作
		String sql="insert into text (href,pic) values(?,?)";
		db.update(sql, a.getHref(),a.getPic());
	}
	public void update(A a) throws SQLException {
		String sql="update text set href=?,pic=? where id=?";
		db.update(sql, a.getHref(),a.getPic(),a.getId());
	}

}
