package com.ly.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ly.bean.Addr;
import com.ly.util.DBHelper;
import com.ly.util.Utils;


public class AddrDao {
	DBHelper db=new DBHelper();
	public List<Addr> query(String userid) throws SQLException {
		String sql="select *from addr where userid=?";
		List<Map<String, Object>> list=db.selectList(sql, userid);
		return Utils.castElement(list, Addr.class);
	}

}
