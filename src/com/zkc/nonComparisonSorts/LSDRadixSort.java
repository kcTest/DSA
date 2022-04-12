package com.zkc.nonComparisonSorts;

import com.zkc.utils.MyUtils;

/**
 * 基数排序 最低位优先
 */
public class LSDRadixSort {
	
	public static void main(String[] args) {
		int[] arr = MyUtils.getArray(9, 112);
		MyUtils.printArr(arr);
		lsdRadixSort(arr);
		MyUtils.printArr(arr);
	}
	
	private static void lsdRadixSort(int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}
		//十进制为例
		int radix = 10;
		//算出最大元素的位数 确定进行几轮进出桶的流程
		int maxIndex = 0;
		for (int m = 1; m < arr.length - 1; m++) {
			if (arr[m] > arr[maxIndex]) {
				maxIndex = m;
			}
		}
		int maxRound = 0;
		int temp = arr[maxIndex];
		while ((temp / radix) > 0 || (temp % radix) > 0) {
			temp = temp / radix;
			maxRound++;
		}
		for (int r = 0; r < maxRound; r++) {
			
			//计算每个整数出现次数  下标值即为所有可能出现的单个整数 方便确定桶内元素位置
			int[] count = new int[radix];
			
			for (int j : arr) {
				//不入桶 只记录本轮要统计的指定位置的整数出现的次数
				int remainder = (j / (int) (Math.pow(radix, r))) % radix;
				count[remainder]++;
			}
			
			//对桶内每个0-9的每个位置存放元素数量进行计数的count数组再次处理 
			//每个位置的数不再表示单个整数已存放的个数，而是表示当前小于等于当前位置的值得整数的总个数
			for (int i = 1; i < count.length; i++) {
				count[i] += count[i - 1];
			}
			
			//桶内元素保持先进先出 不使用二维数组 节省空间 桶的个数为进制数值
			int[] arrBucket = new int[arr.length];
			
			//因为count每个位置存放与下标相同的整数出现的次数，所以指定位置相同整数的数在确定位置后处于同一片区域
			// 而且在count越靠后的位置记录的值为较大数的出现的次数，根据count数组每个位置的数值 可以直接算出出桶后的位置
			//从后往前先算出本轮指定位置的整数  从count中获取小于等于该整数的数量
			//因为从后向前遍历，而且先入先出，所以第一个数如果在进行入桶操作时肯定是相同整数中最后一个入桶的，出桶时也是最后一个出来，
			//该数在出桶后的位置肯定会处在所有小于等于该数的区域中的最后一位，这个位置由count的当前数值可以确定
			//如果再次出现相同的整数 出桶后的位置会在该数所属区域内依次靠前排列
			for (int i = arr.length - 1; i >= 0; i--) {
				int remainder = (arr[i] / (int) (Math.pow(radix, r))) % radix;
				arrBucket[--count[remainder]] = arr[i];
			}
			
			//arrBucket内的位置为出桶后排好序的位置 依次写回
			System.arraycopy(arrBucket, 0, arr, 0, arrBucket.length);
		}
	}
	
	private static void lsdRadixSort2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}
		//十进制为例
		int radix = 10;
		//计算每个桶内当前元素数量 方便确定桶内元素位置
		int[] count = new int[radix];
		//桶内元素保持先进先出 二维数组 桶的个数为进制数值，每个桶内最多可能存放的数的个数与数组相同
		int[][] arrBucket = new int[radix][arr.length];
		//算出最大元素的位数 确定进行几轮进出桶的流程
		int maxIndex = 0;
		for (int m = 1; m < arr.length - 1; m++) {
			if (arr[m] > arr[maxIndex]) {
				maxIndex = m;
			}
		}
		int maxRound = 0;
		int temp = arr[maxIndex];
		while ((temp / radix) > 0 || (temp % radix) > 0) {
			temp = temp / radix;
			maxRound++;
		}
		for (int r = 0; r < maxRound; r++) {
			for (int value : arr) {
				//根据(当前数从右往左逐渐减少位数作为被除数 除以进制取余)提取出当前位的一个数放入对应的桶里 count数组记录当前类型的余数已经存放的数量
				int remainder = (value / (int) (Math.pow(radix, r))) % radix;
				arrBucket[remainder][count[remainder]++] = value;
			}
			//计算从头开始的位置 将桶内数从头开始取出再写回到原数组
			int coverIndex = 0;
			for (int j = 0; j < radix; j++) {
				int l = count[j];
				for (int k = 0; k < l; k++) {
					arr[coverIndex++] = arrBucket[j][k];
					//写回后桶内当前位置清除 对应计数也减少
					arrBucket[j][k] = 0;
					count[j]--;
				}
			}
		}
	}
	
}
