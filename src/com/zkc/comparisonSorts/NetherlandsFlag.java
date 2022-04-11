
package com.zkc.comparisonSorts;

import com.zkc.utils.MyUtils;

import java.util.Random;

/**
 * 荷兰国旗问题O(N)
 */
public class NetherlandsFlag {
	
	public static void main(String[] args) {
		int[] arr = MyUtils.getArray(7, 20);
		MyUtils.printArr(arr);
		int num = new Random().nextInt(25);
		System.out.println(num);
		//partition1(arr, num);
		partition2(arr, num);
		MyUtils.printArr(arr);
	}
	
	/**
	 * 指定一个数，调整数组使数组分为两部分，左侧部分的数均小于等于选定数 右侧数均比选定数大
	 */
	private static void partition1(int[] arr, int num) {
		//小于等于num的左边界   左侧数均保持小于等于num
		int lowerBoundary = -1;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] <= num) {
				//与边界右侧第一个数交换
				int temp = arr[lowerBoundary + 1];
				arr[lowerBoundary + 1] = arr[i];
				arr[i] = temp;
				lowerBoundary++;
			}
		}
	}
	
	/**
	 * 指定一个数，调整数组使数组分为两部分，左侧部分的数均小于等于选定数 右侧数均比选定数大 相等的在中间
	 */
	private static void partition2(int[] arr, int num) {
		//小于num的左边界   左侧数均保持小于num
		int lowerBoundary = -1;
		//大于num的右边界   右侧数均保持大于num 左右边界之间如果有值则是等于num的数
		int higherBoundary = arr.length;
		for (int i = 0; i < arr.length; i++) {
			if (i == higherBoundary) {
				break;
			}
			//交换会将当前数调整到左边界的右侧第一个位置或右边界的左侧第一个位置，然后增加左边界或减小右边界
			if (arr[i] < num) {
				int temp = arr[lowerBoundary + 1];
				arr[lowerBoundary + 1] = arr[i];
				arr[i] = temp;
				lowerBoundary++;
			} else if (arr[i] > num) {
				int temp = arr[higherBoundary - 1];
				arr[higherBoundary - 1] = arr[i];
				arr[i] = temp;
				higherBoundary--;
				//交换后继续判断当前位置
				i--;
			}
		}
	}
}
	
		
		