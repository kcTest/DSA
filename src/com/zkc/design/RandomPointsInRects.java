package com.zkc.design;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 设计一个算法来随机挑选一个被某一矩形覆盖的整数点。矩形周长上的点也算做是被矩形覆盖。所有满足要求的点必须等概率被返回
 * 在给定的矩形覆盖的空间内的任何整数点都有可能被返回。
 */
public class RandomPointsInRects {
	public static void main(String[] args) {
		int[][] rects = new int[][]{new int[]{-2, -2, 1, 1}, new int[]{2, 2, 4, 6}};
		Solution solution = new Solution(rects);
		for (int i = 0; i < 10000; i++) {
			int[] pick = solution.pick();
			System.out.printf("[%d,%d]", pick[0], pick[1]);
		}
	}
	
	/**
	 * 将所有矩形内的点编号连起来  随机取编号  根据 编号确定点所在的矩形及内部坐标
	 */
	private static class Solution {
		
		private final int[][] rects;
		/**
		 * 只记录每个矩形内部可以产生的编号的最大值
		 */
		private final List<Integer> endSeqLst;
		
		public Solution(int[][] rects) {
			this.rects = rects;
			endSeqLst = new ArrayList<>();
			endSeqLst.add(0);
			for (int[] rect : rects) {
				//在上一矩形的编号基础上连续产生
				endSeqLst.add(endSeqLst.get(endSeqLst.size() - 1) + ((rect[2] - rect[0] + 1) * (rect[3] - rect[1] + 1)));
			}
		}
		
		public int[] pick() {
			int maxSeq = endSeqLst.get(endSeqLst.size() - 1);
			int targetSeq = (int) (Math.random() * maxSeq) + 1;
			int idxInLst = binarySearchIdxInLst(endSeqLst, targetSeq);
			//当前矩形内序号 从1开始
			int seq = targetSeq - endSeqLst.get(idxInLst - 1);
			//lst初始多加一个0
			int rectIdx = idxInLst - 1;
			int[] rect = rects[rectIdx];
			int lx = rect[0], by = rect[1], rx = rect[2];
			
			int rowLen = rx - lx + 1;
			//从1开始
			int rowSeq = (seq - 1) / rowLen + 1;
			int colSeq = seq - (rowSeq - 1) * rowLen;
			
			return new int[]{lx + colSeq - 1, by + rowSeq - 1};
		}
		
		private int binarySearchIdxInLst(List<Integer> endSeqLst, int targetSeq) {
			int low = 0, high = endSeqLst.size() - 1;
			while (low <= high) {
				int mid = low + (high - low) / 2;
				int curEndSeq = endSeqLst.get(mid);
				if (curEndSeq > targetSeq) {
					high = mid - 1;
				} else if (curEndSeq < targetSeq) {
					low = mid + 1;
				} else {
					return mid;
				}
			}
			return low;
		}
	}
}
