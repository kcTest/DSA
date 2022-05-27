package com.zkc;

/**
 * 一个岛屿是由一些相邻的1(代表土地) 构成的组合，
 * 这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。
 * 你可以假设grid 的四个边缘都被 0（代表水）包围着。
 * 找到给定的二维数组中最大的岛屿面积。如果没有岛屿，则返回面积为 0
 */
public class MaxAreaOfIsland {
	
	public static void main(String[] args) {
		int[][] table = new int[][]{
				new int[]{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
				new int[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
				new int[]{0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[]{0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
				new int[]{0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
				new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
				new int[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
				new int[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
		};
		System.out.println(maxAreaOfIsland(table));
	}
	
	private static int curMax = 0;
	
	private static int maxAreaOfIsland(int[][] table) {
		int max = 0;
		int rowLen = table.length;
		int colLen = table[0].length;
		for (int i = 0; i < rowLen; i++) {
			for (int j = 0; j < colLen; j++) {
				bfs(i, j, rowLen, colLen, table);
				if (curMax > max) {
					max = curMax;
				}
				curMax = 0;
			}
		}
		return max;
	}
	
	private static void bfs(int rowIdx, int colIdx, int rowLen, int colLen, int[][] table) {
		if (rowIdx < 0 || rowIdx >= rowLen || colIdx < 0 || colIdx >= colLen || table[rowIdx][colIdx] != 1) {
			return;
		}
		curMax++;
		table[rowIdx][colIdx] = 0;
		bfs(rowIdx - 1, colIdx, rowLen, colLen, table);
		bfs(rowIdx, colIdx + 1, rowLen, colLen, table);
		bfs(rowIdx + 1, colIdx, rowLen, colLen, table);
		bfs(rowIdx, colIdx - 1, rowLen, colLen, table);
	}
}
