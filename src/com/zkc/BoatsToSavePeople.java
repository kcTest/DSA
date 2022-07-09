package com.zkc;

import com.zkc.utils.MyUtils;

import java.util.Arrays;

/**
 * 给定一个数组people， people[i]表示每个人的体重，
 * 船的最大载重为limit,每个人的体重都不会超过limit,每艘船最多运载2人。
 * 求运载people最少需要几艘船。
 */
public class BoatsToSavePeople {
	public static void main(String[] args) {
//		int[] people = MyUtils.getArray(5, 20, true, false);
//		int limit = (int) (Math.random() * 15) + 1;
		int[] people = new int[]{1, 2};
		int limit = 3;
		MyUtils.printArr(people);
		System.out.printf("limit=%d,boatsNum=%d,", limit, numRescueBoats(people, limit));
	}
	
	
	/**
	 * 先小到大排序
	 * 建立左指针 右指针从俩侧匹配 如果没有找到匹配 右侧当前指向数占用一艘，右指针左移，左指针不动。
	 * 如果匹配 左右同时占用一艘 左右同时向内移动。
	 */
	private static int numRescueBoats(int[] people, int limit) {
		int n = people.length;
		if (n == 1) {
			return 1;
		}
		Arrays.sort(people);
		int left = 0;
		int right = n - 1;
		int count = 0;
		while (left <= right) {
			if (people[right--] + people[left] <= limit) {
				left++;
			}
			count++;
		}
		return count;
	}
}
