package com.ly.util;

import java.util.ArrayList;
import java.util.Random;


/**
 * 随机选择座位
 * @author 李阳
 *
 */
public class RanadomSeat {
	int top=0;
	seat []arr1=new seat[4*40];
	seat []arr2=new seat[4*40];
	seat []arr3=new seat[4*40];
	public seat selectsofe(){//外界向改函数传入席别，
		//假设软座是1-----4车厢 入栈
		
		//进栈
		for(int i=1;i<=4;i++){
			for(int j=1;j<=40;j++){
				arr1[top++]=new seat(i, j);
			}
		}
		//出栈
		return arr1[top--];
	}
	public seat selecthard(){
		
		for(int i=5;i<=10;i++){
			for(int j=1;j<=40;j++){
				arr2[top++]=new seat(i,j);//进栈
			}
		}
		return arr2[top--];//出栈
	}
	public seat softsleep(){
	
		for(int i=11;i<=12;i++){
			for(int j=1;j<=40;j++){
				arr3[top++]=new seat(i,j);//进栈
			}
		}
		return arr3[top--];//出栈
	}
	//将未付款成功的还要压入栈中
	public void insert(int i,int j,String farewell ){
		if(farewell.equals("软座")){
			arr1[top++]=new seat(i, j);
		}
		if(farewell.equals("硬座")){
			arr2[top++]=new seat(i, j);
		}
		if(farewell.equals("软卧")){
			arr3[top++]=new seat(i,j);
		}

	}
	
	
	
	
	
	
}
