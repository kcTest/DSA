package com.zkc;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 打印 N 皇后在 N × N 棋盘上的各种摆法，其中每个皇后都不同行、不同列，也不在对角线上
 */
public class EightQueensPuzzle {
	
	public static void main(String[] args) {
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
		System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS").format(new Date(System.currentTimeMillis())));
		//生成n*n的数组
		RowCell[][] rowArr = new RowCell[dim][dim];
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				RowCell rowCell = new RowCell(i, j);
				rowArr[i][j] = rowCell;
			}
		}
		//记录在每行选中的单元格
		RowCell[] validCellPerRow = new RowCell[dim];
		//记录结果
		List<List<String>> ret = new ArrayList<>();
		solveEightQueueSub(ret, rowArr, 0, validCellPerRow);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS").format(new Date(System.currentTimeMillis())));
		return ret;
	}
	
	private static void solveEightQueueSub(List<List<String>> ret, RowCell[][] rowArr, int curRowIdx, RowCell[] validCellPerRow) {
		if (curRowIdx == rowArr.length) {
			//最后一行处理完成后  生成字符串
			List<String> rowStrLst = new ArrayList<>();
			for (RowCell cell : validCellPerRow) {
				//每行生成一个字符串
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < rowArr.length; i++) {
					//默认打印* 如果遇到选中单元格的位置打印Q
					sb.append(i == cell.colIdx ? "Q" : ".");
				}
				rowStrLst.add(sb.toString());
			}
			ret.add(rowStrLst);
			return;
		}
		//获取当前行所有单元格 逐个判断
		for (RowCell curRowCell : rowArr[curRowIdx]) {
			//每次把当前行及之后的已选中单元格重置
			for (int i = curRowCell.rowIdx; i < rowArr.length; i++) {
				//从上到下  如果当前行 没有下面的也没有 不用再继续
				if (validCellPerRow[i] == null) {
					break;
				}
				Queue<RowCell> crossCellSet = validCellPerRow[i].invalidCellQ;
				validCellPerRow[i] = null;
				while (crossCellSet.size() > 0) {
					crossCellSet.poll().valid = true;
				}
			}
			//遇到有效的单元格 
			if (curRowCell.valid) {
				//选中当前单元格
				validCellPerRow[curRowCell.rowIdx] = curRowCell;
				//找出当前单元格选中后需要禁用的单元格
				handleCrossedCell(curRowCell, rowArr);
				//继续递归到下一行查找
				solveEightQueueSub(ret, rowArr, curRowIdx + 1, validCellPerRow);
			}
		}
	}
	
	private static void handleCrossedCell(RowCell curRowCell, RowCell[][] rowArr) {
		Queue<RowCell> invalidCellQ = new LinkedList<>();
		//找到需要被禁用的单元格  十字线 对角线  不考虑同行及向上的部分
		int colIdx = curRowCell.colIdx;
		for (int offSet = 1; offSet < rowArr.length - curRowCell.rowIdx; offSet++) {
			//正下
			int bottomY = curRowCell.rowIdx + offSet;
			RowCell cellBottom = rowArr[bottomY][colIdx];
			if (cellBottom.valid && cellBottom != curRowCell) {
				cellBottom.valid = false;
				invalidCellQ.add(cellBottom);
			}
			//对角线左下
			if (colIdx - offSet >= 0) {
				RowCell cellBottomLeft = rowArr[bottomY][colIdx - offSet];
				if (cellBottomLeft.valid && cellBottomLeft != curRowCell) {
					cellBottomLeft.valid = false;
					invalidCellQ.add(cellBottomLeft);
				}
			}
			//对角线右下
			if (colIdx + offSet < rowArr.length) {
				RowCell cellBottomRight = rowArr[bottomY][colIdx + offSet];
				if (cellBottomRight.valid && cellBottomRight != curRowCell) {
					cellBottomRight.valid = false;
					invalidCellQ.add(cellBottomRight);
				}
			}
		}
		curRowCell.invalidCellQ = invalidCellQ;
	}
	
	private static class RowCell {
		public int rowIdx;
		public int colIdx;
		/**
		 * 当选中当前单元格后  哪些单元格无法被选中
		 */
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
