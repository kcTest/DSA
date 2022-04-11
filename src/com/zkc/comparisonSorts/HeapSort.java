package com.zkc.comparisonSorts;

import com.zkc.utils.MyUtils;

/**
 * 堆（heap）通常是一个可以被看做一棵树的数组对象。堆总是满足下列性质：
 * 堆中某个结点的值总是不大于或不小于其父结点的值；
 * 堆总是一棵完全二叉树（
 * 一棵深度为k的有n个结点的二叉树，对树中的结点按从上至下、从左到右的顺序进行编号，
 * 如果编号为i（1≤i≤n）的结点与满二叉树中编号为i的结点在二叉树中的位置相同，则这棵二叉树称为完全二叉树
 * 完全二叉树的特点：叶子结点只能出现在最下层和次下层，且最下层的叶子结点集中在树的左部。需要注意的是，满二叉树肯定是完全二叉树，而完全二叉树不一定是满二叉树
 * 1、具有n个结点的完全二叉树的深度⌊log2⌋+1（注：⌊ ⌋表示向下取整）
 * 2、如果对一棵有n个结点的完全二叉树的结点按层序编号, 则对任一结点i (1≤i≤n) 有:
 * 如果i=1, 则结点i是二叉树的根, 无双亲;如果i>1, 则其双亲parent (i) 是结点[i/2].
 * 如果2i>n, 则结点i无左孩子, 否则其左孩子lchild (i) 是结点2i;
 * 如果2i+1>n, 则结点i无右孩子, 否则其右孩子rchild (i) 是结点2i+1.
 * ）。
 * 将根结点最大的堆叫做最大堆或大根堆，根结点最小的堆叫做最小堆或小根堆
 */
public class HeapSort {
	
	public static void main(String[] args) {
		int[] arr = MyUtils.getArray(9, 30);
		MyUtils.printArr(arr);
		System.out.println("===================");
		heapSort(arr);
		MyUtils.printArr(arr);
	}
	
	/**
	 * 空间复杂度O(1) 时间杂度O(nlogn)
	 */
	private static void heapSort(int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}
		//先从上往下执行堆插入流程使数组中的数的位置关系在逻辑上形成大根堆O(nlogn)
		for (int i = 0; i < arr.length; i++) {
			heapInsert(arr, i);
		}
//		//从下往上堆化处理O(n)
//		for (int i = arr.length - 1; i >= 0; i--) {
//			heapify(arr, i, arr.length);
//		}
		//根元素与最后一个元素交换，最后一个元素最大，有效元素减小，从根部往下执行堆化的处理过程O(nlogn)
		int heapSize = arr.length;
		for (int i = arr.length - 1; i >= 0; i--) {
			swap(arr, 0, i);
			heapify(arr, 0, --heapSize);
		}
	}
	
	/**
	 * 堆插入
	 * 向上调整当前元素
	 * 空间复杂度O(logn),为插入时当前完全二叉树的高度
	 */
	private static void heapInsert(int[] arr, int currentIndex) {
		//与父元素比较
		while (arr[currentIndex] > arr[(currentIndex - 1) / 2]) {
			swap(arr, currentIndex, (currentIndex - 1) / 2);
			currentIndex = (currentIndex - 1) / 2;
		}
	}
	
	/**
	 * 堆化
	 * 向下调整当前元素
	 * parent为当前待比较的父元素初始为root
	 * 因为要保持parent最大 所以需要与子元素进行比较交换的heapify过程
	 * heapSize为当前数组中有效元素的个数
	 * 时间复杂度O(logn),为调整时当前完全二叉树的高度
	 */
	private static void heapify(int[] arr, int parent, int heapSize) {
		int left = 2 * parent + 1;
		while (left < heapSize) {
			//取左右孩子中最大值位置
			int largest = left + 1 < heapSize && arr[left] < arr[left + 1] ? left + 1 : left;
			//不小于左右孩子时结束
			if (arr[parent] >= arr[largest]) {
				return;
			}
			//与左右孩子中最大的交换
			swap(arr, parent, largest);
			//更新父元素及左孩子的位置 继续向下比较
			parent = largest;
			left = 2 * parent + 1;
		}
	}
	
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	
//	private static void heapInsert2(int[] arr, int currentIndex) {
//		int parentIndex = (currentIndex - 1) / 2;
//		if (arr[currentIndex] > arr[parentIndex]) {
//			swap(arr, currentIndex, parentIndex);
//		}
//		heapInsert2(arr, parentIndex);
//	}
	
}
