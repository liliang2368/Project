package com.ly.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ly.bean.Cart;
import com.ly.util.DBHelper;
import com.ly.util.Utils;



public class CartDao {
		public static  String msg;
	DBHelper db=new DBHelper();
	public List<Cart> query(String id,String num) throws SQLException {
		if(id!=null) {
			//System.out.println("这里是"+id);
		//先将该foodid插入到购物车表里面
		if(!isAdd(Integer.parseInt(id))) {
		String sql1="insert into cart (foodid,num) values(?,?)";
		db.update(sql1, id,num);
		//System.out.println("已经");
			msg="成功添加至购物车";
		}else {
			System.out.println("dsd");
			msg="该商品已经添加到了购物车";
		}
		}
		//编写sql语句信息
		String sql="SELECT\r\n" + 
				"a.foodname,b.id,\r\n" + 
				"a.newprice,\r\n" + 
				"a.pic\r\n" + 
				"FROM\r\n" + 
				"food AS a\r\n" + 
				"INNER JOIN cart AS b ON a.id= b.foodid";
		List<Map<String, Object>> list=db.selectList(sql, null);
		return Utils.castElement(list, Cart.class);
	}
	//判断是否已经添加到购物车
	public boolean isAdd(int id) throws SQLException {
		String sql="select *from cart where foodid=?";
		Map<String, Object> map=db.selectone(sql, id);
		if(map==null) {
			return false;
		}else {
			return true;
		}
	}
	public List<Cart> del(String id) throws SQLException {
		//从购物车里面来删除购物车里面的内容
		String sql="delete from cart where id=?";
		String sql1="SELECT\r\n" + 
				"a.foodid,\r\n" + 
				"b.pic,\r\n" + 
				"a.num,\r\n" + 
				"b.newprice,\r\n" + 
				"b.foodname,\r\n" + 
				"b.`dec`,\r\n" + 
				"a.id\r\n" + 
				"FROM\r\n" + 
				"cart AS a\r\n" + 
				"INNER JOIN food AS b ON a.foodid= b.id";
		db.update(sql, id);
		List<Map<String, Object>> list=db.findMutil(sql1);
		return Utils.castElement(list, Cart.class);
	}
	public List<Cart> query() throws SQLException {
		//编写sql语句信息
		String sql="SELECT\r\n" + 
				"a.foodname,b.id,\r\n" + 
				"a.newprice,\r\n" + 
				"b.num,\r\n" + 
				"a.pic\r\n" + 
				"FROM\r\n" + 
				"food AS a\r\n" + 
				"INNER JOIN cart AS b ON a.id= b.foodid";
		List<Map<String, Object>> list=db.findMutil(sql);
		List<Cart> cart=Utils.castElement(list, Cart.class);
		return cart;
	}
	public List<Cart> addCart(Cart cart) throws SQLException {
		String sql1="SELECT\r\n" + 
				"a.foodid,\r\n" + 
				"b.pic,\r\n" + 
				"a.num,\r\n" + 
				"b.newprice,\r\n" + 
				"b.foodname,\r\n" + 
				"b.`dec`,\r\n" + 
				"a.id\r\n" + 
				"FROM\r\n" + 
				"cart AS a\r\n" + 
				"INNER JOIN food AS b ON a.foodid= b.id";
		//先判断该商品是否已经加入购物车
		if(isAdd(Integer.parseInt(cart.getFoodid()))) {
			//先找出数量
			Map<String, Object> map=db.selectone("select num from cart where foodid=?", cart.getFoodid());
			int value=Integer.parseInt(map.get("num").toString());
			int num=value+1;
					//如果已经添加 就是修改数量
			String sql="update cart set num=? where foodid=?";
			db.update(sql,num,cart.getFoodid());
		}else {
			//如果该购物车还没有添加就先添加到购物车
			String sql="insert into cart (foodid,num) values(?,?)";
			db.update(sql, cart.getFoodid(),cart.getNum());
		}
		return Utils.castElement(db.findMutil(sql1), Cart.class);
	}
	public Map<String, Object> getCount() throws SQLException {
		String sql="select count(*) as num from cart";
		Map<String, Object> map=db.selectone(sql, null);
		return map;
	}
	public List<Cart> addquery() throws SQLException {
		String sql="SELECT\r\n" + 
				"a.foodid,\r\n" + 
				"b.pic,\r\n" + 
				"a.num,\r\n" + 
				"b.newprice,\r\n" + 
				"b.foodname,\r\n" + 
				"b.`dec`,\r\n" + 
				"a.id\r\n" + 
				"FROM\r\n" + 
				"cart AS a\r\n" + 
				"INNER JOIN food AS b ON a.foodid= b.id";
		return Utils.castElement(db.findMutil(sql), Cart.class);
	}

}
