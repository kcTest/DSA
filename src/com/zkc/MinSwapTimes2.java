package com.zkc;

import com.zkc.utils.MyUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 无序数组长度为n 所有数字都不一样
 * 返回让这个无序数组变成有序数组的最少交换次数
 */
public class MinSwapTimes2 {
	public static void main(String[] args) {
		int[] arr = new int[]{43, 2, 234, 21, 11, 9};
		MyUtils.printArr(arr);
		System.out.println(getMinSwapTimes(arr));
	}
	
	/**
	 * 算出从小到大和从大到小排序，分别需要交换几次 再取其中最小
	 * <p>
	 * 数值范围不在0-n-1,但是可做离散化 得到新的数组为原始数组每个数的正确下标。
	 * 再算将新数组的值（下标）交换到自己的正确位置需要几次
	 */
	private static int getMinSwapTimes(int[] arr) {
		boolean sorted = true;
		int n = arr.length;
		for (int i = 1; i < n; i++) {
			if (arr[i - 1] < arr[i]) {
				sorted = false;
				break;
			}
		}
		//如果已经有序不需要交换
		if (sorted) {
			return 0;
		}
		//先离散化 为每个位置的数算出从小到大排序正确的下标信息 
		return Math.min(getSwapTimes(arr, true), getSwapTimes(arr, false));
	}
	
	
	private static int getSwapTimes(int[] arr, boolean asc) {
		int n = arr.length;
		int[] copy = Arrays.copyOf(arr, n);
		Arrays.sort(copy);
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			map.put(copy[i], i);
		}
		int[] arrSorted = new int[n];
		for (int i = 0; i < n; i++) {
			//copy排序没变 逆序时的位置要做修改
			arrSorted[i] = asc ? map.get(arr[i]) : n - 1 - map.get(arr[i]);
		}
		int count = 0;
		for (int i = 0; i < n; i++) {
			while (arrSorted[i] != i) {
				swap(arrSorted, arrSorted[i], i);
				count++;
			}
		}
		return count;
	}
	
	private static void swap(int[] arrAsc, int i, int j) {
		int temp = arrAsc[i];
		arrAsc[i] = arrAsc[j];
		arrAsc[j] = temp;
	}
}
