package com.zkc;

import com.zkc.utils.MyUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回-1。 你可以认为每种硬币的数量是无限的。
 */
public class CoinChange {
	public static void main(String[] args) {
//		int[] coins = MyUtils.getArray(4, 50, true);
		int[] coins = new int[]{411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422};
//		int[] coins = new int[]{1, 2, 5};
//		int[] coins = new int[]{3, 11, 7, 30};
		MyUtils.printArr(coins);
//		int sum = (int) (Math.random() * 100) + 1;
		int sum = 9864;
//		int sum = 11;
//		int sum = 36;
		System.out.printf("target: %d\n", sum);
		System.out.printf("ret: %d\n", coinChange1(coins, sum));
	}
	
	private static int coinChange1(int[] coins, int target) {
		if (target == 0) {
			return 0;
		}
		Arrays.sort(coins);
		for (int i = 0, j = coins.length - 1; i < j; i++, j--) {
			int temp = coins[i];
			coins[i] = coins[j];
			coins[j] = temp;
		}
		int[][] records = new int[coins.length][target + 1];
		for (int j = 0; j <= target; j++) {
			records[0][j] = j % coins[0] == 0 ? j / coins[0] : -1;
		}
		for (int i = 1; i < coins.length; i++) {
			for (int j = 1; j <= target; j++) {
				int overVal=records[i - 1][j];
				records[i][j] =overVal;
				if (j < coins[i]) {
					continue;
				}
				int backStep = 1;
				while (j - coins[i] * backStep >= 0) {
					if (overVal > 0 && backStep > overVal) {
						break;
					}
					int prevIdx = j - coins[i] * backStep;
					int prevIdxVal = records[i - 1][prevIdx];
					if (prevIdxVal >= 0) {
						if (records[i][j] >= 0) {
							records[i][j] = Math.min(backStep + prevIdxVal, records[i][j]);
						} else {
							records[i][j] = backStep + prevIdxVal;
						}
					}
					backStep++;
				}
			}
		}
		return records[coins.length - 1][target];
	}
	
}
