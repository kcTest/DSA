package com.zkc.comparisonSorts;

import com.zkc.utils.MyUtils;

/**
 * 快速排序
 */
public class QuickSort {
	
	public static void main(String[] args) {
		int[] arr = MyUtils.getArray(7, 30);
		MyUtils.printArr(arr);
		//quickSort1(arr, 0, arr.length - 1);
		//quickSort2(arr, 0, arr.length - 1);
		quickSort3(arr, 0, arr.length - 1);
		MyUtils.printArr(arr);
	}
	
	/**
	 * 选定数组最后一个数num，将num之前的数分为左右两部分，左边小于等于num 右边大于num，最后将num与右边第一个数交换，此时num在数组中的位置已确定。
	 * 再将num左右两侧按同样步骤递归处理。
	 * O（n^2）
	 */
	private static void quickSort1(int[] arr, int start, int end) {
		if (start >= end) {
			return;
		}
		int lowerBoundary = start - 1;
		for (int i = start; i < end; i++) {
			if (arr[i] <= arr[end]) {
				int temp = arr[lowerBoundary + 1];
				arr[lowerBoundary + 1] = arr[i];
				arr[i] = temp;
				lowerBoundary++;
			}
		}
		int temp = arr[end];
		arr[end] = arr[lowerBoundary + 1];
		arr[lowerBoundary + 1] = temp;
		quickSort1(arr, start, lowerBoundary);
		quickSort1(arr, lowerBoundary + 2, end);
	}
	
	/**
	 * 选定数组最后一个数num，将num之前的数分为左中右三部分，左边小于num 中间等于num,右边大于num，最后将num与右边第一个数交换，此时num在数组中的位置已确定。
	 * 再将num左右两侧按同样步骤递归处理。
	 * O（n^2）
	 */
	private static void quickSort2(int[] arr, int start, int end) {
		if (start >= end) {
			return;
		}
		int lowerBoundary = start - 1;
		int higherBoundary = end;
		for (int i = start; i < end; i++) {
			if (i == higherBoundary) {
				break;
			}
			if (arr[i] < arr[end]) {
				int temp = arr[lowerBoundary + 1];
				arr[lowerBoundary + 1] = arr[i];
				arr[i] = temp;
				lowerBoundary++;
			} else if (arr[i] > arr[end]) {
				int temp = arr[higherBoundary - 1];
				arr[higherBoundary - 1] = arr[i];
				arr[i] = temp;
				higherBoundary--;
				i--;
			}
		}
		int temp = arr[end];
		arr[end] = arr[higherBoundary];
		arr[higherBoundary] = temp;
		quickSort2(arr, start, lowerBoundary);
		quickSort2(arr, higherBoundary + 1, end);
	}
	
	/**
	 * 随机选定数组中一个数num 与最后一个数交换num，将num之前的数分为左中右三部分，左边小于num 中间等于num,右边大于num，最后将num与右边第一个数交换，此时num在数组中的位置已确定。
	 * 再将num左右两侧按同样步骤递归处理。
	 * 时间复杂度与选定数的位置有关，如果选定的数经排序之后正好处在中间位置，此时左右边界会在中间相遇 后续正好从中间分成左右两半开始递归 左右两侧待排序数量一样 时间复杂度最好情况O(n*logn) 空间复杂度O(logn)，
	 * 如果选定的数经排序之后正好处在两端位置 后续递归只会处理左侧或右侧数据 左右两侧待排序数量为n-i，与quickSort2情况相同 最差时间复杂度最好情况O(n^2),空间复杂度O(n)。
	 * 根据选定数所在位置的概率和时间复杂度可以得到，时间复杂度数学期望为O(n*logn)
	 */
	private static void quickSort3(int[] arr, int start, int end) {
		if (start >= end) {
			return;
		}
		//简化
		swap(arr, end, (int) (Math.random() * (end - start + 1)) + start);
		int[] boundary = partition(arr, start, end);
		quickSort3(arr, start, boundary[0]);
		quickSort3(arr, boundary[1], end);
	}
	
	private static int[] partition(int[] arr, int start, int end) {
		int lowerBoundary = start - 1;
		int higherBoundary = end;
		while (start < higherBoundary) {
			if (arr[start] < arr[end]) {
				//左边界下一个数和当前数交换  增加左边界  继续向后处理
				swap(arr, start++, ++lowerBoundary);
			} else if (arr[start] > arr[end]) {
				//右边界前一个数和当前数交换  减小右边界 继续比较当前数 start不变
				swap(arr, --higherBoundary, start);
			} else {
				//相等情况下不处理继续向后
				start++;
			}
		}
		//与右边界第一个数交换
		swap(arr, higherBoundary, end);
		//返回后续递归的左部分的结束位置和右部分的起始位置
		return new int[]{lowerBoundary, higherBoundary + 1};
	}
	
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
