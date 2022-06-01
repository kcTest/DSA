package com.zkc.subString;

/**
 * Knuth-Morris-Pratt 字符串搜索算法(或 KMP 算法)在字符串s中找到与字符串w匹配的起始索引m。
 */
public class KmpSearch {
	
	public static void main(String[] args) {
		String s = "ABC ABCDAB  ABACABABC ABCDABCDABDE ABACABABA PARTICIPATE IN PARACHUTEPARTICIPATE IN PARACHUTE ABCDABD  ";
//		String w = "ABC";
		String w = "ABCDABD";
//		String w = "ABACABABC";
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
	 * <p>
	 * i	0	1	2	3	4	5	6	7
	 * W[i]	A	B	C	D	A	B	D
	 * T[i]	-1	0	0	0	0	1	2	0
	 * <p>
	 * O(N)  n为字符串s的长度
	 *
	 * @param s 字符数组 要搜索的文本
	 * @param w 字符数组 要寻找的单词
	 * @return 一个整数数组 P (在S中找到W的位置) 长度为出现的次数
	 */
	private static int[] kmpSearch(String s, String w) {
		if (s == null || s.length() == 0 || w == null || w.length() == 0 || w.length() > s.length()) {
			return new int[0];
		}
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
			//相等同时移动指针
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
				//如果W的0位置已经不相同  S从当前字符直接跳到下一个 再与W0位置开始对比
				if (T[k] == -1) {
					j++;
					k = 0;
				} else {
					//跳到W前位置的备选回溯位置再进行对比  S中的当前对比字符不变 通过T可以减少不必要的对比 提高时间
					k = T[k];
				}
			}
		}
		
		return P;
	}
	
	/**
	 * O(m)
	 */
	private static int[] getKmpTable(char[] W) {
		//存放W中每个字符的候选对比位置 也是当前字符之前能找到的前缀的下一个位置 也是当前字符之前存在的匹配的俩个前缀和后缀的长度  当S[j]与W[k]不匹配时 使用候选位置再与S[j]对比
		//长度加一 当S中能够找到W俩次及以上时会用到  当W第一次匹配完成后借助最后一个设置的候选位置继续寻找下一个
		int[] T = new int[W.length + 1];
		//0位置之前没有字符  直接设置-1 对比不匹配时移动到S[++j]
		T[0] = -1;
		//1位置之前只有一个字符 找不到俩个匹配的前缀和后缀 长度为0  对比不匹配的时移动到W[0]从W头部开始与S[j]对比
		T[1] = 0;
		//代表W中当前字符的前一个字符的候选位置T[pos-1]或不断向前能够找到的候选位置的候选位置
		int cnd = 0;
		//直接从2开始
		int pos = 2;
		while (pos < T.length) {
			//当前字符的前一个字符是否等于其候选位置字符  如果相等 当前字符的候选位置增加 相当于前缀和后缀的长度分别扩大
			//abxabc c的前一个字符为b b的候选位置为1 此时b等于1位置 所以c的候选位置为2 
			if (W[pos - 1] == W[cnd]) {
				T[pos++] = ++cnd;
			} else {
				//adxabc  c的前一个字符为b b的候选位置为1 此时b不等于1位置的d 所以将对比位置从1改为d的候选位置,下次就可以让b与d的候选位置继续比较
				//如果还不相等继续取候选位置的候选位置
				if (cnd > 0) {
					cnd = T[cnd];
				} else {
					//如果对比位置的候选位置不大于0  不能再继续往前取0位置的候选位置 没有找到前缀和后缀 当前位置设置为0
					T[pos++] = 0;
				}
			}
		}
		return T;
	}
}
