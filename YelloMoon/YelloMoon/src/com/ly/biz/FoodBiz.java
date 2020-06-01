package com.ly.biz;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ly.bean.Food;
import com.ly.bean.Fpage;
import com.ly.dao.FoodDao;



public class FoodBiz {
	/**
	 * 静态内部类
	 */
	public static class FoodBean extends Food{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private double minprice;
		private double maxprice;
		private String type;
		private String foodid;
		private String info;
		private String head1;
		private String head2;
		private String head3;
		private String head4;
		
		public String getHead1() {
			return head1;
		}
		public void setHead1(String head1) {
			this.head1 = head1;
		}
		public String getHead2() {
			return head2;
		}
		public void setHead2(String head2) {
			this.head2 = head2;
		}
		public String getHead3() {
			return head3;
		}
		public void setHead3(String head3) {
			this.head3 = head3;
		}
		public String getHead4() {
			return head4;
		}
		public void setHead4(String head4) {
			this.head4 = head4;
		}
		public String getInfo() {
			return info;
		}
		public void setInfo(String info) {
			this.info = info;
		}
		public String getFoodid() {
			return foodid;
		}
		public void setFoodid(String foodid) {
			this.foodid = foodid;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public double getMinprice() {
			return minprice;
		}
		public void setMinprice(double minprice) {
			this.minprice = minprice;
		}
		public double getMaxprice() {
			return maxprice;
		}
		public void setMaxprice(double maxprice) {
			this.maxprice = maxprice;
		}
		
	}

	public List<FoodBean> query(FoodBean fb ,Fpage page) throws SQLException {
		FoodDao dao=new FoodDao();
		List<FoodBean> list=dao.query(fb,page);
		return list;
	}

	public void save(FoodBean fb) throws SQLException {
		FoodDao dao=new FoodDao();
		//插入操作
		if(fb.getFoodname()==null) {
			
			return ;
		}
		//这里分成两种情况  一种是带id就是修改 另一种是不带id就是为了添加
		if(fb.getId().trim()==null) {
			System.out.println("这里是添加的操作");
		dao.add(fb);
		}else {
			System.out.println("这里是修改的操作");
			dao.update(fb);
		}
		
	}
	public void del(int id) throws SQLException {
		FoodDao fdao=new FoodDao();
		fdao.del(id);
	}

	public List<Food> FindAll() throws SQLException {
		FoodDao dao=new FoodDao();
		//查询出所有的食品
		List<Food> list=dao.FindALL();
		return list;
	}

	public List<FoodBean> query() throws SQLException {
		FoodDao dao=new FoodDao();
		//查询出所有的食品
		List<FoodBean> list=dao.query();
		return list;
	}

	public Map<String, Object> querycount() throws SQLException {
		FoodDao dao=new FoodDao();
		Map<String, Object> map=dao.queryCount();
		return map;
	}

	public List<Food> FindList() throws SQLException {
		FoodDao dao=new FoodDao();
		//查询出所有的食品
		List<Food> list=dao.FindList();
		return list;
	}

	public List<FoodBiz> FindFoodName(String foodname) throws SQLException {
		FoodDao dao=new FoodDao();		
		return dao.FindFoodname(foodname);
	}
}
