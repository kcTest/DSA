package com.zkc.comparisonSorts;

import com.zkc.utils.MyUtils;

import java.util.PriorityQueue;

/**
 * 已知一个几乎有序的数组，几乎有序是指如果把数组排好序的话，每个元素移动的距离可以不超过k,并且k相对于数组来说比较小。
 * 请选择一个合适的排序算法针对这个数据进行排序
 * 先将前k+1个元素上生成小根堆，堆顶元素为最小元素 放在结果的第一个位置上。
 * 再将堆顶元素替换为第k+2个元素，调整后的最小值放在结果的第二个位置上。
 * 后面元素依次进行相同的处理，剩k+1个元素时，从堆顶依次输出。此时所有输出的结果有序。
 */
public class SortArrayDistanceLessK {
	
	public static void main(String[] args) {
		//int[] arr = MyUtils.getArray(9, 30);
		int[] arr = new int[]{22, 17, 27, 9, 22, 29, 21, 29, 26};
		MyUtils.printArr(arr);
		int k = 4;
		sortArrayDistanceLessK(arr, k);
		heapSortK(arr, k);
		MyUtils.printArr(arr);
	}
	
	private static void sortArrayDistanceLessK(int[] arr, int k) {
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		for (int i = 0; i <= (k > arr.length ? arr.length - 1 : k); i++) {
			heap.add(arr[i]);
		}
		int l = k + 1;
		//前k+1个元素已确定  停在到第k+2个元素，从第k+2个元素开始继续添加,并将小根堆的最小值依次赋给数组。当所有元素添加完 数组剩余k个位置待确定  
		while (l < arr.length) {
			heap.add(arr[l]);
			arr[l++ - (k + 1)] = heap.poll();
		}
		while (!heap.isEmpty()) {
			arr[l++ - (k + 1)] = heap.poll();
		}
	}
	
	/**
	 * 时间杂度O(nlogn)
	 */
	private static void heapSortK(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return;
		}
		k = k > arr.length ? arr.length - 1 : k;
		int[] arrTemp = new int[k + 1];
		System.arraycopy(arr, 0, arrTemp, 0, k + 1);
		//先从下往上执行堆化生成小根堆
		for (int i = arrTemp.length - 1; i >= 0; i--) {
			heapify(arrTemp, i, arrTemp.length);
		}
		//在前K+1个是小根堆的基础上
		//第k+1个元素与已位于已确定元素的下一个元素交换后 在执行从上往下的堆化过程 生成小根堆
		int l = k + 1;
		while (l < arr.length) {
			//顶部最小元素放入原数组  从头开始
			arr[l - (k + 1)] = arrTemp[0];
			//顶部元素更换
			arrTemp[0] = arr[l++];
			heapify(arrTemp, 0, k + 1);
		}
		int heapSize = arrTemp.length;
		for (int i = arrTemp.length - 1; i >= 0; i--) {
			swap(arrTemp, 0, i);
			heapify(arrTemp, 0, --heapSize);
			arr[l++ - (k + 1)] = arrTemp[i];
		}
	}
	
	/**
	 * 生成小根堆
	 * 向下
	 */
	private static void heapify(int[] arr, int parent, int heapSize) {
		int left = 2 * parent + 1;
		while (left < heapSize) {
			//取左右孩子中最小值位置
			int smallest = left + 1 < heapSize && arr[left] > arr[left + 1] ? left + 1 : left;
			//不小于左右孩子时结束
			if (arr[parent] <= arr[smallest]) {
				return;
			}
			//与左右孩子中最小的交换
			swap(arr, parent, smallest);
			//更新父元素及左孩子的位置 继续向下比较
			parent = smallest;
			left = 2 * parent + 1;
		}
	}
	
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
