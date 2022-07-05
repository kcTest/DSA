package com.zkc.subString;

import com.zkc.utils.MyUtils;

import java.util.Arrays;

/**
 * 字符串由a-z组成 求无重复字符的子串的最大长度
 */
public class NoRepeatSubString {
	public static void main(String[] args) {
		String s = MyUtils.getAZString(10);
		System.out.printf("%s, %d", s, getNoRepeatSubStringLen(s));
	}
	
	/**
	 * 求以每个i位置结尾的子串无重复字符的最大长度
	 * acbcda  如果i-1位置d已经求出record[i-1]为4，当前a要看a上次出现的位置和record[i-1]哪个更小 ，record[i]向左扩 最多扩到4位置,
	 * 但是如果前一个a与当前a的距离小于4落在record[i-1]的范围内 cbacda 此时record[i]左扩最多只能到a的后一个位置。
	 * 所以i位置的结果为record[i-1]再+当前位置1 和i-lastA中的最小者
	 */
	private static int getNoRepeatSubStringLen(String s) {
		//在遍历过程中更新字符更靠后出现的位置
		int[] charPos = new int[26];
		//初始
		Arrays.fill(charPos, -1);
		char[] charArr = s.toCharArray();
		//第一个字符可以直接获取结果
		//以0位置字符结尾的无重复字符子串长度为1；
		charPos[charArr[0] - 'a'] = 1;
		//最大长度直接设置为1
		int max = 1;
		//上一个位置0算出的最大长度为1
		int preMaxLen = 1;
		//从1开始
		for (int i = 1; i < charArr.length; i++) {
			//如果a出现在record[i-1]左侧边界的左边,record[i-1]+1为当前结果
			//否则为当前a到上一个a的前一个位置的长度
			//i - charPos[charArr[i] - 'a']如果更小说明 是第二种情况  否则直接为record[i-1]+1
			preMaxLen = Math.min(preMaxLen + 1, i - charPos[charArr[i] - 'a']);
			max = Math.max(preMaxLen, max);
			//更新字符位置 方便后续字符获取自己上一次出现的最近位置
			charPos[charArr[i] - 'a'] = i;
		}
		return max;
	}
}
