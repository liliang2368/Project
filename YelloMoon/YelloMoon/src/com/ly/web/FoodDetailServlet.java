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

import org.apache.commons.beanutils.BeanUtils;

import com.ly.bean.Comment;
import com.ly.bean.fooddtail;
import com.ly.biz.CommentBiz;
import com.ly.biz.FoodDetailBiz;


/**
 * Servlet implementation class FoodDetailServlet
 */
@WebServlet("/fooddetil.do")
public class FoodDetailServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @throws SQLException s
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, IllegalAccessException, InvocationTargetException {
		
		//从服务器里面获取foodid
		String id=request.getParameter("id");
		String userid=request.getParameter("userid");
		CommentBiz cbiz=new CommentBiz();
		List<Comment> list=cbiz.FindAll(id);
		request.getSession().setAttribute("foodid", id);
		request.getSession().setAttribute("userid", userid);
		request.getSession().setAttribute("comment", list);
		//将方法设置到回话中
		//调用fooddetail
		FoodDetailBiz foodbiz=new FoodDetailBiz();
		//调用业务逻辑
		fooddtail list1=foodbiz.FindId(id);
		//将所有的方法都设置到会话中
		request.getSession().setAttribute("detail", list1);
		//跳转到单个商品详情页面
		response.sendRedirect("single-product.jsp");
	}
	/**
	 * @throws SQLException s
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, IllegalAccessException, InvocationTargetException {
		//先接收参数
		fooddtail fdteail=new fooddtail();
		BeanUtils.populate(fdteail, request.getParameterMap());
		System.out.println("看这里所有的商品信息"+fdteail.getFoodname()+fdteail.getHead1()+fdteail.getHead2()+fdteail.getHead3()+fdteail.getId());
		FoodDetailBiz fbiz=new FoodDetailBiz();
		fbiz.save(fdteail);
	}


}
