package com.zkc.comparisonSorts;

import com.zkc.utils.MyUtils;

public class InsertionSort {
	
	public static void main(String[] args) {
		int[] arr = MyUtils.getArray(10, 20);
		MyUtils.printArr(arr);
		insertionSort(arr);
		MyUtils.printArr(arr);
	}
	
	/**
	 * 插入排序的工作方式像许多人排序一手扑克牌。开始时，我们的左手为空并且桌子上的牌面向下。
	 * 然后，我们每次从桌子上拿走一张牌并将它插入左手中正确的位置。
	 * 为了找到一张牌的正确位置，我们从右到左将它与已在手中的每张牌进行比较。
	 * 拿在左手上的牌总是排序好的，原来这些牌是桌子上牌堆中顶部的牌。
	 * <p>
	 * <br>
	 * 插入排序是指在待排序的元素中，假设前面n-1(其中n>=2)个数已经是排好顺序的，
	 * 现将第n个数插到前面已经排好的序列中，然后找到合适自己的位置，使得插入第n个数的这个序列也是排好顺序的。
	 * 按照此法对所有元素进行插入，直到整个序列排为有序的过程，称为插入排序
	 * <p>
	 * <br>
	 * 时间复杂度：
	 * 在插入排序中，当待排序数组是有序时，是最优的情况，只需当前数跟前一个数比较一下就可以了，这时一共需要比较N- 1次，时间复杂度为O(n)  。
	 * 最坏的情况是待排序数组是逆序的，此时需要比较次数最多，总次数记为：1+2+3+…+N-1，所以，插入排序最坏情况下的时间复杂度为 O(n^2)  。
	 * 平均来说，A[1..j-1]中的一半元素小于A[j]，一半元素大于A[j]。插入排序在平均情况运行时间与最坏情况运行时间一样，是输入规模的二次函数 。
	 * <p>
	 * <br>
	 * 空间复杂度
	 * 插入排序的空间复杂度为常数阶O(1) 。
	 * <p>
	 * <br>
	 * 稳定性分析：
	 * 如果待排序的序列中存在两个或两个以上具有相同关键词的数据，排序后这些数据的相对次序保持不变，即它们的位置保持不变，
	 * 通俗地讲，就是两个相同的数的相对顺序不会发生改变，则该算法是稳定的；
	 * 如果排序后，数据的相对次序发生了变化，则该算法是不稳定的。
	 * 关键词相同的数据元素将保持原有位置不变，所以该算法是稳定的 。
	 */
	private static void insertionSort(int[] arr) {
		//直接从第二个位置的数开始与前面的数执行比较交换
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j > 0; j--) {
				//升序
				if (arr[j] < arr[j - 1]) {
					swap(arr, j - 1, j);
					//交换后再与新位置之前的数比较
				} else {
					//j之前的位置已经有序 如果不需要交换，右边界直接移动到下一个位置
					break;
				}
			}
		}
	}
	
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
