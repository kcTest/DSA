package com.zkc.BitwiseOperation;

public class IsPowerOfN {
	
	public static void main(String[] args) {
		System.out.println(isPowerOfTwo(32));
		System.out.println(isPowerOfFour(64));
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
	
	private static boolean isPowerOfTwo2(int x, int n) {
		if (x <= 0) {
			return false;
		}
		System.out.println(Integer.toBinaryString(n));
		System.out.println(Integer.toBinaryString(x));
		int steps = log2(2);
		int curVal = 1;
		int validPos = 1;
		int curPos = 1;
		boolean checked = false;
		while (curVal > 0 && curVal <= x) {
			System.out.println(Integer.toBinaryString(curVal));
			if (curPos == validPos) {
				validPos += steps;
				if ((x & curVal) > 0) {
					checked = true;
				}
			} else if ((x & curVal) > 0) {
				return false;
			}
			if (checked && (curVal < x)) {
				return false;
			}
			curPos++;
			curVal = curVal << 1;
		}
		return true;
	}
	
	private static int log2(int N) {
		return (int) (Math.log(N) / Math.log(2));
	}
}
