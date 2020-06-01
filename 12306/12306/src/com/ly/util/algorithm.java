package com.ly.util;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.swt.widgets.Combo;

import com.ly.dao.addbianDao;
import com.ly.dao.railwayDao;

public class algorithm {
	 railwayDao railDao=new railwayDao();
	 private addbianDao dao=new addbianDao();
		//1.根据前面选择的生成好剩下的车站供选择
	 	//2.下拉列表，需要添加的数据的下拉
	 	//3.铁路的线路名，根据铁路的线路名来将站台信息来分组
	 //4.改列车是升序还是降序
	public  void add(String station ,Combo combo_arriver1,String railwayname,String asc) throws SQLException{
		combo_arriver1.removeAll();
		List<Map<String, Object>> list3=railDao.findALL(station,railwayname,asc);//这里从铁路管理表要分组，将京广线来查询出所有的站台信息
		for(Map<String, Object> map:list3){
			combo_arriver1.add(map.get("RAILWAY_STATION").toString());
		
		}
		System.out.println(combo_arriver1.getText());
	}

	//添加站台信息，将所有的站台到显示到下拉列表中
	public void addstation(Combo combo_start) throws SQLException{
		combo_start.removeAll();
		Set<String> set=railDao.findAllStation();
		//循环所有的元素并输出
		Iterator<String> iterator=set.iterator();
		while(iterator.hasNext()){
			//System.out.println(iterator.next());
			System.out.println("-----------------------------");
			combo_start.add(iterator.next());
		}
	
	
	}
	//添加车次信息
	//列车的出发站，我要根据出发站来确定后续给下拉列表提供的是什么值   车次信息其实就是为了获取改列车是经过那一条线路
	//最后的asc就是改列车是正向进行的还是你逆向进行的
	public void addcheci(Combo combo_selectchufa,Combo combo_checi,String asc) throws SQLException{
		combo_selectchufa.removeAll();
		String railwayname="";//这个是用户选择的车次
		if(	combo_checi.getText()==null){
			railwayname="G1001";
		}else{
			railwayname=combo_checi.getText();
		}
		//System.out.println(railwayname);
		List<Map<String, Object>> list=railDao.station(railwayname,asc);//根据车次查询出所有的 站台信息要修改为根据线路来查询所有的信息
		for(Map<String, Object> map:list){
			System.out.println("复选框得值为"+map);
			combo_selectchufa.add(map.get("RAILWAY_STATION").toString());
			
		}
	}
	//必须要再设计一个算法，就是当你输入线路名太会自动查找到是否逆序然后显示最后面的那个站点信息
	public void addAuto(Combo com,String tid) throws SQLException{
		//先根据列车名来查询到这趟列车
		Map<String, Object> map=dao.SingleFindAsc(tid);//这里储存的是这躺列车是升序还是降序
		//就是将所有已经插入的值经行删除
		System.out.println(map.get("BASC").toString());
		System.out.println("车次为"+tid);
		
		
		List<Map<String, Object>> list=railDao.FindStation1(tid, map.get("BASC").toString());
		System.out.println(list);
		//不管它是正序还是逆序,我只要最后面的站点
		for(Map<String, Object> map1:list){
			com.removeAll();
			com.add(map1.get("S_STATNAME").toString());
		}	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
