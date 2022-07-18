package com.zkc.MononicStack;

import com.zkc.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NearestLessNumPos {
	public static void main(String[] args) {
//		int[] arr = MyUtils.getArray(7, 20);
//		int[] arr = new int[]{3, 2, 0, 6, 5, 0, 15};
		// 54 54 54 41 41 41 41 44[c(4-2)=6] 54 54 54 
		int[] arr = new int[]{4, 5, 4, 5, 4, 5, 4, 1};
		MyUtils.printArr(arr);
		System.out.println(getSubarrayNum(arr));
		int[][] infoArr = getNearestLessNumPos(arr);
		for (int i = 0; i < infoArr.length; i++) {
			int[] info = infoArr[i];
			System.out.print("[");
			System.out.print("[");
			if (info[0] != -1 || info[1] != -1) {
				if (info[0] != -1) {
					for (int j = info[0]; j < i; j++) {
						System.out.printf("%d,", arr[j]);
					}
				}
				System.out.printf("%d,", arr[i]);
				if (info[1] != -1) {
					for (int j = i + 1; j <= info[1]; j++) {
						System.out.printf("%d,", arr[j]);
					}
				}
			}
			System.out.print("]\n");
		}
	}
	
	/**
	 * 子数组左右俩端都是该子数组内的最小值  求这样的子数组的总个数，
	 * <p>
	 * arr[i]作为右侧最小统计个数
	 * 第一遍遍历先从左向右 将元素位置入栈，但要保持栈中每个位置对应的数值是单调递增的。
	 * 遍历过程中 当遇到比栈顶元素大的数arr[i]时，弹出栈顶lst,此时arr[i]与弹出的lst中的每个元素组合可以产生一个
	 * 符合要求的子数组，总次数加上lst的位置个数，如果lst包含的是m个重复元素的位置，这些重复元素任取2个作为左右边界
	 * 组合也是符合要求的子数组，目标次数再加上组合C(m-2)=m*m-1 / 2。
	 * 遍历完成后栈内lst挨个弹出，对每个lst求组合， 只有当lst存放位置个数大于1的时候个数才会大于0。
	 * 以上过程求出了每个位置作为右侧最小值的情况下符合条件的子数组的个数 以及重复元素组合产生的符合条件的子数组的个数
	 * <p>
	 * arr[i]作为左侧最小统计个数
	 * 第二遍从右向左算出自己作为左边界最小值的情况下能够产生的符合要求的子数组个数。但不再计入重复元素组合产生子数组的情况。
	 * 俩遍个数总和返回
	 */
	private static int getSubarrayNum(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		Stack<List<Integer>> stack = new Stack<>();
		int count = 0;
		//arr[i]作为右侧最小 找左侧离自己最近而且比自己大的
		for (int i = 0; i < arr.length; i++) {
			while (!stack.isEmpty() && arr[i] < arr[stack.peek().get(0)]) {
				List<Integer> top = stack.pop();
				count += top.size() + cn2(top.size());
			}
			if (!stack.isEmpty() && arr[i] == arr[stack.peek().get(0)]) {
				stack.peek().add(i);
			} else {
				List<Integer> newTop = new ArrayList<>();
				newTop.add(i);
				stack.push(newTop);
			}
		}
		while (!stack.isEmpty()) {
			count += cn2(stack.pop().size());
		}
		for (int i = arr.length - 1; i >= 0; i--) {
			//逆序 arr[i]作为左侧最小 找右侧离自己最近而且比自己大的
			while (!stack.isEmpty() && arr[i] < arr[stack.peek().get(0)]) {
				count += stack.pop().size();
			}
			if (!stack.isEmpty() && arr[i] == arr[stack.peek().get(0)]) {
				stack.peek().add(i);
			} else {
				List<Integer> newTop = new ArrayList<>();
				newTop.add(i);
				stack.push(newTop);
			}
		}
		return count;
	}
	
	private static int cn2(int m) {
		return (m * (m - 1)) >> 1;
	}
	
	/**
	 * 给定一个数组 对于数组中每个数 求 左右俩侧小于自己且离自己最近的数的位置 某侧如果找不到则为-1
	 * <p>
	 * 建立栈，然后遍历每个位置,如果当前位置元素arr[i]大于栈顶元素值，入栈；如果等于,入栈但是和栈顶元素存入同一列表；如果小于，弹出栈顶元素并开始设置栈顶元素的信息，
	 * 此时栈顶元素右侧离自己最近的值是arr[i],左侧离自己最近的值是栈顶元素前一个位置的元素(此时栈顶元素右侧比自己小左侧也比自己小),弹出栈顶元素，再检查此时的栈顶元素，如果arr[i]还小于它，继续对当前栈顶元素进行
	 * 同样的处理。直到不再小于，arr[i]入栈。该过程使栈内元素始终保持单调递增。
	 * 遍历完成后 对栈内元素 从上往下弹出并设置信息，右侧最小且距离自己最近的没有设置为-1，左侧为自己的前一个元素，最后一个左右均为-1。
	 */
	private static int[][] getNearestLessNumPos(int[] arr) {
		if (arr == null || arr.length == 0) {
			return null;
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
				List<Integer> pop = stack.pop();
				//获取弹出元素左侧信息    取lst中最后一个 为靠后的位置
				int leftPos = !stack.isEmpty() ? stack.peek().get(stack.peek().size() - 1) : -1;
				//相同数 一起设置 
				for (Integer pos : pop) {
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
			int leftPos = !stack.isEmpty() ? stack.peek().get(stack.peek().size() - 1) : -1;
			for (Integer pos : top) {
				infoArr[pos][0] = leftPos;
				infoArr[pos][1] = -1;
			}
		}
		return infoArr;
	}
	
	
}
