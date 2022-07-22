package com.zkc.BitwiseOperation;

public class ReverseBits {
	public static void main(String[] args) {
		System.out.println(reverseBits(10));
	}
	
	private static int reverseBits(int n) {
		int val = 0;
		for (int i = 0; i < 32; i++) {
			//n的二进制形式 从右取 每取一个数转移到镜像位置 1010->0101 由高位设置到低位填充值 0+0000->0000+0100->0100+0000->0100+0001
			val |= ((n >> i) & 1) << (31 - i);
		}
		return val;
	}
}
