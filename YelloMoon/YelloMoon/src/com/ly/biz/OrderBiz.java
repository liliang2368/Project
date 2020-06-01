package com.ly.biz;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ly.bean.Fpage;
import com.ly.bean.Order;
import com.ly.dao.OrderDao;


public class OrderBiz {

	public List<Order> query(Order order,Fpage page) throws SQLException {
		//逻辑类判断先
		OrderDao dao=new OrderDao();
		List<Order> list=dao.query(order,page);
		return list;
	}

	public String insert(String userid) throws SQLException {
		//先插入userid
		OrderDao dao=new OrderDao();
	
		return 	dao.insert(userid);
	}

	public List<Order> FindAll(String id, String userid) throws SQLException {
		OrderDao dao=new OrderDao();
		return dao.findAll(id,userid);
	}

	public Order FindSingle(String id) throws IllegalAccessException, InvocationTargetException, SQLException {
		OrderDao dao=new OrderDao();
		return dao.findSingle(id);
	}

	public void update(Order order) throws SQLException {
		OrderDao order1=new OrderDao();
		order1.update(order);
	}

	public int FindSum(String id, String userid) throws SQLException {
		OrderDao dao=new OrderDao();
		return dao.findSum(id,userid);
	}

	public void num(String id, String num) throws SQLException {
		OrderDao dao=new OrderDao();
		dao.num(id,num);
	}

	public List<Order> FindTypeOrder(String userid, String status) throws SQLException {
		OrderDao dao=new OrderDao();
		return dao.FindTypeOrder(userid,status);
	}

	public Map<String, Object> FindCount() throws SQLException {
		OrderDao dao=new OrderDao();
		return dao.findCount();
	}

	
}
