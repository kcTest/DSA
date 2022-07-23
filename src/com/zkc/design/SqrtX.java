package com.zkc.design;

public class SqrtX {
	public static void main(String[] args) {
		int n = 2147395599;
		System.out.println(Integer.MAX_VALUE);
		System.out.printf("n=%d,sqrt(n)=%d", n, sqrtN(n));
	}
	
	/**
	 * 求对n开平方的结果  结果向下取整
	 * 在1-n的范围上二分取数  计算平方
	 * 如果位置的数平方大于n 说明目标数比当前数要小一些 去左侧找
	 * 如果位置的数平方小于n 说明目标数比当前数要大一些 去右侧找
	 */
	private static int sqrtN(int n) {
		if (n == 0) {
			return 0;
		}
		long ret = 1;
		long low = 1;
		long high = n;
		while (low <= high) {
			long mid = low + (high - low) / 2;
			long temp = mid * mid;
			if (temp > n) {
				high = mid - 1;
			} else if (temp == n) {
				ret = mid;
				break;
			} else if (temp < n) {
				ret = mid;
				low = mid + 1;
			}
		}
		return (int) ret;
		
	}
	
	private static int sqrtX(int n) {
		if (n == 0) {
			return 0;
		}
		long l = 1, r = n, ret = 1;
		while (l <= r) {
			long mid = l + ((r - l) >> 1);
			long cur = mid * mid;
			if (cur < n) {
				ret = mid;
				l = mid + 1;
			} else if (cur == n) {
				return (int) mid;
			} else {
				r = mid - 1;
			}
		}
		return (int) ret;
	}
}
