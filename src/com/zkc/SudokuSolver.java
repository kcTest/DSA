package com.zkc;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * <p>
 * A sudoku solution must satisfy all of the following rules:
 * <p>
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * The '.' character indicates empty cells.
 * <p>
 * Constraints:
 * <p>
 * board.length == 9
 * board[i].length == 9
 * board[i][j] is a digit or '.'.
 * It is guaranteed that the input board has only one solution.
 */
public class SudokuSolver {
	public static void main(String[] args) {
		char[][] board = new char[][]{
				{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
				{'6', '.', '.', '1', '9', '5', '.', '.', '.'},
				{'.', '9', '8', '.', '.', '.', '.', '6', '.'},
				{'8', '.', '.', '.', '6', '.', '.', '.', '3'},
				{'4', '.', '.', '8', '.', '3', '.', '.', '1'},
				{'7', '.', '.', '.', '2', '.', '.', '.', '6'},
				{'.', '6', '.', '.', '.', '.', '2', '8', '.'},
				{'.', '.', '.', '4', '1', '9', '.', '.', '5'},
				{'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
		solveSudoku(board);
		for (int i = 0; i < 9; i++) {
			StringBuilder sb = new StringBuilder();
			for (char c : board[i]) {
				sb.append(c).append("  ");
			}
			System.out.println(sb.deleteCharAt(sb.length() - 2));
		}
	}
	
	private static void solveSudoku(char[][] board) {
		//每行是否出现数字1-9
		boolean[][] row = new boolean[9][10];
		//每列是否出现数字1-9
		boolean[][] col = new boolean[9][10];
		//每个3X3的盒子是否出现数字1-9
		boolean[][] box = new boolean[9][10];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int boxIdx = (i / 3) * 3 + j / 3;
				if (board[i][j] != '.') {
					int num = board[i][j] - 48;
					row[i][num] = true;
					col[j][num] = true;
					box[boxIdx][num] = true;
				}
			}
		}
		fillingCell(board, 0, 0, row, col, box);
	}
	
	private static boolean fillingCell(char[][] board, int i, int j, boolean[][] row, boolean[][] col, boolean[][] box) {
		if (i == 9 && j == 0) {
			return true;
		}
		int nextI = i, nextJ = j + 1;
		if (nextJ == 9) {
			nextI++;
			nextJ = 0;
		}
		if (board[i][j] != '.') {
			return fillingCell(board, nextI, nextJ, row, col, box);
		}
		for (int num = 1; num <= 9; num++) {
			int boxIdx = (i / 3) * 3 + j / 3;
			if (!row[i][num] && !col[j][num] && !box[boxIdx][num]) {
				board[i][j] = (char) (num + '0');
				row[i][num] = true;
				col[j][num] = true;
				box[boxIdx][num] = true;
				if (fillingCell(board, nextI, nextJ, row, col, box)) {
					return true;
				} else {
					board[i][j] = '.';
					row[i][num] = false;
					col[j][num] = false;
					box[boxIdx][num] = false;
				}
			}
		}
		return false;
	}
}
