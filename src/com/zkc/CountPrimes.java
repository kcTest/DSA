package com.zkc;

import java.util.Arrays;
import java.util.Vector;

/**
 * Given an integer n, return the number of prime numbers that are strictly less than n.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 * Example 2:
 * <p>
 * Input: n = 0
 * Output: 0
 * Example 3:
 * <p>
 * Input: n = 1
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= n <= 5 * 106
 */
public class CountPrimes {
	public static void main(String[] args) {
		int n = (int) (Math.random() * 1000);
		int c1 = countPrimes(n);
		System.out.printf("n=%d,c1=%d", n, c1);

//		int prevPos = 0;
//		int d = 0;
//		for (int i = 2; i < 1000; i++) {
//			boolean prime = isPrime(i);
//			if (prime) {
//				if (prevPos != 0) {
//					d = i - prevPos;
//				}
////				System.out.printf("n=%d,isPrime=%d,d=%d\n", i, 1, d);
//				System.out.printf("d=%d\n", d);
//				prevPos = i;
//			} else {
////				System.out.printf("n=%d\n", i);
//			}
//		}
	}
	
	/**
	 * 如果x是质数，那么大于x的倍数2*x,3*x,......<n,一定不是质数
	 * 我们设isPrime[i] 表示数 i 是不是质数，如果是质数则为 1，否则为 0。
	 * 从小到大遍历每个数，如果这个数为质数，则将其所有的倍数都标记为合数（除了该质数本身），即 0，这样在运行结束的时候我们即能知道质数的个数。
	 * <p>
	 * 对于所有的i不需要判断比i与i小的数的乘积，因为之前已经判断了，比如i=5,i*2..i*4已经在i=2...4的时候判断过了。
	 */
	private static int countPrimes(int n) {
		if (n <= 2) {
			return 0;
		}
		int count = 0;
		//默认全是质数 后续将不是质数的标记为1
		int[] arrIsPrime = new int[n];
		//从3开始 跳过奇数 
		for (int i = 3; i < n; i += 2) {
			if (arrIsPrime[i] == 0) {
				count++;
				if ((long) i * i < n) {
					//j从i的i倍开始,j=i*i,i*(i+1),i*(i+2)....<n
					for (int j = i * i; j < n; j += i) {
						arrIsPrime[j] = 1;
					}
				}
			}
		}
		//算上偶数2
		return count + 1;
	}
	
	/**
	 * 判断一个是否是质数
	 * 比如n=36,遍历时遇到可能整除36且在[2,n-1]上的数 之间相乘有
	 * 2*18 3*12 4*9 6*6 9*4 12*3 18*2
	 * 如果x*y=36,那么36/y也是36的因数，反之也一样。所以如果判断2是36的因数18肯定也是 不需要再去判断18。
	 * 所以只看因数中较小的一方的最大范围，从2->6->2，为sqrt(36);
	 * 所以给定一个n，因数只需要判断到sqrt(n)，如果到sqrt(n)也不是n的因数 后面的也都不是;
	 */
	private static boolean isPrime(int n) {
		for (int i = 2; i * i <= n; i++) {
			//i是n的因数 不是质数
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
}
