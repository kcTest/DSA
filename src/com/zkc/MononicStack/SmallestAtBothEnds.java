package com.zkc.MononicStack;

import com.zkc.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个数组 求子数组中满足最小值和次小值位于该子数组俩端的子数组的总个数
 */
public class SmallestAtBothEnds {
	public static void main(String[] args) {
		int[] arr = MyUtils.getArray(7, 20);
		MyUtils.printArr(arr);
		System.out.println(getSubarrayNum(arr));
	}
	
	
	/**
	 * 相当于求 每个位置的数arr[i] 左右俩侧小于自己距离自己最近的位置，返回有效位置个数。
	 * <p>
	 * 建立栈，然后遍历每个位置,如果当前位置元素arr[i]大于栈顶元素值，入栈；如果等于,入栈但是和栈顶元素存入同一列表；如果小于，弹出栈顶元素并开始统设置栈顶元素的信息，
	 * 此时栈顶元素右侧离自己最近的值是arr[i],左侧离自己最近的值是栈顶元素前一个位置的元素,弹出栈顶元素，再检查此时的栈顶元素，如果arr[i]还小于它，继续对当前栈顶元素进行
	 * 同样的处理。直到不再小于，arr[i]入栈。该过程使栈内元素始终保持单调递增。
	 * 遍历完成后 对栈内元素 从上往下弹出并设置信息，右侧最小且距离自己最近的没有设置为-1，左侧为自己的前一个元素，最后一个左右均为-1。
	 * 最后统计每个位置的左右有效位置的个数返回。
	 */
	private static int getSubarrayNum(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		//栈内只存放入栈的数的位置
		Stack<List<Integer>> stack = new Stack<>();
		//先加第一个位置
		List<Integer> lst0 = new ArrayList<>();
		lst0.add(0);
		stack.push(lst0);
		//记录每个位置左右俩侧小于自己且距离自己最近的位置。
		int[][] infoArr = new int[arr.length][2];
		for (int i = 1; i < arr.length; i++) {
			int cur = arr[i];
			//大于时 设置栈顶元素信息 弹出 直到不再大于 ，设置完成后将当前元素位置再入栈
			//假设弹出位置为j,j前一个位置为k。k到j之间的数肯定全部大于arr[k]并且大于arr[j],否则kj不会相邻，同时arr[j]>arr[k]。所以arr[k]-arr[j]满足最大和次大在子数组俩端
			while (!stack.isEmpty() && cur < arr[stack.peek().get(0)]) {
				List<Integer> top = stack.pop();
				int leftPos = !stack.isEmpty() ? stack.peek().get(0) : -1;
				//相同数 一起设置 
				for (Integer pos : top) {
					infoArr[pos][0] = leftPos;
					infoArr[pos][1] = i;
				}
			}
			if (!stack.isEmpty() && cur == arr[stack.peek().get(0)]) {
				//相同数并列 加入之前已存在的lst 
				stack.peek().add(i);
			} else {
				//小于或大于 最后统一入栈
				List<Integer> newTop = new ArrayList<>();
				newTop.add(i);
				stack.push(newTop);
			}
		}
		//剩余数右侧统一-1 左侧为前一个数的位置
		while (!stack.isEmpty()) {
			List<Integer> top = stack.pop();
			int leftPos = !stack.isEmpty() ? stack.peek().get(0) : -1;
			for (Integer pos : top) {
				infoArr[pos][0] = leftPos;
				infoArr[pos][1] = -1;
			}
		}
		int count = 0;
		for (int[] info : infoArr) {
			if (info[0] >= 0) {
				count++;
			}
			if (info[1] >= 0) {
				count++;
			}
		}
		return count;
	}
	
	
}
