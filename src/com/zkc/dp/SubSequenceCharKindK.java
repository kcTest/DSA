package com.zkc.dp;

import com.zkc.utils.MyUtils;

/**
 * 给定字符串(只包含小写字母)和一个正数k
 * 求字符串的子序列中满足 字符种数为k 的子序列一共有几个
 */
public class SubSequenceCharKindK {
	public static void main(String[] args) {
		String s = MyUtils.getAZString(20);
		int k = (int) (Math.random() * 10) + 1;
		System.out.printf("s=%s,k=%d,count1=%d,count2=%d\n", s, k, getSubSequenceNum(s, k), getSubSequenceNum2(s, k));
		for (int i = 0; i < 10000; i++) {
			s = MyUtils.getAZString(11);
			int count1 = getSubSequenceNum(s, k);
			int count2 = getSubSequenceNum2(s, k);
			if (count1 != count2) {
				System.out.println("error");
				System.out.printf("s=%s,k=%d,count1=%d,count2=%d\n", s, k, count1, count2);
			}
		}
	}
	
	private static int getSubSequenceNum2(String s, int k) {
		char[] charArr = s.toCharArray();
		int[] arr = new int[26];
		for (char c : charArr) {
			arr[c - 'a']++;
		}
		//横坐标表示要选取的字符在arr中的位置  纵坐标表示当前还有剩几个字符要选  
		int rowLen = arr.length + 1;
		int colLen = k + 1;
		int[][] record = new int[rowLen][colLen];
		//不需要再选的单元格为1种
		for (int i = 0; i < rowLen; i++) {
			record[i][0] = 1;
		}
		record[arr.length - 1][0] = 1;
		//从下往上 从左往右  
		//依赖前一行的俩个位置 最后一行除了第一个位置 其余全是0 不需要设置 从倒数第二行开始
		for (int i = rowLen - 2; i >= 0; i--) {
			for (int j = 1; j < colLen; j++) {
				record[i][j] = record[i + 1][j] + ((1 << arr[i]) - 1) * record[i + 1][j - 1];
			}
		}
		//目标位置
		return record[0][k];
	}
	
	/**
	 * 假如字符串=caacccfffcccbbbddd，k=3。选出acd时，可能的数量为选取不同位置a组合出长度分别为1、2...count(a)个字符串所产生的总个数
	 * 再乘以下一个字符c用该方法算出的总个数再乘以d用该方法算出的总个数。结果与每种字符所处前后顺序无关。
	 * <p>
	 * <p>
	 * 先用数组存放字符串中所有字符出现的次数。再对每一个位置进行遍历。进行选取或不选取处理。将俩种路线的总和相加返回。
	 * 如果选取当前位置字母,再去下一个位置 剩余可选种类-1；如果不选当前位置字母,再去下一个位置 剩余可选种类不变；
	 * <p>
	 * 如果选取 当前字符凑出的子序列长度可能为1、2...count(a)，计算出每种长度下的字符的组合个数相加,为当前字符能产生的总个数
	 */
	private static int getSubSequenceNum(String s, int k) {
		char[] charArr = s.toCharArray();
		int[] arr = new int[26];
		for (char c : charArr) {
			arr[c - 'a']++;
		}
		//递归表示从当前位置开始选取或不选取 一共选出k种字符 可能的个数。
		return sln(arr, 0, k);
	}
	
	private static int sln(int[] arr, int i, int restKind) {
		if (i == arr.length) {
			//所有位置遍历完成 来到末尾 如果还有要选的  说明当前路线下无法选出  
			return restKind == 0 ? 1 : 0;
		}
		
		//当前字符不选的路线 种类不变
		int unselect = sln(arr, i + 1, restKind);
		int select = 0;
		//当前字符要在字符串中存在
		if (arr[i] > 0) {
			//当前字符的可能选择为 从1选到arr[i]的长度 所有位置字符组合的个数相加 cn-1+cn-2+....+cn-arr[i]=2^n-cn0  
			//再乘以下一个字符的选择可能性
			select = ((1 << arr[i]) - 1) * sln(arr, i + 1, restKind - 1);
		}
		//返回俩种路线下总个数
		return select + unselect;
	}
	
}
