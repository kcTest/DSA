package com.zkc;

import java.util.LinkedList;

public class LongestValidParentheses {
	public static void main(String[] args) {
		String s = "()(((()))))";
//		String s = "(()()";
//		String s = "()(()";
//		String s = ")()())";
		System.out.println(longestValidParentheses(s));
	}
	
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
