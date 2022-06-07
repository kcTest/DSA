package com.zkc.BitwiseOperation;

public class GetMax {
	
	public static void main(String[] args) {
		System.out.println(getMax(-9, 0));
	}
	
	/**
	 * +  if else
	 */
	private static int getMax(int a, int b) {
		//>=0 返回1  <0返回 0
		int signA = ((a >> 31) & 1) ^ 1;
		int signB = ((b >> 31) & 1) ^ 1;
		int c = a - b;
		//a>=b 返回1 
		int signC = ((c >> 31) & 1) ^ 1;
		//ab符号是否不同
		int diffSign = signA ^ signB;
		//ab符号是否相同
		int sameSign = diffSign ^ 1;
		//返回a的情况 1和2情况互斥。  1、符号不同 diffSign=1 a大于等于0 a的msb为0 signA为1。  2、符号相同 diffSign=0 sameSign=1  a>=b signC=1
		int returnA = diffSign * signA + sameSign & signC;
		int returnB = returnA ^ 1;
		return a * returnA + b * returnB;
	}
	
	
}
