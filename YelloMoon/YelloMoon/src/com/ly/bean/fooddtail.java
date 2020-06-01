package com.ly.bean;

import java.io.Serializable;

public class fooddtail extends Food implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String head;
	private String head1;
	private String head2;
	private String head3;
	private String head4;
	private String head5;
	private String info;
	private String foodid;

	


	public String getHead4() {
		return head4;
	}
	public void setHead4(String head4) {
		this.head4 = head4;
	}
	public String getHead5() {
		return head5;
	}
	public void setHead5(String head5) {
		this.head5 = head5;
	}
	public String getHead3() {
		return head3;
	}
	public void setHead3(String head3) {
		this.head3 = head3;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getHead1() {
		return head1;
	}
	public void setHead1(String head1) {
		this.head1 = head1;
	}
	public String getHead2() {
		return head2;
	}
	public void setHead2(String head2) {
		this.head2 = head2;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getFoodid() {
		return foodid;
	}
	public void setFoodid(String foodid) {
		this.foodid = foodid;
	}
	
}
