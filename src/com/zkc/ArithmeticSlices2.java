package com.zkc;

import com.zkc.utils.MyUtils;

import java.util.*;

/**
 * Given an integer array nums, return the number of all the arithmetic subsequences of nums.
 * <p>
 * A sequence of numbers is called arithmetic if it consists of at least three elements
 * and if the difference between any two consecutive elements is the same.
 * <p>
 * For example, [1, 3, 5, 7, 9], [7, 7, 7, 7], and [3, -1, -5, -9] are arithmetic sequences.
 * For example, [1, 1, 2, 5, 7] is not an arithmetic sequence.
 * A subsequence of an array is a sequence that can be formed by removing some elements (possibly none) of the array.
 * <p>
 * For example, [2,5,10] is a subsequence of [1,2,1,2,4,1,5,10].
 * The test cases are generated so that the answer fits in 32-bit integer.
 */
public class ArithmeticSlices2 {
	public static void main(String[] args) {
//		int[] arr = new int[]{2, 4, 6, 8, 10};
		int[] arr = new int[]{7, 7, 7, 7, 7};
		MyUtils.printArr(arr);
		System.out.println(numberOfArithmeticSlices(arr));
	}
	
	/**
	 * 来到每个位置 将当前位置i与i-1...0做差, 统计以当前i位置结尾 差值为0...k..n的且长度大于等于2的子序列分别有几个。
	 * 如果i-1..0某个位置j已经生成以j结尾差值为k的记录且num[i]-nums[j]==k,当前位置生成以i结尾差值为k的新记录的数量直接在j位置记录数量的基础上+1,
	 * (因为之前的子序列长度大于等于2以j结尾 再拼接上当前位置数字形成的新长度大于等于3的子序列 数量至少相等，加上新以j开头i结尾的长度为2的子序列 数量再+1；
	 * 而此时形成的大于等于3的子序列数量正好为之前位置已记录的大于等于2的子序列数量。)
	 * 如果i-1..0某个位置如果没有生成过记录 新的以j开头i结尾差值为k的长度为2的子序列 数量为1；
	 */
	public static int numberOfArithmeticSlices(int[] nums) {
		//i位置 表示 以nums的i结尾的子序列 差值为0...n的有几个
		List<Map<Integer, Integer>> diffMapLst = new ArrayList<>();
		int ans = 0;
		for (int i = 0; i < nums.length; i++) {
			diffMapLst.add(new HashMap<>());
			for (int j = i - 1; j >= 0; j--) {
				long diff = (long)nums[i] - (long)nums[j];
				if (diff > Integer.MAX_VALUE || diff <= Integer.MIN_VALUE) {
					continue;
				}
				int diff2=(int)diff;
				int curCount = diffMapLst.get(i).getOrDefault(diff2, 0);
				//已经记录 长度至少为2  拼接当前数字结尾可以形成新的长度大于等于3的子序列 数量相同
				int prevCount = diffMapLst.get(j).getOrDefault(diff2, 0);
				ans += prevCount;
				//当前位置设置记录  数量再加上新的以j开头i结尾差值为k的长度为2的子序列数量为1
				diffMapLst.get(i).put(diff2, curCount + prevCount + 1);
			}
		}
		return ans;
	}
}
