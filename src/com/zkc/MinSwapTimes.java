package com.zkc;

/**
 * 无序数组长度为n 所有数字都不一样且值都在[0-n-1]范围上
 * 返回让这个无序数组变成有序数组的最少交换次数
 */
public class MinSwapTimes {
	public static void main(String[] args) {
		int[] arr = new int[]{0, 1, 3, 4, 2, 5};
		System.out.println(getMinSwapTimes(arr));
	}
	
	/**
	 * 每个位置的数值对应该数的正确位置
	 */
	private static int getMinSwapTimes(int[] arr) {
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			//如果数值和自己的下标不匹配 交换到目标位置。继续判断当前位置交换回来的新值是否与下标匹配 不匹配继续交换到目标位置 直到arr[i]==i。
			while (arr[i] != i) {
				swap(arr, arr[i], i);
				count++;
			}
		}
		return count;
	}
	
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
}
