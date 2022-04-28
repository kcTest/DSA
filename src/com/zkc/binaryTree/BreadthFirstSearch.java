package com.zkc.binaryTree;

import com.zkc.utils.MyUtils;

import java.util.*;

/**
 * 宽度优先 遍历二叉树
 */
public class BreadthFirstSearch {
	
	public static void main(String[] args) {
		MyUtils.BTDS result = MyUtils.getCompleteBinaryTree((int) (Math.random() * 16), 10);
		MyTreeNode head = result.head;
		MyUtils.printNodes(result.nodes);
//		Integer[] arr = new Integer[]{1, 9, 7, 5, 6, 8, null, 9, 2, 9, 4, null, null};
//		MyTreeNode head = MyUtils.arrayToTreeNode(arr);
		levelOrderTraversal(head);
		System.out.printf("\n\n%d\n", getMaxWidth(head));
		System.out.printf("\n%d\n", widthOfBinaryTree(head));
		System.out.printf("\n%s\n", isCompleteTree(head));
		System.out.printf("\n%s\n", isFullTree(head));
	}
	
	/**
	 * 每层的节点从左到右遍历完成后再到下一层
	 * 先将头节点加入队列。判断队列不为空输出队列的头部节点 如果该节点有左右子节点 左右依次加入队列 ，重复操作。
	 */
	private static void levelOrderTraversal(MyTreeNode head) {
		if (head == null) {
			return;
		}
		Queue<MyTreeNode> queue = new LinkedList<>();
		queue.add(head);
		while (!queue.isEmpty()) {
			MyTreeNode cur = queue.poll();
			System.out.printf("%d,", cur.val);
			if (cur.left != null) {
				queue.add(cur.left);
			}
			if (cur.right != null) {
				queue.add(cur.right);
			}
		}
	}
	
	/**
	 * 求二叉树最大宽度，获取每层的节点数，返回最大值
	 * [4,3,6,2,6,4,null,null,null,8,8,6,2,null,null,null,null,null,6]
	 * 4|36|264|8862|6
	 * 返回4
	 * 宽度优先搜索过程中完成统计
	 */
	private static int getMaxWidth(MyTreeNode head) {
		if (head == null) {
			return 0;
		}
		Queue<MyTreeNode> queue = new LinkedList<>();
		queue.add(head);
		//当前层的节点个数
		int currentLevelCount = queue.size();
		//下一层的节点个数
		int nextLevelCount = 0;
		//记录最大节点数
		int maxWidth = currentLevelCount;
		while (!queue.isEmpty()) {
			MyTreeNode cur = queue.poll();
			currentLevelCount--;
			if (cur.left != null) {
				queue.add(cur.left);
				//左右节点加入队列 增加下层节点个数
				nextLevelCount++;
			}
			if (cur.right != null) {
				queue.add(cur.right);
				//左右节点加入队列 增加下层节点个数
				nextLevelCount++;
			}
			//当前层的节点正好全部出队列 下一层节点正好全部入队列， 可以比较当前最大值与下一层节点数谁大
			if (currentLevelCount == 0) {
				if (nextLevelCount > maxWidth) {
					//更新最大值为下一层节点总数
					maxWidth = nextLevelCount;
				}
				//下一层作为当前层
				currentLevelCount = nextLevelCount;
				//下一层节点数重置，重新开始记录
				nextLevelCount = 0;
			}
		}
		return maxWidth;
	}
	
	/**
	 * 求二叉树最大宽度，获取每层的节点数，每层起始和结束节点不为空，两端之间的空节点也算作节点数，返回最大值
	 * [1,2,3,4,5,6,7,null,9,10,11,null,null,null,14,null]
	 * 1|23|4567|9 10 11 null null 14
	 * 返回6
	 * 宽度优先搜索过程中完成统计
	 */
	private static int widthOfBinaryTree(MyTreeNode head) {
		if (head == null) {
			return 0;
		}
		//从左到右弹出   非空节点
		Queue<MyTreeNode> nodeQueue = new LinkedList<>();
		nodeQueue.add(head);
		//从左到右弹出   非空节点的序号 
		Queue<Integer> seqQueue = new LinkedList<>();
		seqQueue.add(0);
		//当前层的节点个数
		int currentLevelCount = nodeQueue.size();
		//下一层起始位置
		int nextLevelStart = 0;
		//下一层结束位置
		int nextLevelEnd = 0;
		//下一层的节点个数
		int nextLevelCount = 0;
		//记录最大节点数
		int maxWidth = currentLevelCount;
		while (!nodeQueue.isEmpty()) {
			MyTreeNode cur = nodeQueue.poll();
			int parentSeq = seqQueue.poll();
			if (cur.left != null) {
				int leftChildSeq = parentSeq * 2 + 1;
				nodeQueue.add(cur.left);
				seqQueue.add(leftChildSeq);
				if (nextLevelCount++ == 0) {
					nextLevelStart = leftChildSeq;
				}
				nextLevelEnd = leftChildSeq;
			}
			if (cur.right != null) {
				int rightChildSeq = parentSeq * 2 + 2;
				nodeQueue.add(cur.right);
				seqQueue.add(rightChildSeq);
				if (nextLevelCount++ == 0) {
					nextLevelStart = rightChildSeq;
				}
				nextLevelEnd = rightChildSeq;
			}
			if (--currentLevelCount == 0) {
				maxWidth = Math.max(nextLevelEnd - nextLevelStart + 1, maxWidth);
				currentLevelCount = nextLevelCount;
				nextLevelCount = 0;
			}
		}
		return maxWidth;
	}
	
	/**
	 * 判断是否是完全二叉树 宽度优先 判断序号连续
	 */
	private static boolean isCompleteTree2(MyTreeNode head) {
		if (head == null) {
			return true;
		}
		Queue<MyTreeNode> nodeQueue = new LinkedList<>();
		Queue<Integer> seqQueue = new LinkedList<>();
		nodeQueue.add(head);
		seqQueue.add(0);
		int prevSeq = 0;
		while (!nodeQueue.isEmpty()) {
			MyTreeNode cur = nodeQueue.poll();
			Integer curSeq = seqQueue.poll();
			if (curSeq > prevSeq + 1) {
				return false;
			}
			prevSeq = curSeq;
			if (cur.left != null) {
				nodeQueue.add(cur.left);
				seqQueue.add(curSeq * 2 + 1);
			}
			if (cur.right != null) {
				nodeQueue.add(cur.right);
				seqQueue.add(curSeq * 2 + 2);
			}
		}
		return true;
	}
	
	/**
	 * 判断是否是完全二叉树 宽度优先 判断
	 * 无左有右、缺失左或右的节点之后的节点全为不是叶节点，出现这两种情况不为完全二叉树
	 */
	private static boolean isCompleteTree(MyTreeNode head) {
		if (head == null) {
			return true;
		}
		boolean beginLeafNode = false;
		Queue<MyTreeNode> nodeQueue = new LinkedList<>();
		nodeQueue.add(head);
		while (!nodeQueue.isEmpty()) {
			MyTreeNode cur = nodeQueue.poll();
			if (cur.left == null && cur.right != null || beginLeafNode && cur.left != null) {
				return false;
			}
			if (cur.left != null) {
				nodeQueue.add(cur.left);
			}
			if (cur.right != null) {
				nodeQueue.add(cur.right);
			}
			if (cur.left == null || cur.right == null) {
				beginLeafNode = true;
			}
		}
		return true;
	}
	
	/**
	 * 判断是否是满二叉树 宽度优先 判断n=2^(depth-1),n为当前层节点个数,depth为当前层的深度。
	 */
	private static boolean isFullTree(MyTreeNode head) {
		if (head == null) {
			return true;
		}
		Queue<MyTreeNode> nodeQueue = new LinkedList<>();
		nodeQueue.add(head);
		int currentLevelCount = nodeQueue.size();
		int currentLevelDepth = currentLevelCount;
		int nextLevelCount = 0;
		while (!nodeQueue.isEmpty()) {
			MyTreeNode cur = nodeQueue.poll();
			if (cur.left != null) {
				nodeQueue.add(cur.left);
				nextLevelCount++;
			}
			if (cur.right != null) {
				nodeQueue.add(cur.right);
				nextLevelCount++;
			}
			if (--currentLevelCount == 0) {
				if (nextLevelCount > 0 && Math.pow(2, ++currentLevelDepth - 1) != nextLevelCount) {
					return false;
				}
				currentLevelCount = nextLevelCount;
				nextLevelCount = 0;
			}
		}
		return true;
	}
	
}
