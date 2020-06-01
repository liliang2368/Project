package com.ly.util;

public class seat {
	private int carriage;//车厢
	private int seat;//座位
	public seat(int carriage, int seat ) {
		super();
		this.carriage = carriage;
		this.seat = seat;
	}
	public seat() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCarriage() {
		return carriage;
	}
	public void setCarriage(int carriage) {
		this.carriage = carriage;
	}
	public int getSeat() {
		return seat;
	}
	public void setSeat(int seat) {
		this.seat = seat;
	}
	@Override
	public String toString() {
		return "seat [carriage=" + carriage + ", seat=" + seat + "]";
	}
}
