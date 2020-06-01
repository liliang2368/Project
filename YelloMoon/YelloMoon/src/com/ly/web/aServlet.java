package com.ly.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.ly.bean.A;
import com.ly.biz.Abiz;


/**
 * Servlet implementation class aServlet 
 */
@WebServlet("/a.do")
public class aServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	Abiz abiz=new Abiz();
	protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

		Abiz abiz=new Abiz();
		List<A> list=abiz.query();
		//request.setAttribute(name, o);
		request.getSession().setAttribute("href", list);
		//将list转换成json
		String json=JSON.toJSONString(list);
	//	PrintStream
	//	response.getWriter().append(json);
		response.sendRedirect("foodindex.jsp");
		
	}
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, IllegalAccessException, InvocationTargetException {
		//先接收参数
		A a=new A();
		BeanUtils.populate(a, request.getParameterMap());
		abiz.add(a);
		response.getWriter().append("添加成功");
	}
}
