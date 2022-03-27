package com.zkc.utils;

import java.util.Random;

public class DataSource {
	
	public static int[] getArray(int length, int bound) {
		if (length < 2) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		int[] ret = new int[length];
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			ret[i] = random.nextInt(bound);
		}
		return ret;
	}
}
