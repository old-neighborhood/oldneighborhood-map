package com.oldneighborhood.demo.tools;

import java.util.Random;

public class CommonUtils {

	public static String createRandomNum() {
		// TODO Auto-generated method stub

		Random d = new Random();

		String str = "";

		for (int m = 0; m < 6; m++) {

			int num = d.nextInt(10);

			str += num + "";

		}
		return str;
	}

}
