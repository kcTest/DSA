package com.zkc;


import com.zkc.utils.MyUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个数组  数组中的数可以是正数 负数 0
 * 求连续子数组中累加和为a的总个数
 */
public class SubarrayEqualsX {
	public static void main(String[] args) {
//		int[] arr = MyUtils.getArray(4, 10, false, true);
//		int target = (int) (Math.random() * 10) + 1;
//		int[] arr = new int[]{7, -1, 1, 0, 1, -1, 10};
//		int target = 10;
		int[] arr = new int[]{5, -1, 1, 2, 3, -5, 0};
		int target = 5;
		MyUtils.printArr(arr);
		System.out.printf("target=%d,count=%d\n", target, getCount(arr, target));
	}
	
	/**
	 * 假设来到i位置 arr[0...i]累加和为sum,记录sum出现的次数c。 如果存在某个位置j使arr[j...i]累加和为target,
	 * 那么肯定存在某个前缀arr[0...j-1]的累加和为sum-target。
	 * 求arr[0...i]每个前缀的累加和，记录出现的次数。
	 * 来到i位置，如果在记录中查到了sum-target出现过次数为c，此时连续子数组中累加和为target的总个数为c+1;
	 * 如果目标target正好和第一个位置要求arr[0]相等 此时要求之前累加和为0的，但此时不存在会导致漏算，
	 * 所以需要提前存入记录累加和为0的出现过一次。
	 */
	private static int getCount(int[] arr, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);
		int sum = 0;
		int count = 0;
		for (int num : arr) {
			sum += num;
			int c = sum - target;
			//有符合要求的前缀  结果加上前缀的数量
			if (map.containsKey(c)) {
				count += map.get(c);
			}
			if (map.containsKey(sum)) {
				map.put(sum, map.get(sum) + 1);
			} else {
				map.put(sum, 1);
			}
		}
		return count;
	}
}
