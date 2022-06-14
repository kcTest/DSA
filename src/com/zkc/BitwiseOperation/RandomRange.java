package com.zkc.BitwiseOperation;

public class RandomRange {
	public static void main(String[] args) {
		int[] record = new int[7];
		for (int i = 0; i < 10000; i++) {
			record[rOne2Severn() - 1]++;
		}
		for (int j : record) {
			System.out.println(j);
		}
	}
	
	/**
	 * 给定一个函数可以等概率返回1到5，设计一个函数等概率返回1到7
	 * 相当于等概率0-6
	 * 同理给定一个函数返回a-d 设计一个函数返回 m-x
	 * 相当于等概率返回(m-m,x-m) 可以根据a-d设计等概率返回0-1的函数  通过移位1或0凑成与x-m相同位数的二进制形式。
	 * 如果凑成的数不在0->x-m之间 继续凑 最后在加上m。得到的数等概率落在m-x之间
	 */
	private static int rOne2Severn() {
		int num;
		do {
			//  如果为7继续  使num等概率落在0-6
			num = (rZeroOrOne() << 2) + (rZeroOrOne() << 1) + rZeroOrOne();
		} while (num == 7);
		//结果加1
		return num + 1;
	}
	
	/**
	 * 先设计一个函数等概率返回0或1
	 * 小于3返回0  大于3返回1. 等于3继续直到返回的数落在3两侧
	 */
	private static int rZeroOrOne() {
		int num;
		do {
			num = rOne2Five();
		} while (num == 3);
		return num < 3 ? 0 : 1;
	}
	
	/**
	 * 给定函数
	 */
	private static int rOne2Five() {
		return (int) (Math.random() * 5) + 1;
	}
	
	
}
