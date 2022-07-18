package com.zkc;

import com.zkc.utils.MyUtils;

/**
 * Given an integer array nums and an integer k, return the number of good subarrays of nums.
 * <p>
 * A good array is an array where the number of different integers in that array is exactly k.
 * <p>
 * For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
 * A subarray is a contiguous part of an array.
 * <p>
 * Example 1:
 * Input: nums = [1,2,1,2,3], k = 2
 * Output: 7
 * Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
 * Example 2:
 * Input: nums = [1,2,1,3,4], k = 3
 * Output: 3
 * Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 * <p>
 * Constraints:
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i], k <= nums.length
 */
public class SubarraysWithKDifferentIntegers {
	public static void main(String[] args) {
		int[] nums = new int[]{1, 2, 1, 2, 3};
		MyUtils.printArr(nums);
		int k = 2;
		System.out.printf("k=%d,count=%d\n", k, subarraysWithKDistinct(nums, k));
	}
	
	/**
	 * 先求包含数字种类数量小于等于K的子数组的个数a，再求包含数字种类数量小于等于K-1的子数组的个数b，a-b为返回结果
	 * <p>
	 * <p>
	 * 双指针从左往右遍历数组，求每个位置开头的符合条件的子数组个数。
	 * 初始i来到0位置,j来到0位置向后移动,记录j遇到的每个数字及个数，直到遇到一个位置，使记录里的种类超过K。
	 * 此时统计i开头、以i...j-1结尾的包含数字种类数量小于等于K的子数组的个数，为((j-1)-i+1)个。
	 * 统计完成后将0位置的数字个数从记录中减1，i向右扩来到i+1位置。
	 * 如果减少后这个数字的数量还大于0，说明在i+1->j-1范围上仍然有K种，此时可以继续统计i+1开头、以i+1...j-1结尾的包含数字种类数量小于等于K的子数组的个数。
	 * 此时如果出现j先来到末尾的情况,直接结算剩余每个i位置与j位置的个数。
	 * 如果减少后这个数字的数量等于0，在当前i+1->j-1范围上种类少一种只有K-1种，没有达到K种，j可以向右移动了。
	 * 重复上面的流程直到i指针遍历结束。返回记录的总数量。
	 */
	private static int subarraysWithKDistinct(int[] nums, int k) {
		return getCount(nums, k) - getCount(nums, k - 1);
	}
	
	private static int getCount(int[] arr, int k) {
		int n = arr.length;
		//1 <= nums[i], k <= nums.length。使用数组记录每个整数的个数
		int[] map = new int[n + 1];
		int i = 0, j = 0, ret = 0, kind = 0;
		while (j < n) {
			if (map[arr[j]]++ == 0) {
				kind++;
			}
			while (kind > k) {
				//每次超出时结算i与j-1位置的结果 直到不再超出
				//j停止在刚超出K的位置 每次结算时结果加j-1-i+1
				ret += j - i;
				if (--map[arr[i++]] == 0) {
					kind--;
				}
			}
			//如果来到末尾 i及之后的位置与j位置形成的子数组均不超过K, 剩余位置挨个结算， 每次结果加j-i+1
			if (j == n - 1) {
				while (i <= j) {
					ret += j - i++ + 1;
				}
			}
			//不超过K时j才加
			j++;
		}
		return ret;
	}
}
