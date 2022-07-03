package com.zkc.Preprocessing;

import com.zkc.utils.MyUtils;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it can trap after raining.
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 */
public class TrappingRainWater {
	public static void main(String[] args) {
		int[] arr = MyUtils.getArray(8, 10);
//		int[] arr = new int[]{8, 8, 2, 2, 9, 7, 2, 2};
		MyUtils.printArr(arr);
		System.out.println(trap(arr));
	}
	
	
	/**
	 * 先将arr[0]记为arr[1]不包含自身在左侧的存在的最大值leftMax，再将arr[length-1]记为arr[length-2]不包含自身在右侧的存在的最大值rightMax
	 * 将左右指针移动到1和length-2位置 。比较leftMax和rightMax， 较低的一侧先计算当前位置能够捕获的水量。
	 * 如果此时右侧较低 水量为rightMax-arr[i]， 如果差值小于0算作0   然后向左移动右侧指针 来到i-1位置。
	 * 此时arr[i]和rightMax中的最大值为i-1位置的rightMax。再将新的rightMax与leftMax比较。
	 * 如果此时左侧较低 水量为leftMax-arr[i]， 如果差值小于0算作0   然后向右移动左侧指针 来到i+1位置。
	 * 此时arr[i]和rightMax中的最大值为i+1位置的leftMax。再将新的leftMax与rightMax比较。
	 * 重复上述步骤 直到左右俩侧指针重合 ，此时每个位置能够捕获的水量已经算出 再累加得到最终结果。
	 */
	private static int trap(int[] arr) {
		int leftMax = arr[0];
		int l = 1;
		int rightMax = arr[arr.length - 1];
		int r = arr.length - 2;
		int total = 0;
		while (l <= r) {
			if (leftMax <= rightMax) {
				total += (leftMax - arr[l] < 0 ? 0 : leftMax - arr[l]);
				leftMax = Math.max(leftMax, arr[l]);
				l++;
			} else {
				total += (rightMax - arr[r] < 0 ? 0 : rightMax - arr[r]);
				rightMax = Math.max(rightMax, arr[r]);
				r--;
			}
		}
		return total;
	}
}
