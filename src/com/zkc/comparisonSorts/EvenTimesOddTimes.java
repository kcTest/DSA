package com.zkc.comparisonSorts;

import com.zkc.utils.MyUtils;

public class EvenTimesOddTimes {
	
	public static void main(String[] args) {
		printOddTimesNum2();
	}
	
	/**
	 * 数组中只有一种数出现奇数次
	 */
	private static void printOddTimesNum1() {
		int[] arr = new int[]{5, 5, 4, 4, 4, 7, 7, 3, 3};
		MyUtils.printArr(arr);
		System.out.println();
		System.out.println("===================");
		int ret = 0;
		for (int j : arr) {
			ret = ret ^ j;
		}
		System.out.println(ret);
	}
	
	/**
	 * 数组中只有俩种数出现奇数次
	 * eor=a^b,<b>ab不相同 </b>则eor至少有一位为1，则a或b在某个位置一个为1一个为0,其余所有数在该位置都存在偶数个1或0。
	 * 该数组中所有在该位置为1或0的数进行异或，得到的结果为a或b， 再与eor进行异或得到另一个。
	 */
	private static void printOddTimesNum2() {
		int[] arr = new int[]{5, 5, 4, 4, 7, 7, 3, 3, 12, 12, 12, 9, 9, 5};
		MyUtils.printArr(arr);
		System.out.println();
		System.out.println("===================");
		int aEorb = 0;
		for (int j : arr) {
			aEorb = aEorb ^ j;
		}
		
		//找右侧第一个1的位置
		//    int n = 0;
		//    while (true) {
		//    	if ((eor & (1 << n)) != 0) {
		//    		break;
		//    	}
		//    	n++;
		//    }
		//int rightOne = 1 << n;
		
		//找到二进制形式右侧第一个1的位置并截取包含该位置及之后的数
		int rightOne = aEorb & (~aEorb + 1);
		//数组中所有指定位置为1的数异或会得到a、b其中一个
		int aOrb = 0;
		for (int j : arr) {
			if ((j & rightOne) != 0) {
				aOrb = aOrb ^ j;
			}
		}
		System.out.println(aOrb);
		//得到另一个
		System.out.println(aOrb ^ aEorb);
	}
}
