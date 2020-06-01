package com.ly.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.ly.bean.Cart;
import com.ly.biz.CartBiz;



@WebServlet("/cart.do")
public class cartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       /**
        * 添加到购物车
        * @param request
        * @param response
        * @throws SQLException
        * @throws IOException
        */
	protected void cart(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		String id=request.getParameter("id");
		String num=request.getParameter("num");
		if(num==null) {
			num="1";
		}
		//获取到购物车id 
		//调用cart
		CartBiz cbiz=new CartBiz();
		List<Cart> list=cbiz.query(id,num);	
		//将该设置到回话中
		request.getSession().setAttribute("cart", list);
		Map<String, Object> map=cbiz.getCount();
		map.put("arrdata", list);
		String json=JSON.toJSONString(map);
		//将错误信息设置到
		//request.getSession().setAttribute("msg", CartDao.msg);
		//跳转到index.jsp页面
		response.getWriter().append(json);

	}
	protected void del(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
		
		//通过购物车表来进行删除操作
		String id=request.getParameter("id");
		CartBiz cbiz=new CartBiz();
		List<Cart> list=cbiz.del(id);
		//获取购物车得数量
		Map<String, Object> map=cbiz.getCount();
		map.put("arrdata", list);
	//	request.getSession().setAttribute("msg", "菜品删除成功");
		String json=JSON.toJSONString(map);
		response.getWriter().append(json);
		//可以到这里调用一下FoodServlet  里面的方法
	}
	protected void kjcart(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
		//不需要接收参数
		CartBiz cbiz=new CartBiz();
		List<Cart> list=cbiz.query();
		String json=JSON.toJSONString(list);
		response.getWriter().append(json);
	}
	protected void addcart(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, IllegalAccessException, InvocationTargetException{
		//需要接收fooidid 和 num数量
		Cart cart=new Cart();
		BeanUtils.populate(cart, request.getParameterMap());
		CartBiz cbiz=new CartBiz();
		List<Cart> list=cbiz.daacart(cart);
		Map<String, Object> map=cbiz.getCount();
		map.put("arrdata", list);
		String json=JSON.toJSONString(map);
		//返回服务器
		response.getWriter().append(json);
		//利用ajax是不会跳转页面的
	}
	protected void cartquery(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, IllegalAccessException, InvocationTargetException{
		CartBiz cbiz=new CartBiz();
		List<Cart> list=cbiz.cartQuery();
		Map<String, Object> map=cbiz.getCount();
		map.put("arrdata", list);
		String json=JSON.toJSONString(map);
		//返回服务器
		response.getWriter().append(json);
	}
}
