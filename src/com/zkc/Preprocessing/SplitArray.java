package com.zkc.Preprocessing;

import com.zkc.utils.MyUtils;

/**
 * 将数组分割两半 使 左侧部分最大值 与 右侧部分最大值 的 差值 的 绝对值 尽量大
 * 左右两半必须有数
 */
public class SplitArray {
	public static void main(String[] args) {
		int[] arr = MyUtils.getArray(8, 20);
		MyUtils.printArr(arr);
		System.out.println(splitArray(arr));
		System.out.println(splitArray2(arr));
		for (int i = 0; i < 10000; i++) {
			if (splitArray(arr) != splitArray2(arr)) {
				System.out.println("error");
				MyUtils.printArr(arr);
				break;
			}
		}
	}
	
	/**
	 * 假如全局最大值被划分在右侧 此时要使左侧最大值尽量小 如果左侧最大值不在头部右侧范围左扩来压缩左侧的范围 最后可能只剩一个最大值在头部 
	 * 假如全局最大值被划分在左侧 此时要使右侧最大值尽量小 如果右侧最大值不在尾部左侧范围右扩来压缩右侧的范围 最后可能只剩一个最大值在尾部 
	 * 先取全局最大值 ，最大值与首尾最小的那个值的差值的绝对值为最终返回结果
	 */
	private static int splitArray2(int[] arr) {
		int max = Integer.MIN_VALUE;
		for (int j : arr) {
			max = Math.max(max, j);
		}
		return Math.abs(max - Math.min(arr[0], arr[arr.length - 1]));
	}
	
	/**
	 * 建立辅助数组 从左往右遍历 记录每个位置的左侧最大值
	 * 建立辅助数组 从右往左遍历 记录每个位置的右侧最大值
	 * 最后从左往右遍历每个位置 从辅助数组中取当前位置的左侧最大值与右侧最大值再计算差值的绝对值
	 * 记录每次得到的绝对值中最大的值。
	 */
	private static int splitArray(int[] arr) {
		int[] leftMax = new int[arr.length];
		leftMax[0] = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > leftMax[i - 1]) {
				leftMax[i] = arr[i];
			} else {
				leftMax[i] = leftMax[i - 1];
			}
		}
		int[] rightMax = new int[arr.length];
		rightMax[arr.length - 1] = arr[arr.length - 1];
		for (int i = arr.length - 2; i >= 0; i--) {
			if (arr[i] > rightMax[i + 1]) {
				rightMax[i] = arr[i];
			} else {
				rightMax[i] = rightMax[i + 1];
			}
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, Math.abs(leftMax[i] - rightMax[i]));
		}
		
		return max;
	}
}
