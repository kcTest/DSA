package com.zkc;

import com.zkc.utils.DataSource;

public class BubbleSort {
	
	public static void main(String[] args) {
		int[] arr = DataSource.getArray(5, 20);
		printArr(arr);
		System.out.println();
		System.out.println("===================");
		bubbleSort(arr);
		printArr(arr);
	}
	
	private static void printArr(int[] arr) {
		for (int j : arr) {
			System.out.printf("%d ", j);
		}
	}
	
	/**
	 * 冒泡排序算法的原理如下：
	 * 1.比较相邻的元素。如果第一个比第二个大，就交换他们两个。
	 * 2.对每一对相邻元素做同样的工作，从开始第一对到结尾的最后一对。在这一点，<b>最后的元素应该会是最大的数</b>。
	 * 3.针对所有的元素重复以上的步骤，除了最后一个。
	 * 4.持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
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
	
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
