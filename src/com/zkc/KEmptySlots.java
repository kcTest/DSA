package com.zkc;

/**
 * 有n个灯泡 编号1-n, 每天打开一个。
 * 给定一个数组bulbs每个位置i的值表示第(i+1)天打开的是第 bulbs[i] 个灯泡
 * 给定一个整数K 如果某天存在俩个被打开灯泡中间存在K个灯泡是关闭状态 返回最早出现这种情况的天数。
 */
public class KEmptySlots {
	public static void main(String[] args) {
		int[] bulbs = new int[]{1, 2, 3};
		int k = 1;
		System.out.printf("k=%d,minDayNumber=%d\n", k, kEmptySlots(bulbs, k));
	}
	
	/**
	 * 看中间的值是否高于俩端
	 * <p>
	 * 从1->n观察每个灯泡的开启时间 如果存在符合条件情况，中间的K个灯泡的开启时间一定会比俩端的灯泡的开启时间都要晚。
	 * 新建数组arr2 按编号顺序记录每个灯泡的开启时间 遍历这个数组 从头开始左侧left指针初始指向0 right指针初始指向left+k+1位置
	 * 用mid指针挨个检查left到right之间的K个灯泡的开启时间是否都比left和right的开启时间要大。
	 * 1、如果都大说明这K+2个灯泡符合要求 left和right中的最大值作为结果记录,此时mid来到right-1位置。因为中间k个位置
	 * 的灯泡开启时间都比right位置的大，所以再以中间k个灯泡做开头检查肯定不符合要求,所以left直接换到right,而right指针再来到
	 * left+k+1位置。
	 * 2、如果在中间的j位置发现比left或right位置的开启时间小，停止检查。left+1到j-1的位置也不能作为新的left。left选择j作为新的left,
	 * right来到left+k+1位置。
	 * 每次获取的结果取最小值返回。
	 */
	private static int kEmptySlots(int[] bulbs, int k) {
		if (bulbs.length < 2) {
			return -1;
		}
		if (k > bulbs.length - 2) {
			return -1;
		}
		int n = bulbs.length;
		//每个位置i表示第(i+1)个灯泡是在第onDays[i]天被打开
		int[] onDays = new int[n];
		for (int i = 0; i < bulbs.length; i++) {
			onDays[bulbs[i] - 1] = i + 1;
		}
		int minDayNumber = Integer.MAX_VALUE;
		int left = 0, right = left + k + 1, mid = 1;
		while (right <= n - 1) {
			int checkPos = left + 1;
			//开启时间大于俩端
			while (checkPos <= right - 1 && onDays[checkPos] > onDays[left] && onDays[checkPos] > onDays[right]) {
				checkPos++;
			}
			//说明中间全部符合要求
			if (checkPos == right) {
				minDayNumber = Math.min(minDayNumber, right);
				//right-1开启时间大于right  不能作为新的left  再向后一位  用right作为新left 
				left = right;
			} else {
				//第一个不符合要求的位置 此时checkPos位置的开启时间小于right  可以作为新的left
				left = checkPos;
			}
			right = left + k + 1;
		}
		return minDayNumber == Integer.MAX_VALUE ? -1 : minDayNumber;
	}
}
