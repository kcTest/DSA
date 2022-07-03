package com.zkc.Preprocessing;

import com.zkc.utils.MyUtils;

/**
 * 字符串由R、G俩种字符组成
 * 1.让字符串全变成R
 * 2.让字符串全变成G
 * 3.让字符串分成两部分，半边为R 右边为G
 * 返回 达成任一目标所需要修改字符的最小个数
 */
public class ColorLr {
	public static void main(String[] args) {
		String s = "GGGGRR";
		System.out.println(s);
		System.out.println(colorLeftRight(s));
	}
	
	/**
	 * 遍历一遍获取R个数 遍历一遍获取G个数
	 * 获取G R中最小值  为1、2两个目标最少修改字符个数
	 * <p>
	 * 分割位置来到0位置 leftG 记录当前位置左侧G(包含自身)的总个数 rightR 记录当前位置右侧R(不包括自身)的总个数 直接取之前遍历时获取的R的总个数
	 * 如果0位置为G leftG为1 rightR不变  如果0位置为R leftG为0 rightR为R-1
	 * 分割位置每次向后移动 之前跳过的字符为G leftG++， 之前跳过的字符为R rightR--  每次比较记录leftG和rightR中的最小值
	 * 返回最小值
	 */
	private static int colorLeftRight(String s) {
		if (s == null || s.length() < 2) {
			return 0;
		}
		char[] arr = s.toCharArray();
		int totalG = 0;
		int totalR = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 'G') {
				totalG++;
			} else {
				totalR++;
			}
		}
		//只修改G或R的最小字符个数
		int min12 = Math.min(totalG, totalR);
		int curLeftG = 0;
		int curRightR = totalR;
		int min3 = arr.length;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 'G') {
				curLeftG++;
			} else {
				curRightR--;
			}
			//需要修改的左侧G的个数和右侧R的个数相加
			min3 = Math.min(min3, curLeftG + curRightR);
		}
		//第三种已经包含了1 2的情况 totalG可以去掉
		return Math.min(min12, min3);
	}
}
