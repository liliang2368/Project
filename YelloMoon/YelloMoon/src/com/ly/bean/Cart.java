package com.ly.bean;

import java.io.Serializable;

/**
 *购物车
 * @author 李阳
 *
 */
public class Cart extends Food implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String  foodid;

	public String getFoodid() {
		return foodid;
	}
	public void setFoodid(String foodid) {
		this.foodid = foodid;
	}

	
}
