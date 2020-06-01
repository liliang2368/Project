package com.ly.bean;

import java.io.Serializable;

public class peizhi implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String peizhi_info;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPeizhi_info() {
		return peizhi_info;
	}
	public void setPeizhi_info(String peizhi_info) {
		this.peizhi_info = peizhi_info;
	}
	
}
