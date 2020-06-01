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

import com.ly.bean.home;
import com.ly.bean.homedetail;
import com.ly.bean.peizhihome;
import com.ly.biz.HomeBiz;



/**
 * Servlet implementation class homeServer
 */
@WebServlet("/home.do")
public class homeServer extends BaseServlet {
	private static final long serialVersionUID = 1L;
	protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		HomeBiz hbiz=new HomeBiz();
		List<home> list=hbiz.query();
		List<home> list1=hbiz.querylimit();
		List<home> list2=hbiz.querylimit2();
		List<home> list3=hbiz.querylimit3();
		List<peizhihome> list4=hbiz.queryuser();
		//将该链表设置到回话中
		request.getSession().setAttribute("homelist", list);
		request.getSession().setAttribute("homelimit1", list1);
		request.getSession().setAttribute("homelimit2", list2);
		request.getSession().setAttribute("homelimit3", list3);
		request.getSession().setAttribute("homelimit4", list4);
		response.sendRedirect("index.jsp");
	}
	protected void queryall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		HomeBiz hbiz=new HomeBiz();
		List<home> list=hbiz.querylist();
		request.getSession().setAttribute("allhomelist", list);
		response.sendRedirect("allhome.jsp");
	}
	//singehome
	protected void singehome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, IllegalAccessException, InvocationTargetException {
		homedetail hd=new homedetail();
		BeanUtils.populate(hd, request.getParameterMap());//将id都获取到里面
		System.out.println(hd.getId());
		HomeBiz hbiz=new HomeBiz();
		List<homedetail> list=hbiz.querydetail(hd);
		List<peizhihome> list1 =hbiz.querypeizhi(hd);
		
		System.out.println(list1);
		System.out.println();
		//将各个回话写入到单间房间里面
		request.getSession().setAttribute("singlehome", list.get(0));
		request.getSession().setAttribute("sigle_peizhi", list1);
		response.sendRedirect("single.jsp");
		
		
		
	}

}
