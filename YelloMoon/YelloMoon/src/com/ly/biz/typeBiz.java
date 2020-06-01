package com.ly.biz;

import java.sql.SQLException;
import java.util.List;

import com.ly.bean.foodtype;
import com.ly.dao.TypeDao;



public class typeBiz {
	TypeDao dao=new TypeDao();
	public List<foodtype> query() throws SQLException {
	
		List<foodtype> list=dao.query();
		return list;
	}

	public List<foodtype> findType() throws SQLException {
	
		return dao.FindAll();
	}

	public void add(foodtype fd) throws SQLException {
		System.out.println("看这里"+fd.getId());
		if(fd.getId()==0) {
			//说明是新增操作
			dao.add(fd);
		}else {
			dao.update(fd);
		}
	}

	public void del(String id) throws SQLException {
		dao.del(id);
	}

}
