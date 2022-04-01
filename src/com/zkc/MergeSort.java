package com.zkc;

import com.zkc.utils.ArrayUtils;

public class MergeSort {
	
	public static void main(String[] args) {
		int[] arr = ArrayUtils.getArray(9, 50);
		printArr(arr);
		System.out.println();
		System.out.println("===================");
		int[] tempArr = new int[arr.length];
		mergeSort(arr, 0, arr.length - 1, tempArr);
		printArr(arr);
	}
	
	private static void printArr(int[] arr) {
		for (int j : arr) {
			System.out.printf("%d ", j);
		}
	}
	
	/**
	 * 归并操作，也叫归并算法，指的是将两个顺序序列合并成一个顺序序列的方法 。
	 * 速度仅次于快速排序，为稳定排序算法，一般用于对总体无序，但是各子项相对有序的数列。
	 * 时间复杂度O(n log n)，空间复杂度T（n)。
	 * <br>
	 * 如　设有数列{6，202，100，301，38，8，1}
	 * <br>
	 * 初始状态：6,202,100,301,38,8,1
	 * <br>
	 * 第一次归并后：{6,202},{100,301},{8,38},{1}，比较次数：3；
	 * <br>
	 * 第二次归并后：{6,100,202,301}，{1,8,38}，比较次数：4；
	 * <br>
	 * 第三次归并后：{1,6,8,38,100,202,301},比较次数：4；
	 * <br>
	 * 总的比较次数为：3+4+4=11；
	 * <br>
	 * 逆序数为14；
	 * <p>
	 * <br>
	 * 算法描述：
	 * 归并操作的工作原理如下：
	 * <br>
	 * 第一步：申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列。
	 * <br>
	 * 第二步：设定两个指针，最初位置分别为两个已经排序序列的起始位置。
	 * <br>
	 * 第三步：比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并将较小元素所在数组的当前指针移动到下一位置。
	 * <br>
	 * 重复步骤3直到某一指针超出序列尾。
	 * <br>
	 * 将另一序列剩下的所有元素直接复制到合并序列尾。
	 */
	private static void mergeSort(int[] arr, int left, int right, int[] tempArr) {
		if (left < right) {
			int mid = left + ((right - left) >> 1);
			mergeSort(arr, left, mid, tempArr);
			mergeSort(arr, mid + 1, right, tempArr);
			merge(arr, left, mid, right, tempArr);
		}
	}
	
	private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
		int i = left;
		int j = mid + 1;
		//临时数组存储相邻序列元素排序结果  
		int t = 0;
		//升序  较小者放入临时数组 且该侧继续向右移动下标获取新元素与另一侧元素对比。
		while (i <= mid && j <= right) {
			if (arr[j] < arr[i]) {
				temp[t++] = arr[j++];
			} else {
				temp[t++] = arr[i++];
			}
		}
		
		//两个数组比较完成时其中一个数组元素全部放置完成下标将超出其索引范围，另一数组会有剩余元素无法做对比，将这部分剩余元素全部放入临时数组末尾
		//左边全部放完时i=mid+1
		while (i <= mid) {
			temp[t++] = arr[i++];
		}
		while (j <= right) {
			temp[t++] = arr[j++];
		}
		
		t = 0;
		
		//将排好序的左右俩部分序列元素再复制到原数组的指定范围内
		while (left <= right) {
			arr[left++] = temp[t++];
		}
	}
	
}
