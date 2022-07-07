package com.zkc;

import java.util.Stack;

/**
 * 计算运算符表达式的值 [0 正整数]   + - * /
 */
public class CalExpression {
	public static void main(String[] args) {
//		String s = "35*(3+5-(6*2+1))+34";
//		String s = "1+((((2+3)*4)+5)-6)+7+8/4";
		String s = "1+2+3-3-2-1+(1+1)";
		int[] ret = getExpressionVal(s.toCharArray(), 0);
		System.out.printf("val=%d,end=%d\n", ret[0], ret[1]);
	}
	
	/**
	 * 每遇到一个数入栈，入栈前检查栈顶符号 如果为* 将*及前一个数弹出与当前数做乘法后再入栈。遇到*或+将符号入栈
	 * 如果在i位置遇到左括号，递归调用方法求f(i+1)的值及结束位置，结束位置由遇到的右括号决定。i位置得到结果后
	 * 将值入栈，入栈前同样检查栈顶符号。
	 * 如果在i位置遇到右括号，依次将栈顶元素和符号弹出挨个相加将结果返回。
	 * 如果来到末尾 依次将栈顶元素和符号弹出挨个相加将结果返回。
	 */
	private static int[] getExpressionVal(char[] charArr, int start) {
		Stack<String> stack = new Stack<>();
		int curVal = 0;
		boolean getVal = false;
		for (int i = start; i < charArr.length; i++) {
			char c = charArr[i];
			if (c >= 48 && c <= 57) {
				curVal = curVal * 10 + c - 48;
				getVal = true;
			} else {
				if (getVal) {
					checkAndPush(stack, curVal);
				}
				if (c == '(') {
					int[] temp = getExpressionVal(charArr, i + 1);
					i = temp[1];
					checkAndPush(stack, temp[0]);
				} else if (c == ')') {
					return new int[]{computeStack(stack), i};
				} else {
					stack.push(String.valueOf(c));
				}
				curVal = 0;
				getVal = false;
			}
		}
		if (getVal) {
			checkAndPush(stack, curVal);
		}
		return new int[]{computeStack(stack), charArr.length - 1};
	}
	
	private static int computeStack(Stack<String> stack) {
		int ret = 0;
		int type = -1;
		//从头开始
		for (String s : stack) {
			switch (s) {
				case "+":
					type = 1;
					break;
				case "-":
					type = 2;
					break;
				default:
					int val = Integer.parseInt(s);
					if (type > 0) {
						ret = type == 1 ? ret + val : ret - val;
					} else {
						ret = val;
					}
			}
		}
		return ret;
	}
	
	private static void checkAndPush(Stack<String> stack, int curVal) {
		if (!stack.isEmpty()) {
			String s = stack.peek();
			//入栈前 遇到*/先与前一个数计算完再入栈  保持栈内只有+-
			if ("*".equals(s)) {
				stack.pop();
				curVal = Integer.parseInt(stack.pop()) * curVal;
			} else if ("/".equals(s)) {
				stack.pop();
				curVal = Integer.parseInt(stack.pop()) / curVal;
			}
		}
		stack.push(String.valueOf(curVal));
	}
}
