
package com.zkc;

/**
 * 给定俩个数a b
 * 第1轮把 1 给a或b
 * 第2轮把 2 给a或b
 * 第i轮把 i 给a或b
 * 最少需要几轮 可以使a b值相等
 */
public class NumEqualMinSteps {
	public static void main(String[] args) {
//		int a = (int) (Math.random() * 100) + 1;
//		int b = (int) (Math.random() * 100) + 1;
		int a = 1;
		for (int i = 1; i <= 29 + 100; i++) {
			System.out.printf("a=%s, b=%s，minSteps=%d\n", a, i, getMinSteps(a, i));
		}
	}
	
	/**
	 * 设a>b 差值为a-b=d。可以设a b分别得到的数的和为 x,y.
	 * 因为b小 所以分配给b的数y较多，a分配的数x较少或者不用分配,y-x的值肯定也是d.
	 * 如果第i轮ab相等 第i轮分配出去的数为x y的总和为sum=（1+i)*i/2；所以 x+y=sum,y-x=d ---> x=(sum-d)/2;
	 * 分配给a的数有可能为0，所以(sum-d)/2>=0--->sum>=d;因为添加给a或b的数之和都为整数,所以sum-d要为偶数。
	 * 所以i只需要从1开始遍历  尝试计算((1+i)*i/2),遇到的第一个大于等于d并且为偶数的sum停止， 此时的i为返回结果。
	 * 为了加快遍历速度 i从1开始翻倍计算。如果得到了一个偶数，则最终结果i会在翻倍前的位置和当前位置之间。
	 * 此时可以用二分法找到商为偶数的最小的i返回。
	 */
	private static int getMinSteps(int a, int b) {
		if (a == b) {
			return 0;
		}
		if (a < b) {
			a = a ^ b;
			b = b ^ a;
			a = a ^ b;
		}
		int d = a - b;
		int i = 1;
		//先找到快速找到一个刚开始满足sum>=d的位置 从该位置遍历
		while (true) {
			int sum0i = (((1 + i) * i) >> 1);
			if (sum0i >= d) {
				i >>= 1;
				break;
			} else {
				i <<= 1;
			}
		}
		while (true) {
			int sum0i = ((1 + i) * i) >> 1;
			if (sum0i >= d && ((sum0i - d) & 1) == 0) {
				return i;
			}
			i++;
		}
	}
	
}
