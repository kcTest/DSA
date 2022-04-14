package com.zkc.comparisonSorts;

import com.zkc.utils.MyUtils;

public class BubbleSort {
	
	public static void main(String[] args) {
		int[] arr = MyUtils.getArray(9, -1);
		MyUtils.printArr(arr);
		bubbleSort(arr);
		MyUtils.printArr(arr);
	}
	
	/**
	 * 冒泡排序算法的原理如下：
	 * 1.比较相邻的元素。如果第一个比第二个大，就交换他们两个。
	 * 2.对每一对相邻元素做同样的工作，从开始第一对到结尾的最后一对。在这一点，<b>最后的元素应该会是最大的数</b>。
	 * 3.针对所有的元素重复以上的步骤，除了最后一个。
	 * 4.持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
	 * <p>
	 * <br>
	 * 时间复杂度：
	 * 若文件的初始状态是正序的，一趟扫描即可完成排序。所需的关键字比较次数C 和记录移动次数 M均达到最小值：Cmin=n-1,Mmin=0。
	 * 所以，冒泡排序最好的时间复杂度为 O(n）。
	 * 若初始文件是反序的，需要进行n-1趟排序。每趟排序要进行n-i次关键字的比较(1≤i≤n-1)，且每次比较都必须移动记录三次来达到交换记录位置。
	 * 在这种情况下，比较和移动次数均达到最大值：Cmax=n(n-1)/2=O(n^2),Mmax=3n(n-1)/2=O(n^2)。
	 * 冒泡排序的最坏时间复杂度为 O(n^2)。
	 * 综上，因此冒泡排序总的平均时间复杂度为 O(n^2)。
	 * <p>
	 * 空间复杂度O（1）
	 * <br>
	 * 算法稳定性：
	 * 冒泡排序就是把小的元素往前调或者把大的元素往后调。比较是相邻的两个元素比较，交换也发生在这两个元素之间。
	 * 所以，如果两个元素相等，是不会再交换的；如果两个相等的元素没有相邻，那么即使通过前面的两两交换把两个相邻起来，这时候也不会交换，
	 * 所以相同元素的前后顺序并没有改变，所以冒泡排序是一种稳定排序算法。
	 */
	private static void bubbleSort(int[] arr) {
		//n个元素需要冒泡n-1个元素来确定右边n-1个位置的顺序
		for (int i = 0; i < arr.length - 1; i++) {
			//每次从0位置开始比较相邻元素，每次循环结束后确定一个最大元素在最右，需要比较的元素范围也逐渐缩小
			for (int j = 0; j < arr.length - 1 - i; j++) {
				//升序
				if (arr[j] > arr[j + 1]) {
					swap(arr, j, j + 1);
				}
			}
		}
	}
	
	/**
	 * a^a=0;
	 * <br>
	 * a^0=a;
	 * <br>
	 * a^b=b^a;
	 * <br>
	 * a^b^c=a^(b^c);
	 */
	private static void swap(int[] arr, int i, int j) {
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
	}
}
