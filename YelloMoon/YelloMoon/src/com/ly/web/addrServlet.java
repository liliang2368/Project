package com.ly.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.ly.bean.Addr;
import com.ly.biz.AddrBiz;


/**
 * Servlet implementation class addrServlet
 */
@WebServlet("/addr.do")
public class addrServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		AddrBiz abiz=new AddrBiz();
		String userid=request.getParameter("userid");
		System.out.println("看这里有没有获取到userid"+userid);
		List<Addr> list=abiz.query(userid);
		String json=JSON.toJSONString(list);
		response.getWriter().append(json);
		response.sendRedirect("foodindex.jsp");
	}

}
