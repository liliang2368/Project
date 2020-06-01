package com.ly.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ly.comms.DbHelper;

public class rraletime {
	DbHelper db=new DbHelper();
	private traininfoDao info=new traininfoDao();
	private railwayDao rail=new railwayDao();
	//根据用户输入的起始站和终点站 查询出总票数   座位席别 和查询日期 和车次
	public String really(String start,String end,String seatype, String ttd,  String clader) throws SQLException{
		int sum=0;
		//先判断列车是否有存入票数表中
		if(info.FindTid(ttd)){
			//说明是有该值的存在的，基于可以查询出所有的票数了
			Map<String, Object> map=info.Findvote(seatype, ttd);
			sum=Integer.parseInt(map.get("SOFT1SEATP").toString());//获取到总票数
		}else{
			sum=50;
		}
		int i=rail.transToNum(start);
		//1.线根据用户输入的起始站和终点站转换成数字
		//int i 代表起始站 j代表终点站
//		String sql1="select railway_bh from railway where railway_station=?";
//		Map<String, Object> map=db.findSingle(sql1, start);
//		System.out.println(map);
//		int i=Integer.parseInt(map.get("RAILWAY_BH").toString());
//		System.out.println(i);
//		Map<String, Object> map1=db.findSingle(sql1, end);
		int j=rail.transToNum(end);
//		System.out.println(j);
		//查询出根据用户总票数
		String sql="select ?-count(*) GG from rraletime where softseatp=? and not ( ?> s_getloc or  ? <s_loclation ) and r_id=? and calender=?";
		Map<String, Object> map3=db.findSingle(sql, sum,seatype,i,j,ttd,clader);
		String k=map3.get("GG").toString();
		return k;
	}
	//退票后修改订单状态
	public int refund(int pid) throws SQLException{
		String sql = "update rraletime set status = 2  where kid = ?";
		return db.update(sql, pid);
	}
	//根据身份证来修改订单状态
	public int rezhaung(int status,String caid) throws SQLException{
		String sql="update rraletime set status=? where caid caid=?";
		return db.update(sql, status,caid);
	}
	//根据列车订单号来删除原订单
	public int delete(int i) throws SQLException{
		String sql="delete from rraletime where kid=?";
		return db.update(sql, i);
	}
	//改签
	public int changeDate(int kid) throws SQLException{
		String sql = "update rraletime set status = 3 where kid = ?";
		return db.update(sql, kid);
	}
	//找出最新的订单方便直接改签操作
	public Map<String , Object> findLatest() throws SQLException{
		String sql = "select max(kid) ss from rraletime";
		return db.findSingle(sql, null);
	}
	public int afterPay(int kid) throws SQLException{
		String sql = "update rraletime set status = 1 where kid = ? ";
		return db.update(sql, kid);
	}
	//根据车厢号和座位号来出来所有的值
	public boolean inquyer(String carrige,String seat) throws SQLException{
		String sql="select *from rraletime where carriage=? and seat=?";
		Map<String, Object> map=db.findSingle(sql, carrige,seat);
		if(map.size()>0 ){
			return false;
		}else{
			return true;
		}
	}
	//根据状态来获取所有的值
	public List<Map<String, Object>> getAllstatus(int status) throws SQLException{
		String sql="select *from rraletime where status=?";
		return db.findMutil(sql, status);
	}
	public Map<String,Object> findstatus(int kid) throws SQLException{
		String sql = "select status from rraletime where kid = ? ";
		return db.findSingle(sql, kid);
	}
	public List<Map<String,Object>> Query(String calender) throws SQLException{
		String sql="select * from rraletime where calender=?";
		return db.findMutil(sql, calender);
	}
	//获取所有的订单编号和下单日期
	public List<Map<String, Object>> getCalder() throws SQLException{
		String sql="select kid,orderup,pname,status from rraletime";
		return db.findMutil(sql, null);
	}	
	
	
	

}

