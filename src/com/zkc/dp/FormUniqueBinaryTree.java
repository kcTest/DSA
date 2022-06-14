package com.zkc.dp;

/**
 * 给定节点数 返回可以形成的所有二叉树结构的个数
 */
public class FormUniqueBinaryTree {
	public static void main(String[] args) {
		int nodeCount = 4;
		System.out.println(sln1(nodeCount));
		System.out.println(sln2(nodeCount));
	}
	
	private static int sln2(int nodeCount) {
		if (nodeCount < 0) {
			return 0;
		}
		if (nodeCount == 0) {
			return 1;
		}
		if (nodeCount == 1) {
			return 1;
		}
		if (nodeCount == 2) {
			return 2;
		}
		//记录不同数量的节点可以形成的二叉树结构数量
		int[] record = new int[nodeCount + 1];
		record[0] = 1;
		record[1] = 1;
		record[2] = 2;
		for (int i = 3; i < record.length; i++) {
			for (int j = 0; j <= i - 1; j++) {
				record[i] += record[j] * record[i - 1 - j];
			}
		}
		return record[nodeCount];
	}
	
	private static int sln1(int nodeCount) {
		if (nodeCount < 0) {
			return 0;
		}
		//空树
		if (nodeCount == 0) {
			return 1;
		}
		if (nodeCount == 1) {
			return 1;
		}
		if (nodeCount == 2) {
			return 2;
		}
		int ret = 0;
		for (int i = 0; i <= nodeCount - 1; i++) {
			//不包括根节点 左树节点数量可能值从0到n-1 右树节点数量n-1到0
			int left = sln1(i);
			int right = sln1(nodeCount - 1 - i);
			//左右两侧可能性相乘
			ret += left * right;
		}
		return ret;
	}
	
}
