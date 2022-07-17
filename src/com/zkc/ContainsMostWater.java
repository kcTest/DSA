package com.zkc;

import com.zkc.utils.MyUtils;

/**
 * You are given an integer array height of length n.
 * There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 * <p>
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * <p>
 * Return the maximum amount of water a container can store.
 * <p>
 * Notice that you may not slant the container.
 * <p>
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
 * In this case, the max area of water (blue section) the container can contain is 49.
 */
public class ContainsMostWater {
	public static void main(String[] args) {

//		int[] arr = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
//		MyUtils.printArr(arr);
//		System.out.printf("maxArea:%d\n", maxArea(arr));
//		System.out.printf("maxArea:%d", maxArea2(arr));
		for (int i = 0; i < 10000; i++) {
			int[] arr = MyUtils.getArray(10, 30, true, false);
			int max1 = maxArea(arr);
			int max2 = maxArea2(arr);
			if (max1 != max2) {
				MyUtils.printArr(arr);
				System.out.printf("max1=%d, max2=%d\n", max1, max2);
			}
		}
	}
	
	/**
	 * 双指针  较低一侧移动,高度相等可以选择任一侧移动  向内缩小范围，
	 * 初始在left=0，right=n-1,假设left.h较低
	 * <p>
	 * 如果此时向左移动了right,新的高度有两种情况
	 * 1、left.h<right.h, left.h作为高的情况最大面积上次已经算过,不可能出现较大值。
	 * 2、left.h>right.h, right.h作为高的情况算出的面积比上次left.h作为高算出的面积会更小，也不会可能出现最大值。
	 * 如果此时向右移动了left,新的高度有两种情况
	 * 1、left.h<right.h, 本次left.h更小，宽度更小，算出的面积比上次更小，不可能出现较大值。
	 * 2、left.h>right.h, 虽然宽度变小,但如果left.h比之前的高度足够高的话，算出的面积能弥补宽度带来的损失可能会出现最大值。
	 */
	private static int maxArea2(int[] arr) {
		int left = 0, right = arr.length - 1, max = 0;
		while (left < right) {
			max = Math.max(max, Math.min(arr[left], arr[right]) * (right - left));
			if (arr[left] <= arr[right]) {
				left++;
			} else {
				right--;
			}
		}
		return max;
	}
	
	private static int maxArea(int[] arr) {
		int len = arr.length;
		int max = 0;
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				max = Math.max(Math.min(arr[i], arr[j]) * (j - i), max);
			}
		}
		return max;
	}
}
