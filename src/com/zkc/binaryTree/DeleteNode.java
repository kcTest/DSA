package com.zkc.binaryTree;

import com.zkc.utils.MyUtils;

/**
 * 给定一个二叉搜索树的根节点 root 和一个值 key，节点值唯一，删除二叉搜索树中的key对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * 一般来说，删除节点可分为两个步骤：
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 */
public class DeleteNode {
	public static void main(String[] args) {
		Integer[] arr = new Integer[]{2, 1};
		MyTreeNode head = MyUtils.arrayToTreeNode(arr);
		MyUtils.printBinaryTree(head);
		int key = 2;
		head = deleteNode(head, key);
		MyUtils.printBinaryTree(head);
	}
	
	/**
	 * 找到可以顶替当前位置的节点  先找右侧最小节点 没有再找左侧最大节点
	 */
	private static MyTreeNode deleteNode(MyTreeNode head, int key) {
		if (head == null) {
			return null;
		}
		MyTreeNode cur = head;
		MyTreeNode parent = null;
		while (cur != null) {
			if (cur.val == key) {
				MyTreeNode substitute = findMinInRight(cur.right);
				if (substitute == null) {
					substitute = findMaxInLeft(cur.left);
					//替代节点从左子树找到 说明当前节点右子树不存在 只需要把当前节点的左子树给替代节点
					if (substitute != null) {
						if (substitute != cur.left) {
							substitute.left = cur.left;
						}
					}
				} else {
					//替代节点从右子树找到 需要把当前节点的左子树给替代节点  替代节点不为右子树根的情况下可以把右子树也给替代节点
					if (substitute != cur.right) {
						substitute.right = cur.right;
					}
					substitute.left = cur.left;
				}
				if (parent != null) {
					//当前节点的父节点指向替代节点
					if (cur == parent.left) {
						parent.left = substitute;
					} else {
						parent.right = substitute;
					}
				}
				//当前节点需要断开连接
				cur.left = null;
				cur.right = null;
				if (cur == head) {
					//如果在头结点发现 用替代结点作为新的头结点
					head = substitute;
				}
				break;
			} else {
				parent = cur;
				cur = key < cur.val ? cur.left : cur.right;
			}
		}
		return head;
	}
	
	private static MyTreeNode findMinInRight(MyTreeNode start) {
		if (start == null) {
			return null;
		}
		MyTreeNode parent = null;
		while (start.left != null) {
			parent = start;
			start = start.left;
		}
		if (parent != null) {
			parent.left = start.right;
		}
		return start;
	}
	
	private static MyTreeNode findMaxInLeft(MyTreeNode start) {
		if (start == null) {
			return null;
		}
		MyTreeNode parent = null;
		while (start.right != null) {
			parent = start;
			start = start.right;
		}
		if (parent != null) {
			parent.right = start.left;
		}
		return start;
	}
	
}
