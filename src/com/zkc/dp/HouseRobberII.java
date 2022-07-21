package com.zkc.dp;

import com.zkc.utils.MyUtils;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed. All houses at this place are arranged in a circle.
 * That means the first house is the neighbor of the last one.
 * Meanwhile, adjacent houses have a security system connected,
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 * Example 2:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 3:
 * <p>
 * Input: nums = [1,2,3]
 * Output: 3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 */
public class HouseRobberII {
	public static void main(String[] args) {
		int[] arr = MyUtils.getArray(10, 10);
		MyUtils.printArr(arr);
		System.out.println(rob(arr));
	}
	
	/**
	 * record[i]代表来到i位置时，能选取的最大值
	 * n=1,只能选取arr[0]
	 * n=2,不选arr[1]时可以选取arr[0] 或 选ar[1]不可以再选取arr[0], 所以当前位置能获取的最大值为arr[0]、arr[1]中的较大者
	 * n=3,不选当前位置i，可选范围后退到i-1及之前位置，取之前已经算过的i-1位置记录的最大值。
	 * <p> 选取当前位置i, 可选范围后退到i-2及之前位置，取之前已经算过的i-2位置记录的最大值与当前位置的值相加之后的结果。
	 * ...
	 * 首尾相邻 如果从0开始选取 不能选n-1,范围为[0,n-2];如果从1开始选取 可以选n-1,范围为[1,n-1]
	 * 俩种情况取最大值
	 */
	private static int rob(int[] nums) {
		int n = nums.length;
		if (n == 1) {
			return nums[0];
		}
		//从0->n-2 
		int preMaxD2 = nums[0];
		int preMaxD1 = Math.max(nums[0], nums[1]);
		if (n == 2) {
			return preMaxD1;
		}
		int curMax1 = preMaxD1;
		for (int i = 2; i < n - 1; i++) {
			curMax1 = Math.max(preMaxD1, preMaxD2 + nums[i]);
			preMaxD2 = preMaxD1;
			preMaxD1 = curMax1;
		}
		//从1->n-1
		preMaxD2 = nums[1];
		preMaxD1 = Math.max(nums[1], nums[2]);
		int curMax2 = preMaxD1;
		for (int i = 3; i < n; i++) {
			curMax2 = Math.max(preMaxD1, preMaxD2 + nums[i]);
			preMaxD2 = preMaxD1;
			preMaxD1 = curMax2;
		}
		return Math.max(curMax1, curMax2);
	}
	
	
}
