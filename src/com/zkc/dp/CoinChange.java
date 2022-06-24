package com.zkc.dp;

import com.zkc.utils.MyUtils;

import java.util.*;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回-1。 你可以认为每种硬币的数量是无限的。
 */
public class CoinChange {
	public static void main(String[] args) {
//		int[] coins = MyUtils.getArray(4, 50, true);
		int[] coins = new int[]{30, 11, 7, 3};
		MyUtils.printArr(coins);
//		int sum = (int) (Math.random() * 100) + 1;
		int sum = 8633;
		System.out.printf("target: %d\n", sum);
		System.out.printf("ret: %d\n", coinChange(coins, sum));
	}
	
	private static int coinChange(int[] coins, int target) {
		if (target == 0) {
			return 0;
		}
		Arrays.sort(coins);
		for (int i = 0, j = coins.length - 1; i < j; i++, j--) {
			int temp = coins[i];
			coins[i] = coins[j];
			coins[j] = temp;
		}
		//纵向坐标代表数组索引  从大到小排序。 横向代表拼凑的数值0-target
		//每个单元格代表从数组中取任意数量的数（数组中索引值小于当前单元格的纵坐标的都可以取）拼凑成 对应当前单元格的横坐标的数 最少需要的个数
		int[][] records = new int[coins.length][target + 1];
		// 第一列统一为0 因为无法拼凑成   每个单元格依赖上一行数据  先处理第一行  
		for (int j = 0; j <= target; j++) {
			//能整除取商 否则-1代表不能组成纵坐标代表的数
			records[0][j] = j % coins[0] == 0 ? j / coins[0] : -1;
		}
		//从第二行开始 从后往前
		for (int i = 1; i < coins.length; i++) {
			for (int j = target; j > 0; j--) {
				//最后一行只取处理最后一个单元格
				if (i == coins.length - 1 && j < target) {
					break;
				}
				//当前单元格同一列上一行的单元格的值  先统一给当前单元格
				int overHeadVal = records[i - 1][j];
				records[i][j] = overHeadVal;
				if (j < coins[i]) {
					//每行的开始的几个位置(用纵坐标从数组里的取数  得到一个值  当前行横坐标小于这个值的单元格都无法拼凑成这个值 设置为-1)
					continue;
				}
				//往前检查 每次跳的步数为 当前行纵坐标在数组中对应的数值
				int backStep = 1;
				//默认跳到头
				while (j - coins[i] * backStep >= 0) {
					//上一行该位置的值如果大于0  往前跳的步数不能大于这个值 此时可以停止 否则再往前跳所需个数会越来越大
					if (overHeadVal > 0 && backStep > overHeadVal) {
						break;
					}
					//回跳位置
					int backwardIdx = j - coins[i] * backStep;
					//回跳位置的值
					int backwardVal = records[i - 1][backwardIdx];
					//遇到遇到非负的值 用当前值加上步数为一种可能的组合 
					if (backwardVal >= 0) {
						//如果此时已经得到的一个大于等于0的值 需要比较大小
						if (records[i][j] >= 0) {
							records[i][j] = Math.min(backStep + backwardVal, records[i][j]);
						} else {
							//如果开始时是无效值 直接赋值
							records[i][j] = backStep + backwardVal;
						}
					}
					backStep++;
				}
			}
		}
		//目标位置
		return records[coins.length - 1][target];
	}
	
}
