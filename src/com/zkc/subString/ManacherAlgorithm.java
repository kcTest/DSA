package com.zkc.subString;

import java.util.HashSet;
import java.util.Set;

public class ManacherAlgorithm {
	
	public static void main(String[] args) {
		//一 当前字符串不在最大回文字符串的左右最大边界之内 对当前字符做扩展检查
		String s1 = "abcd";
		printLongestPalindrome(s1);
		//二 在最大回文字符串以x为中心  x两侧存在较短的关于x对称的回文字符串 而且均位于最大回文字符串的左右边界内
		// 右侧回文字符串的长度与左侧一样
		String s2 = "mfdcdaxadcdfn";
		printLongestPalindrome(s2);
		//三 在最大回文字符串以x为中心 存在两个较短的关于x对称的回文字符串  左侧回文字符串左边界f与最大的左边界f重合 
		// 右侧回文字符串的右边界至少与最大右边界f重合  是否超出右边界需要再扩大检查  
		String s3 = "mfdcdfaxafdcdfan";
		printLongestPalindrome(s3);
		//四 在最大回文字符串以x为中心 存在两个较短的关于x对称的回文字符串  左侧回文字符串左边界a超出与最大的左边界f  
		// 右侧回文字符串的右边界肯定与最大右边界重合 
		String s4 = "mafdcdfaxafdcdfbn";
		printLongestPalindrome(s4);
		String s5 = "abcde##fgffgf##llll##########";
		printLongestPalindrome(s5);
		String s6 = "0112345676543210";
		printLongestPalindrome(s6);
	}
	
	private static void printLongestPalindrome(String s) {
		int[] ret = manacher(s);
		System.out.printf("\nC:%d,R:%d\n", ret[0], ret[1]);
		System.out.println(s);
		int realC = ret[2];
		int start = ret[0] - ret[1] + 1;
		int end = realC == 0 ? ret[0] + ret[1] : ret[0] + ret[1] - 1;
		for (int i = 0; i <= end; i++) {
			if (i < start) {
				System.out.print(" ");
			} else {
				System.out.printf("%s", s.charAt(i));
			}
		}
		System.out.println();
	}
	
	/**
	 * 传统方式从每个位置向两侧展开对比统计
	 * 如果存在一个回文字符串位于另外一个更长的回文字符串之内的情况
	 * manacher可以根据当前位置i关于中心c对称位置的回文字符串的左边界与最大左边界的关系加快计算速度
	 */
	private static int[] manacher(String s) {
		//打印使用
		Set<Integer> insertPos = new HashSet<>();
		//原始字符串数组
		char[] oriChars = s.toCharArray();
		//记录半径最长的回文字符串的中心位置
		int maxC = -1;
		//记录半径最长的回文字符串的半径
		int maxR = 0;
		//回文串长度为偶的情况如abccba bb 如果从每个位置向两侧展开检查 会遗漏中间俩个位置的对比
		//所以生成新的字符串数组 在字符之间及左右两侧插入辅助字符  如果还存在上述情况肯定是原数组中的字符与新插入字符之间的遗漏 不需要统计
		char[] newChars = new char[(oriChars.length << 1) + 1];
		for (int i = 0; i < oriChars.length; i++) {
			newChars[i << 1] = '#';
			insertPos.add(i << 1);
			newChars[(i << 1) + 1] = oriChars[i];
		}
		newChars[newChars.length - 1] = '#';
		//记录每个位置字符的回文半径curR
		int[] radiiRecord = new int[newChars.length];
		for (int i = 0; i < newChars.length; i++) {
			//第一种情况 当前位置大于之前算出的最大右边界 需要从当前位置开始继续向两侧检查
			if (i > maxC + maxR - 1) {
				//自身算一个长度
				int curR = 1;
				int left = i - 1;
				int right = i + 1;
				//不越界的情况下
				while (left >= 0 && right < newChars.length) {
					if (newChars[left--] == newChars[right++]) {
						curR++;
					} else {
						break;
					}
				}
				//记录当前位置的回文半径
				radiiRecord[i] = curR;
				if (curR >= maxR) {
					//记录最大回文半径及位置
					maxC = i;
					maxR = curR;
				}
			} else {//当前位置位于 最大回文字符串的右边界内
				//最大左边界位置
				int leftBoundary = maxC - maxR;
				//i的对称位置 位于c左侧
				int mirroredI = maxC - (i - maxC);
				//对称位置的左边界
				int mirroredLeftBoundary = mirroredI - radiiRecord[mirroredI];
				//左侧对称回文串的左边界在最大左边界之内 左右俩个回文子串长度相同 直接取之前的
				if (leftBoundary < mirroredLeftBoundary) {
					radiiRecord[i] = radiiRecord[mirroredI];
				} else if (leftBoundary == mirroredLeftBoundary) {
					//左侧对称回文串的左边界等于最大左边界 右侧回文子串的长度至少与左侧一样 右边界至少与最大右边界重合，
					// 可以先确定一个半径 再从两端继续向两侧展开检查回文串是否会超出右边界
					int curR = radiiRecord[i] = radiiRecord[mirroredI];
					int left = i - curR;
					int right = i + curR;
					while (left >= 0 && right < newChars.length) {
						if (newChars[left--] == newChars[right++]) {
							curR++;
						} else {
							break;
						}
					}
					radiiRecord[i] = curR;
					if (curR >= maxR) {
						maxC = i;
						maxR = curR;
					}
				} else if (leftBoundary > mirroredLeftBoundary) {
					//左侧对称回文串的左边界超出最大左边界 i的回文半径为右边界到i的长度 不会超出
					radiiRecord[i] = (maxC + (maxR - 1) - i) + 1;
				}
			}
		}
		int[] ret = new int[3];
		//还原为原始数组中的位置及半径
		ret[0] = (maxC - 1) / 2;
		ret[1] = maxR / 2;
		//打印使用 当前位置maxC是否为之前插入的辅助字符的位置
		ret[2] = insertPos.contains(maxC) ? 0 : 1;
		return ret;
	}
}
