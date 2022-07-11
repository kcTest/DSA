package com.zkc.dp;

import com.zkc.utils.MyUtils;

import java.util.Arrays;

/**
 * You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width and the height of an envelope.
 * One envelope can fit into another if and only if
 * both the width and height of one envelope are greater than the other envelope's width and height.
 * Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).
 */
public class RussianDollEnvelopes {
	public static void main(String[] args) {

//		int len = (int) (Math.random() * 10) + 3;
//		int[][] envelopes = new int[len][];
//		for (int i = 0; i < len; i++) {
//			int[] envelope = MyUtils.getArray(2, 20);
//			System.out.printf("[%d,%d], ", envelope[0], envelope[1]);
//			envelopes[i] = envelope;
//		}
		int[][] envelopes = new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}};
		System.out.println();
		System.out.println(maxEnvelopes(envelopes));
	}
	
	/**
	 * 将二维数组按信封宽度由小到大排序 宽度相同按高度由大到小排序。
	 * 宽度整体递增，宽度相同高度递减 ，所以此时只看高度 如果后一个位置的高度度大于前一个位置的高度，后一个位置的宽度肯定也比前一个宽。
	 * 排序之后 只看按高度求递增子序列 最大长度为信封最多嵌套层数，
	 */
	private static int maxEnvelopes(int[][] envelopes) {
		Arrays.sort(envelopes, (a, b) -> (a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]));
		int n = envelopes.length;
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = envelopes[i][1];
		}
		return lengthOfLIS(arr);
	}
	
	private static int lengthOfLIS(int[] arr) {
		int n = arr.length;
		int[] record = new int[n];
		int[] ends = new int[n];
		record[0] = 1;
		ends[0] = arr[0];
		int endsRight = 0;
		for (int i = 1; i < arr.length; i++) {
			int left = 0;
			int right = endsRight;
			int destPos = -1;
			while (left <= right) {
				int mid = left + ((right - left) >> 1);
				if (ends[mid] >= arr[i]) {
					destPos = mid;
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
			ends[destPos != -1 ? destPos : ++endsRight] = arr[i];
			record[i] = endsRight + 1;
		}
		return record[n - 1];
	}
	
	
}
