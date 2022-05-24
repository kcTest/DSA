package com.zkc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 打印元素全排列
 */
public class PrintPermute {
	public static void main(String[] args) {
		int[] src = new int[]{1, 2, 3, 4};
		List<List<Integer>> permute = printPermute(src);
		for (List<Integer> lst : permute) {
			System.out.print("[");
			for (int i = 0; i < lst.size(); i++) {
				Integer integer = lst.get(i);
				if (i != lst.size() - 1) {
					System.out.printf("%d,", integer);
				} else {
					System.out.printf("%d]\n", integer);
				}
			}
		}
	}
	
	private static List<List<Integer>> printPermute(int[] nums) {
		List<List<Integer>> lst = new ArrayList<>();
		Integer[] select = new Integer[0];
		permute(nums, 0, select, lst);
		return lst;
	}
	
	/**
	 * @param ori    元素数组
	 * @param pos    遍历位置
	 * @param select 已选排列
	 * @param lst    结果集
	 *               每来到一个位置  复制上次已选排列  将当前位置依次替换为未选的剩余元素  每次选定一个元素时将其放在已选排列的后面
	 *               此时确定了一个已遍历元素的排列 将已排列元素向下传递 继续向下一个位置遍历  每种选择确定一个路线到终点将排列返回
	 */
	private static void permute(int[] ori, int pos, Integer[] select, List<List<Integer>> lst) {
		if (pos == ori.length) {
			//遍历完成后将当前路线产生的排列加入到结果集
			lst.add(Arrays.asList(select));
			return;
		}
		//来到当前元素位置  将上次已选的元素排列 复制一份  再追加当前元素
		Integer[] cur = new Integer[pos + 1];
		System.arraycopy(select, 0, cur, 0, select.length);
		//当前位置元素可能为剩余元素中的任意一个
		for (int i = pos; i < ori.length; i++) {
			//原始数组的当前位置挨个交换为剩余元素  产生的结果 继续向下传递
			swap(ori, pos, i);
			cur[cur.length - 1] = ori[pos];
			permute(ori, pos + 1, cur, lst);
			//向后递归时后续元素的位置会发生交换  遍历完成回退到当前位置时再将后面的位置恢复
			swap(ori, i, pos);
		}
	}
	
	private static void swap(int[] ori, int pos, int i) {
		int temp = ori[pos];
		ori[pos] = ori[i];
		ori[i] = temp;
	}
}
