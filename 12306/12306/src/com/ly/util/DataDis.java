package com.ly.util;



import org.eclipse.swt.custom.StackLayout;

import com.ly.Ui.Admin;
import com.ly.Ui.Cps_register;
import com.ly.Ui.Cps_userInfo;
import com.ly.Ui.HomePage;
import com.ly.Ui.OrderQuery;
import com.ly.Ui.PassengerTable;
import com.ly.Ui.Site;
import com.ly.Ui.TimeTable;
import com.ly.Ui.chaxun;




public class DataDis {

public static StackLayout stackLayout=new StackLayout();
	
	public static chaxun cha;
	public static Admin admin;
	public static OrderQuery orderquery;//查询订单面板
	public static PassengerTable passenger;//乘客表面板
	public static Cps_register cps_register;//网上购票用户注册
	public static TimeTable timetable;//旅客列车时刻表查询
	public static Cps_userInfo cps_userinfo;
	public static Site site;
	public static HomePage homepage;
}
