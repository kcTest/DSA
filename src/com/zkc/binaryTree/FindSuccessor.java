package com.zkc.binaryTree;

import com.zkc.utils.MyUtils;

import java.io.FileOutputStream;
import java.io.FileWriter;
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
		List<MyNewTreeNode> successors = getInOrderSuccessor(node);
		if (successors != null && successors.size() > 0) {
			successors.remove(0);
		}
		MyUtils.printNewNodes(successors);
		
		
		Integer[] arr = new Integer[]{2, null, 3};
		MyTreeNode node2 = MyUtils.arrayToTreeNode(arr);
		MyTreeNode target = inOrderBstSuccessor(node2, node2);
		System.out.printf("\n%s%n", target == null ? "null" : target.val);
	}
	
	/**
	 * 特殊结构二叉树 获取所有后继节点 中序遍历的后续节点
	 */
	private static List<MyNewTreeNode> getInOrderSuccessor(MyNewTreeNode node) {
		if (node == null) {
			return null;
		}
		List<MyNewTreeNode> lst = new ArrayList<>();
		MyNewTreeNode cur = node;
		while (cur != null) {
			if (cur.left != null && cur != node) {
				cur = cur.left;
			} else if (cur.right != null) {
				//无左有右树
				lst.add(cur);
				cur = cur.right;
			} else {
				//叶节点 左或右
				lst.add(cur);
				while (cur.parent != null && (cur.parent.right == null || cur == cur.parent.right)) {
					if (cur == cur.parent.left) {
						//左节点的父节点
						lst.add(cur.parent);
					}
					cur = cur.parent;
				}
				if (cur.parent != null) {
					//左节点的父节点
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
	 * 普通结构二叉搜索树求下一个后继节点 中序遍历的下一个节点
	 * 所有节点值唯一 递归找到大于指定节点值的最小值所在节点
	 */
	public static MyTreeNode inOrderBstSuccessor(MyTreeNode root, MyTreeNode p) {
		if (root == null) {
			return null;
		}
		MyTreeNode left = inOrderBstSuccessor(root.left, p);
		MyTreeNode right = inOrderBstSuccessor(root.right, p);
		//左侧最小 如果大于直接返回，然后头部 最后右部
		if (left != null && left.val > p.val) {
			return left;
		}
		if (root.val > p.val) {
			return root;
		}
		if (right != null && right.val > p.val) {
			return right;
		}
		return null;
	}
}
