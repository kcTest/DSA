package com.zkc.dp;

import com.zkc.utils.MyUtils;

import java.util.Arrays;

public class MinCoins {
	public static void main(String[] args) {
		int[] coins = MyUtils.getArray(7, 15, true);
		MyUtils.printArr(coins);
		int sum = (int) (Math.random() * 40) + 1;
		System.out.printf("combination:%d\n", sum);
		System.out.println(minCoins1(coins, sum));
		System.out.println(minCoins2(coins, sum));
		System.out.println(minCoins3(coins, sum));
	}
	
	private static int minCoins3(int[] coins, int sum) {
		//idx可能走到coins.length  rest最大为sum 也可能小于0
		int[][] record = new int[coins.length + 1][sum + 1];
		//标识初始每种情况均未处理过
		for (int[] r : record) {
			Arrays.fill(r, -2);
		}
		return sln3(coins, 0, sum, record);
	}
	
	/**
	 * @param record 缓存指定位置和可选数值的情况下能选取的硬币数量
	 */
	private static int sln3(int[] coins, int idx, int rest, int[][] record) {
		//小于0的位置  不能直接使用record判断，直接返回 等同于小于0record中记录的是-1
		if (rest < 0) {
			return -1;
		}
		//说明可以从缓存中直接获取
		if (record[idx][rest] != -2) {
			return record[idx][rest];
		}
		if (rest == 0) {
			//返回前记录当前情况的结果
			record[idx][rest] = 0;
			return 0;
		}
		if (idx == coins.length) {
			//返回前记录当前情况的结果
			record[idx][rest] = -1;
			return -1;
		}
		int deselect = sln3(coins, idx + 1, rest, record);
		int select = sln3(coins, idx + 1, rest - coins[idx], record);
		if (deselect == -1 && select == -1) {
			//返回前记录当前情况的结果
			record[idx][rest] = -1;
		} else if (deselect == -1) {
			//返回前记录当前情况的结果
			record[idx][rest] = select + 1;
		} else if (select == -1) {
			//返回前记录当前情况的结果
			record[idx][rest] = deselect;
		} else {
			//返回前记录当前情况的结果
			record[idx][rest] = Math.min(deselect, select + 1);
		}
		return record[idx][rest];
	}
	
	private static int minCoins2(int[] coins, int sum) {
		//初始从0开始 剩余待选值为sum
		return sln2(coins, 0, sum);
	}
	
	/**
	 * @param coins 硬币数组
	 * @param idx   当前位置
	 * @param rest  剩余待选数值
	 * @return 已选硬币数量
	 */
	private static int sln2(int[] coins, int idx, int rest) {
		//说明将上次遇到的硬币统计在内时 累积总值已经大于目标总值  不能继续选 路径无效   2 5 4 选3  
		if (rest < 0) {
			return -1;
		}
		//没有剩余硬币值  说明之前选取的硬币总值时正好和目标总值相等  本次没有选取
		if (rest == 0) {
			return 0;
		}
		//末尾的下一次  还没有找到可以组成目标值的硬币 不能继续 路径无效
		if (idx == coins.length) {
			return -1;
		}
		//来到当前位置 不选取当前硬币值的情况 rest不变 位置加
		int deselect = sln2(coins, idx + 1, rest);
		//来到当前位置 选取当前硬币值的情况 rest减少 位置加 
		int select = sln2(coins, idx + 1, rest - coins[idx]);
		if (deselect == -1 && select == -1) {
			//俩个路径都无效
			return -1;
		} else if (deselect == -1) {
			//选取的情况已选取硬币数量值加1
			return select + 1;
		} else if (select == -1) {
			//不选情况已选取硬币数量值不变
			return deselect;
		} else {
			//俩个情况都能选取到目标值  返回其中选取硬币数量最小的
			return Math.min(deselect, select + 1);
		}
	}
	
	
	private static int min = -1;
	
	private static int minCoins1(int[] coins, int sum) {
		for (int i = 0; i < coins.length; i++) {
			sln1(coins, 0, i, coins[i], sum);
		}
		return min;
	}
	
	/**
	 * @param coins   硬币数组
	 * @param coinNum 记录当前路径选取的硬币数量
	 * @param curPos  当前位置
	 * @param total   当前路径硬币总值
	 * @param sum     目标总和
	 *                <p>
	 *                从左往右 先选定一个位置  再依次选定后续位置  当前累积总和不大于目标数值 继续选定下一个位置
	 */
	private static void sln1(int[] coins, int coinNum, int curPos, int total, int sum) {
		if (total > sum) {
			return;
		}
		coinNum++;
		if (total == sum) {
			if (coinNum < min || min == -1) {
				min = coinNum;
			}
			return;
		}
		for (int i = curPos + 1; i < coins.length; i++) {
			sln1(coins, coinNum, i, total + coins[i], sum);
		}
	}
}
