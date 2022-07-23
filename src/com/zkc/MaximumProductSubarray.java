package com.zkc;

import com.zkc.utils.MyUtils;

public class MaximumProductSubarray {
	public static void main(String[] args) {
		for (int i = 0; i < 100000; i++) {
			int[] nums = MyUtils.getArray(10, 10, false, true);
			int num1 = maxProduct(nums);
			int num2 = maxProduct2(nums);
			if (num1 != num2) {
				MyUtils.printArr(nums);
				System.out.println("error");
				System.out.printf("num1=%d,num2=%d\n", num1, num2);
				break;
			}
		}
		long s = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			int[] nums = MyUtils.getArray(9, 10, false, true);
			maxProduct(nums);
		}
		long s1 = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			int[] nums = MyUtils.getArray(9, 10, false, true);
			maxProduct2(nums);
		}
		long s2 = System.currentTimeMillis();
		System.out.printf("t1=%d,t2=%d\n", s1 - s, s2 - s1);
	}
	
	/**
	 * 俩个方向分别遍历算一次 可以算出负数作为左边界和右边界时每个位置的乘积 取最大
	 */
	private static int maxProduct2(int[] nums) {
		int n = nums.length;
		if (n == 1) {
			return nums[0];
		}
		int pre = 0, max = 0;
		for (int num : nums) {
			if (num == 0) {
				pre = 0;
			} else {
				pre = pre == 0 ? num : pre * num;
			}
			max = Math.max(max, pre);
		}
		pre = 0;
		for (int i = n - 1; i >= 0; i--) {
			if (nums[i] == 0) {
				pre = 0;
			} else {
				pre = pre == 0 ? nums[i] : pre * nums[i];
			}
			max = Math.max(max, pre);
		}
		return max;
	}
	
	/**
	 * 2 ,2,4, 2,-2,-3,-1,2,3,4,2,0,-4,-3
	 * ------------------------------
	 * 2,-4,4,2,-3,2,-4,2,0,3,0,-3,-4
	 * ------------------------------
	 * <p>
	 * 。。start。。。i.....j......k......end,x..
	 * ...start......i......j........end,x....
	 * ..start.....i......end,x...
	 * <p>
	 * 遍历每个数相乘直到遇到0的位置x，可以确定一个不含0的有效范围 并记录之前遇到的第一个负数和最后一个负数
	 * start默认=0 每次处理完成后start=x+1，如果当前数不为0但是来到末尾 end=x,否则end=x-1
	 * 如果负数数量为奇数 用x位置之前的乘积除以nums[x-1,end]得到p1为[start,end-1]范围上含偶数个负数的最大乘积，为当前范围内获取的最大乘积 与max比较
	 * 如果负数数量为偶数 end位置之前的乘积为当前范围内获取的最大乘积 与max比较
	 * 如果范围内只有一个数 这个数为当前范围内获取的最大乘积 与max比较
	 */
	private static int maxProduct(int[] nums) {
		int n = nums.length, max = 0, curProduct = 0, firstNeg = -1, lastNeg = -1, countNeg = 0, start = 0;
		if (n == 1) {
			return nums[0];
		}
		for (int i = 0; i < n; i++) {
			int num = nums[i];
			if (num < 0) {
				countNeg++;
				if (firstNeg == -1) {
					firstNeg = i;
				}
				lastNeg = i;
			}
			if (num != 0) {
				curProduct = curProduct == 0 ? num : curProduct * num;
			}
			if (num == 0 || i == n - 1) {
				if ((countNeg & 1) == 0) {
					max = Math.max(max, curProduct);
				} else {
					//末尾不是0 有效范围结尾为当前位置
					int end = i == n - 1 && num != 0 ? i : i - 1;
					if (end - start == 0) {
						max = Math.max(max, curProduct);
					} else {
						/* 去掉范围内头部到第一个负数之间的乘积 */
						int p1 = curProduct;
						while (start <= firstNeg) {
							p1 /= nums[start++];
						}
						/* 去掉范围内尾部到最后一个负数之间的乘积 */
						int p2 = curProduct;
						while (end >= lastNeg) {
							p2 /= nums[end--];
						}
						max = Math.max(max, Math.max(p1, p2));
					}
				}
				firstNeg = -1;
				curProduct = 0;
				start = i + 1;
				countNeg = 0;
			}
		}
		return max;
	}
}
