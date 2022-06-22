package com.zkc;

import java.util.LinkedList;

/**
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 */
public class LongestValidParentheses {
	public static void main(String[] args) {
		String s = "()(((()))))";
//		String s = "(()()";
//		String s = "()(()";
//		String s = ")()())";
		System.out.println(longestValidParentheses(s));
	}
	
	/**
	 * 遇到右括号 检查之前的位置是否是左括号 如果是消除
	 * 最后未消除的括号之间的最大距离为序列的最大深度
	 */
	private static int longestValidParentheses(String s) {
		int max = 0;
		char[] arr = s.toCharArray();
		LinkedList<Integer> queue = new LinkedList<>();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == ')' && queue.size() > 0 && arr[queue.getLast()] == '(') {
				queue.removeLast();
			} else {
				queue.addLast(i);
			}
		}
		int start = -1;
		while (queue.size() > 0) {
			int idx = queue.remove(0);
			max = Math.max(idx - start - 1, max);
			start = idx;
		}
		if (arr.length - 1 - start > max) {
			max = arr.length - 1 - start;
		}
		return max;
	}
}
