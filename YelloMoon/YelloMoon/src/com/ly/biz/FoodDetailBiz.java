package com.ly.biz;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import com.ly.bean.fooddtail;
import com.ly.dao.FoodDetailDao;




public class FoodDetailBiz {

	public fooddtail FindId(String id) throws SQLException, IllegalAccessException, InvocationTargetException {
		//直接调用FoodDetailDao
		FoodDetailDao fdao=new FoodDetailDao();
		
		return fdao.FindId(id);
	}

	public void save(fooddtail fdteail) throws SQLException {
		FoodDetailDao fdao=new FoodDetailDao();	
		fdao.update(fdteail);
	}

}
