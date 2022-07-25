package com.zkc;

/**
 * 给定一个长度n 表示一共有几位 所有字符都是小写a-z,可以生成长度分别为1、2、...n的字符串序列。
 * 如果把所有字符串按字典序排序，每个字符串都有所在的位置。
 * 给定一个长度n 一个字符串str,求这个字符串在序列中是第几个。
 * 如n=4, 形成的所有按字典序排序的字符串为:
 * a,aa,aaa,aaaa,aaab,...aaaz,aaba,aabb,aabc....aabz....aazz...azzz,
 * b,ba,baa,baaa,baab,...baaz...bazz...bzzz
 * c,ca,caa,caaa,...cbad..czzz
 * ...
 * zzzz
 * 如给定str=aaab,它是序列中的5个,如给定str=bzzz,它是序列中的36558个,
 */
public class SeqInKLenLexicalOrderStrs {
	public static void main(String[] args) {
		int n = (int) (Math.random() * 10) + 1;
		String s = "bzzz";// MyUtils.getAZString(n);
		System.out.println(s);
		System.out.println(getStrOrder(s));
	}
	
	/**
	 * 假设str=cnfde,n=5，求字典序位于当前字符串之前的所有字符串的总个数x，最后再来到字典序的下一位正好是str的位置，str的顺序为x+1。
	 * <p>
	 * 来到i位置,检查还有几个字母在字典序中处于str[i]之前，假如有m个，以这m中的每一个字母替换当前位置的字母的情况下，
	 * [i,n-1]位置形成长度为[1,n-i]的字符串能有几个(相当于固定了字符串[0,i]位置的字母作为前缀)。
	 * 假设当前位置为1，str[i]=n;
	 * ca  作为前缀 第i位确定，剩下3个空位不确定可以任选26个字母,形成的长度范围为[0,3]的字典序字符串个数有:0的长度(只有ca)+1的长度+2的长度+3的长度=1+26+26*26+26*26*26
	 * .
	 * .
	 * .
	 * cm  作为前缀  计算方法与结果与ca相同。固定前缀，剩余空位相同的情况下每种前缀求得的个数都一样。
	 * 算出位置的总个数再加1 正好为字典序中的cn。
	 * 假设当前位置为2，str[i]=b;
	 * cna  作为前缀 作为前缀 第i位确定，剩下2个空位不确定可以任选26个字母,形成的长度范围为[0,2]的字典序字符串个数有:0的长度(只有cna)+1的长度+2的长度=1+26+26*26
	 * .
	 * .
	 * .
	 * cne  作为前缀  计算方法与结果与cna相同。固定前缀，剩余空位相同的情况下每种前缀求得的个数都一样。
	 * 算出位置的总个数再加1 正好为字典序中的cnf。
	 * <p>
	 * 所有位置用相同方法算出返回总个数。
	 */
	private static int getStrOrder(String s) {
		int n = s.length();
		char[] chars = s.toCharArray();
		int order = 0;
		for (int i = 0; i < n; i++) {
			//算完后 再加1为当前前缀的正确字典序
			order += (chars[i] - 'a') * compute(n - i - 1) + 1;
		}
		return order;
	}
	
	
	/**
	 * 求(26^0)+(26^1)+(26^2)+.....+(26^n)
	 */
	private static int compute(int n) {
		//0次方直接返回1，从1开始
		int ret = 1, base = 1, i = 1;
		while (i++ <= n) {
			base *= 26;
			ret += base;
		}
		return ret;
	}
	
}
