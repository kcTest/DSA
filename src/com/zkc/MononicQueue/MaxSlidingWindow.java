package com.zkc.MononicQueue;

import com.zkc.utils.MyUtils;

import java.util.LinkedList;

/**
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 * 使用 double ended Queue
 */
public class MaxSlidingWindow {
	public static void main(String[] args) {
		int[] nums = MyUtils.getArray(7, 10);
		MyUtils.printArr(nums);
		int w = 3;
		int[] ret = getMaxWindow(nums, w);
		for (int j : ret) {
			System.out.printf("%d,", j);
		}
	}
	
	/**
	 * @param nums 数组
	 * @param k    窗口大小
	 * @return 每个窗口内对应的最大值
	 * dequeue从尾部开始加入数值对应索引，加之前判断如果值比尾部大 尾部出队列
	 * 形成窗口及后续每次添加值 除了判断尾部是否需要移除  还需要判断头部的索引是否已经低于当前窗口的最小位置
	 */
	private static int[] getMaxWindow(int[] nums, int k) {
		if (nums == null || nums.length == 0 || k < 1 || k > nums.length) {
			return new int[0];
		}
		//窗口大小为k 窗口个数最多为n-k+1 
		int[] ret = new int[nums.length - k + 1];
		//按数组值递减存放值的索引
		LinkedList<Integer> deque = new LinkedList<>();
		for (int i = 0; i < nums.length; i++) {
			//如果当前数大于等于队列尾部的数 移除队列尾部的数  然后加入新的数的索引 保持索引对应数值递减
			while (deque.size() > 0 && nums[i] >= nums[deque.getLast()]) {
				deque.removeLast();
			}
			deque.addLast(i);
			//来到第k-1个数 窗口已经形成 开始统计窗口内最大值   
			if (i >= k - 1) {
				//失效的位置 如计算第一个窗口后0位置失效  i=2时当前处理第j+1=1个窗口的最大值
				int j = i - (k - 1);
				//队列头部为当前已存入的最大值对应的索引 
				int curMaxIdx = deque.getFirst();
				ret[j] = nums[curMaxIdx];
				//移除时效位置
				if (curMaxIdx == j) {
					deque.removeFirst();
				}
			}
		}
		return ret;
	}
}
