package com.zkc;

import java.util.*;

/**
 * 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
 * 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 13
 * 输出：[1,10,11,12,13,2,3,4,5,6,7,8,9]
 * 示例 2：
 * <p>
 * 输入：n = 2
 * 输出：[1,2]
 */
public class LexicalOrder {
	
	public static void main(String[] args) {
//		int n = (int) (Math.random() * 200);
//		int n = 9;
//		int n = 19;
		int n = 101;
//		int n = 199;
		System.out.printf("%d - [1,%d] => ", n, n);
		for (Integer integer : lexicalOrder(n)) {
			System.out.printf("%d,", integer);
		}
	}
	
	/**
	 * 1-[1,1] =>                    1
	 * 2-[1,2] =>                    1,2
	 * 3-[1,3] =>                    1,2,3
	 * .
	 * .
	 * .
	 * 9-[1,9] =>                    1,2,3,.....9
	 * 10-[1,10] =>                  1,10,2,3,.....9
	 * 19-[1,19] =>                  1,10,11,12......19,2,3,.....9
	 * 29-[1,29] =>                  1,10,11,12......19,2,20,21,22,......29,3,4,5......9
	 * 39-[1,39] =>                  1,10,11,12......19,2,20,21,22,......29,3,31,32,33,34.....39,4,5......9
	 * 99-[1,99] =>                  1,10,11,12......19,2,20,21,22,......29,3,31,32,33,34.....39,4,40,41...49
	 * <p></p>                       5,50,51,......59,6,60,61,62,.....69,7,70,.....79,8,80.....89,9,90......99
	 * 199-[1,199] =>                1,10,100,101....109,11,110,111,112,113.....119,12,120,121......129,13,130,131.....139,14,140,141......149,
	 * <p></p>                       15,150,....159,16,160,....169,170,...179,18,180,.....189,19,190,191,...199
	 * <p></p>                       2,20,21,22,......29,3,31,32,33,34.....39,4,40,41...49,5,50,51,......59,6,60,61,62,.....69,
	 * <p></p>                       7,70,.....79,8,80.....89,9,90......99
	 * <p>
	 * <p>
	 * 当前n除以10得到的数 m为需要为 i补充字典序0-9得次数   在数字从0递增添加到结果的过程中如果遇到[1->m]中的数 在后面补上 i*10->i*10+9
	 */
	private static int count = 0;
	private static int m = 0;
	private static int insertRange = 0;
	private static List<Integer> result = null;
	
	private static List<Integer> lexicalOrder(int n) {
		result = new ArrayList<>();
		insertRange = n / 10;
		count = n;
		m = n;
		lexicalOrder2(0, n);
		return result;
	}
	
	private static void lexicalOrder2(int curNum, int curMax) {
		while (curNum <= m && curNum <= curMax && count > 0) {
			if (insertRange >= curNum) {
				int insertStart = curNum * 10;
				if (curNum > 0) {
					result.add(curNum);
					count--;
				} else {
					insertStart = curNum * 10 + 1;
				}
				lexicalOrder2(insertStart, curNum * 10 + 9);
			} else {
				result.add(curNum);
				count--;
			}
			curNum++;
		}
	}
	
}
