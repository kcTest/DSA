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
		MyUtils.BTDS result = MyUtils.getBinaryTree((int) (Math.random() * 16), 90);
		MyTreeNode head = result.head;
		MyUtils.printNodes(result.nodes);
//		Integer[] arr = new Integer[]{-2147483648};
//		MyTreeNode head = MyUtils.arrayToTreeNode(arr);
		MyUtils.printBinaryTree(head);
		printTreeWithoutRecursion(head);
		System.out.printf("\n\n%s", isValidBST(head, Long.MIN_VALUE, Long.MAX_VALUE));
		System.out.printf("\n\n%s", isValidBST2(head));
		System.out.printf("\n\n%s", isValidBST3(head));
		System.out.printf("\n\n%s", isFullTree(head) != -1);
		System.out.printf("\n\n%s", isBalanced(head) != -1);
		MyTreeNode nodeA = result.nodes.get((int) (Math.random() * result.nodes.size() - 1));
		MyTreeNode nodeB = result.nodes.get((int) (Math.random() * result.nodes.size() / 2 - 1));
		MyUtils.printNodes(result.nodes);
		MyTreeNode ancestor = lowestCommonAncestor(head, nodeA, nodeB);
		System.out.printf("\n\nnodeA:%s,nodeB:%s,ancestor:%s\n",
				nodeA == null ? "null" : nodeA.val,
				nodeB == null ? "null" : nodeB.val,
				ancestor == null ? "null" : ancestor.val);
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
	 * 判断二叉树是否是搜索二叉树  对每个节点 左<头<右
	 * 递归判断左右子树  切到左侧时最小值向下传递 父节点作为最大值；切到右侧时父节点作为最小值，最大值向下传递
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
	
	//	/**
//	 * 判断是否是平衡二叉树 递归  判断 当前节点大于左侧最大 小于右侧最小
//	 */
//	private static Object[] isBalanced2(MyTreeNode head) {
//		if (head == null) {
//			return null;
//		}
//		Object[] left = isBalanced2(head.left);
//		Object[] right = isBalanced2(head.right);
//		boolean isBalanced = (left != null && (left.isBalanced && head.val > left.max))
//				&& (right != null && (right.isBalanced && head.val < right.min));
//		
//		int min = left != null ? Math.min(left.min, head.val) : head.val;
//		min = right != null ? Math.min(right.min, min) : min;
//		int max = left != null ? Math.max(left.max, head.val) : head.val;
//		max = right != null ? Math.max(right.max, max) : max;
//		return new Object[]{isBalanced, min, max};
//	}
	
	/**
	 * 判断二叉树是否是搜索二叉树  对每个节点 左<头<右 中序遍历 判断值递增
	 */
	private static boolean isValidBST2(MyTreeNode head) {
		if (head == null) {
			return true;
		}
		Stack<MyTreeNode> stack = new Stack<>();
		long prev = Long.MIN_VALUE;
		MyTreeNode cur = head;
		while (cur != null || stack.size() > 0) {
			if (cur != null) {
				stack.push(cur);
				cur = cur.left;
			} else {
				cur = stack.pop();
				if (prev >= cur.val) {
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
	private static boolean isValidBST3(MyTreeNode head) {
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
	 * 判断是否是满二叉树  递归
	 */
	private static int isFullTree(MyTreeNode head) {
		if (head == null) {
			return 0;
		}
		int leftDepth = isFullTree(head.left);
		int rightDepth = isFullTree(head.right);
		if (rightDepth == -1 || leftDepth == -1 || leftDepth != rightDepth) {
			return -1;
		}
		return Math.max(leftDepth, rightDepth) + 1;
	}
	
	/**
	 * 判断是否是平衡二叉树 递归 判断左右子树高度差是否超过1。
	 */
	private static int isBalanced(MyTreeNode head, boolean[] arrIsBalanced) {
		if (head == null) {
			return 0;
		}
		int leftDepth = isBalanced(head.left, arrIsBalanced);
		int rightDepth = isBalanced(head.right, arrIsBalanced);
		if (Math.abs(leftDepth - rightDepth) > 1) {
			arrIsBalanced[0] = false;
		}
		return Math.max(leftDepth, rightDepth) + 1;
	}
	
	/**
	 * 判断是否是平衡二叉树 递归 判断左右子树高度差是否超过1。
	 */
	private static int isBalanced(MyTreeNode head) {
		if (head == null) {
			return 0;
		}
		int leftDepth = isBalanced(head.left);
		int rightDepth = isBalanced(head.right);
		if (leftDepth == -1 || rightDepth == -1 || Math.abs(leftDepth - rightDepth) > 1) {
			return -1;
		}
		return Math.max(leftDepth, rightDepth) + 1;
	}
	
	/**
	 * 求两个节点a、b最近公共祖先节点,ab不同且存在   递归
	 * 当前节点等于a或b直接返回当前节点， 左侧包a或b含返回a或b,右侧包含a或b返回a或b,如果左右均包含直接当前节点即为最新公共祖先
	 */
	private static MyTreeNode lowestCommonAncestor(MyTreeNode head, MyTreeNode nodeA, MyTreeNode nodeB) {
		if (head == null || head == nodeA || head == nodeB) {
			return null;
		}
		MyTreeNode nodeInLeft = lowestCommonAncestor(head.left, nodeA, nodeB);
		MyTreeNode nodeInRight = lowestCommonAncestor(head.right, nodeA, nodeB);
		//返回a、b或最近祖先。ab在同一条路径上 head最后更新为a或b；不在同一路线时 一定会出现左右同时不为null且只有回到最近祖先节点时才会出现
		if (nodeInLeft != null && nodeInRight != null) {
			return head;
		}
		return nodeInLeft != null ? nodeInLeft : nodeInRight;
	}
}
