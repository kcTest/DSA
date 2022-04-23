package com.zkc.binaryTree;

import com.zkc.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 遍历二叉树
 */
public class TreeTraversal {
	
	public static void main(String[] args) {
		Object[] objects = MyUtils.getBinaryTree(18, 10);
		if (objects.length != 2) {
			return;
		}
		MyTreeNode head = (MyTreeNode) objects[0];
		MyUtils.printNodes((List<MyTreeNode>) objects[1]);
		MyUtils.printBinaryTree(head);
		printTreeWithoutRecursion(head);
	}
	
	private static void printTreeWithoutRecursion(MyTreeNode head) {
		System.out.println("InOrder:");
		inOrderNoRecur(head);
		System.out.println();
		System.out.println("======================");
		System.out.println("PreOrder:");
		preOrderNoRecur(head);
		System.out.println();
		System.out.println("======================");
		System.out.println("PostOrder:");
		postOrderNoRecur(head);
	}
	
	/**
	 * 中序遍历二叉树  不使用递归  左->头->右
	 */
	private static void inOrderNoRecur(MyTreeNode head) {
		Stack<MyTreeNode> stack = new Stack<>();
		MyTreeNode cur = head;
		while (cur != null) {
			if (cur.left != null) {
				//只有左侧进栈
				stack.push(cur);
				cur = cur.left;
			} else if (cur.right != null) {
				System.out.printf("%d,", cur.val);
				cur = cur.right;
			} else {
				System.out.printf("%d,", cur.val);
				//左右弹出时均转到右子树不为空的父节点
				while (!stack.isEmpty() && stack.peek().right == null) {
					cur = stack.pop();
					System.out.printf("%d,", cur.val);
				}
				if (stack.isEmpty()) {
					return;
				} else {
					System.out.printf("%d,", stack.peek().val);
					cur = stack.pop().right;
				}
			}
		}
	}
	
	/**
	 * 先序遍历二叉树  不使用递归  头->左->右
	 */
	private static void preOrderNoRecur(MyTreeNode head) {
		Stack<MyTreeNode> stack = new Stack<>();
		MyTreeNode cur = head;
		while (cur != null) {
			System.out.printf("%d,", cur.val);
			if (cur.left != null) {
				stack.push(cur);
				cur = cur.left;
			} else {
				while (!stack.isEmpty() && cur.right == null) {
					cur = stack.pop();
				}
				cur = cur.right;
			}
		}
	}
	
	/**
	 * 后序遍历二叉树  不使用递归  左->右->头
	 */
	private static void postOrderNoRecur(MyTreeNode head) {
		Stack<MyTreeNode> stack = new Stack<>();
		MyTreeNode cur = head;
		while (cur != null) {
			if (cur.left != null) {
				stack.push(cur);
				cur = cur.left;
			} else if (cur.right != null) {
				stack.push(cur);
				cur = cur.right;
			} else {
				System.out.printf("%d,", cur.val);
				while (!stack.isEmpty() && (stack.peek().right == null || stack.peek().right == cur)) {
					cur = stack.pop();
					System.out.printf("%d,", cur.val);
				}
				cur = stack.isEmpty() ? null : stack.peek().right;
			}
		}
	}
	
	/**
	 * 后序遍历二叉树  不使用递归  左->右->头
	 */
	private static void postOrderNoRecur2(MyTreeNode head) {
		if (head == null) {
			return;
		}
		Stack<MyTreeNode> stack1 = new Stack<>();
		Stack<MyTreeNode> stack2 = new Stack<>();
		stack1.push(head);
		while (!stack1.isEmpty()) {
			head = stack1.pop();
			stack2.push(head);
			if (head.left != null) {
				stack1.push(head.left);
			}
			if (head.right != null) {
				stack1.push(head.right);
			}
		}
		while (!stack2.isEmpty()) {
			System.out.printf("%d,", stack2.pop().val);
		}
	}
}
