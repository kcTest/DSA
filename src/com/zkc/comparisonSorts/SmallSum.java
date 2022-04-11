package com.zkc.comparisonSorts;

import com.zkc.utils.MyUtils;

/**
 * 求最小和
 * 对数组中每一个数求：左侧所有小于当前位置数的数的和
 * 每个数求得的值再求和
 */
public class SmallSum {
	
	public static void main(String[] args) {
		int[] arr = MyUtils.getArray(7, 20);
		MyUtils.printArr(arr);
		int[] tempArr = new int[arr.length];
		System.out.println(mergeSort(arr, 0, arr.length - 1, tempArr));
	}
	
	private static int mergeSort(int[] arr, int left, int right, int[] tempArr) {
		if (left == right) {
			return 0;
		}
		int mid = left + ((right - left) >> 1);
		return mergeSort(arr, left, mid, tempArr) + mergeSort(arr, mid + 1, right, tempArr) + merge(arr, left, mid, right, tempArr);
	}
	
	private static int merge(int[] arr, int left, int mid, int right, int[] temp) {
		int l = left;
		int r = mid + 1;
		int t = 0;
		int sum = 0;
		
		while (l <= mid && r <= right) {
			//右侧比左侧大的情况下求和 ，否则不求和 ，因为相等的情况下无法确定在右侧有几个比左侧当前数大的数
			if (arr[r] > arr[l]) {
				//如果左侧比右侧小 右侧当前索引及之后的数都比左侧大。右侧比左侧当前数大的数的个数乘以左侧当前数
				int current = arr[l++];
				temp[t++] = current;
				sum += (right - r + 1) * current;
				
			} else {
				temp[t++] = arr[r++];
			}
		}
		
		while (l <= mid) {
			temp[t++] = arr[l++];
		}
		while (r <= right) {
			temp[t++] = arr[r++];
		}
		t = 0;
		while (left <= right) {
			arr[left++] = temp[t++];
		}
		
		return sum;
	}
	
}
