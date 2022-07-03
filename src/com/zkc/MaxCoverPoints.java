package com.zkc;

import com.zkc.utils.MyUtils;

import java.util.Arrays;

/**
 * 数组每个位置的值代表分布在x轴上的点的位置
 * 求给定长度的绳子最多可以覆盖几个点
 */
public class MaxCoverPoints {
	public static void main(String[] args) {
		int[] arr = MyUtils.getArray(8, 20);
//		int[] arr = new int[]{2, 2, 3, 7, 8, 16, 17, 18};
		Arrays.sort(arr);
		MyUtils.printArr(arr);
		int ropeLen = (int) (Math.random() * 20) + 1;
//		int ropeLen = 16;
		System.out.printf("ropeLen:%d,maxCoverage: %d\n", ropeLen, maxCoverPoints(arr, ropeLen));
	}
	
	/**
	 * 定义绳子头部和尾部 初始在0位置 
	 * 1如果头尾长度不超过绳子长度 尾部增加  覆盖的点的个数可以增加
	 * 2如果超过绳子长度  获取之前已统计的最大个数。 尾部不动  头部来到下一个位置。点的个数减一 。
	 * 重复12直到头部均已来到尾部。
	 */
	private static int maxCoverPoints(int[] arr, int ropeLen) {
		if (arr == null || arr.length == 0 || ropeLen <= 0) {
			return 0;
		}
		int head = 0;
		int tail = 0;
		int max = 0;
		int cur = 0;
		while (tail < arr.length) {
			if (arr[tail] - arr[head] <= ropeLen) {
				cur++;
				tail++;
			} else {
				max = Math.max(cur, max);
				cur--;
				head++;
			}
		}
		max = Math.max(cur, max);
		return max;
	}
}
