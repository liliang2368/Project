package com.ly.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.ly.bean.Addr;
import com.ly.bean.Fpage;
import com.ly.bean.Order;
import com.ly.biz.AddrBiz;
import com.ly.biz.OrderBiz;


/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/order.do")
public class OrderServlet extends BaseServlet {
	private String userid;
	private String id;

	private static final long serialVersionUID = 1L;


	/**
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SQLException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, SQLException {
		//调用order的实体类来接收所有的参数
		OrderBiz abiz=new OrderBiz();
		Order order=new Order();
		Fpage page=new Fpage();
		BeanUtils.populate(order, request.getParameterMap());
		BeanUtils.populate(page, request.getParameterMap());
		List<Order> list=abiz.query(order,page);
		//查询出总订单
		Map<String, Object> map=abiz.FindCount();
		map.put("rows", list);
		String json=JSON.toJSONString(map);
		System.out.println("看这里的所有的json文件"+json);
		response.getWriter().append(json);
	}
	protected void order(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, SQLException {
		OrderBiz obiz=new OrderBiz();
		AddrBiz abiz=new AddrBiz();
		//先接收用户id   
		userid=request.getParameter("userid");
		//先生成订单号能对其他地方进行操作
		id=obiz.insert(userid);
		//如何将id和购物车表来进行关联，将所有的购物车信息都要加到
		//1.找出userid的填写的所有地址并设置到回话中
		List<Addr> addr=abiz.query(userid);
		request.getSession().setAttribute("addrlist", addr);
		//设置总价
		int num=obiz.FindSum(id,userid);
		//将总价都写入到回话中
		request.getSession().setAttribute("num", num);
		//2.找出foodid所有信息该关联的字段都应该要有
		Order list=obiz.FindSingle(id);
		request.getSession().setAttribute("order", list);
		response.sendRedirect("order.jsp");
	
	}
	//这里是orderquery
	protected void orderquery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, SQLException {
		Order order=new Order();
		BeanUtils.populate(order, request.getParameterMap());
		//这里根据订单id和用户id来获取到值
		OrderBiz obiz=new OrderBiz();
		if(id==null) {
			id=request.getParameter("id");
		}
		if(userid==null) {
			userid=request.getParameter("userid");
		}
		if(order.getAddrid()!=null) {
			//执行插入操作
			obiz.update(order);
		}
		//执行完操作之后就可以查询所有的菜品名
		List<Order> list=obiz.FindAll(id, userid);
		//转换成json格式
		String json=JSON.toJSONString(list);
		response.getWriter().append(json);
	}
	protected void num(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, SQLException {
		//这里获取到的是 id  和num值
		String id=request.getParameter("id");
		String num=request.getParameter("num");
		OrderBiz obiz=new OrderBiz();
		obiz.num(id,num);
	}
	protected void findorder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, SQLException {
		String userid=request.getParameter("userid");
		String status=request.getParameter("status");
		OrderBiz obiz=new OrderBiz();
		List<Order> list=obiz.FindTypeOrder(userid,status);
		request.getSession().setAttribute("typeorder", list);
		response.getWriter().append("查询成功");
		response.sendRedirect("person.jsp");
	}
}
