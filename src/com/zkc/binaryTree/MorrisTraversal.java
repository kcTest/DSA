package com.zkc.binaryTree;

import com.zkc.utils.MyUtils;

public class MorrisTraversal {
	
	public static void main(String[] args) {
		MyUtils.BTDS ret = MyUtils.getCompleteBinaryTree(12, 20);
		MyUtils.printNodes(ret.nodes);
		MyTreeNode head = ret.head;
		System.out.println("Morris:");
		morris(head);
		System.out.println("\nPreOrder:");
		preOrder(head);
		System.out.println("\nInOrder:");
		inOrder(head);
		System.out.println("\nPostOrder:");
		postOrder(head);
	}
	
	/**
	 * <p> 1、当前节点cur有左子树
	 * <p>    1.1、找到<b>左子树中最右子节点rightMost</b> 该节点的右指针为null(第一次遍历到)或者为cur(第二次遍历到)
	 * <p>      1.1.1 如果rightMost的右指针为null:将右指针指向cur，然后cur下移指向其左子节点。
	 * <p>      1.1.2 如果rightMost的右指针为cur:说明为第二次遍历到当前节点 将右指针恢复为null,cur下移指向其右子节点。
	 * <p> 2、当前节点没有右子树
	 * <p>    2.1、将cur直接指向其右子节点
	 * <p>
	 * cur初始指向根节点 重复上述步骤 直到cur为null时停止。rightMost为最右叶节点
	 * time complexity O(N)  space complexityO(1)
	 * 避免使用 调用堆栈及消耗内存和时间的 递归。利用叶节点记录父节点地址
	 */
	private static void morris(MyTreeNode head) {
		if (head == null) {
			return;
		}
		MyTreeNode cur = head;
		MyTreeNode rightMost;
		while (cur != null) {
			System.out.printf("%d,", cur.val);
			if (cur.left != null) {
				rightMost = cur.left;
				while (rightMost.right != null && rightMost.right != cur) {
					rightMost = rightMost.right;
				}
				if (rightMost.right == null) {
					//当前节点第一次处理
					rightMost.right = cur;
					cur = cur.left;
				} else if (rightMost.right == cur) {
					rightMost.right = null;
					cur = cur.right;
				}
			} else {
				cur = cur.right;
			}
		}
	}
	
	/**
	 * morris的遍历顺序中 被cur指向2次的节点只在第一次打印
	 */
	private static void preOrder(MyTreeNode head) {
		if (head == null) {
			return;
		}
		MyTreeNode cur = head;
		MyTreeNode rightMost;
		while (cur != null) {
			if (cur.left != null) {
				rightMost = cur.left;
				while (rightMost.right != null && rightMost.right != cur) {
					rightMost = rightMost.right;
				}
				if (rightMost.right == null) {
					System.out.printf("%d,", cur.val);
					rightMost.right = cur;
					cur = cur.left;
				} else if (rightMost.right == cur) {
					rightMost.right = null;
					cur = cur.right;
				}
			} else {
				System.out.printf("%d,", cur.val);
				cur = cur.right;
			}
		}
		
	}
	
	/**
	 * morris的遍历顺序中 被cur指向2次的节点只在第一次打印
	 */
	private static void inOrder(MyTreeNode head) {
		if (head == null) {
			return;
		}
		MyTreeNode cur = head;
		MyTreeNode rightMost;
		while (cur != null) {
			if (cur.left != null) {
				rightMost = cur.left;
				while (rightMost.right != null && rightMost.right != cur) {
					rightMost = rightMost.right;
				}
				if (rightMost.right == null) {
					rightMost.right = cur;
					cur = cur.left;
				} else if (rightMost.right == cur) {
					System.out.printf("%d,", rightMost.right.val);
					rightMost.right = null;
					cur = cur.right;
				}
			} else {
				System.out.printf("%d,", cur.val);
				cur = cur.right;
			}
		}
	}
	
	/**
	 * 当前节点第二次被cur指到 并再次检查并恢复左子树最右子节点的右指针后，逆序打印cur节点的左子树上的所有右边界节点，然后cur再跳到右子节点
	 */
	private static void postOrder(MyTreeNode head) {
		if (head == null) {
			return;
		}
		MyTreeNode cur = head;
		MyTreeNode rightMost;
		while (cur != null) {
			if (cur.left != null) {
				rightMost = cur.left;
				while (rightMost.right != null && rightMost.right != cur) {
					rightMost = rightMost.right;
				}
				if (rightMost.right == null) {
					rightMost.right = cur;
					cur = cur.left;
				} else if (rightMost.right == cur) {
					rightMost.right = null;
					printRightEdgeInLeft(cur.left);
					cur = cur.right;
				}
			} else {
				cur = cur.right;
			}
		}
		printRightEdgeInLeft(head);
	}
	
	/**
	 * 从左子树根节点开始 连续俩次翻转链表 第二次打印
	 */
	private static void printRightEdgeInLeft(MyTreeNode start) {
		MyTreeNode prev = null;
		MyTreeNode next;
		MyTreeNode cur2 = start;
		boolean print = false;
		while (cur2 != null) {
			if (print) {
				System.out.printf("%d,", cur2.val);
			}
			next = cur2.right;
			cur2.right = prev;
			prev = cur2;
			cur2 = next;
			if (cur2 == null && !print) {
				cur2 = prev;
				prev = null;
				print = true;
			}
		}
	}
}
