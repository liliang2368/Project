package com.ly.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ly.bean.Fpage;
import com.ly.bean.KuwuMusic;
import com.ly.util.DBHelper;
import com.ly.util.Utils;

public class MusicDao {
	DBHelper db=new DBHelper();
	public List<KuwuMusic> FindAllMusic(Fpage fp, KuwuMusic ku) throws SQLException {
		ArrayList<String> array=new ArrayList<String>();
		//到这里来获取页数
		int page=fp.getPage();
		int row=fp.getRows();//每行几个数
		//设置逻辑第一个问号
		String sql="select id ,artist,name,album,music_url,lrc from kuwo_music limit ?, ?";
		array.add(""+(page-1)*row);
		array.add(""+row);
		
		return Utils.castElement(db.selectList(sql, array), KuwuMusic.class);
	}
	public Map<String, Object> FindCount() throws SQLException {
		String sql="select count(*) as total from kuwo_music";
		return db.selectone(sql, null);
	}
}
