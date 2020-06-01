package com.ly.web;


import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/file.do")
public class FileUploadServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       

	protected void file(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		/**
		 * getPart 用于或企业表单提交的文件  方法参数是表单的一个文件字段名
		 */
		Part part=request.getPart("file");//获取指定的文件名
		//多文件上传
		String filename=part.getSubmittedFileName();//这里是获取提交的文件名
		//JSP的内置对象 上下文对象
		ServletContext applicatio=request.getServletContext();
		//将 web路径转换 成 磁盘路径
		String realPath=applicatio.getRealPath("/upload");
		System.out.println("看这里需要保存的路径"+realPath);
		//构建文件的web路径
		String webPath="/upload/"+filename;
		part.write("F:\\tomcat\\apache-tomcat-9.0.19\\upload"+"/"+filename);  //保存文件到指定文件夹
		
		
		
		
		
		
		
		doGet(request, response);
	}

}
