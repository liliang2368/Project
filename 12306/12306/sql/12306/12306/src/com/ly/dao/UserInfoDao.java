package com.ly.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ly.comms.DbHelper;

/**
 * 
 *
 */
public class UserInfoDao {
	private DbHelper db=new  DbHelper();
	//1.
	public Map<String, Object> FindByPwd(String userinfo,String pwd) throws SQLException{
		//编写sql语句
		String sql="select *from userinfo where uname=? and pwd=?";
		return db.findSingle(sql, userinfo,pwd);
	}
	//获取到所有的用户名
	public List<Map<String, Object>> FindAll() throws SQLException{
		//编写sql语句
		String sql="select *from userinfo";
		return db.findMutil(sql, null);		
	}
	//3.根据用户名来查询出密保
	public Map<String, Object> Findby(String username) throws SQLException{
		String sql="select question,email,answer,balance from userinfo where uname=?";
		return db.findSingle(sql, username);
		
	}
	
	//2.
	public int update(Map<String, Object> map) throws SQLException{
		//1.
		String	sql="update into userinfo set uname=? pwd=? email=? balance=? question=? answer=? where usid=?";
		return db.update(sql, map.get("UNAME"),map.get("PWD"),map.get("BALANCE"),map.get("QUESTION"),map.get("ANSWER"),map.get("USID"));
		
	}
	//建立新用户
	public int setNewUser(String name,String pwd,String email,String question,String answer) throws SQLException{
		String sql = "insert into userinfo values(seq_passenge.nextval,?,?,?,0,?,?)";
		return db.update(sql, name,pwd,email,question,answer);
	}
	
	//查询用户信息
	public Map<String, Object> InquInfo(String userName) throws SQLException{
		String sql = "select usid,uname,email,balance from userinfo where uname = ?";
		return db.findSingle(sql, userName);
	}
	//登陆时获取密码进行比较
	public Map<String,Object> getPwd(String userName) throws SQLException{
		String sql = "select pwd from userinfo where uname = ?";
		return db.findSingle(sql, userName);
	}
	//修改邮箱时获取密保问题题目
	public Map<String,Object> getQue(String userName) throws SQLException{
		String sql = "select question from userinfo where uname = ?";
		return db.findSingle(sql,userName);
	}
	//修改邮箱时获取密保问题答案进行比较
	public Map<String,Object> getAns(String userName) throws SQLException{
		String sql = "select answer from userinfo where uname = ? ";
		return db.findSingle(sql, userName);
	}
	//修改邮箱时获取原邮件地址进行验证
	public Map<String,Object> getMail(String userName) throws SQLException{
		String sql = "select email from userinfo where uname = ?";
		return db.findSingle(sql, userName);
	}
	//完成修改邮箱
	public int updateMail(String userName,String mail ) throws SQLException{
		String sql = "update userinfo set email = ? where uname = ?" ;
		return db.update(sql, mail,userName);
	}
	//查询个人信息
	public List<Map<String, Object>> message() throws SQLException{
		String sql="select * from userinfo";
		return db.findMutil(sql, null);
	}
	//判断用户名是否已存在
	public boolean userName(String username) throws SQLException{
		String sql="select *from userinfo where uname=?";
		if(db.findSingle(sql, username) == null){
			return false;
		}
		return true;
	}
	//充值
	public int addSal(String userName,int add) throws SQLException{
		String sql = "update userinfo set balance = balance + ? where uname = ?";
		return db.update(sql, add,userName);
	}
	//修改密码
	public int changepwd(String newPwd,String userName) throws SQLException{
		String sql = "update userinfo set pwd = ? where uname = ?";
		return db.update(sql, newPwd,userName);
	}
	//获取余额进行比较
	public Map<String ,Object> getBal(String userName) throws SQLException{
		String sql = "select balance from userinfo where uname = ?";
		return db.findSingle(sql, userName);
	}
	//付款
	public int payTicket(String userName,double price) throws SQLException{
		String sql = "update userinfo set balance = balance -?  where uname = ?";
		return db.update(sql, price, userName );
	}
	
}
