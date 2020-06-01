package com.ly.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ly.bean.home;
import com.ly.bean.homedetail;
import com.ly.bean.peizhihome;
import com.ly.util.DBHelper;
import com.ly.util.Utils;

public class HomeDao {
	DBHelper db=new DBHelper();
	public List<home> query() throws SQLException {
		String sql="select *from home limit 4";
		List<Map<String, Object>> list=db.findMutil(sql);
		return Utils.castElement(db.findMutil(sql), home.class);
	}
	public List<homedetail> querydetail(homedetail hd2) throws SQLException {
		String sql="SELECT\r\n" + 
				"a.are,\r\n" + 
				"a.bed_id,\r\n" + 
				"a.person_in,\r\n" + 
				"a.price,\r\n" + 
				"a.pic,\r\n" + 
				"b.pic1,\r\n" + 
				"b.pic2,\r\n" + 
				"b.pic3,\r\n" + 
				"b.info1,\r\n" + 
				"b.pic4,\r\n" + 
				"b.pic5,\r\n" + 
				"b.pic6,\r\n" + 
				"b.pic7,\r\n" + 
				"a.cheku,\r\n" + 
				"a.bash,\r\n" + 
				"b.info2\r\n" + 
				"FROM\r\n" + 
				"home AS a\r\n" + 
				"INNER JOIN home_detail AS b ON a.id= b.home_id where a.id=?";
		System.out.println(db.selectList(sql, hd2.getId()));
		return Utils.castElement(db.selectList(sql, hd2.getId()), homedetail.class);
	}
	public List<peizhihome> querypeizhi(homedetail hd2) throws SQLException {
		String sql="SELECT\r\n" + 
				"a.username,\r\n" + 
				"a.passwd,\r\n" + 
				"a.email,\r\n" + 
				"a.phone\r\n" + 
				"FROM\r\n" + 
				"`user` AS a where home_id=?";
		System.out.println(db.selectList(sql, hd2.getId()));
		return Utils.castElement(db.selectList(sql, hd2.getId()), peizhihome.class);
	}
	public List<home> querlimit() throws SQLException {
		// TODO Auto-generated method stub
		String sql="select *from home where type_id=1 limit 6";
		List<Map<String, Object>> list=db.findMutil(sql);
		return Utils.castElement(db.findMutil(sql), home.class);
	}
	public List<home> querlimit3() throws SQLException {
		String sql="select *from home where type_id=2 limit 6";
		List<Map<String, Object>> list=db.findMutil(sql);
		return Utils.castElement(db.findMutil(sql), home.class);
	}
	public List<home> querlimit2() throws SQLException {
		String sql="select *from home where type_id=3 limit 6";
		List<Map<String, Object>> list=db.findMutil(sql);
		return Utils.castElement(db.findMutil(sql), home.class);
	}
	public List<peizhihome> queryuser() throws SQLException {
		// TODO Auto-generated method stub
		String sql="select *from user where home_id=2";
		return Utils.castElement(db.findMutil(sql), peizhihome.class);
	}
	//querylist
	public List<home> querylist() throws SQLException {
		// TODO Auto-generated method stub
		String sql="select *from home";
		return Utils.castElement(db.findMutil(sql), home.class);
	}

}
