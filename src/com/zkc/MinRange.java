package com.zkc;

import com.zkc.utils.MyUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * 给定k个有序数组
 * 任意俩个来自不同数组里的数组成一个区间，且每个数组中至少有一个数在该区间内，求该区间的可能的最小长度
 */
public class MinRange {
	public static void main(String[] args) {
		int k = 3;
		int[][] nums = new int[k][];
		for (int i = 0; i < k; i++) {
			nums[i] = MyUtils.getArray((int) (Math.random() * 8) + 2, 20, false, true);
			Arrays.sort(nums[i]);
		}
		MyUtils.print2DArray(nums);
		System.out.println(getMinRange(nums));
	}
	
	/**
	 * 依次从k个数组开头开始选取1个数成一个集合，保持集合从小到大，集合长度达到为k时,计算集合中最大值与最小值的差值绝对值，为一个区间的长度,记录。
	 * 移除最小值a,假设a在自己的数组位置为i，再次从i所在的数组中取出i+1位置的数b加入集合，集合长度再次达到k，再次计算区间长度,并与之前获取的比较 只取最小。
	 * 重复上述操作 直到某个数组的元素已经取完，无法形成k长度的集合，停止。返回之前所求区间长度中的最小值。
	 */
	private static int getMinRange(int[][] nums) {
		for (int[] num : nums) {
			if (num == null || num.length == 0) {
				return -1;
			}
		}
		//有序表logn,可以直接取最大值最小值 
		TreeSet<NUM> treeSet = new TreeSet<>(new NUMComparator());
		int k = nums.length;
		//先分别从各个数组头部取一个 使集合达到k，计算完区间值移除最小值，再根据最小值决定下一次从哪个数组取。
		for (int i = 0; i < k; i++) {
			treeSet.add(new NUM(nums[i][0], 0, i));
		}
		int min = Integer.MAX_VALUE;
		while (treeSet.size() == k) {
//			System.out.printf("x= %d, z= %d, d= %d\n", treeSet.first().val, treeSet.last().val, treeSet.last().val - treeSet.first().val);
			min = Math.min(min, treeSet.last().val - treeSet.first().val);
			NUM old = treeSet.first();
			treeSet.remove(old);
			if (old.idx != nums[old.parentIdx].length - 1) {
				treeSet.add(new NUM(nums[old.parentIdx][old.idx + 1], old.idx + 1, old.parentIdx));
			}
		}
		return min;
	}
	
	
	private static class NUM {
		private int val;
		private int idx;
		private int parentIdx;
		
		public NUM(int val, int idx, int parentIdx) {
			this.val = val;
			this.idx = idx;
			this.parentIdx = parentIdx;
		}
		
		@Override
		public String toString() {
			return "NUM{" +
					"val=" + val +
					", idx=" + idx +
					", parentIdx=" + parentIdx +
					'}';
		}
	}
	
	private static class NUMComparator implements Comparator<NUM> {
		@Override
		public int compare(NUM pre, NUM next) {
			//值相同 如果不在同一数组按所在数组顺序排序，如果在同一数组前后顺序任意 
//			return pre.val == next.val ? pre.parentIdx == next.parentIdx ? 0 : pre.parentIdx - next.parentIdx : pre.val - next.val;
			return pre.val != next.val ? pre.val - next.val : pre.parentIdx - next.parentIdx;
		}
	}
	
}
