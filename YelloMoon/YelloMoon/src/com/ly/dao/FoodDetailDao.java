package com.ly.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ly.bean.fooddtail;
import com.ly.util.DBHelper;


public class FoodDetailDao {
	DBHelper db=new DBHelper();

	public fooddtail FindId(String id) throws SQLException, IllegalAccessException, InvocationTargetException {
		fooddtail detail=new fooddtail();
		//编写sql语句信息
		String sql="SELECT\r\n" + 
				"a.foodname,\r\n" + 
				"a.newprice,\r\n" + 
				"a.dec,\r\n" + 
				"a.pic,\r\n" + 
				"b.head,\r\n" + 
				"b.head1,\r\n" + 
				"b.head2,\r\n" + 
				"b.head3,\r\n" + 
				"b.head4,\r\n" + 
				"a.id,\r\n" + 
				"b.info\r\n" + 
				"FROM\r\n" + 
				"food AS a\r\n" + 
				"INNER JOIN fooddetail AS b ON a.id= b.foodid where a.id=?";
		Map<String, Object> map=db.selectone(sql, id);
		BeanUtils.populate(detail, map);
		System.out.println("看这里"+detail.getPic());
		return detail;
	}

	public void update(fooddtail fdteail) throws SQLException {
		String sql="update fooddetail set head=?,head1=?,head2=?,head3=?,head4=?,info=? where foodid=?";
		db.update(sql, fdteail.getHead(),fdteail.getHead1(),fdteail.getHead2(),fdteail.getHead3(),fdteail.getHead4(),fdteail.getHead5(),fdteail.getFoodid());
		
	}

}
