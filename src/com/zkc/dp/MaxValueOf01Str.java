package com.zkc.dp;

/**
 * 给定一个只由0和1组成的字符串S，假设下标从1开始,规定i位置的字符价值V[i]计算方式如下:
 * 1. i==1时，V[i]=1;
 * 2. i>1时， 如果S[i]!=S[i-1],V[i]=1;
 * 3. i>1时, 如果S[i]==S[i-1],V[i]=V[i-1]+1;
 * 你可以随意删除S中的字符，返回整个S的最大价值
 * 字符串长度5<=5000
 */
public class MaxValueOf01Str {
	public static void main(String[] args) {
		int len = (int) (Math.random() * 30) + 1;
		char[] chars = new char[len];
		for (int i = 0; i < len; i++) {
			chars[i] = (char) ((int) (Math.random() * 2) + 48);
		}
		String s = String.valueOf(chars);
		System.out.println(s);
		int maxVal = getMaxVal(s);
		int maxVal2 = getMaxVal2(s);
		System.out.printf("maxVal2=%d,maxVal=%d\n", maxVal2, maxVal);
//		for (int j = 0; j < 100; j++) {
//			int len = (int) (Math.random() * 25) + 1;
//			char[] chars = new char[len];
//			for (int i = 0; i < len; i++) {
//				chars[i] = (char) ((int) (Math.random() * 2) + 48);
//			}
//			String s = String.valueOf(chars);
//			int maxVal2 = getMaxVal2(s);
//			int maxVal = getMaxVal(s);
//			if (maxVal2 != maxVal) {
//				System.out.println(s);
//				System.out.printf("maxVal2=%d,maxVal=%d\n", maxVal2, maxVal);
//				break;
//			}
//		}
	}
	
	private static int getMaxVal2(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int n = s.length();
		char[] chars = s.toCharArray();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = chars[i] == '0' ? 0 : 1;
		}
		//每个位置表示来到i位置，上个数是0、1， 上一数价值为j的情况下， i位置到字符串结尾累积获取的最大价值。
		int rowLen = n + 1, colLen = n + 1;
		int[][][] record = new int[rowLen][colLen][2];
		for (int i = rowLen - 1; i >= 0; i--) {
			for (int k = 0; k < 2; k++) {
				for (int j = colLen - 1; j >= 0; j--) {
					int valUnselect = i + 1 < rowLen ? record[i + 1][j][k] : 0;
					//选择的情况下需要考虑上个数是0还是1
					int valSelect = 0;
					if (i < n) {
						int iVal = arr[i] == k ? j + 1 : 1;
						if (iVal < rowLen) {
							valSelect = iVal + record[i + 1][iVal][arr[i]];
						}
					}
					record[i][j][k] = Math.max(valUnselect, valSelect);
				}
			}
		}
		//初始来到0位置 上个数是0 携带价值为0
		return record[0][0][0];
	}
	
	/**
	 * 每个位置的字符串选择或者不选择
	 * 如果选择，判断与传递下来的上一字符(不一定相邻)是否相等,相等携带价值V[i-1]+1、不相等携带价值1，到下个位置的递归处理,
	 * 如果不选择，直接携带上个数的价值 到下个位置的递归处理
	 */
	private static int getMaxVal(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] chars = s.toCharArray();
		int[] arr = new int[chars.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = chars[i] == '0' ? 0 : 1;
		}
		return sln(arr, 0, 0, 0);
	}
	
	private static int sln(int[] arr, int idx, int prevNum, int prevVal) {
		//来到末尾没有字符 价值为0  
		if (idx == arr.length) {
			return 0;
		}
		//不选当前字符 到下一位置 前一个数仍然作为下一位置的前一个数  ,  前一个数的价值也不变传递
		int valUnselect = sln(arr, idx + 1, prevNum, prevVal);
		//选择 判断与上一个数是否相同 相同 i位置价值为上个数的价值加1，向下传递； 不同 i位置价值重置为1，向下传递。 
		int iVal = arr[idx] == prevNum ? prevVal + 1 : 1;
		//当前位置及后面的价值相加
		int valSelect = iVal + sln(arr, idx + 1, arr[idx], iVal);
		//俩种情况选最大值
		return Math.max(valUnselect, valSelect);
	}
	
	
}
