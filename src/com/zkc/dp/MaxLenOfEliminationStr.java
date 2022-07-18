package com.zkc.dp;

/**
 * 一个由0、1、2、3四种字符组成的字符串str,可以生成很多子序列，返回全消子序列的最大长度。
 * 一个子序列的消除规则如下:
 * 1、在某个子序列中 如果1的左边有0 那么这两个字符01可以消除
 * 2、在某个子序列中 如果3的左边有2 那么这两个字符23可以消除
 * 3、当这个子序列的某个部分消除之后，认为其它字符会自动连在一起，如果符合1、2条件可以继续消除
 * 如果某个子序列通过最优良的方式可以都消掉，这样的子序列叫做全消子序列。
 * 字符串长度<=200
 */
public class MaxLenOfEliminationStr {
	public static void main(String[] args) {
//		int len = (int) (Math.random() * 200) + 1;
//		char[] chars = new char[len];
//		for (int i = 0; i < len; i++) {
//			chars[i] = (char) ((int) (Math.random() * 4) + 48);
//		}
//		int maxLen2 = getMaxLen2(chars);
//		System.out.println(String.valueOf(chars));
//		System.out.printf("maxLen2=%d", maxLen2);
		for (int j = 0; j < 10000; j++) {
			int len = (int) (Math.random() * 40) + 1;
			char[] chars = new char[len];
			for (int i = 0; i < len; i++) {
				chars[i] = (char) ((int) (Math.random() * 4) + 48);
			}
			int maxLen2 = getMaxLen2(chars);
			int maxLen = getMaxLen(chars, 0, chars.length - 1);
			if (maxLen2 != maxLen) {
				System.out.println(String.valueOf(chars));
				System.out.printf("maxLen2=%d,maxLen=%d\n", maxLen2, maxLen);
				break;
			}
		}
	}
	
	private static int getMaxLen2(char[] arr) {
		int n = arr.length;
		//行代表开头位置l 列代表结尾位置r 每个位置代表以l开头r结尾的子序列中 最长全消子序列的长度
		int[][] record = new int[n][n];
		//每个位置依赖左及下方信息。所以从左往右,从下往上设置信息。
		for (int r = 0; r < n; r++) {
			for (int l = r - 1; l >= 0; l--) {
				if (l == r - 1) {
					record[l][r] = (arr[l] == '0' && arr[r] == '1' || arr[l] == '2' && arr[r] == '3') ? 2 : 0;
					continue;
				}
				int unselect = record[l + 1][r];
				if (arr[l] == '1' || arr[l] == '3') {
					record[l][r] = unselect;
					continue;
				}
				int select = 0;
				char match = arr[l] == '0' ? '1' : '3';
				for (int t = l + 1; t <= r; t++) {
					if (arr[t] == match) {
						select = Math.max(select, (l + 1 >= t - 1 ? 0 : record[l + 1][t - 1]) + 2 + (t + 1 >= r ? 0 : record[t + 1][r]));
					}
				}
				record[l][r] = Math.max(select, unselect);
			}
		}
		//以0位置开头n-1结尾的子序列能够找到的最长全消子序列的长度
		return record[0][n - 1];
	}
	
	/**
	 * 每个位置选择当前字符或不选择当前字符的情况下，形成的以l开头r结尾的子序列中，最长全消子序列的长度
	 */
	private static int getMaxLen(char[] arr, int l, int r) {
		//起始位置初始从0 结尾为n-1
		//如果开头位置大于等于结尾位置，长度小于2无法形成子序列
		if (l >= r) {
			return 0;
		}
		//相邻长度为2，判断是不是01、23的情况  
		if (l == r - 1) {
			return (arr[l] == '0' && arr[r] == '1' || arr[l] == '2' && arr[r] == '3') ? 2 : 0;
		}
		//不选当前字符,获取以下一位置开头的子序列 结尾不变的路线中能获取的最长全消子序列的长度
		int unselect = getMaxLen(arr, l + 1, r);
		//如果选择当前字符，但是当前字符不是0 2，无法与后续子序列形成可消除的字符对。不会增加长度，同不选择当前字符效果一样。 
		if (arr[l] == '1' || arr[l] == '3') {
			return unselect;
		}
		int select = 0;
		//当前字符为0或2， 在后续字符中寻找与当前字符匹配的位置。
		char match = arr[l] == '0' ? '1' : '3';
		for (int i = l + 1; i <= r; i++) {
			if (arr[i] == match) {
				//找到后20123012 如2找到3，算出中间字符串012的最长全消子序列的长度，再加上 外层的23长度为2，再加上 3后面的字符串的最长全消子序列的长度
				//所有匹配位置的算出的长度取最长
				select = Math.max(select, getMaxLen(arr, l + 1, i - 1) + 2 + getMaxLen(arr, i + 1, r));
			}
		}
		//俩种情况选最长的
		return Math.max(unselect, select);
	}
	
	
}
