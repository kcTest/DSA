package com.zkc;

import com.zkc.utils.MyUtils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * [0,1,2] [1,1,2] [2,1,2]表示一些石头
 * 数组0位置 0表示石头无色 可以修改颜色； 1表示石头是红色 不能再变色 ；2表示石头是蓝色 不能再变色。
 * 数组1位置表示无色石头变成红色代价
 * 数组2位置表示无色石头变成蓝色代价
 * 给定一组这样的数组，修改无色石头的颜色 使所有石头有色并且红色石头和蓝色石头的数量一样。
 * 如果能实现返回最小代价，如果不能返回-1
 */
public class MagicStone {
	public static void main(String[] args) {
		int[][] arr = new int[][]{{1, 3, 6}, {2, 5, 8}, {0, 3, 2}, {1, 9, 7}, {2, 1, 8}, {2, 2, 7}, {0, 3, 6}, {0, 3, 7}, {0, 7, 7}, {0, 9, 2}};
		for (int[] stones : arr) {
			MyUtils.printArr(stones);
		}
		System.out.println(minCost(arr));
	}
	
	/**
	 * 统计红色石头和蓝色石头的个数 如果有一种超过n/2,或者总数为奇数，无法使红蓝数量一致。
	 * 假设所有无色石头都先修改成红色 统计出总代价。此时再将一部分石头改成蓝色，代价变化cost-red+blue，
	 * 修改的石头如果red与blue差值越大 cost减少的越多，修改无色石头颜色的总代价越底。
	 */
	private static int minCost(int[][] stones) {
		int n = stones.length;
		//奇数无法达到数量均等
		if ((n & 1) != 0) {
			return -1;
		}
		//将石头排序，无色(颜色相同的按 红蓝差距排序)->蓝->红,方便统计
		Arrays.sort(stones, new StoneComparator());
		int colorless = 0;
		int red = 0;
		int blue = 0;
		int modifyCost = 0;
		for (int[] stone : stones) {
			if (stone[0] == 0) {
				colorless++;
				//先统一修改成红色的总代价
				modifyCost += stone[1];
			} else if (stone[0] == 1) {
				red++;
			} else if (stone[0] == 2) {
				blue++;
			}
		}
		//先判断是否有一种颜色超过一半
		if (red > (n >> 1) || blue > (n >> 1)) {
			return -1;
		}
		//均分 n/2-red为 是red达到一半时要修改为red的无色石头的个数，剩下为蓝色
		blue = colorless - ((n >> 1) - red);
		for (int i = 0; i < blue; i++) {
			//无色中部分由红色转蓝色 代价去掉红色再加蓝色
			modifyCost = modifyCost - stones[i][1] + stones[i][2];
		}
		return 0;
	}
	
	private static class StoneComparator implements Comparator<int[]> {
		@Override
		public int compare(int[] prev, int[] next) {
			return prev[0] == 0 && next[0] == 0 ? ((next[0] - next[1]) - (prev[0] - prev[1])) : prev[0] - next[1];
		}
	}
}
