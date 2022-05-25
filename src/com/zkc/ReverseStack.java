package com.zkc;

import java.util.Stack;

/**
 * 栈元素逆序 不借助其它数据结构
 */
public class ReverseStack {
	public static void main(String[] args) {
		int[] nums = new int[]{3, 7, 1, 6};
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < nums.length; i++) {
			int val = nums[i];
			System.out.printf("%d,", nums[nums.length - 1 - i]);
			stack.push(val);
		}
		reverseStack(stack);
		System.out.println();
		while (stack.size() > 0) {
			System.out.printf("%d,", stack.pop());
		}
	}
	
	/**
	 * <p>⑥ 1 7 3
	 * <p>   ① 7 3 ⑥
	 * <p>      7 3 ① ⑥
	 * <p>        3 ⑦ ① ⑥
	 * 每次遍历将头部元素与底部底元素交换
	 * 将栈顶元素往下传递  遍历到结束位置后将栈顶元素先入栈  回退过程将之前的元素再入栈
	 * 执行交换的次数为栈的大小减一
	 */
	private static void reverseStack(Stack<Integer> stack) {
		if (stack.size() == 0) {
			return;
		}
		int i = 0;
		while (i < stack.size() - 1) {
			int top = stack.pop();
			recurse(stack, top, i++);
		}
	}
	
	private static void recurse(Stack<Integer> stack, int top, int end) {
		//控制交换的位置  end为0时全部弹出 交换底部，后续遍历继续向上位置不重复 
		if (stack.size() == end) {
			stack.push(top);
			return;
		}
		//保留 回退时再入栈
		int prev = stack.pop();
		recurse(stack, top, end);
		stack.push(prev);
	}
}
