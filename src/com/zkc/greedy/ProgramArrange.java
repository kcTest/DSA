package com.zkc.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 我们可以做出目前看起来最好的选择，然后再解决以后出现的次要问题。
 * 贪心算法所做的选择可能取决于到目前为止所做的选择，但不取决于未来的选择或子问题的所有解决方案。
 * 它迭代地做出一个又一个贪心的选择，将每个给定的问题缩小为一个更小的问题。换句话说，
 * 贪心算法永远不会重新考虑它的选择。这是与动态规划的主要区别，动态规划是穷举式的，并且保证能够找到解决方案。
 * 在每个阶段之后，动态规划基于前一阶段的所有决策做出决策，并可能重新考虑前一阶段的算法路径到解决方案
 */
public class ProgramArrange {
	
	public static void main(String[] args) {
		int min = 6;
		int max = 18;
		Program[] arr = new Program[(int) (Math.random() * (max - min))];
		for (int i = 0; i < arr.length; i++) {
			int start = (int) (Math.random() * (max - min - 1)) + min;
			int end = start + (int) (Math.random() * (max - start)) + 1;
			Program program = new Program(start, end);
			arr[i] = program;
		}
		Arrays.sort(arr, Comparator.comparingInt(p -> p.end));
		for (Program program : arr) {
			System.out.printf("[%d,%d]\n", program.start, program.end);
		}
		System.out.println(bestArrange(arr, min));
	}
	
	
	/**
	 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。给你每一个项目开始的时间和结束的时间 。
	 * 你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。 返回最多的宣讲场次。
	 */
	private static int bestArrange(Program[] programs, int timePoint) {
		int count = 0;
		for (Program program : programs) {
			if (timePoint <= program.start) {
				count++;
				timePoint = program.end;
			}
		}
		return count;
	}
	
	
	static class Program {
		
		public int start;
		public int end;
		
		public Program(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
}
