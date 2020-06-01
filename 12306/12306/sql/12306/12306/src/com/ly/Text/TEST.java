package com.ly.Text;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.ibm.icu.text.SimpleDateFormat;
import com.ly.Ui.Cps_buyTickets;
import com.ly.comms.DbHelper;
import com.ly.dao.UserInfoDao;
import com.ly.dao.railwayDao;
import com.ly.dao.rraletime;
import com.ly.dao.trainTimeDao;
import com.ly.dao.traininfoDao;
import com.ly.util.RanadomSeat;
import com.ly.util.algorithm;
import com.ly.util.seat;
import com.ly.util.setSeatNum;
import com.ly.util.zhuanhuantime;

import oracle.net.aso.i;

public class TEST {
	rraletime dao1=new  rraletime();
	DbHelper db=new DbHelper();
	traininfoDao dao=new traininfoDao();
	railwayDao wayDao=new railwayDao();
	algorithm slg=new algorithm();
	UserInfoDao infodao=new UserInfoDao();
	trainTimeDao traindao=new trainTimeDao();
	zhuanhuantime time=new zhuanhuantime();
	@Test
	public void getconn() throws SQLException{
		System.out.println(db.getConn().getClass().getName());
		
	}
	@Test
	public void cc() throws SQLException{
		List<Map<String, Object>> map=dao.SingleFind();
		System.out.println(map);
	}
	@Test
	public void cc1() throws SQLException{
		List<Map<String, Object>> list=wayDao.station("G1001","desc");
		System.out.println("--------------------");
		System.out.println(list);
	}
	//测试实时票数的统计和更新
	@Test
	public void cc2() throws SQLException{
	String i=dao1.really("北京", "长沙", "软座", "G1001", "2019-1-11");
	String j=dao1.really("北京", "长沙", "硬座", "G1001", "2019-1-11");
	String k=dao1.really("北京", "长沙", "软卧", "G1001", "2019-1-11");
		System.out.println(i);
		System.out.println(j);
		System.out.println(k);
;
	}
	@Test
	public void test01() throws SQLException{
		Set<String> set=wayDao.findAllStation();
		//循环所有的元素并输出
		Iterator<String> iterator=set.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}	
	}
	@Test
	public void test02() throws SQLException{
		List<Map<String, Object>> list=wayDao.FindAllBy("保定","广州");
		System.out.println(list);
		for(Map<String, Object> map:list){
			System.out.println(map.get("S_NAME").toString());
		}
	}
	@Test
	public void test03() throws SQLException{
		List<Map<String, Object>> list=wayDao.findALL("保定", "京广线", "desc");
		System.out.println(list);
			
		
	}
	@Test
	public void test04() throws SQLException{
		List<Map<String, Object>> list=wayDao.FindAllBy("北京", "广州");
		System.out.println(list);
		
	}
	@Test
	public void test05() throws SQLException{
		Map<String, Object> map=wayDao.fandBian("北京", "深圳");
		System.out.println(map);
	}
	//测试能不能根据车次来查询所有的座位席别
	@Test
	public void test06() throws SQLException{
		List<Map<String, Object>> list=dao.FindByTid("G1001");
		System.out.println(list);
	}

	@Test
	public void test08() throws SQLException{
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("TID", "K1008");
		map.put("START", "北京");
		map.put("ARRIVES", "深圳");
		map.put("SLEEPERP", "97");
		map.put("SOFTEEATP", "97");
		map.put("HARDSEATP", "30");
		map.put("GOTIME", "2019-10-1");
		map.put("ARRIVETIME", "2019-10-1");
		int result=traindao.update("G1004", "北京", "深圳", "97", "36", "20", "2019-10-1", "2019-10-1");
		if(result>0){
			System.out.println("插入成功");
		}else{
			System.out.println("插入失败");
		}
		
	}
	@Test
	public void test09() throws SQLException{
		System.out.println("***********************");
		Map<String, Object> map=wayDao.FindPrice("G1003","北京","衡阳");
		System.out.println("*****************");
		System.out.println(map);
	}
	@Test
	public void test10(){
		System.out.println("*****8888888888888888");
		RanadomSeat seat1=new RanadomSeat();
		seat seat = new seat();
		seat1.selecthard();
		System.out.println("车厢号"+ seat.getCarriage() );
	}
	@Test
	public void test11(){
		setSeatNum set=new setSeatNum();
		System.out.println(set.getCar());
		System.out.println(set.getSeat());
	}
	@Test
	public void test12() throws ParseException{
		System.out.println("*****************");
		
	//	System.out.println(date);
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		Date date=format.parse("2018-9-6");
		System.out.println(format.format(date));
	}
	
	
	
	
	
	
	
	
	
}
