package com.zkc;

import com.zkc.utils.MyUtils;

import java.util.Arrays;

/**
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * <p>
 * The overall run time complexity should be O(log (m+n)).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 * Example 2:
 * <p>
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 */
public class MedianOfTwoSortedArrays {
	public static void main(String[] args) {
//		int[] nums1 = MyUtils.getArray(3, 10);
//		int[] nums2 = MyUtils.getArray(3, 10);
		int[] nums1 = new int[]{1};
		int[] nums2 = new int[]{1};
		Arrays.sort(nums1);
		MyUtils.printArr(nums1);
		Arrays.sort(nums2);
		MyUtils.printArr(nums2);
		System.out.println(findMedianSortedArrays(nums1, nums2));
	}
	
	/**
	 * 新建数组 按顺序从俩个数组中取数存放到数组中，存放数量为k=(m+n)/2+1 该长度足够判断中位数
	 * 双指针 分别从数组开头取数  较小的一方优先放入数组
	 * 结束时 如果有一方已经取完 用另一方的数补满数组 最后判断中位数 长度为奇数直接取新数组的末尾 为偶数时取新数组末尾俩个位置之和除以2。
	 */
	private static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int m = nums1.length, n = nums2.length;
		if (m == 0 && n == 0) {
			return 0;
		}
		if (m == 0) {
			return (n & 1) == 0 ? ((double) (nums2[n >> 1] + nums2[(n >> 1) - 1]) / 2) : nums2[n >> 1];
		}
		if (n == 0) {
			return (m & 1) == 0 ? ((double) (nums1[m >> 1] + nums1[(m >> 1) - 1]) / 2) : nums1[m >> 1];
		}
		int k = ((m + n) >> 1) + 1;
		int[] nums3 = new int[k];
		int top = 0, bot = 0, mid = 0;
		while (top < m && bot < n && mid < k) {
			int topVal = nums1[top], botVal = nums2[bot];
			if (topVal <= botVal) {
				nums3[mid++] = topVal;
				top++;
			} else {
				nums3[mid++] = botVal;
				bot++;
			}
		}
		if (top == m) {
			for (int i = mid; i < k; i++, bot++) {
				nums3[i] = nums2[bot];
			}
		} else if (bot == n) {
			for (int i = mid; i < k; i++, top++) {
				nums3[i] = nums1[top];
			}
		}
		return ((m + n) & 1) == 0 ? ((double) ((nums3[k - 1] + nums3[k - 2])) / 2) : nums3[k - 1];
	}
}
