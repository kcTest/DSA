package com.zkc.BitwiseOperation;

public class AddMinusMultiDivide {
	public static void main(String[] args) {
		int a = -27, b = 3;
		System.out.printf("%d + %d = %d\n", a, b, add(a, b));
		System.out.printf("%d - %d = %d\n", a, b, minus(a, b));
		System.out.printf("%d x %d = %d\n", a, b, multiply(a, b));
		System.out.printf("%d ÷ %d = %d\n", a, b, divide(a, b));
	}
	
	private static int add(int a, int b) {
		while (b != 0) {
			//无进位相加 
			int temp = a ^ b;
			//&获取进位位置  <<1获取待补充进位信息的位置
			b = (a & b) << 1;
			a = temp;
		}
		return a;
	}
	
	private static int minus(int a, int b) {
		return add(a, negNum(b));
	}
	
	private static int negNum(int n) {
		//按位取反加一得相反数的二进制形式
		return add(~n, 1);
	}
	
	private static int multiply(int a, int b) {
		int ret = 0;
		while (b != 0) {
			if ((b & 1) != 0) {
				ret = add(ret, a);
			}
			//将b向右移 方便检查b的每一位 如果为1，加上已经左移对应位数的a  
			//可以看成从lsb开始向左检查b的每一位 如果为1加a a再左移 与b的下一位检查
			//1011   101100
			//0101   000001
			a <<= 1;
			//b无符号右移 左侧总是以0填充  最终为0
			b >>>= 1;
		}
		return ret;
	}
	
	private static int divide(int a, int b) {
		int c = 0;
		//处理负数
		boolean aIsNeg = a < 0;
		boolean bIsNeg = b < 0;
		a = aIsNeg ? negNum(a) : a;
		b = bIsNeg ? negNum(b) : b;
		//根据c中的每一位是否是1 b左移相应的位数后相加最终得到a。 所以当b尽量左移到一个高位 并且能在该位置保持b小于a。
		//则结果c中的该位置为1。 a减去b左移后的结果后 再让b尽量左移 找到一个不大于a的位置 c中该设置仍会是1。
		//重复操作 直到a无法再大于b时 b如果还大于0 b此时为相除的余数。 c为每次设置对应位置为1的结果。
		for (int i = 31; i > -1; i = minus(i, 1)) {
			//b左移i位小于a a右移i位也会大于b 此处防止b左移溢出先操作a
			if ((a >> i) >= b) {
				c = c | (1 << i);
				//b此时移位肯定小于a并且不会溢出  减去移位后的b  
				a = minus(a, b << i);
			}
		}
		c = aIsNeg ^ bIsNeg ? negNum(c) : c;
		return c;
	}
	
	
}
