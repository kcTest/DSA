package com.zkc;

import java.util.*;

public class EightQueensPuzzle {
	
	public static void main(String[] args) {
//		int dim=(int) (Math.random() * 9);
		int dim = 5;
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
		List<List<String>> result = new ArrayList<>();
		RowCell[][] rowArr = new RowCell[dim][dim];
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				RowCell rowCell = new RowCell(i, j);
				rowArr[i][j] = rowCell;
			}
		}
		Set<RowCell> invalidCells = new HashSet<>();
		List<RowCell> validCellPerRow = new ArrayList<>();
		solveEightQueueSub(result, rowArr, 0, validCellPerRow, invalidCells);
		return result;
	}
	
	
	private static void solveEightQueueSub(List<List<String>> result, RowCell[][] rowArr, int curRowIdx, List<RowCell> validCellPerRow, Set<RowCell> invalidCells) {
		if (curRowIdx == rowArr.length) {
			List<String> rowStrLst = new ArrayList<>();
			for (RowCell cell : validCellPerRow) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < rowArr.length; i++) {
					if (i == cell.colIdx) {
						sb.append("Q");
					} else {
						sb.append(".");
					}
				}
				rowStrLst.add(sb.toString());
			}
			result.add(rowStrLst);
			return;
		}
		for (RowCell curRowCell : rowArr[curRowIdx]) {
			for (int i = rowArr.length - 1; i >= curRowCell.rowIdx; i--) {
				if (validCellPerRow.size() > i) {
					Set<RowCell> crossCellSet = validCellPerRow.get(i).crossCellSet;
					validCellPerRow.remove(i);
					for (RowCell rowCell : crossCellSet) {
						invalidCells.remove(rowCell);
						rowCell.valid = true;
					}
				}
			}
			if (!curRowCell.valid) {
				continue;
			}
			handleCrossedCell(curRowCell, rowArr, invalidCells);
			validCellPerRow.add(curRowCell.rowIdx, curRowCell);
			solveEightQueueSub(result, rowArr, curRowIdx + 1, validCellPerRow, invalidCells);
		}
	}
	
	private static void handleCrossedCell(RowCell curRowCell, RowCell[][] rowArr, Set<RowCell> invalidCells) {
		Set<RowCell> linkedCell = new HashSet<>();
		for (int i = 0; i < rowArr.length; i++) {
			if (i > curRowCell.rowIdx) {
				RowCell sameCol = rowArr[i][curRowCell.colIdx];
				if (sameCol != curRowCell && !invalidCells.contains(sameCol)) {
					sameCol.valid = false;
					linkedCell.add(sameCol);
					invalidCells.add(sameCol);
				}
			}
			int nextRowIdx = curRowCell.rowIdx + i;
			if (nextRowIdx >= 0 && nextRowIdx < rowArr.length) {
				int nextColIdx = curRowCell.colIdx + i;
				if (nextColIdx >= 0 && nextColIdx < rowArr.length) {
					RowCell diagonalBottomRight = rowArr[nextRowIdx][nextColIdx];
					if (diagonalBottomRight != curRowCell && !invalidCells.contains(diagonalBottomRight)) {
						diagonalBottomRight.valid = false;
						linkedCell.add(diagonalBottomRight);
						invalidCells.add(diagonalBottomRight);
					}
				}
				int prevColIdx = curRowCell.colIdx - i;
				if (prevColIdx >= 0 && prevColIdx < rowArr.length) {
					RowCell diagonalBottomLeft = rowArr[nextRowIdx][prevColIdx];
					if (diagonalBottomLeft != curRowCell && !invalidCells.contains(diagonalBottomLeft)) {
						diagonalBottomLeft.valid = false;
						linkedCell.add(diagonalBottomLeft);
						invalidCells.add(diagonalBottomLeft);
						
					}
				}
			}
		}
		curRowCell.crossCellSet = linkedCell;
	}
	
	private static class RowCell {
		public int rowIdx;
		public int colIdx;
		public Set<RowCell> crossCellSet;
		public boolean valid;
		
		public RowCell(int rowNo, int colNo) {
			this.rowIdx = rowNo;
			this.colIdx = colNo;
			crossCellSet = new HashSet<>();
			valid = true;
		}
	}
	
}
