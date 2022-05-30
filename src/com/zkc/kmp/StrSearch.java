package com.zkc.kmp;

import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Knuth-Morris-Pratt 字符串搜索算法(或 KMP 算法)在字符串s中找到与字符串w匹配的起始索引m。
 * O(N) n为字符串s的长度
 */
public class StrSearch {
	
	public static void main(String[] args) {
		String s = "ABC ABCDAB ABCDABCDABDE ABCDABD A ABACABABC ";
//		String w = "ABCDABD";
		String w = "ABACABABC";
//		String w = "ABACABAAC";
//		String w = "ABACABABA";
//		String w = "PARTICIPATE IN PARACHUTE";
		int[] ret = kmpSearch(s, w);
		System.out.println(s);
		int prev = 0;
		int prevLen = 0;
		for (int start : ret) {
			if (start > 0) {
				for (int j = 0; j < start - prev - prevLen; j++) {
					System.out.print(" ");
				}
				System.out.print(w);
				prevLen = w.length();
				prev = start;
			}
		}
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
	 * S下次对比的起始位置m为10 直接从W[2]开始对比，但匹配的起始位置m为8
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
	 * S下次对比的起始位置m为17 直接从W[2]开始对比，但匹配的起始位置m为15
	 * <p>==========================
	 * <p>             1         2
	 * <p>m: 01234567890123456789012
	 * <p>S: ABC ABCDAB ABCDABCDABDE
	 * <p>W:                ABCDABD
	 * <p>i:                0123456
	 * <p>==========================
	 * 在W的每一个位置之前的字符中查找 是否存在匹配的前缀和后缀 如果存在算出最大匹配长度填入T[i]
	 * i=0时 A之前不存在字符 没有回溯的可能性 填写-1。
	 * i=1时 B之前只存在前缀A 不存在两个匹配的前缀和后缀 填写0
	 * i=2时 C之前存在前缀A和后缀B 但是不等 填0
	 * 。
	 * i=4时 A之前不存在两个匹配的前缀和后缀 因为当前字符与起始字符A一样 如果填写0 从W[0]开始与S[i]对比仍然不一致，S[i]需要增加来到下一位继续再与W[0]对比。
	 * 这一步可以设置T[i]为-1而不是0 省一步不必要的对比，可以直接来到S[i+1]与W[0]对比。
	 * <p>
	 * i	0	1	2	3	4	5	6	7
	 * W[i]	A	B	C	D	A	B	D
	 * T[i]	-1	0	0	0	-1	0	2	0
	 * <p>==========================
	 * <p>
	 * i	0	1	2	3	4	5	6	7	8	9
	 * W[i]	A	B	A	C	A	B	A	B	C
	 * T[i]	-1	0	-1	1	-1	0	-1	3	2	0
	 * <p>==========================
	 * <p>
	 * i	0	1	2	3	4	5	6	7	8	9
	 * W[i]	A	B	A	C	A	B	A	B	A
	 * T[i]	-1	0	-1	1	-1	0	-1	3	-1	3
	 * <p>==========================
	 * <p>
	 * i	00	01	02	03	04	05	06	07	08	09	10	11	12	13	14	15	16	17	18	19	20	21	22	23	24
	 * W[i]	P	A	R	T	I	C	I	P	A	T	E		I	N		P	A	R	A	C	H	U	T	E
	 * T[i]	-1	0	0	0	0	0	0	-1	0	2	0	0	0	0	0	-1	0	0	3	0	0	0	0	0	0
	 * <p>==========================
	 *
	 * @param s 字符数组 要搜索的文本
	 * @param w 字符数组 要寻找的单词
	 * @return 一个整数数组 P (在S中找到W的位置) 长度为出现的次数
	 */
	private static int[] kmpSearch(String s, String w) {
		char[] S = s.toCharArray();
		char[] W = w.toCharArray();
		//当前选取字符在S中的位置
		int j = 0;
		//当前选取字符在W中的位置
		int k = 0;
		//部分匹配表 备用位置的列表，该表的目标是使S中的字符不被多次匹配。每个位置存放之前字符中匹配的前缀和后缀的最大长度，前缀和后缀不等于字符串本身 也不是空。
		//在某个位置不匹配时根据T中的值调整W或S中对比的位置
		int[] T = getKmpTable(W);
		//如果S存在匹配W的子串 记录起始匹配位置
		int[] P = new int[S.length];
		//W匹配的次数
		int nP = 0;
		while (j < S.length) {
			if (S[j] == W[k]) {
				j++;
				k++;
				if (k == W.length) {
					//匹配完成 继续查找  
					P[nP++] = j - k;
					//T的长度为W.length+1,多出的位置视为空字符来生成k的备选位置 此时k不会越界 并且T[k]不会为-1
					k = T[k];
				}
			} else {
				//从W的起始位置对比 S跳到下一个 
				if (T[k] == -1) {
					j++;
					k = 0;
				} else {
					//跳到W前位置的备选回溯位置再进行对比  S中的字符不变
					k = T[k];
				}
			}
		}
		
		return P;
	}
	
	private static int[] getKmpTable(char[] W) {
		
		int[] T = new int[W.length + 1];
		//T中当前位置
		int pos = 1;
		//W中当前位置的子字符串候选的对比回溯位置 也为当前字符之前存在的匹配的前缀和后缀的最大长度 从0开始
		int cnd = 0;
		T[0] = -1;
		while (pos < W.length) {
			if (W[pos] == W[cnd]) {
				T[pos] = T[cnd];
			} else {
				T[pos] = cnd;
				//
				while (cnd >= 0 && W[pos] != W[cnd]) {
					cnd = T[cnd];
				}
			}
			pos++;
			cnd++;
		}
		T[pos] = cnd;
		return T;
	}
	
}
