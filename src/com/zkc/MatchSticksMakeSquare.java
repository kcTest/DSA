package com.zkc;

import com.zkc.utils.MyUtils;

import java.util.*;

public class MatchSticksMakeSquare {
	
	public static void main(String[] args) {
//		int[] matchsticks = new int[]{3, 3, 3, 3, 4};
//		int[] matchsticks = new int[]{1, 1, 2, 2, 2};
//		int[] matchsticks = new int[]{5, 5, 5, 5, 16, 4, 4, 4, 4, 4, 3, 3, 3, 3};
//		int[] matchsticks = new int[]{5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3};
//		int[] matchsticks = new int[]{2, 2, 2, 2, 2, 6};
		int[] matchsticks = new int[]{10, 6, 5, 5, 5, 3, 3, 3, 2, 2, 2, 2};
		MyUtils.printArr(matchsticks);
		System.out.println(makeSquare(matchsticks));
	}
	
	private static boolean makeSquare(int[] matchsticks) {
		if (matchsticks.length < 4) {
			return false;
		}
		int total = 0;
		Arrays.sort(matchsticks);
		for (int matchstick : matchsticks) {
			total += matchstick;
		}
		//逆序  先选较长的火柴填充  尽量降低火柴的可选范围
		for (int i = 0, j = matchsticks.length - 1; i < j; i++, j--) {
			int temp = matchsticks[i];
			matchsticks[i] = matchsticks[j];
			matchsticks[j] = temp;
		}
		if (total % 4 != 0) {
			return false;
		}
		int edgeLen = total >> 2;
		int[] edges = new int[4];
		return dfs(matchsticks, edges, edgeLen, 0);
	}
	
	/**
	 * 保持当前选择的火柴被填充到一个边之后 再取下一个
	 *
	 * @param matchsticks 火柴数组 值对应第i个火柴长度
	 * @param edges       4条边对应已填充边长
	 * @param edgeLen     每条边长
	 * @param idx         当前火柴的位置
	 */
	private static boolean dfs(int[] matchsticks, int[] edges, int edgeLen, int idx) {
		if (idx == matchsticks.length) {
			return true;
		}
		//每根火柴 每次从第一个边尝试填充
		for (int i = 0; i < edges.length; i++) {
			if (edges[i] + matchsticks[idx] <= edgeLen) {
				edges[i] += matchsticks[idx];
				//成功后取下一根火柴
				if (dfs(matchsticks, edges, edgeLen, idx + 1)) {
					return true;
				} else {
					//当前路径下失败  撤销当前填充的火柴  尝试填充下一条边
					edges[i] -= matchsticks[idx];
				}
			}
		}
		//所有边都尝试过 不能填充成功
		return false;
	}
	
}
