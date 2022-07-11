package com.zkc.greedy;

import com.zkc.utils.MyUtils;

import java.util.Arrays;

/**
 * 给定一个正数数组 一个正数x 一个正数y
 * 对数组中的每个数num可以执行三种操作 且每个数最多只能执行一次操作
 * 1.不变；
 * 2.让num变为0,代价为x;
 * 3.让num变为-num,代价为y;
 * 让数组整体累加和sum<=0需要的最小代价
 */
public class SumLessOrEqualTo0MinCost {
	public static void main(String[] args) {
		int[] arr = MyUtils.getArray(7, 20, false, false);
//		int[] arr = new int[]{7, 7, 1};
		MyUtils.printArr(arr);
		int x = (int) (Math.random() * 10) + 1, y = (int) (Math.random() * 10) + 1;
//		int x = 2, y = 10;
		System.out.printf("x=%d,y=%d,cost=%d\n", x, y, getMinCost(arr, x, y));
		
	}
	
	/**
	 * 如果x>=y   因为3操作抵消的数比2操作抵消的数大，能尽快使sum<=0而且3操作的代价少，此时对每个数没必要执行2操作 都选择执行第3种操作 能在达成目标的同时使代价最小；
	 * 此时 将数组从大到小排序，优先使大数变为相反的负数，返回sum第一次<=0时总的代价。
	 * 如果x<y    因为此时2操作代价低需要使用2 虽然3操作代价高但是3操作抵消的数更大，加入3操作能让sum逼近0的速度更快，此时需要考虑2、3同时配合使用，
	 * 此时 将数组从大到小排序,列出所有可能的情况
	 * 左侧选择1个大数执行3操作变为负数, 该负数可以抵消右侧从右向左累加小于负数的所有小数，直到不能再抵消，剩余的数全部执行2操作,返回x和y操作的总代价。
	 * 左侧选择2个大数执行3操作变为负数, 该负数可以抵消右侧从右向左累加小于负数的所有小数，直到不能再抵消，剩余的数全部执行2操作,返回x和y操作的总代价。
	 * .
	 * .
	 * .
	 * 直到左侧指针和右侧指针相遇。取所有可能中的最小代价返回
	 */
	private static int getMinCost(int[] arr, int x, int y) {
		Arrays.sort(arr);
		int n = arr.length;
		//交换 逆序
		for (int l = 0, r = n - 1; l <= r; l++, r--) {
			int temp = arr[l];
			arr[l] = arr[r];
			arr[r] = temp;
		}
		if (x >= y) {
			int cost = 0;
			int sum = 0;
			for (int num : arr) {
				sum += num;
			}
			//sum<=0时停止
			for (int i = 0; i < arr.length && sum > 0; i++) {
				//num变为-num  sum累加和整体减少了2num 
				sum -= arr[i] << 1;
				cost += y;
			}
			return cost;
		} else {
			//先算出左侧不执行3操作 即所有数全部选择执行2操作 总代价为n*x;再去从左侧开始尝试选择1、2..执行3操作的总代价。
			int cost = n * x;
			//左侧从0开始
			int l = 0;
			//左侧已选负数绝对值 从选1个开始
			int lSum = 0;
			//左侧总代价
			int lyCost = 0;
			//右侧总最后开始
			int r = n;
			//右侧从右向左累加和
			int rSum = 0;
			//从左右向中间扩
			while (l < r) {
				lSum += arr[l];
				lyCost += y;
				//左侧每执行一次3操作 记录y的总代价 查看右侧能有哪些位置的数能被左侧负数和抵消  下一次直接累加
				while (rSum + arr[r - 1] <= lSum && l < r - 1) {
					rSum += arr[r - 1];
					r--;
				}
				//每次结束时 保证l r停在正确的位置 再去计算当前情况下的总代价
				cost = Math.min(cost, lyCost + x * (r - l - 1));
				l++;
			}
			return cost;
		}
	}
}
