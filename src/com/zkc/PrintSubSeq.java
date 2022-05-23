package com.zkc;

/**
 * 打印字符串的子序列  包括空串
 */
public class PrintSubSeq {
	public static void main(String[] args) {
		String str = "abc";
		char[] selected = new char[0];
		printSubSeq(str.toCharArray(), 0, selected);
	}
	
	/**
	 * ori原字符数组
	 * pos当前遍历字符的位置
	 * select已选择的字符集合
	 * 每来到字符串的一个位置 生成两个新的结果继续向下传递，包括加入当前位置字符以及排除当前位置字符两个路线的结果 到终点时打印结果
	 */
	private static void printSubSeq(char[] ori, int pos, char[] select) {
		if (pos == ori.length) {
			//来到末尾输出当前路线下的选择结果
			System.out.printf("(%d)%s, ", select.length, new String(select));
			return;
		}
		//生成新的include存放当前位置字符串  不修改之前的选择结果abc 将新的d加入include 并继续向下传递  
		char[] include = new char[select.length + 1];
		System.arraycopy(select, 0, include, 0, select.length);
		include[include.length - 1] = ori[pos];
		printSubSeq(ori, pos + 1, include);
		
		//生成新的exclude排除当前位置字符串  不修改之前的选择结果abc 并继续向下传递  
		char[] exclude = new char[select.length];
		System.arraycopy(select, 0, exclude, 0, select.length);
		printSubSeq(ori, pos + 1, exclude);
	}
}
