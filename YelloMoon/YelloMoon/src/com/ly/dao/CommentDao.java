package com.ly.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ly.bean.Comment;
import com.ly.util.DBHelper;
import com.ly.util.Utils;


public class CommentDao {
	DBHelper db=new DBHelper();
	public List<Comment> findAll(String id) throws SQLException {
		//编写sql语句
		String sql="SELECT\r\n" + 
				"b.`name`,\r\n" + 
				"b.head,\r\n" + 
				"a.id,\r\n" + 
				"a.createtime,\r\n" + 
				"a.`comment`\r\n" + 
				"FROM\r\n" + 
				"comments AS a\r\n" + 
				"INNER JOIN res_user AS b ON a.userid= b.id\r\n" + 
				"INNER JOIN food AS c ON a.foodid= c.id\r\n" + 
				"where a.foodid=?";
		List<Map<String, Object>> list=db.selectList(sql, id);
		List<Comment> comment=Utils.castElement(list, Comment.class);
		return comment;
	}
	public void addComment(Comment comment) throws SQLException {
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		String sql="insert into comments (userid,foodid,comment,createtime) values(?,?,?,?)";
		db.update(sql, comment.getUserid(),comment.getFoodid(),comment.getComment(),time);
	
	}
	public void del(String id) throws SQLException {
		String sql="delete from comments where id=?";
		db.update(sql, id);
	}

}
