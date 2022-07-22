package com.zkc;

public class FactorialTrailingZeroes {
	public static void main(String[] args) {
//		for (int i = 0; i < 28; i++) {
//			System.out.printf("n=%d,ret=%d\n", i, trailingZeroes(i));
//		}
		int n = (int) (Math.random() * 1000);
		System.out.println(trailingZeroes(n));
	}
	
	/**
	 * 2*5得到10 ,阶乘因数中 偶数可以分解出2 出现的次数比5多，只看5出现的次数。
	 * <p>
	 * 1*2*3..*5......*10.....*15.....*25.....*125......*625.....
	 * <p>
	 * 每5个位置出现一个5的倍数  +1
	 * 每25个位置出现一个25的倍数 +1(25的倍数的因数中有至少有2个1按第一种方法查找时当前位置已经算过1个1，只需要再加1)
	 * 每125个位置出现一个125的倍数 +1(125的倍数的因数中有至少有3个1按第一、二种方法查找时当前位置已经算过2个1，只需要再加1)
	 * .
	 * .
	 * .
	 * 直到n不能再按某个5的倍数划分
	 */
	private static int trailingZeroes(int n) {
		if (n == 0) {
			return 0;
		}
		int divisor = 5;
		int count = 0;
		while (divisor <= n) {
			count += n / divisor;
			divisor *= 5;
		}
		return count;
	}
}
