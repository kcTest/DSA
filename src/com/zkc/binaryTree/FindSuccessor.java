package com.zkc.binaryTree;

import com.zkc.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

public class FindSuccessor {
	public static void main(String[] args) {
		MyUtils.NewBTDS result = MyUtils.getNewBinaryTree((int) (Math.random() * 32), 10);
		MyUtils.printNewNodes(result.nodes);
		MyUtils.printNewBinaryTree(result.head);
		MyNewTreeNode node = result.nodes.get((int) (Math.random() * result.nodes.size() - 1));
		System.out.printf("node:%s\n", node == null ? "null" : node.val);
		System.out.println("successors:");
		List<MyNewTreeNode> successors = getSuccessor(node);
		if (successors != null && successors.size() > 0) {
			successors.remove(0);
		}
		MyUtils.printNewNodes(successors);
//		Integer[] arr = new Integer[]{2, null, 3};
//		MyTreeNode node = MyUtils.arrayToTreeNode(arr);
		MyNewTreeNode target = inOrderSuccessor(result.head, node);
		System.out.println(target == null ? "null" : target.val);
	}
	
	/**
	 * 特殊结构二叉树 获取所有后继节点 中序遍历的后续节点
	 */
	private static List<MyNewTreeNode> getSuccessor(MyNewTreeNode node) {
		if (node == null) {
			return null;
		}
		List<MyNewTreeNode> lst = new ArrayList<>();
		MyNewTreeNode cur = node;
		while (cur != null) {
			if (cur.left != null && cur != node) {
				cur = cur.left;
			} else if (cur.right != null) {
				lst.add(cur);
				cur = cur.right;
			} else {
				lst.add(cur);
				while (cur.parent != null && (cur.parent.right == null || cur == cur.parent.right)) {
					if (cur == cur.parent.left) {
						lst.add(cur.parent);
					}
					cur = cur.parent;
				}
				if (cur.parent != null) {
					lst.add(cur.parent);
					cur = cur.parent.right;
				} else {
					if (!lst.contains(cur.right)) {
						cur = cur.right;
					} else {
						cur = null;
					}
				}
			}
		}
		
		return lst;
	}
	
	/**
	 * 普通结构二叉树求下一个后继节点 中序遍历的下一个节点
	 */
	public static MyNewTreeNode inOrderSuccessor(MyNewTreeNode root, MyNewTreeNode p) {
		if (root == null) {
			return null;
		}
		MyNewTreeNode left = inOrderSuccessor(root.left, p);
		MyNewTreeNode right = inOrderSuccessor(root.right, p);
		if (left != null && left.val > p.val) {
			return left;
		}
		if (root.val > p.val) {
			return root;
		}
		if (right != null && right.val > p.val) {
			return right;
		}
		return root;
	}
}
