package com.ly.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ly.bean.Fpage;
import com.ly.bean.Order;
import com.ly.util.DBHelper;
import com.ly.util.Utils;



public class OrderDao {
	DBHelper db=new DBHelper();

	public List<Order> query(Order order,Fpage p) throws SQLException {
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT\r\n" + 
				"b.id,\r\n" + 
				"c.foodname,\r\n" + 
				"b.num,\r\n" + 
				"e.jtaddr,\r\n" + 
				"c.`dec`,\r\n" + 
				"c.newprice,\r\n" + 
				"c.pic,\r\n" + 
				"b.`status`\r\n" + 
				"FROM\r\n" + 
				"real_order AS a\r\n" + 
				"INNER JOIN oorder AS b ON a.orderid= b.id\r\n" + 
				"INNER JOIN food AS c ON a.foodid= c.id\r\n" + 
				"INNER JOIN res_user AS d ON d.id= b.userid\r\n" + 
				"INNER JOIN addr AS e ON b.addrid= e.id where 1=1");
		ArrayList<Object> array=new ArrayList<Object>();
		if(order.getFoodname()!=null && order.getFoodname().trim().isEmpty()==false) {
			sb.append("and c.foodname like ?");
			array.add("%"+order.getFoodname()+"%");
		}
		if(order.getJtaddr()!=null && order.getJtaddr().trim().isEmpty()==false) {
			sb.append(" and d.jtaddr like ?");
			array.add("%"+order.getJtaddr()+"%");
			System.out.println("看这里"+order.getJtaddr());
		}
		if(order.getId()!=0) {
			sb.append(" and a.id=?");
			array.add(order.getId());
		}
		if(order.getUserid()!=0) {
			sb.append(" and b.userid=?");
			array.add(order.getUserid());
		}
		if(order.getStatus()!=null && order.getStatus().trim().isEmpty()==false) {
			sb.append(" and b.status=?");
			array.add(order.getStatus());
		}
		if(order.getMinprice()!=null && order.getMaxprice()!=null && order.getMaxprice().trim().isEmpty()==false && order.getMinprice().trim().isEmpty()==false) {
			sb.append(" and c.newprice between ? and ?");
			array.add(order.getMinprice());
			array.add(order.getMaxprice());
		}
		//到这里来获取页数
		int page=p.getPage();
		int row=p.getRows();//每行几个数
		sb.append(" limit ?,?");
		array.add((page-1)*row);
		array.add(row);
		//这里要查询条件
		System.out.println(array);
		List<Map<String, Object>> list=db.selectList(sb.toString(), array.toArray());
		System.out.println("看这里的订单"+list);
		List<Order> order1=Utils.castElement(list, Order.class);
		return order1;
	}
	//找出默认的地址插入到订单表中
	public String getAddrid() throws SQLException {
		String sql="select jtaddr from addr where sta=?";
		Map<String, Object> map=db.selectone(sql, 1);
		return map.get("jtaddr").toString();
	}
	public String insert(String userid) throws SQLException {
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createtime=format.format(date);
		//写sql语句信息
		String sql="insert into oorder (userid,status,cratetime,addrid) values(?,?,?,?)";
		db.update(sql, userid,"待支付",createtime,getAddrid());
		//再找寻出订单号将所有的购物车里面的信息都要添加到real_order表中
		String sql1="select max(id) as id from oorder";//找到该插入的订单
		Map<String, Object> map=db.selectone(sql1, null);
		String id=map.get("id").toString();
		//System.out.println("看这里   "+id);
		fillorder(id);//根据id来将素有购物车里面的id都加foodid
		return id;
	}
	//将res_order中的表填满
	public void fillorder(String id) throws SQLException {
		//查询出购物车里面的foodid
		String sql="select foodid from cart";
		List<Map<String, Object>> list=db.findMutil(sql);//找出购物车里面的所有商品将其所有添加到real_order里面
		for (Map<String, Object> map : list) {
			String sql1="insert into real_order(foodid,orderid) values(?,?)";
			db.update(sql1, map.get("foodid").toString(),id);
		}
		
	}

	public List<Order> findAll(String id, String userid) throws SQLException {
		String sql="SELECT\r\n" + 
				"a.id,\r\n" + 
				"a.`comment`,\r\n" + 
				"a.cratetime,\r\n" + 
				"a.`status`,\r\n" + 
				"d.foodname,\r\n" + 
				"d.pic,\r\n" + 
				"a.addrid,\r\n" + 
				"d.num,\r\n" + 
				"d.newprice,\r\n" + 
				"b.`name`,\r\n" + 
				"b.`phone`,\r\n" + 
				"b.type\r\n" + 
				"FROM\r\n" + 
				"oorder AS a\r\n" + 
				"INNER JOIN res_user AS b ON a.userid= b.id\r\n" + 
				"INNER JOIN real_order AS c ON c.orderid= a.id\r\n" + 
				"INNER JOIN food AS d ON c.foodid= d.id\r\n" + 
				" where a.id=? and a.userid=?";
		List<Map<String, Object>> list=db.selectList(sql, id,userid);
	
		//System.out.println(list);
			List<Order> order=Utils.castElement(list, Order.class);
		return order;
	}

	public Order findSingle(String id) throws SQLException, IllegalAccessException, InvocationTargetException {
		Order order=new Order();
		String sql="SELECT\r\n" + 
				"a.id,\r\n" + 
				"a.`comment`,\r\n" + 
				"a.addrid,\r\n" + 
				"b.`name`\r\n" + 
				"FROM\r\n" + 
				"oorder AS a\r\n" + 
				"INNER JOIN res_user AS b ON a.userid= b.id\r\n" + 
				" where a.id=?";
		Map<String, Object> map=db.selectone(sql, id);
		BeanUtils.populate(order, map);
		return order;
	}

	public void update(Order order) throws SQLException {
		String sql="update oorder set comment=?,addrid=?,status=? where id=?";
		int result=db.update(sql, order.getComment(),order.getAddrid(),"已支付",order.getId());
		System.out.println("看这里"+result);
	}

	public int findSum(String id, String userid) throws SQLException {
		String sql="SELECT\r\n" + 
				"sum(c.newprice) price\r\n" + 
				"FROM\r\n" + 
				"oorder AS a\r\n" + 
				"INNER JOIN res_user AS b ON b.id = a.userid\r\n" + 
				"INNER JOIN real_order AS d ON a.id = d.orderid\r\n" + 
				"INNER JOIN food AS c ON d.foodid = c.id\r\n" + 
				"\r\n" + 
				"INNER JOIN addr AS e ON e.userid = b.id\r\n" + 
				"\r\n" + 
				" where a.id=? and a.userid=?";
		Map<String, Object> Map=db.selectone(sql, id,userid);
		
		return Integer.parseInt(Map.get("price").toString());
	}

	public void num(String id, String num) throws SQLException {
		String sql="update oorder set num=? where id=?";
		db.update(sql, num,id);
	}
	public List<Order> FindTypeOrder(String userid, String status) throws SQLException {
		String sql="SELECT\r\n" + 
				"a.`status`,\r\n" + 
				"b.pic,\r\n" + 
				"a.num,\r\n" + 
				"b.newprice\r\n" + 
				"FROM\r\n" + 
				"oorder AS a\r\n" + 
				"INNER JOIN food AS b ON a.foodid= b.id where a.userid=? and a.`status`=?\r\n" + 
				"";
		List<Map<String, Object>> list=db.selectList(sql, userid,status);
		return Utils.castElement(list, Order.class);
	}
	public Map<String, Object> findCount() throws SQLException {
		String sql="select count(*) as total from oorder";
		Map<String, Object> map=db.selectone(sql, null);
		return map;
	}

}
