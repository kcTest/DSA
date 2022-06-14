package com.zkc;

/**
 * 找出边界全部由 1 组成的最大 正方形 子网格，并返回该子网格中的元素数量
 */
public class OneBorderedSquare {
	public static void main(String[] args) {
		int[][] matrix = new int[6][8];
		matrix[0] = new int[]{0, 1, 1, 1, 1, 1, 1, 0};
		matrix[1] = new int[]{1, 1, 1, 1, 1, 1, 1, 1};
		matrix[2] = new int[]{1, 0, 1, 1, 1, 0, 1, 1};
		matrix[3] = new int[]{1, 1, 1, 1, 0, 1, 1, 1};
		matrix[4] = new int[]{1, 0, 1, 0, 0, 1, 1, 1};
		matrix[5] = new int[]{0, 1, 1, 1, 1, 0, 1, 1};
		System.out.println(sln1(matrix));
	}
	
	/**
	 * O(n^3)
	 * 普通遍历需要 O(n^4)，使用辅助数组 每个位置右侧及下侧1的个数 把普通遍历中来到每个位置检查时需要的O(n^2)变成O(1)。
	 * 也可以记录左侧或者上侧
	 */
	private static int sln1(int[][] m) {
		if (m == null || m.length == 0) {
			return 0;
		}
		//辅助数组 记录每个位置右侧（包括自身）有几个1。
		int[][] right = new int[m.length][m[0].length];
		for (int i = 0; i < m.length; i++) {
			//从右向左
			for (int j = m[i].length - 1; j >= 0; j--) {
				if (m[i][j] == 1) {
					//如果不越界直接在前一个位置的连续1的个数的基础上加一
					right[i][j] = j + 1 < m[i].length ? 1 + right[i][j + 1] : 1;
				}
			}
		}
		//辅助数组 记录每个位置下侧（包括自身）有几个1
		int[][] down = new int[m.length][m[0].length];
		for (int i = m.length - 1; i >= 0; i--) {
			//从下向上
			for (int j = 0; j < m[i].length; j++) {
				if (m[i][j] == 1) {
					//如果不越界直接在前一个位置的连续1的个数的基础上加一
					down[i][j] = i + 1 < m.length ? 1 + down[i + 1][j] : 1;
				}
			}
		}
		int max = 0;
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				if (right[i][j] > 0 && down[i][j] > 0) {
					//最小者作为可能存在的正方形的最大边长 逐渐缩小范围检查 四个定点 下方和右方连续1的长度是否都大于等于当前选定边长
					int sideLen = Math.min(right[i][j], down[i][j]);
					//sideLen只是当前点作为左上顶点时  正方形上边和左边的长度，右边和下边长可能更小 需要逐渐缩小边长检查 
					for (int curLen = sideLen; curLen > 0; curLen--) {
						if (down[i][j + curLen - 1] >= curLen && right[i + curLen - 1][j] >= curLen) {
							if (curLen * curLen > max) {
								max = curLen * curLen;
							}
						}
					}
				}
			}
		}
		return max;
	}
}
