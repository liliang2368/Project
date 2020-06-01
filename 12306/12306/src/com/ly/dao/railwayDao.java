package com.ly.dao;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ly.comms.DbHelper;

public class railwayDao {
	//根据编号来查询到是那一线路
	DbHelper db=new DbHelper();
	//插入信息扫铁路管理表
	
	
	//将站点转换为对应编号
	public int transToNum(String station) throws SQLException{
		String sql = "select railway_bh from railway where railway_station = ?";
		Map<String, Object> map = db.findSingle(sql, station);
		String num =map.get("RAILWAY_BH").toString();
		int i = Integer.parseInt(num);
		return i;
	}
	//插入站台信息
	public int insert(String railyway,String station) throws SQLException{
		String sql="insert into railway values(?,seq_railway_sta.nextval,?)";
		return db.update(sql, railyway,station);		
	}

	//根据铁路编号查询线路名王哪一个方向
	public Map<String, Object> findBian(String tid) throws SQLException{
		//编写sql语句信息
		String sql="select  railway_name , basc from addbian where tid=? ";
		Map<String, Object> map=db.findSingle(sql, tid);
	return map;
	
		
	}
	//查询出所有的铁路对应的线路名和哪一个方向
	public List<Map<String, Object>> FindAll() throws SQLException{
		String sql="select tid,railway_name ,basc from addbian";
		return db.findMutil(sql, null);
	}
	//测试一下是否能找到起始站和终点站的编号
	public Map<String, Object> fandBian(String start,String end) throws SQLException{
		String sql="select d.railway_bh 起点站 ,f.railway_bh 终点站 from railway d "
				+ "inner join railway f on(d.railway_name=f.railway_name)"
				+ " where d.railway_station=? and f.railway_station=?";
		return db.findSingle(sql, start,end);
		
	}
	//这里时根据起点站和终点站来选择出发的车次，由于时有次序来限制，故这里要分成两种选择
	public List<Map<String, Object>> FindAllBy(String start,String end) throws SQLException{
		StringBuffer sb=new StringBuffer();
		//这里其实也需要用编号来进行比较先获取到两个站的编号在比较大小，是升序还是降序
		String sql="select d.railway_bh Startsta ,f.railway_bh staend from railway d "
				+ "inner join railway f on(d.railway_name=f.railway_name)"
				+ " where d.railway_station=? and f.railway_station=?";
		//这里根据出发站和终点站经行判断之后就可以查询出两地的价格，经过的时间 和历时信息 
		sb.append("select tid,s.s_time1,d.s_time2,s.s_statname gs ,d.s_statname ge ,s.s_sprice-d.s_sprice 软卧 ,s.s_lprice-d.s_lprice 软座 ,s.s_price-d.s_price 硬座 from stationinfo s "
				+ " inner join stationinfo d on(s.s_name=d.s_name)  "
				+ "inner join addbian e on(d.s_name=e.tid) where s.s_statname=? and d.s_statname=? ");
			Map<String, Object> map=db.findSingle(sql, start,end);
		//	System.out.println(map);这里要经行非空判断
			if(Integer.parseInt(map.get("STARTSTA").toString())>Integer.parseInt(map.get("STAEND").toString())){
				sb.append(" and basc='desc'");
				System.out.println("逆序");
			}else{
				sb.append(" and basc='asc'");
			System.out.println("升序");
			}
		List<Map<String, Object>> list=db.findMutil(sb.toString(), start,end);
		return list;
	}
	//查询出所有的站，利用set集合去重操作
	public Set<String> findAllStation() throws SQLException{
		Set<String> set=new HashSet<String>();
		String sql="select railway_station from railway";
		List<Map<String, Object>> list=db.findMutil(sql, null);
		for(Map<String, Object> map:list){
			set.add(map.get("RAILWAY_STATION").toString());
		}
		return set;
		
	}
	//2.已经得到了列车运行的线路，就可以根据所得的线路来选择添加途径的站点
	public List<Map<String, Object>> station(String tid ,String asc) throws SQLException{
	Map<String, Object> map=this.findBian(tid);//得到的就是京广线或者沪昆线也行
	//从list集合来取得我所要的铁路编号
	String railway="";
	//1.线将Map函数转换位set视图就是为了去重操作
	Set<String> set=map.keySet();
	//将所得的值放入迭代器
	Iterator<String> iterator=set.iterator();
	while(iterator.hasNext()){
		String key=iterator.next();
		railway=map.get(key).toString();
	}
;
	//此时的railway就是线路名这里就只要将线路名获取到所有的站点名
	//1.编写sql语句信息
	StringBuffer sb=new StringBuffer();
	sb.append("select railway_bh ,railway_station from railway where railway_name=? order by 1");
	if(asc.equalsIgnoreCase("desc")){
		sb.append(" desc");
	}else{
		sb.append(" asc");
	}
	//System.out.println(sb.toString());
		List<Map<String, Object>> list=db.findMutil(sb.toString(), railway);
		return list;//这里就已经得到京广线和其他线路的站台
		
	}
	//这里是算法那一步，通过前面运行的车站来获取到后面的车站信息
	//这个方法就是你输入站台可以直接的出来的值就可以确定另一个下拉列表的值
	public List<Map<String, Object>> findALL(String station,String railway_name,String asc) throws SQLException{
		String sql="";
		if(asc.equalsIgnoreCase("desc")){
			//System.out.println("----------------------------------------");
			 sql="select railway_station from railway where railway_bh<(select railway_bh from railway where railway_station=?) and railway_name=?";	
		}else{
		 sql="select railway_station from railway where railway_bh>(select railway_bh from railway where railway_station=?) and railway_name=?";
		}
		System.out.println(sql);
		return db.findMutil(sql, station,railway_name);
	}
	//查询出所有的铁路名
	public List<Map<String, Object>> FindRoadName() throws SQLException{
		String sql="select railway_name from  railway union select railway_name from  railway ";
		return db.findMutil(sql, null);
	}
	//根据铁路名获取所有的站台信息
	public List<Map<String, Object>> FindStation(String railway_name,String asc) throws SQLException{
		StringBuffer sb=new StringBuffer();
		sb.append("select railway_bh ,railway_station from railway where railway_name=? order by 1");
		if(asc.equalsIgnoreCase("desc")){
			sb.append(" desc");
		}
		return db.findMutil(sb.toString(), railway_name);
	}
	//根据列车车次查询出所有的站台开是升序还是降序
	public List<Map<String, Object>> FindStation1(String s_name,String asc) throws SQLException{
		StringBuffer sb=new StringBuffer();
		sb.append("select s.s_statname,d.railway_bh from stationinfo s inner join railway  d on(s.s_statname=d.railway_station)  where s_name=? order by 2");
		if(asc.equalsIgnoreCase("desc")){
			sb.append(" desc");
		}else{
			sb.append("asc");
		}
		return db.findMutil(sb.toString(), s_name);
	}
	//查询出所有的站台
	public List<Map<String, Object>> FindStation(String railyNmae) throws SQLException{
		String sql="select railway_bh ,railway_station from railway where railway_name=? order by 1";
		return db.findMutil(sql, railyNmae);
		}

	//将乘客表和实时票数更新表联合起来查询出两张表的字段值   这里查询中的表格就可以只需要价格就可以所有的值
	//还可以根据乘客的姓名来查找编号订单信息
	public List<Map<String, Object>> updatepiao() throws SQLException{
		String sql="select kid,r_id,d.pname,calender,s_loclation,s_getloc,softseatp,ticket_species,ticket_price,carriage,seat,"
				+ "status,caid,orderup from passengerInfo1 d right  join rraletime s on(d.pname=s.pname) union select kid,r_id,d.pname,calender,s_loclation,"
				+ "s_getloc,softseatp,"
				+ "ticket_species,ticket_price,carriage,seat,status,caid,orderup from passengerInfo1 "
				+ "d right  join rraletime s on(d.pname=s.pname)";
		System.out.println(sql);
		return db.findMutil(sql, null);
	}
	//根据编号来查询出所有的站点
	public Map<String, Object> Findbian2(int bian) throws SQLException{
		String sql="select railway_station from railway where railway_bh=?";
		return db.findSingle(sql, bian);
	}
	//根据站台要查询出所有所经的线路
	public List<Map<String, Object>> FindByRoad(String railway_station) throws SQLException{
		String sql="select railway_name from railway where railway_station=?";
		return db.findMutil(sql, railway_station);	
	}
	//根据铁路名来获取到所有经过的站台
	public List<Map<String, Object>> FindStationbyname(String railyName) throws SQLException{
		String sql="select railway_station  from railway where railway_name=?";
		return db.findMutil(sql, railyName);
	}
	
	public int delect(String sname,String station) throws SQLException{
		String sql="delete from stationinfo where s_name=? and s_statname=?";
		return db.update(sql, sname,station);
	}

	//根据车次和出发站和中点站来查询出所有的价格
	public Map<String, Object> FindPrice(String ttd,String start,String end) throws SQLException{
		System.out.println("******************************************");
		String sql="select s.s_sprice-d.s_sprice 软卧,s.s_lprice-d.s_lprice 软座,s.s_price-d.s_price 硬座  from stationinfo s"
				+ " inner join stationinfo d on(s.s_name=d.s_name) where s.s_name=? "
				+ "and s.s_statname=? and d.s_statname=?";
		System.out.println(sql);
		return db.findSingle(sql, ttd,start,end);
	}

	
	}

