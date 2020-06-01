package com.ly.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.ly.bean.foodtype;
import com.ly.biz.typeBiz;

/**
 * 利用查询出所有的类别然后将类别信息都设置到回话中
 * @author 李阳
 *
 */
@WebServlet("/type.do")
public class TypeServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		typeBiz tbiz=new typeBiz();
		List<foodtype> typelist=tbiz.query();
		request.getSession().setAttribute("typelist", typelist);//将视屏分类都设置到表中
		String cc=request.getParameter("cc");
		if(cc!=null) {
			response.sendRedirect("products.jsp");
		}
		//设置成功，用响应重定向来进行跳转
		response.sendRedirect("food.jsp");
	}   
	protected void queryIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		typeBiz tbiz=new typeBiz();
		List<foodtype> typelist=tbiz.query();
		request.getSession().setAttribute("typelistIndex", typelist);//将视屏分类都设置到表中
		//设置成功，用响应重定向来进行跳转
		response.sendRedirect("foodindex.jsp");
	} 
	protected void type(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		typeBiz tbiz=new typeBiz();
		List<foodtype> list=tbiz.findType();
		String json=JSON.toJSONString(list);
		response.getWriter().append(json);
	}
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, IllegalAccessException, InvocationTargetException {
		foodtype fd=new foodtype();
		typeBiz tbiz=new typeBiz();
		BeanUtils.populate(fd, request.getParameterMap());
		tbiz.add(fd);
		response.getWriter().append("添加商品成功");
	}
	protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String id=request.getParameter("id");
		typeBiz tbiz=new typeBiz();
		tbiz.del(id);
		
	
	}

}
