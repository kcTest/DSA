package com.zkc;

import java.util.*;

public class EightQueensPuzzle {
	
	public static void main(String[] args) {
//		int dim=(int) (Math.random() * 9);
		int dim = 4;
		List<List<String>> result = solveEightQueue(dim);
		StringBuilder sb = new StringBuilder();
		sb.append("[\n");
		for (int j = 0; j < result.size(); j++) {
			sb.append("[");
			List<String> rowStrLst = result.get(j);
			for (int i = 0; i < rowStrLst.size(); i++) {
				String str = rowStrLst.get(i);
				sb.append(str);
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
			HashSet<RowCell> row = new HashSet<>();
			for (int j = 0; j < dim; j++) {
				RowCell rowCell = new RowCell(i, j);
				rowArr[i][j] = rowCell;
				row.add(rowCell);
			}
		}
		Set<RowCell> invalidCells = new HashSet<>();
		Map<Integer, RowCell> validCellPerRowMap = new HashMap<>();
		solveEightQueueSub(result, rowArr, 0, dim, validCellPerRowMap, invalidCells);
		return result;
	}
	
	
	private static void solveEightQueueSub(List<List<String>> result, RowCell[][] rowArr, int curRowIdx, int dim, Map<Integer, RowCell> validCellPerRowMap, Set<RowCell> invalidCells) {
		if (curRowIdx == dim) {
			List<String> rowStrLst = new ArrayList<>();
			for (Map.Entry<Integer, RowCell> entry : validCellPerRowMap.entrySet()) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < dim; i++) {
					if (i == entry.getValue().colIdx) {
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
			int rowIdx = curRowCell.rowIdx;
			for (int i = rowIdx; i < dim; i++) {
				if (validCellPerRowMap.containsKey(i)) {
					Set<RowCell> crossCellSet = validCellPerRowMap.get(i).crossCellSet;
					validCellPerRowMap.remove(i);
					for (RowCell rowCell : crossCellSet) {
						invalidCells.remove(rowCell);
						rowCell.valid = true;
					}
					crossCellSet.clear();
				}
			}
			if (!curRowCell.valid) {
				continue;
			}
			Set<RowCell> crossedCellSet = getCrossedCell(curRowCell, dim, rowArr, invalidCells);
			curRowCell.crossCellSet = crossedCellSet;
			for (RowCell rowCell : crossedCellSet) {
				rowCell.valid = false;
			}
			validCellPerRowMap.put(rowIdx, curRowCell);
			solveEightQueueSub(result, rowArr, curRowIdx + 1, dim, validCellPerRowMap, invalidCells);
		}
	}
	
	private static Set<RowCell> getCrossedCell(RowCell cell, int dim, RowCell[][] rowArr, Set<RowCell> invalidCells) {
		Set<RowCell> linkedCell = new HashSet<>();
		int rowIdx = cell.rowIdx;
		int colIdx = cell.colIdx;
		for (int i = 0; i < dim; i++) {
			RowCell sameRow = rowArr[rowIdx][i];
			if (sameRow != cell && !invalidCells.contains(sameRow)) {
				linkedCell.add(sameRow);
				invalidCells.add(sameRow);
			}
			RowCell sameCol = rowArr[i][colIdx];
			if (sameCol != cell && !invalidCells.contains(sameCol)) {
				linkedCell.add(sameCol);
				invalidCells.add(sameCol);
			}
			if (rowIdx + i >= 0 && rowIdx + i < dim && colIdx + i >= 0 && colIdx + i < dim) {
				RowCell diagonalBottomRight = rowArr[rowIdx + i][colIdx + i];
				if (diagonalBottomRight != cell && !invalidCells.contains(diagonalBottomRight)) {
					linkedCell.add(diagonalBottomRight);
					invalidCells.add(diagonalBottomRight);
				}
			}
			if (rowIdx - i >= 0 && rowIdx - i < dim && colIdx - i >= 0 && colIdx - i < dim) {
				RowCell diagonalTopLeft = rowArr[rowIdx - i][colIdx - i];
				if (diagonalTopLeft != cell && !invalidCells.contains(diagonalTopLeft)) {
					linkedCell.add(diagonalTopLeft);
					invalidCells.add(diagonalTopLeft);
				}
			}
			if (rowIdx - i >= 0 && rowIdx - i < dim && colIdx + i >= 0 && colIdx + i < dim) {
				RowCell diagonalTopRight = rowArr[rowIdx - i][colIdx + i];
				if (diagonalTopRight != cell && !invalidCells.contains(diagonalTopRight)) {
					linkedCell.add(diagonalTopRight);
					invalidCells.add(diagonalTopRight);
				}
			}
			if (rowIdx + i >= 0 && rowIdx + i < dim && colIdx - i >= 0 && colIdx - i < dim) {
				RowCell diagonalBottomLeft = rowArr[rowIdx + i][colIdx - i];
				if (diagonalBottomLeft != cell && !invalidCells.contains(diagonalBottomLeft)) {
					linkedCell.add(diagonalBottomLeft);
					invalidCells.add(diagonalBottomLeft);
					
				}
			}
		}
		
		return linkedCell;
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
