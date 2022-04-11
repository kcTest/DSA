package com.zkc.comparisonSorts;

import com.zkc.utils.MyUtils;

/**
 * 对数组中每一个数如果右边存在比自己小的数，这对数为逆序数。
 * 打印数组中所有的逆序数
 */
public class ReversePairs {
	
	
	public static void main(String[] args) {
		int[] arr = MyUtils.getArray(7, 20);
		MyUtils.printArr(arr);
		System.out.println();
		System.out.println("===================");
		int[] tempArr = new int[arr.length];
		mergeSort(arr, 0, arr.length - 1, tempArr);
	}
	
	private static void mergeSort(int[] arr, int left, int right, int[] tempArr) {
		if (left == right) {
			return;
		}
		int mid = left + ((right - left) >> 1);
		mergeSort(arr, left, mid, tempArr);
		mergeSort(arr, mid + 1, right, tempArr);
		merge(arr, left, mid, right, tempArr);
	}
	
	private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
		int l = left;
		int r = mid + 1;
		int t = 0;
		while (l <= mid && r <= right) {
			if (arr[r] >= arr[l]) {
				temp[t++] = arr[l++];
			} else {
				//升序排列 如果当前右侧数比当前左侧数小，则当前右侧数比当前左侧数及之后的数都小
				int ll = l;
				while (ll <= mid) {
					System.out.printf(" [%d,%d] ", arr[ll++], arr[r]);
				}
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
	}
	
}
