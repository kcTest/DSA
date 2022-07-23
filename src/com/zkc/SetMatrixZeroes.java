package com.zkc;

import com.zkc.utils.MyUtils;


public class SetMatrixZeroes {
	public static void main(String[] args) {
//		int[][] matrix = MyUtils.get2DArray(6, 9, 5);
		int[][] matrix = new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
		MyUtils.print2DArray(matrix);
		setZeroes(matrix);
		MyUtils.print2DArray(matrix);
	}
	
	/**
	 * Given an m x n integer matrix <i>matrix</i> if an element is 0, set its entire row and column to 0's.
	 * <p>
	 * You must do it in place.
	 * <p>
	 * A straightforward solution using O(mn) space is probably a bad idea.
	 * A simple improvement uses O(m + n) space, but still not the best solution.
	 * Could you devise a constant space solution?
	 */
	private static void setZeroes(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		int[] cols = new int[n];
		for (int i = 0; i < m; i++) {
			boolean change = false;
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 0) {
					//记录所有0的列 先只改整行 不覆盖下面行的0
					cols[j] = 1;
					change = true;
				}
			}
			//只改当前行
			if (change) {
				for (int j = 0; j < n; j++) {
					matrix[i][j] = 0;
				}
			}
		}
		//再改列
		for (int j = 0; j < n; j++) {
			if (cols[j] == 1) {
				for (int i = 0; i < m; i++) {
					matrix[i][j] = 0;
				}
			}
		}
	}
}
