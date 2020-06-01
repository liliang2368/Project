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
import com.ly.bean.Fpage;
import com.ly.bean.KuwuMusic;
import com.ly.dao.MusicDao;

@WebServlet("/music.do")
public class MusicServer extends BaseServlet {
	MusicDao dao=new MusicDao();
	private static final long serialVersionUID = 1L;


	protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, SQLException {
		Fpage fp=new Fpage();
		KuwuMusic ku=new KuwuMusic();
		//先获取接收参数信息
		BeanUtils.populate(fp, request.getParameterMap());
		BeanUtils.populate(ku, request.getParameterMap());
		List<KuwuMusic> list=dao.FindAllMusic(fp,ku);
		//分页查询   需要三个值   第一个就是总数
		Map<String, Object> map=dao.FindCount();
		//转换成json数据类型
		String json=JSON.toJSONString(list);
		map.put("rows", list);
		response.getWriter().append(json);
	}


}
