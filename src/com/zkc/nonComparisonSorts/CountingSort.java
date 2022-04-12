package com.zkc.nonComparisonSorts;

import com.zkc.utils.MyUtils;

/**
 * 计数排序O(n) 稳定
 */
public class CountingSort {
	
	public static void main(String[] args) {
		int[] arr = MyUtils.getArray(9, 50);
		MyUtils.printArr(arr);
		lsdRadixSort(arr, 0, 50 - 1);
		MyUtils.printArr(arr);
	}
	
	private static void lsdRadixSort(int[] arr, int min, int max) {
		if (arr == null || arr.length == 0) {
			return;
		}
		
		int[] count = new int[max - min + 1];
		for (int j : arr) {
			count[j]++;
		}
		int index = 0;
		for (int i = 0; i < count.length; i++) {
			for (int j = 0; j < count[i]; j++) {
				//下标为要写回数组中的数
				arr[index++] = i + j;
			}
		}
	}
	
}
