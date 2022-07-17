package com.zkc;

import com.zkc.utils.MyUtils;

/**
 * A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
 * <p>
 * For example, for arr = [1,2,3], the following are considered permutations of arr: [1,2,3], [1,3,2], [3,1,2], [2,3,1].
 * The next permutation of an array of integers is the next lexicographically greater permutation of its integer.
 * More formally, if all the permutations of the array are sorted in one container according to their lexicographical order,
 * then the next permutation of that array is the permutation that follows it in the sorted container.
 * If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).
 * <p>
 * For example, the next permutation of arr = [1,2,3] is [1,3,2].
 * Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
 * While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
 * Given an array of integers nums, find the next permutation of nums.
 * <p>
 * The replacement must be in place and use only constant extra memory.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * Output: [1,3,2]
 * Example 2:
 * <p>
 * Input: nums = [3,2,1]
 * Output: [1,2,3]
 * Example 3:
 * <p>
 * Input: nums = [1,1,5]
 * Output: [1,5,1]
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 */
public class NextPermutation {
	public static void main(String[] args) {
//		int[] arr = MyUtils.getArray(5, 20);
		int[] arr = new int[]{1, 5, 1};
		MyUtils.printArr(arr);
		nextPermutation(arr);
		MyUtils.printArr(arr);
	}
	
	/**
	 * 从右往左 找到一个降序的位置 1 4 7 6 5 3 2 1 ，
	 * 如果没有找到 7 6 5 4 3 2 1 1 整个数组直接逆序。
	 * 如果找到位置i, 从i+1往前找到大于arr[i]的数最右的位置j,将i,j交换。再将j及之后的位置逆序。4和5交换，7-1逆序 相当于字典序升序变降序,
	 */
	private static void nextPermutation(int[] arr) {
		int n = arr.length;
		int descPoint = -1;
		for (int i = n - 1; i > 0; i--) {
			if (arr[i - 1] < arr[i]) {
				descPoint = i - 1;
				break;
			}
		}
		if (descPoint == -1) {
			reverseArr(arr, 0, n - 1);
		} else {
			int j = -1;
			//往右找最后一个大于arr[i]的
			for (int i = descPoint + 1; i < n && arr[i] > arr[descPoint]; i++) {
				j = i;
			}
			if (j != -1) {
				//交换位置后 依然降序 但是j位置字典序降低 i之后的子数组总体字典序减小
				swap(arr, descPoint, j);
				//i之后逆序字典序从小到大
				reverseArr(arr, descPoint + 1, n - 1);
			}
		}
	}
	
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	private static void reverseArr(int[] arr, int l, int r) {
		while (l < r) {
			int temp = arr[l];
			arr[l] = arr[r];
			arr[r] = temp;
			l++;
			r--;
		}
	}
}
