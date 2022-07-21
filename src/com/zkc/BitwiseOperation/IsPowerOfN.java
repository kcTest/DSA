package com.zkc.BitwiseOperation;

public class IsPowerOfN {
	
	public static void main(String[] args) {
//		System.out.println(isPowerOfTwo(32));
//		System.out.println(isPowerOfFour(64));
		for (int i = 0; i < 10; i++) {
			System.out.println(isPowerOfThree(Integer.MIN_VALUE));
		}
	}
	
	private static boolean isPowerOfTwo(int x) {
		if (x <= 0) {
			return false;
		}
		//只包含一个1
		return (x & (x - 1)) == 0;
	}
	
	private static boolean isPowerOfFour(int x) {
		if (x <= 0) {
			return false;
		}
		//只包含一个1并且出现在0 2 4 8 位置
		return (x & (x - 1)) == 0 && (x & 0x55555555) != 0;
	}
	
	private static boolean isPowerOfThree(int n) {
		if (n <= 0) {
			return false;
		}
		if (n == 1 || n == 3) {
			return true;
		}
		double curVal;
		int zs = 1;
		do {
			curVal = Math.pow(3, zs << 1);
			if (curVal == n) {
				return true;
			} else if (curVal > n) {
				int low = zs;
				int high = zs << 1;
				while (low <= high) {
					int mid = low + ((high - low) >> 1);
					curVal = Math.pow(3, mid);
					if (curVal < n) {
						low = mid + 1;
					} else if (curVal == n) {
						return true;
					} else {
						high = mid - 1;
					}
				}
				return false;
			}
			zs <<= 1;
		} while (curVal < n);
		return false;
	}
	
	private static int log2(int N) {
		return (int) (Math.log(N) / Math.log(2));
	}
}
