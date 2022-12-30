package com.zkc;

import com.zkc.utils.MyUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给你一个大小为 n x n 二进制矩阵 grid 。最多 只能将一格0 变成1 。
 * <p>
 * 返回执行此操作后，grid 中最大的岛屿面积是多少？
 * <p>
 * 岛屿 由一组上、下、左、右四个方向相连的1 形成。
 * <p>
 *
 * <p>
 * 示例 1:
 * <p>
 * 输入: grid = [[1, 0], [0, 1]]
 * 输出: 3
 * 解释: 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。
 */
public class MakingALargeIsland {
	
	public static void main(String[] args) {
		int[][] grid = new int[][]{
				{1, 0, 1, 1},
				{1, 0, 0, 0},
				{0, 0, 1, 0},
				{0, 0, 0, 1}
		};
		MyUtils.print2DArray(grid);
		System.out.println(largestIsland(grid));
	}
	
	private static int largestIsland(int[][] grid) {
		int n = grid.length, m = grid[0].length;
		Map<Integer, Integer> areaMap = new HashMap<>();
		int islandSeq = 2;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 1) {
					//确认每片岛屿的面积并标记记录
					int area = searchIsland(grid, i, j, islandSeq);
					areaMap.put(islandSeq++, area);
				}
			}
		}
//		for (Map.Entry<Integer, Integer> entry : areaMap.entrySet()) {
//			System.out.println(entry.getKey() + "---" + entry.getValue());
//		}
		int max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				//检查每个0点四个方向 是否连接其它岛屿 相连的面积累加 
				if (grid[i][j] == 0) {
					int cur = 1;
					//遇到过的岛屿不再计入面积
					Set<Integer> set = new HashSet<>();
					if (j - 1 >= 0 && grid[i][j - 1] != 0) {
						cur += areaMap.get(grid[i][j - 1]);
						set.add(grid[i][j - 1]);
					}
					if (j + 1 < grid[0].length && grid[i][j + 1] != 0 && !set.contains(grid[i][j + 1])) {
						cur += areaMap.get(grid[i][j + 1]);
						set.add(grid[i][j + 1]);
					}
					if (i - 1 >= 0 && grid[i - 1][j] != 0 && !set.contains(grid[i - 1][j])) {
						cur += areaMap.get(grid[i - 1][j]);
						set.add(grid[i - 1][j]);
					}
					if (i + 1 < grid.length && grid[i + 1][j] != 0 && !set.contains(grid[i + 1][j])) {
						cur += areaMap.get(grid[i + 1][j]);
					}
					max = Math.max(max, cur);
				}
			}
		}
		//没有0 全是1
		return max == 0 ? grid.length * grid[0].length : max;
	}
	
	private static int searchIsland(int[][] grid, int i, int j, int islandSeq) {
		if (i < 0 || i == grid.length || j < 0 || j == grid[0].length || grid[i][j] != 1) {
			return 0;
		}
		//标记 区分
		grid[i][j] = islandSeq;
		//扩散计算面积
		return 1 + searchIsland(grid, i, j + 1, islandSeq) +
				searchIsland(grid, i, j - 1, islandSeq) +
				searchIsland(grid, i - 1, j, islandSeq) +
				searchIsland(grid, i + 1, j, islandSeq);
	}
}
