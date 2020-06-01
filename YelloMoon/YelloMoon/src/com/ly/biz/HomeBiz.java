package com.ly.biz;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ly.bean.home;
import com.ly.bean.homedetail;
import com.ly.bean.peizhihome;
import com.ly.dao.HomeDao;

public class HomeBiz {
	HomeDao hd=new HomeDao();
	public List<home> query() throws SQLException {
		
		return hd.query();
	}
	public List<homedetail> querydetail(homedetail hd2) throws SQLException {
		// TODO Auto-generated method stub
		return hd.querydetail(hd2);
	}
	public List<peizhihome> querypeizhi(homedetail hd2) throws SQLException {
		// TODO Auto-generated method stub
		return hd.querypeizhi(hd2);
	}
	public List<home> querylimit() throws SQLException {
		
		return hd.querlimit();
	}
	public List<home> querylimit3() throws SQLException {
		// TODO Auto-generated method stub
		return hd.querlimit3();
	}
	public List<home> querylimit2() throws SQLException {
		// TODO Auto-generated method stub
		return hd.querlimit2();
	}
	public List<peizhihome> queryuser() throws SQLException {
		// TODO Auto-generated method stub
		return hd.queryuser();
	}
	public List<home> querylist()  throws SQLException {
		// TODO Auto-generated method stub
		return hd.querylist();
	}

}
