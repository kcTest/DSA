package com.zkc.subString;

import com.zkc.utils.MyUtils;

import java.util.HashMap;

/**
 * 给定01字符串 求在0...i上 分割字符串 使分割出的每部分字符串0和1的比例均相同  返回数组arr[i]表示i位置及之前的字符串能分割出的最大部分数
 */
public class Str01Split {
	public static void main(String[] args) {
		String s = "010100001";
//		String s = "110110";
		System.out.println(s);
		MyUtils.printArr(split(s));
	}
	
	/**
	 * 来到每个位置 记录遇到的0和1的个数
	 * 如果在当前位置发现只遇到过0或者1，当前位置分割出等比例的单个0或1字符串，份数值取i+1
	 * 否则 计算01比例 比例值取最简 如4:6 和 8:12 统一记为2:3,如果之前位置j发现过01比例为2:3的字符串 那么 j+1到i位置01比例肯定也为2:3,
	 * 此时份数值为之前的+1。否则新增份数为1，双层map第一层k为分子 第二层k为分母 值为当前比例份数。
	 */
	private static int[] split(String s) {
		if (s == null || s.length() == 0) {
			return null;
		}
		char[] charArr = s.toCharArray();
		int[] ret = new int[charArr.length];
		int zero = 0;
		int one = 0;
		HashMap<Integer, HashMap<Integer, Integer>> record = new HashMap<>();
		for (int i = 0; i < charArr.length; i++) {
			if (charArr[i] == '0') {
				zero++;
			} else {
				one++;
			}
			if (zero == 0 || one == 0) {
				ret[i] = i + 1;
			} else {
				int gcd = gcd(zero, one);
				int numerator = zero / gcd;
				int denominator = one / gcd;
				if (!record.containsKey(numerator)) {
					record.put(numerator, new HashMap<>());
				}
				HashMap<Integer, Integer> numeratorMap = record.get(numerator);
				int count = 1;
				if (!numeratorMap.containsKey(denominator)) {
					numeratorMap.put(denominator, count);
				} else {
					count = numeratorMap.get(denominator) + 1;
					numeratorMap.put(denominator, count);
				}
				ret[i] = count;
			}
		}
		return ret;
	}
	
	private static int gcd(int m, int n) {
		return n == 0 ? m : gcd(n, m % n);
	}
}
