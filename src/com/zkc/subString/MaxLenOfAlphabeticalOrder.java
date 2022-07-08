package com.zkc.subString;

import com.zkc.utils.MyUtils;

import java.util.LinkedList;

/**
 * To determine which of two strings of characters comes first when arranging in alphabetical order, their first letters are compared.
 * If they differ, then the string whose first letter comes earlier in the alphabet comes before the other string.
 * If the first letters are the same, then the second letters are compared, and so on.
 * If a position is reached where one string has no more letters to compare while the other does,
 * then the first (shorter) string is deemed to come first in alphabetical order.
 * <p>
 * <p>
 * 给定一个字符串  求长度为K且字典序最大的 子序列
 * 如 s=deafad,k=2  子序列为fd
 * 如 s=deaabc,k=3  子序列为ebc
 */
public class MaxLenOfAlphabeticalOrder {
	public static void main(String[] args) {
		String s = MyUtils.getAZString(11);
		int k = (int) (Math.random() * s.length() / 2) + 1;
//		String s = "deaabc";
//		int k = 3;
		System.out.printf("s=%s; k=%d; ", s, k);
		System.out.printf("ret=%s\n", getSubSequence(s, k));
	}
	
	/**
	 * 遍历字符串，每遇到一个字符入栈，入栈前检查 将栈中比自己字典序小的弹出，只保留k个数，直到遍历结束。
	 * 1遍历到过程中 或者 遇到了一个更大字典序的字符导致栈在往外弹的时候，如果发现栈中元素的数量和剩余待遍历元素数量相加正好为k
	 * 将栈中元素以从下往上的顺序和剩余元素拼接返回为最终k长度的最大字典序子序列结果。
	 * 2.如果没有出现1的情况遍历到了结尾，栈中元素数量肯定大于k，从下往上取前k个元素拼接返回为最终k长度的最大字典序子序列结果。
	 */
	private static String getSubSequence(String s, int k) {
		if (s == null || k > s.length()) {
			return "";
		}
		char[] charArr = s.toCharArray();
		int n = charArr.length;
		LinkedList<Character> deque = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			//n-i 包括i位置剩余待处理元素个数,移除时也要判断如果数量不够k不能再移除
			while (!deque.isEmpty() && deque.getLast() < charArr[i] && deque.size() + (n - i) > k) {
				deque.removeLast();
			}
			if (deque.size() + (n - i) == k) {
				//栈和数组中剩余的返回
				StringBuilder sb = new StringBuilder();
				for (Character c : deque) {
					sb.append(c);
				}
				for (int j = i; j < n; j++) {
					sb.append(charArr[j]);
				}
				return sb.toString();
			}
			deque.addLast(charArr[i]);
		}
		//超过k的情况取前k
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < k; j++) {
			sb.append(deque.get(j));
		}
		return sb.toString();
	}
}
