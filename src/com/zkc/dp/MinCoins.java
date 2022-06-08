package com.zkc.dp;

import com.zkc.utils.MyUtils;

import java.util.*;

public class MinCoins {
	public static void main(String[] args) {
		int[] coins = MyUtils.getArray(7, 15, true);
//		int[] coins = new int[]{6, 4, 14, 11, 9, 2, 13};
		MyUtils.printArr(coins);
		int sum = (int) (Math.random() * 40) + 1;
//		int sum = 35;
		System.out.printf("combination:%d\n", sum);
		System.out.println(minCoins1(coins, sum));
		System.out.println(minCoins2(coins, sum));
		System.out.println(minCoins3(coins, sum));
		System.out.println(minCoins4(coins, sum));
	}
	
	private static int minCoins4(int[] coins, int sum) {
		//行索引代表当前来到coins数组的位置  列索引代表剩余可选的硬币值
		int[][] record = new int[coins.length + 1][sum + 1];
		//第一列剩余可选硬币值为0  为0时不选择硬币 直接全默认为0
		//record.length - 1已经遍历完数组 还没有找到可以组成目标值的硬币 不能继续  最后一行直接设置无效
		for (int j = 1; j < record[0].length; j++) {
			record[record.length - 1][j] = -1;
		}
		//从倒数第二行第一列(从下到上 从左向右设置依赖，不能直接从上开始 下面的依赖还未设置好) 直到第一行
		for (int j = 1; j < record[0].length; j++) {
			for (int i = record.length - 2; i >= 0; i--) {
				//每个单元格依赖左边select 和 下一行的 deselect
				//不选择 剩余可选硬币值不变 跳到下一位
				int deselect = record[i + 1][j];
				//选择当前硬币 减去当前位置硬币的硬币值  可选值减少后再跳到下一位 列减少越界说明
				//将上次遇到的硬币统计在内时 累积总值已经大于目标总值  不能继续选 路径无效   如2 5 4 选3来到4  3-2-5 
				int select = (j - coins[i] > -1 ? record[i + 1][j - coins[i]] : -1);
				if (deselect == -1 && select == -1) {
					record[i][j] = -1;
				} else if (deselect == -1) {
					//当前硬币值被选择  后续的选择正好用完rest 有效路径 硬币值加1 
					record[i][j] = select + 1;
				} else if (select == -1) {
					//不选当前硬币 即使后面最终有效  不需要将当前硬币统计在内
					record[i][j] = deselect;
				} else {
					//取最小硬币值
					record[i][j] = Math.min(deselect, 1 + select);
				}
			}
		}
		//当前所求结果idx为从0开始rest为sum的单元格的结果
		return record[0][sum];
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
