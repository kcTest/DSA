package com.zkc.Preprocessing;

import com.zkc.utils.MyUtils;

/**
 * 给定一个数组 每个值代表一个小朋友的分数。
 * 相邻小朋友如果得分一样 分给这俩个小朋友的糖果数量可以相同也可以不相同
 * 如果分数不一样，分高的小朋友要比分低的小朋友分到的糖果多
 * 假设所有小朋友坐成一个环。根据上述规则 分糖果最少需要的数量
 */
public class CircleCandy {
	public static void main(String[] args) {
		int[] arr = MyUtils.getArray(7, 30);
//		int[] arr = new int[]{11,22,20,25,28,22,19};
		MyUtils.printArr(arr);
		System.out.println(minCandy(arr));
	}
	
	/**
	 * 如果当前分数高于 左侧 或 右侧 或 同时高于左右,当前位置i分得糖果数要比左侧i-1或右侧i+1位置分得糖果多1个，如果i+1分数高于i，i+1要比i还要多一个 依次叠加
	 * 如果小于等于 左侧 或 右侧,当前位置至少需要1个，左或右至少需要2个。如果小于等于左右 这个位置只需要一个 不再叠加
	 * 左侧或右侧叠加最高一侧为当前位置最少需要分得糖果个数
	 * 使用数组记录每个位置考虑左侧至少需要分的糖果数 使用另一个数组记录每个位置考虑右侧至少需要分的糖果数
	 * 每个位置取左右最高侧数量 再求和为整体需要的最少糖果数量
	 * 取从一个均小于左右俩侧的位置的分数 构建一个新数组 这个分数作为头部和尾部 因为这个分数固定只需要一个糖果
	 * 遍历不需要考虑循环叠加带来的不确定问题，当前位置=上一位位置+1,而上一个位置的糖果数量又是双向依赖。
	 * 让头尾固定就可以当成普通数组遍历。
	 */
	private static int minCandy(int[] score) {
		int n = score.length;
		int fixedIdx = -1;
		for (int i = 0; i < n; i++) {
			//取同时小于俩侧的位置 用它固定头尾  
			if (score[i] <= score[getPrevIdx(i, n)] && score[i] <= score[getNextIdx(i, n)]) {
				//遇到第一个直接停止
				fixedIdx = i;
				break;
			}
		}
		//取出最低位置的数加在头尾 新数组长度为n-1+2
		int[] newScore = new int[n + 1];
		for (int i = 0, j = fixedIdx; i < n + 1; i++, j = getNextIdx(j, n)) {
			newScore[i] = score[j];
		}
		int[] left = new int[n + 1];
		int[] right = new int[n + 1];
		//从前往后  只看左侧 头部先设置为固定值1
		left[0] = 1;
		for (int i = 1; i < n + 1; i++) {
			left[i] = newScore[i] > newScore[i - 1] ? left[i - 1] + 1 : 1;
		}
		//从后向前  只看右侧 尾部先设置为固定值1
		right[n] = 1;
		for (int i = n - 1; i >= 0; i--) {
			right[i] = newScore[i] > newScore[i + 1] ? left[i + 1] + 1 : 1;
		}
		int minCandy = 0;
		for (int i = 0; i < n + 1; i++) {
			minCandy += Math.max(left[i], right[i]);
		}
		return minCandy;
	}
	
	private static int getPrevIdx(int idx, int n) {
		return idx == 0 ? n - 1 : idx - 1;
	}
	
	private static int getNextIdx(int idx, int n) {
		return idx == n - 1 ? 0 : idx + 1;
	}
}
