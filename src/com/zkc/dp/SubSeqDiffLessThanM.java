package com.zkc.dp;

import com.zkc.utils.MyUtils;

import java.util.Arrays;

/**
 * 给定一个数组arr,arr[i]=j,表示第i号试题的难度为j。给定一个非负数M。
 * 出一张卷子，对于任何相邻的两道题目，前一题的难度不能超过后一题的难度加M。
 * 返回所有可能的卷子种数
 */
public class SubSeqDiffLessThanM {
	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			int m = (int) (Math.random() * 10);
			int[] arr = MyUtils.getArray(7, 10);
//		int m = 9;
//		int[] arr = new int[]{4, 6, 9};
			int num2 = getNum2(arr, m);
			int num1 = getNum(arr, 0, m);
			if (num1 != num2) {
				MyUtils.printArr(arr);
				System.out.println("error");
				System.out.printf("m=%d,num1=%d,num2=%d\n", m, num1, num2);
			}
		}
	}
	
	/**
	 * 先将所有难度从小到大排序，对于每一个位置i：
	 * 1.从左往右遍历 获取在[i-1,i-1]位置已经算好的种数x，为之前所有位置[0,i-1]难度能够排出的种数，难度相同顺序不同也符合要求。
	 * 2.因为难度从小到大排序，所以arr[i,i]位置的难度比之前arr[0,i-1]都大，[0,i-1]上已有x种排列，对于每一种如果将难度arr[i]固定在最后，i位置此时可以得到x种，
	 * 3.因为前一题的难度不能超过后一题的难度加M，所以把i位置放在[0,i-1]中某个满足arr[m]>=arr[i]-M的难度的位置之前 也符合要求。二分在[0,i-1]上找到
	 * 所有难度小于arr[i]的个数为y，前提是这y个位置的难度排列必须是处于[0,i-1]上x种正确排序中的，对于这y个位置的每一种排列，
	 * 把arr[i]放到其中一个位置m的难度arr[m]之前,此时有y种插入位置，这样x种每种都有y种插入方式, 此时i位置又可以获得x*y种难度排列。
	 * 将最后一个位置上的结果返回
	 */
	private static int getNum2(int[] arr, int m) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		Arrays.sort(arr);
		int n = arr.length;
		//来到0位置只有1种难度可排
		int prev = 1;
		//直接从1位置开始
		for (int i = 1; i < n; i++) {
			int add1 = prev;
			int add2 = bs(arr, i, arr[i] - m) * prev;
			prev = add1 + add2;
		}
		return prev;
	}
	
	private static int bs(int[] arr, int end, int target) {
		int l = 0, r = end, pos = 0;
		while (l <= r) {
			int mid = l + ((r - l) >> 1);
			if (arr[mid] >= target) {
				pos = mid;
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return end - pos;
	}
	
	/**
	 * 暴力递归尝试
	 */
	private static int getNum(int[] arr, int idx, int m) {
		int n = arr.length;
		if (idx == n) {
			for (int i = 1; i < n; i++) {
				//不符合条件当前路线没有找到1种排列
				if (arr[i - 1] > arr[i] + m) {
					return 0;
				}
			}
			return 1;
		}
		int count = 0;
		for (int i = idx; i < n; i++) {
			swap(arr, idx, i);
			count += getNum(arr, idx + 1, m);
			swap(arr, idx, i);
		}
		return count;
	}
	
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
}
