package com.zkc;

import java.util.*;

public class EightQueensPuzzle {
	
	public static void main(String[] args) {
		int dim = 4;
		List<List<String>> result = solveEightQueue(dim);
		StringBuilder sb = new StringBuilder();
		sb.append("[\n");
		for (List<String> strings : result) {
			sb.append("[");
			for (String str : strings) {
				sb.append(str).append(" ");
			}
			sb.append("]\n");
		}
		sb.append("]");
		System.out.print(sb);
	}
	
	private static List<List<String>> solveEightQueue(int dim) {
		RowCell[][] rowArr = new RowCell[dim][dim];
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				RowCell rowCell = new RowCell(i, j);
				rowArr[i][j] = rowCell;
			}
		}
		RowCell[] validCellPerRow = new RowCell[dim];
		List<List<String>> ret = new ArrayList<>();
		solveEightQueueSub(ret, rowArr, 0, validCellPerRow);
		return ret;
	}
	
	private static void solveEightQueueSub(List<List<String>> ret, RowCell[][] rowArr, int curRowIdx, RowCell[] validCellPerRow) {
		if (curRowIdx == rowArr.length) {
			List<String> rowStrLst = new ArrayList<>();
			for (RowCell cell : validCellPerRow) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < rowArr.length; i++) {
					sb.append(i == cell.colIdx ? "Q" : ".");
				}
				rowStrLst.add(sb.toString());
			}
			ret.add(rowStrLst);
			return;
		}
		for (RowCell curRowCell : rowArr[curRowIdx]) {
			for (int i = curRowCell.rowIdx; i < rowArr.length; i++) {
				if (validCellPerRow[i] == null) {
					break;
				}
				Queue<RowCell> crossCellSet = validCellPerRow[i].invalidCellQ;
				validCellPerRow[i] = null;
				while (crossCellSet.size() > 0) {
					crossCellSet.poll().valid = true;
				}
			}
			if (curRowCell.valid) {
				handleCrossedCell(curRowCell, rowArr);
				validCellPerRow[curRowCell.rowIdx] = curRowCell;
				solveEightQueueSub(ret, rowArr, curRowIdx + 1, validCellPerRow);
			}
		}
	}
	
	private static void handleCrossedCell(RowCell curRowCell, RowCell[][] rowArr) {
		Queue<RowCell> invalidCellQ = new LinkedList<>();
		for (int i = 0; i < rowArr.length; i++) {
			int nextRowIdx = curRowCell.rowIdx + i;
			if (nextRowIdx < rowArr.length) {
				int nextColIdx = curRowCell.colIdx + i;
				if (nextColIdx < rowArr.length) {
					RowCell diagonalBottomRight = rowArr[nextRowIdx][nextColIdx];
					if (diagonalBottomRight.valid && diagonalBottomRight != curRowCell) {
						diagonalBottomRight.valid = false;
						invalidCellQ.add(diagonalBottomRight);
					}
				}
				int prevColIdx = curRowCell.colIdx - i;
				if (prevColIdx >= 0) {
					RowCell diagonalBottomLeft = rowArr[nextRowIdx][prevColIdx];
					if (diagonalBottomLeft.valid && diagonalBottomLeft != curRowCell) {
						diagonalBottomLeft.valid = false;
						invalidCellQ.add(diagonalBottomLeft);
					}
				}
			}
			if (i > curRowCell.rowIdx) {
				RowCell sameCol = rowArr[i][curRowCell.colIdx];
				if (sameCol.valid && sameCol != curRowCell) {
					sameCol.valid = false;
					invalidCellQ.add(sameCol);
				}
			}
		}
		curRowCell.invalidCellQ = invalidCellQ;
	}
	
	private static class RowCell {
		public int rowIdx;
		public int colIdx;
		public Queue<RowCell> invalidCellQ;
		public boolean valid;
		
		public RowCell(int rowNo, int colNo) {
			this.rowIdx = rowNo;
			this.colIdx = colNo;
			invalidCellQ = new LinkedList<>();
			valid = true;
		}
	}
	
}
