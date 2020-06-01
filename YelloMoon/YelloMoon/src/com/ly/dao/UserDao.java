package com.ly.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ly.bean.User;
import com.ly.util.DBHelper;
import com.ly.util.Utils;
import com.ly.util.javaMailUtil;


public class UserDao {
	DBHelper db=new DBHelper();
	public User login(User user) throws SQLException, IllegalAccessException, InvocationTargetException {

		//编写sql语句
		String sql="select * from res_user where email=? and pwd=?";
	
			Map<String, Object> map=db.selectone(sql, user.getEmail(),user.getPwd());
			BeanUtils.populate(user, map);
		//登陆新系统
		return user;
	}

	public void FindPassword(String email) throws SQLException {
		String sql="select pwd from res_user where email=?";
		Map<String, Object> map=db.selectone(sql, email);
		String password=map.get("pwd").toString();
		javaMailUtil.send(password, email);
	}

	public List<User> query() throws SQLException {
		String sql="select *from res_user";
		List<Map<String, Object>> list=db.findMutil(sql);
	
		return Utils.castElement(list, User.class);
	}

}
