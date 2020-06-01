package com.ly.biz;

import java.sql.SQLException;
import java.util.List;

import com.ly.bean.Comment;
import com.ly.dao.CommentDao;



public class CommentBiz {

	public List<Comment> FindAll(String id) throws SQLException {
		CommentDao cDao=new CommentDao();
		
		return cDao.findAll(id);
	}

	public void addComment(Comment comment) throws SQLException {
		CommentDao cdao=new CommentDao();
		 cdao.addComment(comment);
	}

	public void del(String id) throws SQLException {
		CommentDao cdao=new CommentDao();
		cdao.del(id);
	}

}
