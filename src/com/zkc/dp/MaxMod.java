package com.zkc.dp;

import com.zkc.utils.MyUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 求非负数组arr的 子序列累加和 % 一个正数m 后的最大值
 */
public class MaxMod {
	public static void main(String[] args) {
		for (int i = 0; i < 10000; i++) {
			int[] arr = MyUtils.getArray(13, 20);
			int m = (int) (Math.random() * 10) + 1;
			int m1 = getMaxMod1(arr, m);
			int m2 = getMaxMod2(arr, m);
			int m3 = getMaxMod3(arr, m);
			if (m1 != m2 || m1 != m3) {
				MyUtils.printArr(arr);
				System.out.printf("m=%d,maxMod1=%d,maxMod2=%d,maxMod3=%d\n", m, m1, m2, m3);
				break;
			}
		}
	}
	
	/**
	 * 当m不是特别大的情况使用 行表示数组索引 列表示0-m-1
	 * 每个单元格表示任意取arr[0...i] 组成的数%m后的结果是否能够等于j 此时二维数组不会太大
	 */
	private static int getMaxMod3(int[] arr, int m) {
		int rowLen = arr.length;
		boolean[][] record = new boolean[arr.length][m];
		//第一列往下 每个单元格都不取arr[0...i]的数 可以得到0 对m取模为0 第一列全设置true
		for (int i = 0; i < rowLen; i++) {
			record[i][0] = true;
		}
		//第一行 对应数组0位置 取数值%m 得到的余数作为列索引 当前行该列可以直接设置为true
		record[0][arr[0] % m] = true;
		for (int i = 1; i < rowLen; i++) {
			for (int j = 1; j < m; j++) {
				//如果arr[0...i]位置的数任取再累加之后对m取模后的结果能够等于j 
				//如果不取当前arr[i]的数,那么要看任取arr[0...i-1]的数对m取模是否能够得到余数j
				record[i][j] = record[i - 1][j];
				//如果取当前arr[i]的数  分俩种情况
				//先算出数组中当前位置的数对m取模 
				int curMod = arr[i] % m;
				//如果curMod小于j,那么要看任取arr[0...i-1]的子序列累加和对m取模是否能够得到余数j-curMod。如果能得到 之前的余数再加上i位置的余数正好等于j
				if (curMod <= j) {
					record[i][j] = record[i][j] | record[i - 1][j - curMod];
				} else {
					//如果curMod大于j,那么要看任取arr[0...i-1]的子序列累加和对m取模是否能够得到一个余数
					//使curMod加上这个余数后的值再%m的结果能够来到升到m-1并且再从0升到j，所以要看任取arr[0...i-1]的子序列%m的结果是否能够等于这个余数
					//对m取模范围0-m-1，从0再到j 共j+1个数
					record[i][j] = record[i][j] | record[i - 1][m - 1 - curMod + j + 1];
				}
			}
		}
		
		int maxMod = 0;
		for (int j = 0; j < m; j++) {
			//最后一行遇到最后一个为true的j 为最大的mod结果
			if (record[rowLen - 1][j]) {
				maxMod = j;
			}
		}
		return maxMod;
	}
	
	/**
	 * 当arr中每个数值都不是特别大的情况使用  所有数的累积和sum也会不会太大  行表示数组索引 列表示sum
	 * 每个单元格表示  在数组中任意取0位置到i位置的数 是否能够组成j 此时二维数组不会太大
	 */
	private static int getMaxMod2(int[] arr, int m) {
		int rowLen = arr.length;
		int sum = 0;
		for (int k : arr) {
			sum += k;
		}
		int colLen = sum + 1;
		//行 表示数组的每个位置 列表示从1到sum
		//每个单元格表示  在数组中任意取0位置到i位置的数 是否能够组成j
		boolean[][] record = new boolean[rowLen][colLen];
		//第一列的数需要组成0  往下的每个位置 如果都不选取数就可以组成0  统一设置为true 
		for (int i = 0; i < arr.length; i++) {
			record[i][0] = true;
		}
		//第一行j位置的数如果正好等于arr[0] 这个位置可以设置为true  
		record[0][arr[0]] = true;
		for (int i = 1; i < rowLen; i++) {
			for (int j = 1; j < colLen; j++) {
				//record[i - 1][j] 表示任意选取arr[0..i]组成j时 不取i位置的数 由i-1来组成 ，此时结果由上一位置决定 直接取上一位置的结果 
				//j - arr[i] >= 0 && record[i - 1][j - arr[i]] 表示取i位置的数,要看在i-1行的某个列能否组成剩余的j-arr[i]，该列位置与
				//j-arr[i]相等，所以当前情况 由 record[i - 1][j - arr[i]]决定 
				//选不选俩种情况存在一种成功的情况 当前单元格结果可以为true
				record[i][j] = record[i - 1][j] | (j - arr[i] >= 0 && record[i - 1][j - arr[i]]);
			}
		}
		int max = 0;
		for (int j = 0; j < colLen; j++) {
			//最后一行每列表示所有路线来到数组结尾时 是否能够组成j(能够组成的j也为所有路线可能产生的累加和） 这些可能的数依次对m取模 返回其中最大值
			if (record[rowLen - 1][j]) {
				max = Math.max(max, j % m);
			}
		}
		return max;
	}
	
	/**
	 * 递归求所有可能子序列的累加和与m取模的最大值
	 */
	private static int getMaxMod1(int[] arr, int m) {
		Set<Integer> sumSet = new HashSet<>();
		getSubSeqSum(arr, m, 0, 0, sumSet);
		int maxSum = 0;
		//每种路线的结果取最大值
		for (Integer subSeqSum : sumSet) {
			maxSum = Math.max(maxSum, subSeqSum);
		}
		return maxSum;
	}
	
	private static void getSubSeqSum(int[] arr, int m, int idx, int subSeqSum, Set<Integer> sumSet) {
		if (idx == arr.length) {
			//当前路线结束 将结果保存
			sumSet.add(subSeqSum % m);
			return;
		}
		//每个位置加或不加俩种情况
		getSubSeqSum(arr, m, idx + 1, subSeqSum + arr[idx], sumSet);
		getSubSeqSum(arr, m, idx + 1, subSeqSum, sumSet);
	}
}
