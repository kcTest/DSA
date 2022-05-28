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
					//记录最大值
					max = curMax;
				}
				//找到岛或没有找到 均重置 
				curMax = 0;
			}
		}
		return max;
	}
	
	/**
	 * @param rowIdx 当前单元格行索引
	 * @param colIdx 当前单元格列索引
	 * @param rowLen 二维数组总行数
	 * @param colLen 二维数组总列数
	 * @param table  二维数组
	 */
	private static void bfs(int rowIdx, int colIdx, int rowLen, int colLen, int[][] table) {
		//是否越界  当前单元格是否为1
		if (rowIdx < 0 || rowIdx >= rowLen || colIdx < 0 || colIdx >= colLen || table[rowIdx][colIdx] != 1) {
			return;
		}
		//找到一个1计数
		curMax++;
		//修改数据源  当前位置改为非1 标识已经检查过 后续再遍历到该单元格会跳过
		table[rowIdx][colIdx] = 0;
		//继续向当前单元格相邻的上右下左四个方向去遍历
		bfs(rowIdx - 1, colIdx, rowLen, colLen, table);
		bfs(rowIdx, colIdx + 1, rowLen, colLen, table);
		bfs(rowIdx + 1, colIdx, rowLen, colLen, table);
		bfs(rowIdx, colIdx - 1, rowLen, colLen, table);
	}
}
