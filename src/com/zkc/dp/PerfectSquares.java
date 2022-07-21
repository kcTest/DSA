package com.zkc.dp;

/**
 * Given an integer n, return the least number of perfect square numbers that sum to n.
 * <p>
 * A perfect square is an integer that is the square of an integer;
 * in other words, it is the product of some integer with itself.
 * For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * Example 2:
 * <p>
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 104
 */
public class PerfectSquares {
	public static void main(String[] args) {
		for (int i = 1; i < 65; i++) {
			int numSq1 = numSquares(i);
			int numSq2 = numSquares2(i);
			int numSq3 = numSquares3(i);
			if (numSq1 != numSq2 || numSq1 != numSq3) {
				System.out.printf("val=%d,numSq1=%d,numSq2=%d,numSq3=%d\n", i, numSq1, numSq2, numSq3);
				break;
			}
		}
	}
	
	/**
	 * 结论
	 * 1.通过开根号取整再相乘可以直接判断是否由1个平方数组成。
	 * 2.每个数最多由4个平方数组成。
	 * 3.任何数%8取模==7，一定由4个平方数组成。
	 * 4.一个数如果是4的倍数(n=4*......x),将系数4消除之后，%8取模==7，一定由4个平方数组成。
	 */
	private static int numSquares3(int n) {
		if (n <= 0) {
			return 0;
		}
		while (n % 4 == 0) {
			n /= 4;
		}
		if (n % 8 == 7) {
			return 4;
		}
		int a = (int) Math.sqrt(n);
		if (a * a == n) {
			return 1;
		}
		for (int x = 1; x < n; x++) {
			int aa = (int) Math.sqrt(n - x * x);
			if (aa * aa == n - x * x) {
				return 2;
			}
		}
		return 3;
	}
	
	
	private static int numSquares2(int n) {
		if (n <= 0) {
			return 0;
		}
		int len = n + 1;
		int[] record = new int[len + 1];
		for (int i = 1; i < len; i++) {
			int a = (int) Math.sqrt(i);
			if (a * a == i) {
				record[i] = 1;
				continue;
			}
			int count = i;
			for (int x = 1; x < i; x++) {
				int num = i - x * x >= 0 ? record[i - x * x] : 0;
				if (num > 0) {
					count = Math.min(count, 1 + num);
				}
			}
			record[i] = count;
		}
		return record[n];
	}
	
	/**
	 * 平方数 1,4,9,16,25，36,49.....
	 * 是否由1个平方数组成: 开根号取整数后得到x,如果a*a=n,说明n本身就是一个平方数。
	 * <p>
	 * 是否由2个平方数组成: 此时n已不满足只由1个平方数组成,
	 * 枚举 设x=1,4,9,16,25，36,49.....的情况下，y=n-x是否由一个平方数组成。
	 * <p>
	 * 是否由3个平方数组成: 此时n已不满足只由2个平方数组成,
	 * 枚举 设x=1,4,9,16,25，36,49.....的情况下，y=n-x至少由2个平方数组成, 将y=n-x再枚举,
	 * 枚举 设y=1,4,9,16,25，36,49.....的情况下，z=n-x-y是否由一个平方数组成。
	 * <p>
	 * 按同样方法判断是否由4个平方数组成，也就是在3的基础上继续拆z,z=1,4,9,16,25，36,49.....的情况下，m=n-x-y-z是否由一个平方数组成
	 * 依次类推
	 * 如果n为平方数1的和，那么n最多由n个平方数组成。
	 */
	private static int numSquares(int n) {
		if (n <= 0) {
			return 0;
		}
		int a = (int) Math.sqrt(n);
		if (a * a == n) {
			return 1;
		}
		int count = n;
		for (int x = 1; x < n; x++) {
			int num = numSquares(n - x * x);
			if (num > 0) {
				count = Math.min(count, 1 + num);
			}
		}
		return count;
	}
}
