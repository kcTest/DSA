package com.zkc;

public class WinnerScore {
	
	/**
	 * 俩人每次分别从数组左边或右边选一个最大的数字  返回数字之和较大一方的值
	 */
	public static void main(String[] args) {
		int[] nums = new int[]{3, 1, 9, 5, 4, 2};
		int max = getWinnerScore(nums);
		System.out.println(max);
	}
	
	private static int getWinnerScore(int[] nums) {
		int max1 = winnerScore(nums, 0, nums.length - 1, true, 0);
		int max2 = winnerScore(nums, 0, nums.length - 1, false, 0);
		return Math.max(max1, max2);
	}
	
	private static int winnerScore(int[] ori, int left, int right, boolean first, int curMax) {
		if (left == right) {
			return first ? curMax + ori[left] : curMax;
		}
		int retTempA = first ? ori[left] + curMax : curMax;
		int selectLeft = winnerScore(ori, left + 1, right, !first, retTempA);
		
		int retTempB = first ? ori[right] + curMax : curMax;
		int selectRight = winnerScore(ori, left, right - 1, !first, retTempB);
		
		return first ? Math.max(selectLeft, selectRight) : Math.min(selectLeft, selectRight);
	}
}
