package com.ly.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.ly.bean.User;
import com.ly.biz.UserBiz;


/**
 * 服务
 */
@WebServlet("/user.do")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private String email;
	private String vcode;

	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, SQLException {
		User user=new User();	
		//获取用户名和密码
		BeanUtils.populate(user, request.getParameterMap());
		//调用业务层接口的方法
		UserBiz fbiz=new UserBiz();
		User ss=fbiz.login(user);
		//如果user1登陆成功将将user是体力对象都设置到回话对象里面去  然后再使用响应重定向来跳转页面
		//System.out.println("看这里"+ss.getId());
		request.getSession().setAttribute("LoginUser", ss);
		//使用重定向的方式来进行跳转页面
		response.sendRedirect("foodindex.jsp");
		
	}
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
		//使会话失效
		request.getSession().invalidate();
		//页面的跳转
		response.sendRedirect("foodindex.jsp");
	}	
	protected void findpwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
		//先获取到账号
		UserBiz fbiz=new UserBiz();
		email=request.getParameter("email");
		vcode=fbiz.sendEmail(email);
		//将验证码保存到回话里面
		request.getSession().setAttribute("vcode", vcode);
		//将邮箱也		UserBiz fbiz=new UserBiz();
//保存到会话中
		request.getSession().setAttribute("email", email);
		
	}
	protected void getpwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, SQLException {
		//先获取验证码
		String vcode1=request.getParameter("vcode1");
		System.out.println("看这里"+vcode1+email+vcode);
		//根据邮箱账号来找密码
		UserBiz fbiz=new UserBiz();
		if(vcode.trim()==vcode1.trim() || vcode.equals(vcode1)) {
		fbiz.sendPwd(email);
		}else {
			response.getWriter().append("验证码不正确");	
		}
		response.getWriter().append("密码已经通过邮箱发送");
	}
	protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, SQLException {
		UserBiz fbiz=new UserBiz();
		List<User> list=fbiz.query();
		String json=JSON.toJSONString(list);
		response.getWriter().append(json);
	
	}
}
