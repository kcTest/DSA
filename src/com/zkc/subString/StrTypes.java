package com.zkc.subString;

import java.util.HashSet;
import java.util.Set;

/**
 * 两个字符串 如果字符种类完全一样 就算作一类
 * 只由a-z组成的一批字符串放入数组中  返回数组中一共由多少类字符串
 */
public class StrTypes {
	public static void main(String[] args) {
		String[] arr = new String[]{"a", "aaa", "abc", "bc", "cc", "cb", "df"};
		System.out.println(getTypes(arr));
	}
	
	private static int getTypes(String[] arr) {
		Set<Integer> types = new HashSet<>();
		for (String s : arr) {
			char[] charArr = s.toCharArray();
			int a = 0;
			//每个字符串用一个整数的26个位记录 出现过的字母 
			for (char c : charArr) {
				a |= 1 << (c - 'a');
			}
			//如果重复 对应位置相同 整数也相同 直接覆盖 只统计一次
			types.add(a);
		}
		return types.size();
	}
	
	
}
