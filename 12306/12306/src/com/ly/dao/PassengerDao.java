package com.ly.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ly.comms.DbHelper;
	
	public class PassengerDao {
		DbHelper db = new DbHelper();
		
		//查询乘车人信息
		public List<Map<String, Object>> findPassenger() throws SQLException{
		String sql = " select pname,carId from passengerInfo1";
		return db.findMutil(sql, null);
	}
		//根据乘客的身份证号获取乘客编号
		public Map<String, Object> transCarIdToPid(String carId) throws SQLException{
			String sql = "select pid from passengerInfo1 where carId = ?";
			return db.findSingle(sql, carId);
			
		}
	
		
}
