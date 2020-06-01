package com.ly.biz;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import com.ly.bean.User;
import com.ly.dao.UserDao;
import com.ly.util.RandomUtil;
import com.ly.util.javaMailUtil;


public class UserBiz {

	public User login(User user) throws IllegalAccessException, InvocationTargetException, SQLException {
		UserDao dao=new UserDao();
		//这里历来进行逻辑判断
		if(user.getEmail()==null || user.getEmail().trim()==null) {
			
		}
		if(user.getPwd()==null || user.getPwd().trim()==null) {
			
		}
		//调用到层的方法来登陆
		User user1=dao.login(user);
		return user;
	}

	public String sendEmail(String email) {
		//先生成验证码
		RandomUtil rd=new RandomUtil();
		String vcode=rd.getRandom();
		System.out.println("看到股票这个验证码"+vcode);
		if(email==null) {
			System.out.println("请输入正确的邮箱");
			return vcode;
		}else {
			javaMailUtil java=new javaMailUtil();
			//发送邮件
			java.send(vcode, email);
		}
		return vcode;
	}

	public void sendPwd(String email) throws SQLException {
		UserDao dao=new UserDao();
		dao.FindPassword(email);
	}

	public List<User> query() throws SQLException {
		UserDao dao=new UserDao();
		return dao.query();
	}

}
