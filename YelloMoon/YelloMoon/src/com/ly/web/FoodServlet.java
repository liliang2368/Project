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
import com.ly.bean.Food;
import com.ly.bean.Fpage;
import com.ly.biz.FoodBiz;
import com.ly.biz.FoodBiz.FoodBean;


/**
 * Servlet implementation class FoodServlet
 */
@WebServlet("/food.do")
public class FoodServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, SQLException {
		FoodBiz dbiz=new FoodBiz();
		FoodBean fb=new FoodBean();
		Fpage page=new Fpage();//用于分页
		//利用工具类给实体类来赋值
		BeanUtils.populate(fb, request.getParameterMap());
		String ff=request.getParameter("ff");		//这里是跳转到购物页面时设置的隐藏字段
		String ffc=request.getParameter("ffc");
		String rr=request.getParameter("rr");//这里 时设置在搜素框里面的隐藏字段
		
		BeanUtils.populate(page, request.getParameterMap());
		List<FoodBean> list=dbiz.query(fb,page);
		//这里将所有的food都设置到回话中
		request.getSession().setAttribute("foodlist1", list);
		if(page.getRows()==6 || ffc!=null || ff!=null) {
			//就要跳转到products.jsp
			response.sendRedirect("products.jsp");
			return;
		}
		if(rr!=null) {
			//这是跳转到查询页面
			response.sendRedirect("query.jsp");
		}
		//查询出总订单
		Map<String, Object> map=dbiz.querycount();
		//easyui的查询只需要将总数和数据两者转换成json数据就可以了
		map.put("rows", list);
		String json=JSON.toJSONString(map);
		System.out.println("看这里的所有商品的数据"+json);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().append(json);
	}
	protected void queryfoodlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, SQLException {
		//先接收到foodname
		String foodname=request.getParameter("foodname");
		//将foodname设置到回话中
		request.getSession().setAttribute("name", foodname);
		FoodBiz dbiz=new FoodBiz();
		List<FoodBiz> list=dbiz.FindFoodName(foodname);
		System.out.println("看这里"+JSON.toJSONString(list));
		request.setAttribute("SqueryFood", list);
		request.getRequestDispatcher("shop.jsp").forward(request, response);
		
	}
	//查询出最近的四个菜
	protected void limitquery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, SQLException {
		FoodBiz dbiz=new FoodBiz();
		FoodBean fb=new FoodBean();
		String gg=request.getParameter("gg");
		//利用工具类给实体类来赋值
		BeanUtils.populate(fb, request.getParameterMap());
		List<FoodBean> list=dbiz.query();
		request.getSession().setAttribute("foodlimit", list);
		if(gg!=null) {
			response.sendRedirect("shop.jsp");
		}
		//执行跳转
		response.sendRedirect("single-product.jsp");	
	}
	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, SQLException {
		//这里来利用实体类具体的储存值
		FoodBiz dbiz=new FoodBiz();
		FoodBean fb=new FoodBean();
		//利用工具类来给实体类来赋值
		BeanUtils.populate(fb, request.getParameterMap());
		//赋值完成,接下来就是逻辑判断层
		dbiz.save(fb);
		response.getWriter().append("菜品添加成功");
	}
	//调用删除的方法
	protected void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, SQLException {
		//删除就只需ID值 于是不需要用到实体
		int id=Integer.parseInt(request.getParameter("id"));
		FoodBiz fbiz=new FoodBiz();
		fbiz.del(id);//删除
		response.getWriter().append("菜品删除成功");
	}
	protected void queryfood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, SQLException {
		//查询所有的食
		FoodBiz fbiz=new FoodBiz();
		List<Food> list=fbiz.FindAll();
		//将该属性都要设置到回话里面
		request.getSession().setAttribute("foodlist", list);
		response.sendRedirect("foodindex.jsp");
	}
	protected void queryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, SQLException {
		//查询出所有的菜品
		FoodBiz dbiz=new FoodBiz();
		String cc=request.getParameter("cc");
		List<Food> list=dbiz.FindList();
		request.getSession().setAttribute("foodlistshop", list);
		if(cc!=null) {
			response.sendRedirect("cart.jsp");
		}
		response.sendRedirect("shop.jsp");
	}
}
