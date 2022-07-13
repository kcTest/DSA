package com.zkc.dp;

import com.zkc.utils.MyUtils;

/**
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 * <p>
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements.
 * For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 */
public class LongestIncreasingSubsequence {
	public static void main(String[] args) {
//		int[] arr = MyUtils.getArray(7, 20);
		int[] arr = new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6};
		MyUtils.printArr(arr);
		System.out.println(lengthOfLIS(arr));
	}
	
	/**
	 * 可以使用数组record记录i位置及之前 能够找到的最长递增子序列.
	 * 一.
	 * 遍历arr 来到i位置时 在记录中遍历record的i-1之前在每个位置发现的最长递增子序列长度。
	 * 如果某个位置k对应原始arr数组的数arr[k]比当前arr[i]要小，arr[k+1]就比arr[i]要大,则record[k]+1为arr[i]作为结尾的长度,为当前位置的record[i]的一个可选值,不一定是全局最大值。
	 * 从i-1遍历到开头 把所有符合的条件的值+1算出来取最大值作为record[i];
	 * 所有位置的record信息设置完成后。遍历record再取最大值为最终返回结果。O(N^2)。
	 * 二.
	 * 为了加快设置record[i]的查找速度，新建ends[i] 每个位置(从0开始)表示 i+1长度的递增子序列 的结尾的最小值。
	 * 遍历arr,来到i位置时,去ends数组左侧已设置的有效部分的结尾值中找到一个大于等于arr[i]的且在最靠左的位置,
	 * 如果找到了为j，用当前arr[i]更新ends[j],此时j+1长度的递增子序列的结尾最小值能就变的更小,record[i]仍然取已发现的最大长度即ends最右有效位置+1;
	 * 如果没有找到 说明arr[i]比之前发现所有子序列的结尾值都大，可以作为新的递增子序列的结尾，子序列长度为ends中当前已设置的位置中最右(假设为i)i+1,
	 * 产生了更长的递增子序列，此时ends可以被扩展，设置新的位置ends[i+1]为arr[i],record[i]值为新发现的递增子序列的长度为i+1。
	 * 因为ends被扩展时新设置的结尾值更大 所以是有序增加的。在查找ends时候可以使用二分查找。
	 * 所有位置record信息设置完成后，取record[n-1]的值或者ends中最后一个有效位置的索引+1作为最终的最长递增子序列长度返回。O(N*(logN))
	 */
	private static int lengthOfLIS(int[] arr) {
		int n = arr.length;
		int[] record = new int[n];
		int[] ends = new int[n];
		//record[i]表示i位置及之前能够找到的最长递增子序列的长度.
		//可以提前设置 数组0位置左侧没有值 最大为长度为1
		record[0] = 1;
		//ends[i]表示长度为i+1的递增子序列的最小结尾数值  
		//长度为0+1的递增子序列结尾值设置为arr[0]
		ends[0] = arr[0];
		//从1开始
		int endsRight = 0;
		for (int i = 1; i < n; i++) {
			int left = 0;
			int right = endsRight;
			int destPos = -1;
			while (left <= right) {
				int mid = left + ((right - left) >> 1);
				//相等时不扩展
				if (ends[mid] >= arr[i]) {
					destPos = mid;
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
			//说明没有找到 可以形成更长的递增子序列 扩展ends 
			if (destPos == -1) {
				//新的位置 设置信息
				ends[++endsRight] = arr[i];
				//长度为扩展的新的索引位置+1
			} else {
				//找到 只更新结尾最小值  长度不变
				ends[destPos] = arr[i];
			}
			record[i] = endsRight + 1;
		}
		
		return record[n - 1];
	}
}
