package com.zkc.subString;

import java.util.*;

/**
 * Given a string s, remove duplicate letters so that every letter appears once and only once.
 * You must make sure your result is the smallest in lexicographical order among all possible results.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "bcabc" bac        abc
 * Output: "abc"
 * Example 2:
 * <p>
 * Input: s = "cbacdcbc"       acdb
 * Output: "acdb"
 */
public class RemoveDuplicateLetters {
	public static void main(String[] args) {
//		String s = "bgbrzorgz";//MyUtils.getAZString(10);     bgrz
//		String s = "rgornguwz";
//		String s = "bbcaac";
		String s = "bcabc";
		System.out.println(s);
		System.out.println(removeDuplicateLetters(s));
	}
	
	/**
	 * 记录初始每个元素的出现次数
	 * 来到每个位置  对应元素出现次数减1
	 * 1.检查当前元素cur是否已经添保存过，如果保存过直接跳过，否则加入容器尾部。
	 * 2.检查cur的字典序是否小于前面容器中已保存的最后一个元素pre的字典序
	 * <p>如果此时pre在后面还有会出现即出现次数>0 可以将pre从容器中移除,pre对应的字母使用后面出现的 可以降低字符串的字典序，
	 * <p>直到cur不在小于容器末尾元素；否则不能移除。
	 * 3.加入当前元素到容器中。
	 */
	private static String removeDuplicateLetters(String s) {
		char[] chars = s.toCharArray();
		//记录字母出现次数
		int[] count = new int[26];
		for (char c : chars) {
			count[c - 'a']++;
		}
		//记录每个字母是否已经添加
		boolean[] added = new boolean[26];
		StringBuilder sb = new StringBuilder();
		for (char cur : chars) {
			count[cur - 'a']--;
			if (!added[cur - 'a']) {
				//直到不再小于前一个已保存的元素的字典序
				while (sb.length() > 0) {
					char pre = sb.charAt(sb.length() - 1);
					if (cur < pre && count[pre - 'a'] > 0) {
						sb.deleteCharAt(sb.length() - 1);
						added[pre - 'a'] = false;
					} else {
						break;
					}
				}
				sb.append(cur);
				added[cur - 'a'] = true;
			}
		}
		return sb.toString();
	}
}
