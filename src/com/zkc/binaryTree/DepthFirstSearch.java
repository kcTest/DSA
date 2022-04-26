package com.zkc.binaryTree;

import com.zkc.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 深度优先 遍历二叉树
 */
public class DepthFirstSearch {
	
	public static void main(String[] args) {
		Object[] objects = MyUtils.getBinaryTree(30, 10);
		if (objects.length != 2) {
			return;
		}
		Integer[] arr = new Integer[]{-2147483648};
		MyTreeNode head = MyUtils.arrayToTreeNode(arr);
//		MyTreeNode head = (MyTreeNode) objects[0];
//		MyUtils.printNodes((List<MyTreeNode>) objects[1]);
		MyUtils.printBinaryTree(head);
		printTreeWithoutRecursion(head);
		System.out.printf("\n\n%s", isValidBST(head, Long.MIN_VALUE, Long.MAX_VALUE));
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
		//遍历到空节点
		while (cur != null || !stack.isEmpty()) {
			if (cur != null) {
				//不为空进栈 转到左侧
				stack.push(cur);
				cur = cur.left;
			} else {
				//当前空节点 为叶节点左侧或右侧空节点 父节点为叶子节点 
				//弹出栈顶节点 转到节点右侧 
				cur = stack.pop();
				System.out.printf("%d,", cur.val);
				cur = cur.right;
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
				//转到右侧不输出头
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
	
	/**
	 * 判断二叉树是否是搜索二叉树  对每个节点 左<头<右 中序遍历 判断值递增
	 */
	private static boolean isSearchBinaryTree(MyTreeNode head) {
		if (head == null) {
			return false;
		}
		List<MyTreeNode> stack = new ArrayList<>();
		List<Integer> values = new ArrayList<>();
		MyTreeNode cur = head;
		while (cur != null) {
			if (cur.left != null) {
				stack.add(cur);
				cur = cur.left;
			} else if (cur.right != null) {
				values.add(cur.val);
				cur = cur.right;
			} else {
				values.add(cur.val);
				while (stack.size() > 0 && (stack.get(stack.size() - 1).right == null)) {
					values.add(stack.remove(stack.size() - 1).val);
				}
				if (stack.size() == 0) {
					break;
				}
				cur = stack.get(stack.size() - 1).right;
				values.add(stack.remove(stack.size() - 1).val);
			}
		}
		for (int i = 0; i < values.size() - 1; i++) {
			if (values.get(i) > values.get(i + 1)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 判断二叉树是否是搜索二叉树  对每个节点 左<头<右 中序遍历 判断值递增
	 */
	private static boolean isValidBST(MyTreeNode head) {
		if (head == null) {
			return true;
		}
		List<MyTreeNode> stack = new ArrayList<>();
		long prev = Long.MIN_VALUE;
		MyTreeNode cur = head;
		while (cur != null || stack.size() > 0) {
			if (cur != null) {
				if (cur.left != null && cur.left.val >= cur.val) {
					return false;
				}
				stack.add(cur);
				cur = cur.left;
			} else {
				cur = stack.remove(stack.size() - 1);
				if (prev >= cur.val) {
					return false;
				}
				if (cur.right != null && cur.right.val <= cur.val) {
					return false;
				}
				prev = cur.val;
				cur = cur.right;
			}
		}
		return true;
	}
	
	/**
	 * 判断二叉树是否是搜索二叉树  对每个节点 左<头<右 中序遍历 判断值递增
	 */
	private static boolean isValidBST(MyTreeNode head, long min, long max) {
		if (head == null) {
			return true;
		}
		if (head.val <= min || head.val >= max) {
			return false;
		}
		return isValidBST(head.left, min, head.val) && isValidBST(head.right, head.val, max);
	}
}
