package com.zkc.greedy;

import com.zkc.utils.MyUtils;
import sun.nio.cs.FastCharsetProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 小明手中有n块积木 并且小明知道每块积木的重量，现在小明希望将这些积木堆起来。
 * 要求是任意一块积木如果想堆在另外一块积木上面，那么要求:
 * 1. 上面的积木的重量不能小于下面积木的重量
 * 2. 上面积木的重量减去下面积木的重量不能超过x
 * 3. 每堆中最下面的积木没有重量要求
 * 现在小明有一个机会，除了这 n 块积木，还可以获得 k 块任意重量的积木。
 * 小明希望将积木堆在一起，同时希望积木堆的数量越少越好，你能帮他找到最好的方案么？
 * 输入描述：
 * 第一行三个整数 n , k , x , 1 < = n < = 200000 , O < = x , k < = 1000000000
 * 第二行 n 个整数，表示积木的重．，任惫整数范围都在 〔 1 , 1000000000 〕
 * 样例输出：
 * 13 1 38
 * 20 20 80 70 70 70 420 5 1 5 1 60 90
 * 1 1 5 5 20 20 60 70 70 70 80 90 420
 * 输出: 2
 * 解释:
 * 两堆分别是
 * 1 1 5  5 20 20 x=50 60 70 70 70 80 90;
 * 420;
 * 其中x是一个任意重量的积木，夹在20和60之间可以让积木继续往上搭
 */
public class MinStackOfBuildingBlock {
	public static void main(String[] args) {
//		int[] arr = new int[]{45, 5, 51, 10, 48, 1, 27, 1, 3, 14, 16, 29, 67};
		int[] arr = new int[]{20, 20, 80, 70, 70, 70, 420, 5, 1, 5, 1, 60, 90};
		int k = 1, x = 38;
		System.out.printf("k=%d,x=%d,minStack=%d\n", k, x, getMinStack(arr, k, x));
		MyUtils.printArr(arr);
	}
	
	/**
	 * 先将数组排序
	 * 从头开始统计有多少无法拼接的缺口，所有缺口的长度再排序，从最小的缺口开始填充魔法积木,直到积木消耗完或缺口全部消除,无法消除的缺口+1为最少的堆数返回。
	 */
	private static int getMinStack(int[] arr, int k, int x) {
		int n = arr.length;
		Arrays.sort(arr);
		List<Integer> gapLst = new ArrayList<>();
		for (int i = 1; i < n; i++) {
			int distance = arr[i] - arr[i - 1];
			if (distance > x) {
				//中间的空位数量 
				// 4,8 消耗0个  (8-4-1)/4； 
				// 4,9 消耗1个  (9-4-1)/4； 
				// 4,13 消耗2个  (13-4-1)/4
				gapLst.add(distance - 1);
			}
		}
		int gapCount = gapLst.size();
		if (gapCount == 0 || x == 0) {
			return ++gapCount;
		}
		gapLst.sort(Comparator.comparingInt(a -> a));
		for (int i = 0; i < gapLst.size() && k > 0; i++) {
			k -= (gapLst.get(i) / x);
			gapCount--;
		}
		return ++gapCount;
	}
	
}
