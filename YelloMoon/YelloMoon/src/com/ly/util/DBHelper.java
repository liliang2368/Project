package com.ly.util;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class DBHelper {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	

	//锟斤拷锟斤拷锟斤拷锟斤拷
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//锟斤拷取锟斤拷锟接讹拷锟斤拷 ?useUnicode=true&characterEncoding=UTF-8
	public Connection getConn() throws SQLException{
		conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1/aipiying?useUnicode=true&characterEncoding=UTF-8",
				"root","a");
		
		return conn;
	}
	
	//鏃犲弬鏁版煡璇㈠鏉℃暟鎹?
	public List<Map<String,Object>> findMutil(String sql) throws SQLException{
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=null;
		try{
			conn=getConn();
			pstmt=conn.prepareStatement(sql);
			//锟斤拷锟矫诧拷锟斤拷
			//this.setParams(pstmt, params);
			//执锟斤拷
		
			rs=pstmt.executeQuery();
			//锟斤拷取锟斤拷锟叫碉拷锟斤拷锟斤拷
			List<String> columnNames=this.getAllColumnName(rs);
			while(rs.next()){
				map=new HashMap<String,Object>();
				//循锟斤拷锟斤拷锟叫碉拷锟斤拷
				for(String columnName:columnNames){
					map.put(columnName, rs.getObject(columnName));
			}
				list.add(map);
		}
	}finally{
		closeAll(rs,pstmt,conn);
		}
		return list;
	}
	
	//鑾峰彇鎵?鏈夊垪鍚?
	private List<String> getAllColumnName(ResultSet rs) throws SQLException {
		List<String> list =new ArrayList<String>();
		ResultSetMetaData data=rs.getMetaData();
		//锟斤拷取锟叫的革拷锟斤拷
		int count=data.getColumnCount();
		for(int i=1;i<=count;i++){
			//System.out.println(data.getColumnName(i));
			list.add(data.getColumnName(i));
		}
		return list;
	}
	
	public void closeAll(ResultSet rs,PreparedStatement pstmt,Connection conn) throws SQLException{
		if(null!=rs){
			rs.close();
		}
		if(null!=pstmt){
			pstmt.close();
		}
		if(null!=conn){
			conn.close();
		}
	}
	
	//甯﹀弬鏁拌幏鍙栧鏉℃暟鎹?
	public List<Map<String,Object>> selectList(String sql,Object...params) throws SQLException{
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=null;
		try{
			conn=getConn();
			pstmt=conn.prepareStatement(sql);
			//锟斤拷锟矫诧拷锟斤拷
			this.setParams(pstmt, params);
			//执锟斤拷
			rs=pstmt.executeQuery();
			//锟斤拷取锟斤拷锟叫碉拷锟斤拷锟斤拷
			List<String> columnNames=this.getAllColumnName(rs);
			while(rs.next()){
				map=new HashMap<String,Object>();
				//循锟斤拷锟斤拷锟叫碉拷锟斤拷
				for(String columnName:columnNames){
					map.put(columnName, rs.getObject(columnName));
			}
				list.add(map);
		}
	}finally{
		closeAll(rs,pstmt,conn);
		}
		return list;
	}
	
	
	public Map<String,Object> selectone(String sql,Object...params) throws SQLException{
			Map<String,Object> map=null;
			try{
				conn=getConn();
				pstmt=conn.prepareStatement(sql);
				this.setParams(pstmt, params);
				rs=pstmt.executeQuery();
				List<String> columnNames=this.getAllColumnName(rs);
				//取值
				if(rs.next()){
					map=new HashMap<String,Object>();
					for(String columnName:columnNames){
						map.put(columnName, rs.getObject(columnName));
					}
				}
			}finally{
				closeAll(rs,pstmt,conn);
			}
			return map;
		}
	
	//鑾峰彇鍊?
	public  Object selectValue(String sql,Object...params) throws SQLException{
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=null;
		try{
			conn=getConn();
			pstmt=conn.prepareStatement(sql);
			//锟斤拷锟矫诧拷锟斤拷
			this.setParams(pstmt, params);
			//执锟斤拷
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				return rs.getObject(1);
			}else{
				return null;
			}
		}
	finally{
		closeAll(rs,pstmt,conn);
		}
	}
	
	//澧炲垹鏀?
	public  int update(String sql,Object...params) throws SQLException{
		
		try{
			conn=getConn();
			pstmt=conn.prepareStatement(sql);
			//锟斤拷锟矫诧拷锟斤拷
			this.setParams(pstmt, params);
			//执锟斤拷
			return pstmt.executeUpdate();
		}
		
	finally{
		closeAll(rs,pstmt,conn);
		}
	
	}
	
	
	public void setParams(PreparedStatement pstmt, Object...params) throws SQLException {
		if(null!=params&&params.length>0){
			for(int i=0;i<params.length;i++){
				pstmt.setObject(i+1, params[i]);
			}
		}
		
	}


	
}