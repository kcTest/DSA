package com.zkc.dp;

import com.zkc.utils.MyUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * You are given two integer arrays nums1 and nums2.
 * We write the integers of nums1 and nums2 (in the order they are given) on two separate horizontal lines.
 * <p>
 * We may draw connecting lines: a straight line connecting two numbers nums1[i] and nums2[j] such that:
 * <p>
 * nums1[i] == nums2[j], and
 * the line we draw does not intersect any other connecting (non-horizontal) line.
 * Note that a connecting line cannot intersect even at the endpoints (i.e., each number can only belong to one connecting line).
 * <p>
 * Return the maximum number of connecting lines we can draw in this way.
 */
public class LongestCommonSubsequence {
	public static void main(String[] args) {
		int[] nums1 = MyUtils.getArray((int) (Math.random() * 15) + 1, 20);
		int[] nums2 = MyUtils.getArray((int) (Math.random() * 15) + 1, 20);
		MyUtils.printArr(nums1);
		MyUtils.printArr(nums2);
		System.out.println(longestCommonSubsequence(nums1, nums2));
	}
	
	private static int longestCommonSubsequence(int[] arrA, int[] arrB) {
		int m = arrA.length;
		int n = arrB.length;
		//每个单元格record[i][j]表示 长度为i的子序列a[0...i] 和 长度为j的子序列b[0...j] 的最长公共子序列长度  
		int[][] record = new int[m + 1][n + 1];
		//长度为0的子序列与另一个子序列 形成公共子序列 第一列 第一行 默认0 第二列 第二行开始才对应数组的0位置
		for (int i = 1; i <= m; i++) {
			int a = arrA[i - 1];
			for (int j = 1; j <= n; j++) {
				int b = arrB[j - 1];
				if (a == b) {
					record[i][j] = record[i - 1][j - 1] + 1;
				} else {
					record[i][j] = Math.max(record[i - 1][j], record[i][j - 1]);
				}
			}
		}
		return record[m][n];
	}
}
