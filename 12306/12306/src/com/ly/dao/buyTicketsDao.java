package com.ly.dao;

import java.sql.SQLException;
import java.util.Map;

import com.ly.comms.DbHelper;

public class buyTicketsDao {
	DbHelper db=new DbHelper();
	railwayDao railwaydao = new railwayDao();
	public int buyTickets(int pid,String trainNum,int begin,int end,String date,String seatRank) throws SQLException{
		
		String sql = "insert into rraletime values(seq_rraltime_udate.nextval,?,?,?,?,?,?,1,null,0)";
		return db.update(sql,  pid,trainNum,begin,end,date,seatRank);
	}
	//买票
	
	//传入乘客  和列列车编号   列车出发站  列车到达站  搭乘得日期   座位得席别 票价  票种,车厢 ，身份证号码，座位,车票状态
	public int buyTickets(Map<String, Object> map) throws SQLException{
		String sql="insert into rraletime values(seq_rraltime_udate.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?)";//插入试试票数
		return db.update(sql,map.get("PNAME"),map.get("R_ID"),map.get("S_LOCLATION"),map.get("S_GETLOC"),map.get("CALENDER")
		,map.get("SOFTSEATP"),map.get("TICKET_PRICE"),map.get("TICKET_SPECIES"),map.get("CARRIAGE"),map.get("CAID"),map.get("ORDERUP"),map.get("SEAT"),map.get("STATUS")	);		
	}
	public int newTickets(int pid , String trainNum, int begin , int end,String date, String seatRank, double price, String car , String seat) throws SQLException{
		String sql = "insert into rraletime values(seq_rraltime_udate.nextval,?,?,?,?,?,?,?,'学生票',?,?,1)";
		return db.update(sql, pid,trainNum,begin,end,date,seatRank,price,car,seat);
	}
}
