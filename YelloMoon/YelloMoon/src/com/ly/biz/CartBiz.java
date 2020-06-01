package com.ly.biz;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ly.bean.Cart;
import com.ly.dao.CartDao;


public class CartBiz {
	CartDao dao=new CartDao();
	public List<Cart> query(String id,String num) throws SQLException {
		
		return dao.query(id,num);
	}
	public List<Cart> del(String id) throws SQLException {
		CartDao dao=new CartDao();
		return dao.del(id);
	}
	public List<Cart> query() throws SQLException {
		return dao.query();
	}
	public List<Cart> daacart(Cart cart) throws SQLException {
		return dao.addCart(cart);
	}
	public Map<String, Object> getCount() throws SQLException {
		// TODO Auto-generated method stub
		return dao.getCount();
	}
	public List<Cart> cartQuery() throws SQLException {
		// TODO Auto-generated method stub
		return dao.addquery();
	}

}
