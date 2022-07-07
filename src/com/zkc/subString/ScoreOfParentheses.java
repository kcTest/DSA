package com.zkc.subString;

/**
 * 给定括号字符串
 * ()为2分 (())为3分  ((()))为4分每包裹一层分值+1 ,每包裹一层分值+1
 * ()()分值为2*2 (())()为2*3 每连接一段分值为各部分相乘
 * (()())()(())->(2*2+1)*2*3->30
 * 给定一个正确组合的字符串 返回字符串的分数
 */
public class ScoreOfParentheses {
	public static void main(String[] args) {
		String s = "(()())()(())";
//		String s = "(())()";
		System.out.println(getScore(s.toCharArray(), 0)[0]);
	}
	
	/**
	 * 每次遇到左括号 递归去求i+1位置开始遇到)位置的分值,用当前分值*子过程返回分值,然后跳到子过程返回的结束位置的下一位。每次遇到右括号不再往下遍历 返回分值。
	 */
	private static int[] getScore(char[] charArr, int pos) {
		int score = 1;
		//为)或者来到末尾 结束
		while (pos < charArr.length && charArr[pos] != ')') {
			if (charArr[pos] == '(') {
				int[] info = getScore(charArr, pos + 1);
				//)(  (( 
				score *= (info[0] + 1);
				pos = info[1];
			}
			pos++;
		}
		//包含当前左括号内部总分值及结束位置
		return new int[]{score, pos};
	}
}
