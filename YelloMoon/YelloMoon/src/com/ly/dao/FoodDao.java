package com.ly.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ly.bean.Food;
import com.ly.bean.Fpage;
import com.ly.bean.KuwuMusic;
import com.ly.biz.FoodBiz;
import com.ly.biz.FoodBiz.FoodBean;
import com.ly.util.DBHelper;
import com.ly.util.Utils;



public class FoodDao {
	DBHelper db=new DBHelper();
	public List<KuwuMusic> FindAllMusic(Fpage fp, KuwuMusic ku) throws SQLException {
		ArrayList<String> array=new ArrayList<String>();
		//到这里来获取页数
		int page=fp.getPage();
		int row=fp.getRows();//每行几个数
		//设置逻辑第一个问号
		String sql="select id ,artist,name,album,music_url,lrc from kuwo_music limit ?, ?";
		array.add(""+(page-1)*row);
		array.add(""+row);
		
		return Utils.castElement(db.selectList(sql, array), KuwuMusic.class);
	}
	public List<FoodBean> query(FoodBean fb,Fpage p) throws SQLException {
		/**
		 * 最小价值  最大价值maxprice     foodname菜品名   info菜品名   
		 */
		StringBuffer sb=new StringBuffer();
		ArrayList<Object> array=new ArrayList<Object>();
		sb.append("SELECT\r\n" + 
				
				"b.head1,\r\n" + 
				"b.head2,\r\n" + 
				"b.head,\r\n" + 
				"b.head3,\r\n" + 
				"b.head4,\r\n" + 
				"a.num,\r\n" + 
				"a.newprice,\r\n" + 
				"a.oldprice,\r\n" + 
				"a.createtime,\r\n" + 
				"a.pic,\r\n" + 
				"a.dec,\r\n" + 
				"a.foodname,\r\n" + 
				"a.id,\r\n" + 
				"c.type,\r\n" + 
				"b.info,\r\n" + 
				"b.foodid\r\n" + 
				"FROM\r\n" + 
				"food AS a\r\n" + 
				"INNER JOIN fooddetail AS b ON a.id= b.foodid\r\n" + 
				"INNER JOIN foodtype AS c ON a.typeid= c.id where 1=1");
		//到这里来获取页数
		int page=p.getPage();
		int row=p.getRows();//每行几个数
		//设置逻辑第一个问号

		if(fb.getFoodname()!=null && fb.getFoodname().trim().isEmpty()==false) {
			sb.append(" and a.foodname like ?");
			array.add("%"+fb.getFoodname()+"%");
		}
		if(fb.getInfo()!=null && fb.getInfo().trim().isEmpty()==false) {
			sb.append(" and a.info like ?");
			array.add("%"+fb.getInfo()+"%");
		}
		if(fb.getMinprice()!=0 && fb.getMaxprice()!=0) {
			sb.append(" and a.newprice between  ? and ?");
			array.add(fb.getMinprice());
			array.add(fb.getMaxprice());
		}
		if(fb.getType()!=null && fb.getType().trim().isEmpty()==false) {
			sb.append(" and c.type=?");
			array.add(fb.getType());
		}

		sb.append(" limit ?,?");
		array.add((page-1)*row);
		array.add(row);
//		System.out.println(sb.toString());
		List<Map<String, Object>> list=db.selectList(sb.toString(), array.toArray());
		System.out.println("看这里所有的商品详情页的数据是"+list);
		List<FoodBean> food=Utils.castElement(list, FoodBean.class);
		return food;
	}
	
	public void add(FoodBean fb) throws SQLException {
		//分解成两个子查询
		Date date =new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		//将所有的值插入到food表中
		String sql1="insert into food(foodname,dec,oldprice,newprice,createtime,pic,num,typeid) values(?,?,?,?,?,?,?,?)";
		db.update(sql1, fb.getFoodname(),fb.getDec(),fb.getOldprice(),fb.getNewprice(),time,fb.getPic(),fb.getNum(),FindTypeId(fb.getType()));
		String sql3="select max(id) as id from food";
		Map<String, Object> map=db.selectone(sql3, null);
		System.out.println(map.get("id").toString());
		//到这里要详情页的图
		String sql2="insert into fooddetail (foodid) values(?)";
		db.update(sql1, map.get("id").toString());
	}

	public void update(FoodBean fb) throws SQLException {
	//	System.out.println("看这里选中的id为"+fb.getId()+fb.getDec()+fb.getNewprice()+fb.getNum()+fb.getOldprice());
		//修改
		//编写sql语句
		String sql="update food set dec=?,oldprice=?,newprice=?,pic=?,num=?,typeid=? where id =?";
		db.update(sql,fb.getDec(),fb.getOldprice(),fb.getNewprice(),fb.getPic(),fb.getNum(),FindTypeId(fb.getType()),fb.getId());
	}
	//根据分类名来查询出id值
	public int FindTypeId(String type) throws SQLException {
		String sql="select id from foodtype where type=? ";
		Map<String, Object> map=db.selectone(sql, type);
		return Integer.parseInt(map.get("id").toString());
	}
	public void del(int id) throws SQLException {
		String sql="delete from food where id=?";
		db.update(sql, id);
	}
	public List<Food> FindALL() throws SQLException {
		//编写sql语句信息
		String sql="select *from food limit 8";
		List<Map<String, Object>> list=db.findMutil(sql);
		List<Food> food=Utils.castElement(list, Food.class);
		return food;
	}
	public List<FoodBean> query() throws SQLException {
		String sql="select *from food limit 4";
		List<Map<String, Object>> list=db.findMutil(sql);
		List<FoodBean> food=Utils.castElement(list, FoodBean.class);
		return food;
	}

	public Map<String, Object> queryCount() throws SQLException {
		String sql="select count(*) as total from food";
		return db.selectone(sql, null);
	}

	public List<Food> FindList() throws SQLException {
		String sql="select *from food";
		List<Map<String, Object>> list=db.findMutil(sql);
		return Utils.castElement(list, Food.class);
	}
	public List<FoodBiz> FindFoodname(String foodname) throws SQLException {
		StringBuffer sb=new StringBuffer();
		ArrayList<Object> array=new ArrayList<Object>();
		sb.append("SELECT\r\n" + 
				"b.head1,\r\n" + 
				"b.head2,\r\n" + 
				"b.head,\r\n" + 
				"b.head3,\r\n" + 
				"b.head4,\r\n" + 
				"a.num,\r\n" + 
				"a.newprice,\r\n" + 
				"a.oldprice,\r\n" + 
				"a.createtime,\r\n" + 
				"a.pic,\r\n" + 
				"a.dec,\r\n" + 
				"a.foodname,\r\n" + 
				"c.type,\r\n" + 
				"b.info,\r\n" + 
				"b.foodid\r\n" + 
				"FROM\r\n" + 
				"food AS a\r\n" + 
				"INNER JOIN fooddetail AS b ON a.id= b.foodid\r\n" + 
				"INNER JOIN foodtype AS c ON a.typeid= c.id where 1=1");
		if(foodname.isEmpty()==false || foodname.trim()==null) {
			sb.append(" and a.foodname=?");
			array.add(foodname);
		}
		List<Map<String, Object>> list=db.selectList(sb.toString(), array.toArray());
		System.out.println("看这里"+list);
		return Utils.castElement(list, FoodBiz.class);
	}
}
