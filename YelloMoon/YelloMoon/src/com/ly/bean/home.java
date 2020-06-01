package com.ly.bean;

import java.io.Serializable;

public class home implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String	id;
	private String home_info;
	private String are;
	private String bed_id;
	private String person_in;
	private String price;
	private String pic;
	private String type_name;//房间类型
	private String cheku;//车库
	private String bash;//浴室
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getCheku() {
		return cheku;
	}
	public void setCheku(String cheku) {
		this.cheku = cheku;
	}
	public String getBash() {
		return bash;
	}
	public void setBash(String bash) {
		this.bash = bash;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHome_info() {
		return home_info;
	}
	public void setHome_info(String home_info) {
		this.home_info = home_info;
	}
	public String getAre() {
		return are;
	}
	public void setAre(String are) {
		this.are = are;
	}
	public String getBed_id() {
		return bed_id;
	}
	public void setBed_id(String bed_id) {
		this.bed_id = bed_id;
	}
	public String getPerson_in() {
		return person_in;
	}
	public void setPerson_in(String person_in) {
		this.person_in = person_in;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
}
