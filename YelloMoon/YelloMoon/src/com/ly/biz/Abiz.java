package com.ly.biz;

import java.sql.SQLException;
import java.util.List;

import com.ly.bean.A;
import com.ly.dao.ADao;

public class Abiz {
	ADao adao=new ADao();

	public List<A> query() throws SQLException {
		return adao.query();
	}

	public void add(A a) throws SQLException {
		System.out.println("这里"+a.getId());
		if(a.getId().trim().isEmpty()==true || a.getId()==null) {
			System.out.println("看这里是新增的操作");
			//说明执行的是新增的操作
			adao.add(a);
		}else {
			//说明是新增的操作
			adao.update(a);
		}
	}

}
