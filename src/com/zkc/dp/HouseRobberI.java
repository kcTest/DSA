package com.zkc.dp;

import com.zkc.utils.MyUtils;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 2:
 * <p>
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */
public class HouseRobberI {
	public static void main(String[] args) {
		for (int i = 1; i < 10000; i++) {
			int[] arr = MyUtils.getArray(10, 10);
			int money1 = rob(arr);
			int money2 = rob2(arr);
			if (money1 != money2) {
				MyUtils.printArr(arr);
				System.out.printf("money1=%d,money2=%d\n", money1, money2);
				break;
			}
		}
		
	}
	
	private static int rob2(int[] nums) {
		int n = nums.length;
		if (n == 1) {
			return nums[0];
		}
		//i-2位上获取的最大值
		int preMaxD2 = nums[0];
		//i-1位上获取的最大值
		int preMaxD1 = Math.max(nums[0], nums[1]);
		int curMax = preMaxD1;
		for (int i = 2; i < n; i++) {
			//不选, 看i-1位；  选， 看i-2位与相当位置相加
			curMax = Math.max(preMaxD1, preMaxD2 + nums[i]);
			//作为下一位截至i-2上获取的最大值
			preMaxD2 = preMaxD1;
			//作为下一位截至i-1上获取的最大值
			preMaxD1 = curMax;
		}
		return curMax;
	}
	
	/**
	 * record[i]代表来到i位置时，能选取的最大值
	 * n=1,只能选取arr[0]
	 * n=2,不选arr[1]时可以选取arr[0] 或 选ar[1]不可以再选取arr[0], 所以当前位置能获取的最大值为arr[0]、arr[1]中的较大者
	 * n=3,不选当前位置i，可选范围后退到i-1及之前位置，取之前已经算过的i-1位置记录的最大值。
	 * <p> 选取当前位置i, 可选范围后退到i-2及之前位置，取之前已经算过的i-2位置记录的最大值与当前位置的值相加之后的结果。
	 * ...
	 */
	private static int rob(int[] nums) {
		int n = nums.length;
		if (n == 1) {
			return nums[0];
		}
		int[] record = new int[n];
		record[0] = nums[0];
		record[1] = Math.max(nums[0], nums[1]);
		for (int i = 2; i < n; i++) {
			record[i] = Math.max(record[i - 1], record[i - 2] + nums[i]);
		}
		return record[n - 1];
	}
}
