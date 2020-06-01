package com.ly.Text;

import com.ly.util.seat;

public class Tesss {
	public static void main(String[] args) {
		seat []arr2 =new seat[4*40];
		int top = 0;
		for(int i=5;i<=10;i++){
			for(int j=1;j<=40;j++){
				
				arr2 [top++]=new seat(i,j);//进栈
			}
		}
		for(seat  t: arr2){
			System.out.println(t);
		}
	}
}
