package com.zkc.dp;

import com.zkc.utils.MyUtils;

/**
 * 俩人每次分别从数组左边或右边选一个最大的数  尽量使结束时自己选的值最大 最大获胜  返回获胜方挑选的总值
 */
public class WinnerScore {
	
	public static void main(String[] args) {
		int[] nums = MyUtils.getArray(15, 20);
//		int[] nums = new int[]{6, 9, 6};
		MyUtils.printArr(nums);
		System.out.printf("winnerScore1:%d\n", winnerScore1(nums));
		System.out.printf("winnerScore2:%d\n", winnerScore2(nums));
		System.out.printf("winnerScore3:%d\n", winnerScore3(nums));
	}
	
	private static int winnerScore3(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int len = nums.length;
		int[][][] record = new int[len][len][2];
		//左边界小于右边界的情况不需要考虑  只设置上半区域  每个单元格依赖其左边和下边的相邻单元格 所以需要保证每轮从左下往左上或者从右上往左下设置依赖值 保证依赖的单元格已经设置好
		for (int i = len - 1; i >= 0; i--) {
			//x位置每轮依次减小
			int x = i;
			//y位置每轮从最后开始
			int y = len - 1;
			// 依次设置右下往左上方向的斜线 从对角线到右上角最后一个单元格  总体每轮设置的单元格逐渐减少 
			for (int j = 0; j <= i; j++) {
				if (x == y) {
					record[x][y][1] = nums[x];
					record[x][y][0] = 0;
				} else {
					record[x][y][1] = Math.max(nums[x] + record[x + 1][y][0], nums[y] + record[x][y - 1][0]);
					record[x][y][0] = Math.min(record[x + 1][y][1], record[x][y - 1][1]);
				}
				x--;
				y--;
			}
		}
		//目标结果位置为（0，n-1)。取z轴所有层中该位置最大值
		return Math.max(record[0][nums.length - 1][0], record[0][nums.length - 1][1]);
	}
	
	private static int winnerScore2(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		//x轴代表左边界索引 范围0-n-1 y轴代表右边界索引范围0-n-1 z轴2层代表先选1、后选0的情况 
		int[][][] record = new int[nums.length][nums.length][2];
		for (int i = 0; i < record.length; i++) {
			for (int j = 0; j < record[0].length; j++) {
				//z轴每层每个位置默认未处理
				record[i][j][0] = -1;
				record[i][j][1] = -1;
			}
		}
		sln2(nums, 0, nums.length - 1, 1, record);
		sln2(nums, 0, nums.length - 1, 0, record);
		//目标结果位置为（0，n-1)。取z轴所有层中该位置最大值
		return Math.max(record[0][nums.length - 1][0], record[0][nums.length - 1][1]);
	}
	
	private static int sln2(int[] nums, int left, int right, int myTurn, int[][][] record) {
		if (record[left][right][myTurn] >= 0) {
			//已缓存过直接取
			return record[left][right][myTurn];
		}
		if (left == right) {
			//返回前缓存当前情况下的结果
			record[left][right][myTurn] = myTurn > 0 ? nums[left] : 0;
			return record[left][right][myTurn];
		}
		if (myTurn == 1) {
			//返回前缓存当前情况下的结果
			record[left][right][myTurn] = Math.max(nums[left] + sln2(nums, left + 1, right, 0, record),
					nums[right] + sln2(nums, left, right - 1, 0, record));
			return record[left][right][myTurn];
		} else {
			//返回前缓存当前情况下的结果
			record[left][right][myTurn] = Math.min(sln2(nums, left + 1, right, 1, record),
					sln2(nums, left, right - 1, 1, record));
			return record[left][right][myTurn];
		}
	}
	
	private static int winnerScore1(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		//刚开始先选的情况下  尽可能获得最大值
		int first = sln1(nums, 0, nums.length - 1, true);
		//刚开始后选的情况下  尽可能获得最大值
		int second = sln1(nums, 0, nums.length - 1, false);
		return Math.max(first, second);
	}
	
	private static int sln1(int[] nums, int left, int right, boolean myTurn) {
		if (left == right) {
			//只剩一个 可选 当前值计入
			return myTurn ? nums[left] : 0;
		}
		if (myTurn) {
			//可选的情况下 计入 当前的选择及后续路径 返回能得到较大值的路径
			return Math.max(nums[left] + sln1(nums, left + 1, right, false),
					nums[right] + sln1(nums, left, right - 1, false));
		} else {
			//轮到对手选的情况下 没有选择可以计入，计入返回后续路径能得到较小值的路径  因为对手的选择会尽量让我得到的返回值小
			return Math.min(sln1(nums, left + 1, right, true),
					sln1(nums, left, right - 1, true));
		}
	}
}
