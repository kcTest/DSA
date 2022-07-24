package com.zkc;

import java.util.Stack;

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 */
public class ValidParentheses {
	public static void main(String[] args) {
		String s = "()";
		System.out.println(isValid(s));
	}
	
	
	private static boolean isValid(String s) {
		char[] chars = s.toCharArray();
		Stack<Character> stack = new Stack<>();
		for (char c : chars) {
			switch (c) {
				case ')':
					if (stack.isEmpty() || stack.pop() != '(') {
						return false;
					}
					break;
				case ']':
					if (stack.isEmpty() || stack.pop() != '[') {
						return false;
					}
					break;
				case '}':
					if (stack.isEmpty() || stack.pop() != '{') {
						return false;
					}
					break;
				default:
					stack.push(c);
			}
		}
		return stack.isEmpty();
	}
}
