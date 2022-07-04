package com.zkc.binaryTree;

import com.zkc.utils.MyUtils;

/**
 * 给定二叉树的头节点 返回二叉树中的任意俩点间的最长路径 每个节点只能经过一次
 */
public class MaxDistance {
	public static void main(String[] args) {
		MyUtils.BTDS ds = MyUtils.getBinaryTree(20, 10);
		MyUtils.printNodes(ds.nodes);
		MyTreeNode head = ds.head;
		System.out.println(getMaxDistance(head));
	}
	
	/**
	 * 最长路径存在于左树或右树 此时路径不经过根节点。最长路径经过根节点，最长路径为左树最大高度+1+右树最大高度
	 * 返回三种情况的最大值
	 */
	private static int getMaxDistance(MyTreeNode head) {
		if (head == null) {
			return 0;
		}
		//第一二种情况 最长路径存在于左子树或右子树。 递归 求左树的最长路径及右树的最长路径的最大者 递归到子树 子树的根节点也需要相同的做法来收集其子树的路径和高度信息
		int maxDistance = Math.max(getMaxDistance(head.left), getMaxDistance(head.right));
		//第三种情况 最长路径经过根节点 由左右子树最大高度及根节点高度1组成 递归 求左子树和右子树的最大高度  
		int maxHeight = getMaxHeight(head.left) + getMaxHeight(head.right);
		//第三种情况 左子树和右子树的最大高度相加 再加上跟节点一个高度 算最长路径。再与第一二种情况比较。
		return Math.max(maxDistance, maxHeight + 1);
	}
	
	private static int getMaxHeight(MyTreeNode head) {
		if (head == null) {
			return 0;
		}
		int leftHeight = getMaxHeight(head.left);
		int rightHeight = getMaxHeight(head.right);
		//只返回较大一侧的高度
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
}
