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
import com.ly.biz.CommentBiz;


/**
 * Servlet implementation class commentServlet
 */
@WebServlet("/comment.do")
public class commentServlet extends BaseServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void comment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			//到这里获取userid和foodid来查找所有的评论
		String userid=request.getParameter("userid");
		String id=request.getParameter("id");
		CommentBiz cbiz=new CommentBiz();
		List<Comment> list=cbiz.FindAll(id);
		//设置到回话中
		request.getSession().setAttribute("comment", list);
		response.sendRedirect("single-product.jsp");
	}
	protected void addcomment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, IllegalAccessException, InvocationTargetException {
		Comment comment=new Comment();
		BeanUtils.populate(comment,request.getParameterMap());
		CommentBiz cbiz=new CommentBiz();
		cbiz.addComment(comment);
		response.getWriter().append("添加评论成功");
	}
	protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, IllegalAccessException, InvocationTargetException {
		String id=request.getParameter("id");//获取评论的id
		CommentBiz cbiz=new CommentBiz();
		cbiz.del(id);
		
	
	
	}

}
