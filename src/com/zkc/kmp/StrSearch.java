package com.zkc.kmp;

/**
 * Knuth-Morris-Pratt 字符串搜索算法(或 KMP 算法)在字符串s中找到与字符串w匹配的起始索引m。
 * O(N) n为字符串s的长度
 */
public class StrSearch {
	
	public static void main(String[] args) {
//		String s = "ABC ABCDAB ABCDABCDABDE";
//		String w = "ABCDABD";
//		String s = "aaaaaaaaaac";
//		String w = "caaaaaaaaa";
		String s = "aaaaaaaaaaaaaaaaaaaaaaaac";
		String w = "aaac";
		int start = strSearch(s, w);
		System.out.println(s);
		for (int i = 0; i < start; i++) {
			System.out.print(" ");
		}
		System.out.println(w);
		System.out.println(start);
	}
	
	/**
	 * <p>             1         2
	 * <p>m: 01234567890123456789012
	 * <p>S: ABC ABCDAB ABCDABCDABDE
	 * <p>W: ABCDABD
	 * <p>i: 0123456
	 * <p>==========================
	 * <p>             1         2
	 * <p>m: 01234567890123456789012
	 * <p>S: ABC ABCDAB ABCDABCDABDE
	 * <p>W:    ABCDABD
	 * <p>i:    0123456
	 * <p>==========================
	 * <p>             1         2
	 * <p>m: 01234567890123456789012
	 * <p>S: ABC ABCDAB ABCDABCDABDE
	 * <p>W:     ABCDABD
	 * <p>i:     0123456
	 * <p>==========================
	 * <p>             1         2
	 * <p>m: 01234567890123456789012
	 * <p>S: ABC ABCDAB ABCDABCDABDE
	 * <p>W:         ABCDABD
	 * <p>i:         0123456
	 * <p>==========================
	 * <p>             1         2
	 * <p>m: 01234567890123456789012
	 * <p>S: ABC ABCDAB ABCDABCDABDE
	 * <p>W:           ABCDABD
	 * <p>i:           0123456
	 * <p>==========================
	 * <p>             1         2
	 * <p>m: 01234567890123456789012
	 * <p>S: ABC ABCDAB ABCDABCDABDE
	 * <p>W:            ABCDABD
	 * <p>i:            0123456
	 * <p>==========================
	 * <p>             1         2
	 * <p>m: 01234567890123456789012
	 * <p>S: ABC ABCDAB ABCDABCDABDE
	 * <p>W:                ABCDABD
	 * <p>i:                0123456
	 */
	private static int strSearch(String s, String w) {
		//记录字符串s当前对比的字符位置
		int si = 0;
		//记录字符串w当前对比的字符位置
		int wi = 0;
		//记录s匹配整个w的起始位置
		int start = -1;
		//记录字符串s中与w第一个字符相同的第二个字符的位置 如果当前匹配过程失败 作为下次的起始匹配位置
		int nextSi = 0;
		//w的当前搜索位置不越界 s的剩余字符个数不小于w的剩余字符个数
		while (wi < w.length() && w.length() - wi <= s.length() - si) {
			//在s中 start位置之后再次遇到的w起始字符 记录该位置 每次匹配过程只记录一次
			if (start > -1 && nextSi == 0 && s.charAt(si) == s.charAt(start)) {
				nextSi = si;
			}
			//对应位置匹配
			if (s.charAt(si) == w.charAt(wi)) {
				//如果还未设置 s中的当前位置为匹配w的起始索引
				if (start == -1) {
					start = si;
				}
				//匹配的情况下对比位置均向后移动
				si++;
				wi++;
			} else {
				//分为匹配刚开始或匹配过程中
				//如果有记录优先从该位置重新对比
				if (nextSi > 0) {
					si = nextSi;
					//重置 以便记录下一个
					nextSi = 0;
				} else {
					//直接跳到下一个
					si++;
				}
				//s匹配w的起始位置重置 w的起始位置再从头开始
				start = -1;
				wi = 0;
			}
		}
		return start;
	}
}
