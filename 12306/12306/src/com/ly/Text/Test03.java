package com.ly.Text;

import java.text.ParseException;
import java.util.Date;

import com.ibm.icu.text.SimpleDateFormat;

public class Test03 {
	public static void main(String[] args) throws ParseException {
		//线格式化时间

		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm");

	
		Date d1=dateFormat.parse("2018-09-26 4:56");
		System.out.println(d1.getHours());
	
		Date d2=dateFormat.parse("2018-09-27 6:36");
		System.out.println(d2.getMinutes());
		long i=(d2.getTime()-d1.getTime());
	long day=i/(24*60*60*1000)	;
	System.out.println(day);
	long hour=(i/(60*60*1000)-day*24);
	long min=(i/(60*1000)-day*24*60-hour*60);
	System.out.println(hour+":"+min);	
	}

}
