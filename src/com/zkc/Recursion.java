package com.zkc;

import com.zkc.utils.ArrayUtils;

public class Recursion {
	
	public static void main(String[] args) {
		int[] arr = ArrayUtils.getArray(10, 50);
		printArr(arr);
		System.out.println();
		System.out.println("===================");
		System.out.println(getMax(arr, 0, arr.length - 1));
	}
	
	private static void printArr(int[] arr) {
		for (int j : arr) {
			System.out.printf("%d ", j);
		}
	}
	
	/**
	 * 递归求最大值
	 */
	private static int getMax(int[] arr, int left, int right) {
		if (left == right) {
			return arr[left];
		}
		int mid = left + ((right - left) >> 1);
		int leftMax = getMax(arr, left, mid);
		int rightMax = getMax(arr, mid + 1, right);
		return Math.max(leftMax, rightMax);
	}
}
