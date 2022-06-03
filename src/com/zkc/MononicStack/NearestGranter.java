package com.zkc.MononicStack;

import com.zkc.utils.MyUtils;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 对数组中每个数 求距离自己最近且大于自己的数
 * 使用monotonic  stack
 */
public class NearestGranter {
	
	public static void main(String[] args) {
		int[] nums = MyUtils.getArray(7, 10);
		MyUtils.printArr(nums);
		int[][] ret = getNearestGranter(nums);
		for (int i = 0; i < ret.length; i++) {
			int[] info = ret[i];
			System.out.printf("[%d > %d < %d], ", info[0], nums[i], info[1]);
		}
	}
	
	private static int[][] getNearestGranter(int[] nums) {
		if (nums == null || nums.length == 0) {
			return new int[][]{};
		}
		//每个位置存放左右俩边大于自身的数
		int[][] ret = new int[nums.length][2];
		// 存放索引  保持索引值递减 可能相等需要存放多个索引 
		Stack<ArrayList<Integer>> stack = new Stack<>();
		for (int i = 0; i < nums.length; i++) {
			int val = nums[i];
			if (stack.size() == 0) {
				ArrayList<Integer> idxLst = new ArrayList<>();
				idxLst.add(i);
				stack.push(idxLst);
			} else {
				ArrayList<Integer> prevLst = stack.peek();
				int prevVal = nums[prevLst.get(0)];
				//小于栈顶值 新建lst直接加入
				if (val < prevVal) {
					ArrayList<Integer> idxLst = new ArrayList<>();
					idxLst.add(i);
					stack.push(idxLst);
				} else if (val == prevVal) {
					//等于时直接加入lst
					prevLst.add(i);
				} else {
					//大于栈顶值时  栈顶的前一个>栈顶值<当前数, 弹出栈顶值 并为弹出的值设置左右值  直到栈顶值不再大于nums中的当前数  再把当前数加入 
					while (stack.size() > 0) {
						prevLst = stack.peek();
						if (val > nums[prevLst.get(0)]) {
							stack.pop();
							int valBeforePrev = stack.size() > 0 ? nums[stack.peek().get(0)] : Integer.MAX_VALUE;
							for (int idx : prevLst) {
								//左侧大于弹出值的位置为它前一个位置或者不存在
								ret[idx][0] = valBeforePrev;
								//右侧值为当前数
								ret[idx][1] = val;
							}
						} else {
							//不大于停止
							break;
						}
					}
					//当前数新建lst加入索引
					ArrayList<Integer> idxLst = new ArrayList<>();
					idxLst.add(i);
					stack.push(idxLst);
				}
			}
		}
		//遍历到最后 如果还有 从栈底到栈顶索引对应数值>=后一个
		while (stack.size() > 0) {
			ArrayList<Integer> lastLst = stack.pop();
			int prevVal = Integer.MAX_VALUE;
			if (stack.size() > 0) {
				prevVal = nums[stack.peek().get(0)];
			}
			for (int idx : lastLst) {
				//最后一个位置 右侧固定 左侧为上一个
				ret[idx][0] = nums[idx] < prevVal ? prevVal : Integer.MAX_VALUE;
				ret[idx][1] = Integer.MAX_VALUE;
			}
		}
		return ret;
	}
	
}
