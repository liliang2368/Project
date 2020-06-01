package com.ly.comms;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class DbHelper {
	private Connection conn ;
	private PreparedStatement pstmt;
	private ResultSet rs ;
	
	
	//�������� 
	static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//��ȡ���Ӷ��� 
	public  Connection  getConn() throws SQLException{
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "a");
		return conn;
	}
	
	/**
	 * �鿴������¼
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findMutil(String sql,Object...params) throws SQLException{
		List<Map<String, Object>>  list = new ArrayList<Map<String,Object>>();
		Map<String, Object>  map =null;
		try{
			conn =getConn();
			pstmt = conn.prepareStatement(sql);
		
			this.setParams(pstmt, params);
			
			rs = pstmt.executeQuery();
			//��ȡ���е����� 
			List<String> columnNames = this.getAllColumnNames(rs);
			while(rs.next()){
				map = new HashMap<String, Object>();
				
				for(String columnName:columnNames){ 
					map.put(columnName, rs.getObject(columnName)) ;//map.put("empno",rs.getObject("empno"));
				}
				list.add(map);//��ӵ�List������ 
			}
		}finally{
			closeAll(rs, pstmt, conn);
		}
		
		return list;
	}
	
	
	/**
	 * ��ѯ������¼  select  * from tableName where id = ? 
	 * @param sql  ��ѯ��sql��� 
	 * @param params   ����Ĳ���  
	 * @return
	 * @throws SQLException 
	 */
	public Map<String, Object> findSingle(String sql,Object...params) throws SQLException{
		Map<String, Object>  map =null;
		try{
			conn =getConn();
			pstmt = conn.prepareStatement(sql);
			
			this.setParams(pstmt, params);
			//ִ�� 
			rs = pstmt.executeQuery();
			//��ȡ���е����� 
			List<String> columnNames = this.getAllColumnNames(rs);
			//System.out.println(columnNames);
			//ȡֵ 
			if(rs.next()){
				map = new HashMap<String, Object>();
				//ѭ�����е��� 
				for(String columnName:columnNames){
					map.put(columnName, rs.getObject(columnName)) ;//map.put("empno",rs.getObject("empno"));
				}
			}
		}finally{
			closeAll(rs, pstmt, conn);
		}
		return map;
	}
	
	/**
	 * ��ȡ���е�����  
	 * @return
	 * @throws SQLException 
	 */
	public List<String>  getAllColumnNames(ResultSet rs) throws SQLException{
		List<String>  list = new ArrayList<String>();
		ResultSetMetaData  data = rs.getMetaData();
		//
		int count =data.getColumnCount();
		for(int i =1;i<=count;i++){
			list.add(data.getColumnName(i));//获取第一列的值
		}
		return list;
	}
	
	
	/**
	 * �ۺϺ����Ĳ�ѯ  select count(*) from emp
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public  int getPolymer(String sql,Object...params) throws SQLException{
		int result =0;
		try{
			conn =getConn();
			pstmt = conn.prepareStatement(sql);
			
			this.setParams(pstmt, params);
		 
			rs = pstmt.executeQuery();
			if(rs.next()){
				result =rs.getInt(1);
			}
		}finally{
			closeAll(rs, pstmt, conn);
		}
		return result;
	}
	
	
	
	/**
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public int update(String sql ,Object...params) throws SQLException{
		int result =0;
		try{
			conn=  getConn();
			pstmt =conn.prepareStatement(sql);
			//���ò��� 
			this.setParams(pstmt, params);
			//ִ�� 
			result =pstmt.executeUpdate();
		}finally{
			closeAll(null, pstmt, conn);
		}
		return result;
	}
	
	//���ò���
	public void setParams(PreparedStatement pstmt ,Object...params) throws SQLException{
		if(null!=params&&params.length>0){
			 for(int i =0;i<params.length;i++){
				 pstmt.setObject(i+1, params[i]);  //? ��1��ʼ 
			 }
		 }
	}
	public void setParams(PreparedStatement pstmt ,List<Object> params) throws SQLException{
		if(null!=params&&params.size()>0){
			 for(int i =0;i<params.size();i++){
				 pstmt.setObject(i+1, params.get(i));  //? ��1��ʼ 
			 }
		 }
	}
	
	//�ر���Դ 
	public  void closeAll(ResultSet rs ,PreparedStatement pstmt,Connection conn){
		if(null!=rs){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(null!=pstmt){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(null!=conn){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public List<Map<String, Object>> findMutil1(String sql,List<Object> params) throws SQLException{
		List<Map<String, Object>>  list = new ArrayList<Map<String,Object>>();
		Map<String, Object>  map =null;
		try{
			conn =getConn();
			pstmt = conn.prepareStatement(sql);
			//���ò��� 
			this.setParams(pstmt, params);
			//ִ�� 
			rs = pstmt.executeQuery();
			//��ȡ���е����� 
			List<String> columnNames = this.getAllColumnNames(rs);
			while(rs.next()){
				map = new HashMap<String, Object>();
				//ѭ�����е��� 
				for(String columnName:columnNames){ //��װ��map�еļ����Ǵ�д��ĸ    oralce���ݿ�Ĭ�϶��Ǵ�д 
					map.put(columnName, rs.getObject(columnName)) ;//map.put("empno",rs.getObject("empno"));
				}
				list.add(map);//��ӵ�List������ 
			}
		}finally{
			closeAll(rs, pstmt, conn);
		}
		
		return list;
	}

	
}
