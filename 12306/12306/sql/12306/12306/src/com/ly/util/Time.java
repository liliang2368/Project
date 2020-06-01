package com.ly.util;

import java.util.Date;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import com.ibm.icu.text.SimpleDateFormat;

public class Time {
		public static void Time2(Label label){
			new Thread(new Runnable() {
				boolean falg=true;
				@Override
				public void run() {
					// TODO Auto-generated method stub
					while(falg){
						//需要通过改线程修改控件，实现线程异常
						Display.getDefault().asyncExec(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								Date date=new Date();
								SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								String str=format.format(date);//格式化时
								label.setText(str);
							//	System.out.println("当前时间为"+str);
							}
						});
						try {
							Thread.sleep(1000);//线程睡眠1秒钟
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
			}).start();//启动线程
		}	
	
	
	
	
	
}
