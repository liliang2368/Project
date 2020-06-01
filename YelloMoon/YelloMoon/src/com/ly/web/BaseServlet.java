package com.ly.web;

import java.io.IOException;
import java.lang.reflect.Method;//导包一定要导这个类里面的包
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class UserServlet
 */
//@WebServlet("/0501/user.do")
public abstract class BaseServlet extends HttpServlet {//抽象类不能够直接被创建  而是要继承它    避免直接使用
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 设置字符集编码必须要尽早
		 */
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		/**
		 * java的反射  根据op参数调用不同控制器(servlet)
		 */ 
		String op=request.getParameter("op");
		//System.out.println(op);
		//用反射就要用到类的类  类名.Class或者是   类.getClass();
		try {
			Method m=getClass().getDeclaredMethod(op,HttpServletRequest.class,HttpServletResponse.class);
			m.invoke(this,request,response);//this   表示自己的方法
		} catch (Exception e) {
  			throw new RuntimeException("未找到该方法"+op,e);//运行器异常
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request,response);
	}

}
