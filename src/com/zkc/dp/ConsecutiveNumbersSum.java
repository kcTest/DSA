package com.zkc.dp;

/**
 * 给定一个正整数 n，返回 连续正整数满足所有数字之和为 n 的组数 。
 */
public class ConsecutiveNumbersSum {
	public static void main(String[] args) {
		int n = 16;
		System.out.println(sln1(n));
	}
	
	
	/**
	 * i横 纵j 坐标为目标数值的一半
	 * i从2开始 j从1开始
	 * 从第一列开始 从上往下填写 每个单元格表示初始j与i累加到当前位置的结果
	 * n会出现在下半区， 包含n的个数再加上n自身一种作为结果 返回
	 */
	private static int sln(int n) {
		if (n == 1 || n == 2) {
			return 1;
		}
		int count = 0;
		int len = n / 2 + 1;
		for (int j = 1; j <= len; j++) {
			int sum = j;
			for (int i = j + 1; i <= len; i++) {
				sum += i;
				if (sum == n) {
					count++;
					break;
				} else if (sum > n) {
					break;
				}
			}
		}
		return count + 1;
	}
	
	/**
	 * 不需要全部遍历  只看第一列  每一条外斜边上的数每次向下增长值为i
	 * 遍历第一列 判断当前单元格累加值 sum+i*x=n 如果x为整数说明n会出现在一条斜边上
	 */
	private static int sln1(int n) {
		if (n == 1 || n == 2) {
			return 1;
		}
		int count = 0;
		int len = n / 2 + 1;
		int j = 1;
		int sum = j;
		for (int i = 2; i <= len; i++) {
			sum += i;
			if (n - sum < 0) {
				break;
			}
			if ((n - sum) % i == 0) {
//			if(cellVal+i*x==n){
				count++;
			}
		}
		return count + 1;
	}
	
}
