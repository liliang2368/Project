package com.ly.util;

public class RandomUtil {

	//  ����6λ�������֤��
	public static String getRandom() {
		String[] letters = new String[] {
				"q","w","e","r","t","y","u","i","o","p","a","s","d","f","g","h","j","k","l","z","x","c","v","b","n","m",
				"A","W","E","R","T","Y","U","I","O","P","A","S","D","F","G","H","J","K","L","Z","X","C","V","B","N","M",
				"0","1","2","3","4","5","6","7","8","9"};
		String code ="";
		for (int i = 0; i < 6; i++) {
			code = code + letters[(int)Math.floor(Math.random()*letters.length)];
		}
		return code;
	}
	public static void main(String[] args) {
		// ����̨��ӡ
		System.out.println(RandomUtil.getRandom());
	}
	
}
