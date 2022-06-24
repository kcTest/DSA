package com.zkc.binaryTree;

import com.zkc.utils.MyUtils;

/**
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的key对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
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
	
	private static MyTreeNode deleteNode(MyTreeNode head, int key) {
		if (head == null) {
			return null;
		}
		MyTreeNode cur = head;
		MyTreeNode parent = null;
		boolean left = false;
		while (cur != null) {
			if (cur.val == key) {
				MyTreeNode substitute = findMinInRight(cur.right, key);
				if (substitute == null) {
					substitute = findMaxInLeft(cur.left, key);
					if (substitute == null) {
						if (parent != null) {
							if (left) {
								parent.left = null;
							} else {
								parent.right = null;
							}
						} else {
							head = null;
						}
					} else {
						if (substitute != cur.left) {
							substitute.left = cur.left;
							cur.left = null;
							cur.right = null;
							if (parent != null) {
								if (left) {
									parent.left = substitute;
								} else {
									parent.right = substitute;
								}
							}
						} else {
							cur.left = null;
							cur.right = null;
							if (parent != null) {
								if (left) {
									parent.left = substitute;
								} else {
									parent.right = substitute;
								}
							}
						}
						if (cur == head) {
							head = substitute;
						}
					}
				} else {
					if (substitute != cur.right) {
						substitute.left = cur.left;
						substitute.right = cur.right;
						cur.left = null;
						cur.right = null;
						if (parent != null) {
							if (left) {
								parent.left = substitute;
							} else {
								parent.right = substitute;
							}
						}
					} else {
						substitute.left = cur.left;
						cur.left = null;
						cur.right = null;
						if (parent != null) {
							if (left) {
								parent.left = substitute;
							} else {
								parent.right = substitute;
							}
						}
					}
					if (cur == head) {
						head = substitute;
					}
				}
				break;
			} else {
				if (key <= cur.val) {
					parent = cur;
					cur = cur.left;
					left = true;
				} else {
					parent = cur;
					cur = cur.right;
					left = false;
				}
			}
		}
		return head;
	}
	
	private static MyTreeNode findMinInRight(MyTreeNode start, int key) {
		if (start == null) {
			return null;
		}
		MyTreeNode parent = null;
		while (start.left != null) {
			parent = start;
			start = start.left;
			if (start.val == key) {
				deleteNode(start, key);
			}
		}
		if (parent != null) {
			if (start.right != null) {
				parent.left = start.right;
			} else {
				parent.left = null;
			}
		}
		return start;
	}
	
	private static MyTreeNode findMaxInLeft(MyTreeNode start, int key) {
		if (start == null) {
			return null;
		}
		MyTreeNode parent = null;
		while (start.right != null) {
			parent = start;
			start = start.right;
			if (start.val == key) {
				deleteNode(start, key);
			}
		}
		if (parent != null) {
			if (start.left != null) {
				parent.right = start.left;
			} else {
				parent.right = null;
			}
		}
		return start;
	}
	
}
