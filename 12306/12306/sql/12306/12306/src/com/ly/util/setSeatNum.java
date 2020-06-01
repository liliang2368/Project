package com.ly.util;
import java.util.Random;
public class setSeatNum {
	public static String getCar(){
		Random rd = new Random();
		String car;
		
		int i = rd.nextInt(9);
	
		car = Integer.toString(i);
		
		return car;
	}
	public static String getSeat(){
		Random rd = new Random();
		String row;
		String col;
		String seat;
		
		int j = rd.nextInt(17);
		int k = rd.nextInt(5);
		switch(k){
		case 0 :
			col = "A";
			break;
		case 1:
			col = "B";
			break;
		case 2:
			col = "C";
			break;
		case 3 :
			col = "D";
			break;
		case 4:
			col = "F";
			break;	
		default:
			col = "E";
			break;
		}
		row = Integer.toString(j);
		
		seat = row +  col ;
		return seat;
	}
}
