package com.ly.util;

import java.text.ParseException;
import java.util.Date;

import org.eclipse.swt.widgets.DateTime;

import com.ibm.icu.text.SimpleDateFormat;
//对时间来进行相减
public class zhuanhuantime {
	public String Time(String str1,String str2) throws ParseException{
		if(str1 ==null || str2==null){
			return null;
		}
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date d1=dateFormat.parse(str1);
		System.out.println(d1.getHours());
		Date d2=dateFormat.parse(str2);
	long i=(d2.getTime()-d1.getTime());
	long day=i/(24*60*60*1000)	;
	System.out.println(day);
	long hour=(i/(60*60*1000)-day*24);
	long min=(i/(60*1000)-day*24*60-hour*60);
	String str=hour+":"+min;
		return str;
		}
	//将时间的日期都去掉
	public String formattime(String str) throws ParseException{
		if(str==null){
			return null;
		}else{
	SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date d1=dateFormat.parse(str);
		String str2=d1.getHours()+":"+d1.getMinutes();
		
		return str2;
		}
	}
	//将swt类的时间和日期经行转换1.转换日期
	public String zhuanCalder(DateTime dateTime){
		return dateTime.getYear()+"-"+(dateTime.getMonth()+1)+"-"+dateTime.getDay();
	}
	//转换时间
	public String zhuantime(DateTime dateTime){
		return dateTime.getHours()+":"+dateTime.getMinutes();
	}
	//同时转换时间和日期
	public String zhuanCalderTime(DateTime dateTime1,DateTime dateTime2){
		return dateTime1.getYear()+"-"+(dateTime1.getMonth()+1)+"-"+dateTime1.getDay()+" "+dateTime2.getHours()+":"+dateTime2.getMinutes();
	}
	}
