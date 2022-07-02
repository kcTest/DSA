package com.zkc;

import com.zkc.utils.MyUtils;

/**
 * 求子数组的最大累积和 子数组元素连续
 */
public class MaxSum {
	public static void main(String[] args) {
		int[] arr = MyUtils.getArray(6, 10, false, true);
		MyUtils.printArr(arr);
		System.out.println(maxSum(arr));
	}
	
	/**
	 * 来到当前位置
	 * <p>如果当前位置之前没有元素 最大累加和直接取当前位置元素值
	 * <p>如果当前位置之前有元素  查看在上一位置求出的最大累积和 是否大于0
	 * <p>  如果大于0 将 上一位置求出的最大累加和 与 当前位置元素值 相加得到当前位置的最大累积和 继续移动到下一位判断
	 * <p>  如果小于0 与当前位置元素值相加会使最大累加和比当前位置值还小  不再相加   把当前位置的元素值作为当前位置求得的最大累加和 连续元素中断 从当前位置重新开始判断
	 * 每次处理完成一个位置  把当前位置求得的最大累加和与已记录的最大值作比较
	 */
	private static int maxSum(int[] arr) {
		int preSum = arr[0];
		int maxSum = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (preSum >= 0) {
				preSum = arr[i] + preSum;
			} else {
				preSum = arr[i];
			}
			maxSum = Math.max(maxSum, preSum);
		}
		return maxSum;
	}
	
}
