package com.zkc.BitwiseOperation;

public class PowAb {
	public static void main(String[] args) {
		int a = 5;
		int b = 4;
		System.out.println(powerN(a, b));
		double c = 2.12;
		int d = 3;
		System.out.println(powerD(c, d));
	}
	
	/**
	 * 快速求整数v的n次方
	 * 将指数n看成二进制   如3=11= 10+1  分解为v^3=v^2*v^1  每个项为v的二的倍数次方
	 * 将n右移 用v相乘作为当前位置的次方结果  只有遇到为1的位置将对应次方结果乘入最终结果
	 */
	private static long powerN(int v, int n) {
		if (n == 0) {
			return 1;
		}
		//临时结果先设置成1次方
		long tempV = v;
		long ret = 1;
		//101->10->1->0
		//101=100+00+1
		//v^(2^2)   *   v^(2^0)
		while (n != 0) {
			//当前位置不为1  结果乘一次方    最后乘四次方   
			if ((n & 1) != 0) {
				ret *= tempV;
			}
			tempV *= tempV;
			n >>= 1;
		}
		return ret;
	}
	
	/**
	 * v为double类型    n为整数
	 * v n都可以为负数
	 * 如果n是负数 先统一算正数次方结果  再作为分母用1除
	 */
	private static double powerD(double v, int n) {
		if (n == 0) {
			return 1D;
		}
		//最小值无法转为正数 比maxvalue大
		if (n == Integer.MIN_VALUE) {
			return v == 1D || v == -1D ? 1D : 0;
		}
		int pow = Math.abs(n);
		double tempV = v;
		double ret = 1;
		while (pow != 0) {
			if ((pow & 1) != 0) {
				ret *= tempV;
			}
			tempV *= tempV;
			pow >>= 1;
		}
		return n < 0 ? (1D / ret) : ret;
	}
}
