package com.zkc;

import com.zkc.utils.MyUtils;

/**
 * According to Wikipedia's article: "The Game of Life, also known simply as Life,
 * is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 * <p>
 * The board is made up of an m x n grid of cells, where each cell has an initial state:
 * live (represented by a 1) or dead (represented by a 0).
 * Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 * <p>
 * Any live cell with fewer than two live neighbors dies as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population.
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously. Given the current state of the m x n grid board, return the next state.
 * <p>
 * Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
 * Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
 * <p>
 * Constraints:
 * <p>
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 25
 * board[i][j] is 0 or 1.
 * <p>
 * <p>
 * Follow up:
 * <p>
 * Could you solve it in-place? Remember that the board needs to be updated simultaneously:
 * You cannot update some cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the board is infinite,
 * which would cause problems when the active area encroaches upon the border of the array (i.e., live cells reach the border).
 * How would you address these problems?
 */
public class GameOfLife {
	public static void main(String[] args) {
		int[][] board = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
		for (int[] cellRow : board) {
			MyUtils.printArr(cellRow);
		}
		System.out.println();
		gameOfLife(board);
		for (int[] cellRow : board) {
			MyUtils.printArr(cellRow);
		}
	}
	
	/**
	 * 原地更新  当前位置如何修改取决于八个位置，一个位置不能利用另一个位置修改后的状态 来修改自己的状态，
	 * 需要保持每个位置信息不变 先决定每个位置如何变化 最后统一修改。
	 * 把当前位置变化后的状态放入二进制形式右侧的第二位。收集位置信息时只读取最右侧第一位 最后再将最右侧第一位的值改成第二位的值，第二位清除。
	 */
	private static void gameOfLife(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				boolean live = board[i][j] == 1;
				int ones = collectOnes(board, i, j);
				if (live && (ones == 2 || ones == 3) || !live && (ones == 3)) {
					board[i][j] = 2 | (board[i][j] & 1);
				}
			}
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = board[i][j] >> 1;
			}
		}
	}
	
	private static int collectOnes(int[][] board, int row, int col) {
		//左上 上 右上
		//左     右
		//左下 下 右下
		return readCell(board, row - 1, col - 1) + readCell(board, row - 1, col) + readCell(board, row - 1, col + 1)
				+ readCell(board, row, col - 1) + readCell(board, row, col + 1)
				+ readCell(board, row + 1, col - 1) + readCell(board, row + 1, col) + readCell(board, row + 1, col + 1);
	}
	
	private static int readCell(int[][] board, int row, int col) {
		return row >= 0 && row < board.length && col >= 0 && col < board[row].length && (board[row][col] & 1) == 1 ? 1 : 0;
	}
}
