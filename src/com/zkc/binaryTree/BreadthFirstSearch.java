package com.zkc.binaryTree;

import com.zkc.utils.MyUtils;

import java.util.*;

/**
 * 宽度优先 遍历二叉树
 */
public class BreadthFirstSearch {
	
	public static void main(String[] args) {
//		Object[] objects = MyUtils.getBinaryTree(1000, 10);
//		if (objects.length != 2) {
//			return;
//		}
//		MyTreeNode head = (MyTreeNode) objects[0];
//		MyUtils.printNodes((List<MyTreeNode>) objects[1]);
		Integer[] arr = new Integer[]{
				1, 5, 8, 9, 7, 7, 8, 1, 4, 8, 1, 9, 0, 8, 7, 1, 7, 4, 2, 9, 8, 2, 4, null, null, 9, null, null, null, 6, 0, 9, 4, 1, 0, 1, 8, 9, 0, 1, 8, 9, 1, 0, 9, 6, 2, 5, null,
				2, 3, 0, 2, 4, 8, 8, 8, 5, 0, 0, 9, 4, 9, 1, null, 0, 7, 2, 2, 3, null, 6, 1, 0, 8, 9, 9, 9, 4, 8, 4, 3, 4, 4, 0, null, null, 8, 3, 8, null, null, 0, null, 0, 4, 9,
				1, 2, null, 4, 4, 0, 4, 3, 5, 5, 7, 4, 1, 6, null, 1, 0, null, null, null, 2, 8, 7, 7, null, null, 0, 2, 5, 5, 9, 3, 3, null, 7, 6, 6, 7, 9, 8, 1, 7, 7, 7, 2, 6,
				null, 7, null, 4, 6, 4, 6, null, null, 9, 1, null, null, null, 5, 5, 5, 4, 2, 2, 8, 5, 1, 1, 3, 1, 3, 7, null, 2, null, 9, 1, 4, 4, 7, 7, null, 1, 5, 6, 2, 7, 3,
				null, 9, 1, null, 2, 4, 4, 8, null, null, 7, null, 6, null, 7, 4, 3, 5, 8, 4, 8, 5, null, null, 8, null, null, null, 4, 4, null, null, null, null, 8, 3, 5, 5,
				null, null, null, 1, 2, 0, null, null, 9, 3, null, 8, 3, 7, 1, 8, 9, 0, 1, 8, 2, null, 4, null, null, 8, null, null, null, null, 2, null, 4, 8, 5, 5, 3, 1, null,
				null, 6, null, 1, null, null, 6, null, null, null, null, 7, 3, null, null, null, 8, 6, 4, null, 6, 9, 0, 7, 8, null, null, 0, 6, 7, null, null, 0, 0, 7, 2, 3, 2,
				null, 0, 2, 3, null, 0, 1, 7, 9, 0, 7, null, null, null, null, 5, 8, 2, 6, 3, 2, 0, 4, null, null, 0, 9, 1, 1, 1, null, 1, 3, null, 7, 9, 1, 3, 3, 8, null, null, null,
				null, 6, null, null, null, null, 9, 8, 1, 3, 8, 3, 0, 6, null, null, 8, 5, 6, 5, 2, 1, null, 5, null, 7, 0, 0, null, 9, 3, 9, null, 3, 0, 0, 9, 1, 7, 0, 2, null, 6, 8, 5,
				null, null, null, null, null, 7, null, 2, 5, null, null, 9, null, null, null, null, null, null, null, null, null, null, null, 4, 1, null, 3, 6, 6, 2, 5, 5, 9,
				null, null, 7, 8, null, null, 2, 7, 3, 7, 2, 5, null, 1, 3, 4, null, null, 8, 3, 6, 9, null, 1, null, null, null, null, 9, 7, 5, 2, null, 5, null, 6, 4, 5, null, 1, 2,
				0, 6, null, 1, 6, null, null, 5, null, 7, 8, 4, 7, 8, 6, 4, null, 5, 6, 7, 9, 1, 0, 4, null, null, null, 6, 4, 8, 4, 5, null, 0, 4, 4, 0, 1, 7, 1, null, 1, null, 3, 6, null,
				null, null, null, 8, null, 5, 0, 7, 5, null, null, 5, 8, null, null, 3, null, null, 8, null, 2, 4, null, null, null, null, null, null, null, 9, null, 9, null, 9,
				null, null, null, null, 7, 1, null, null, 2, null, null, 5, 5, 5, 5, 6, 4, null, null, 1, 6, 4, 0, null, 0, 6, 3, 0, null, 5, 5, null, null, null, null, 2, null, 3, 6,
				null, 3, 0, 5, 0, 1, 0, 3, 4, 9, 9, 2, 7, 3, 8, 6, 9, null, 5, 8, null, null, null, null, 9, 8, 0, 7, null, null, 8, 8, 6, 6, 0, 2, 7, 4, 2, 3, 8, 6, 4, null, 8, null, null,
				null, 2, 0, null, 1, 3, 5, 4, 2, 2, 5, 8, 8, null, 3, 0, null, 1, 6, 0, null, null, 9, null, 2, null, 6, 8, 2, null, null, 5, null, null, null, 9, 6, 6, 4, 2, 0, null,
				null, 1, null, 0, null, null, null, 6, 6, null, null, null, 4, 7, 9, null, 0, 1, null, null, 9, null, null, null, 4, null, 8, null, null, null, null, null, null,
				4, null, 6, null, 3, null, null, 5, 1, 2, 5, null, 0, 7, 8, null, 7, null, null, 4, null, 4, 4, null, 2, null, 6, null, null, null, 7, null, null, null, null, 6, 4,
				null, 6, null, 6, 9, null, null, null, 9, 6, null, 9, null, 3, null, 2, null, 7, 7, null, null, 0, null, 6, 3, null, null, null, null, null, null, 1, null, null,
				null, 6, 9, 7, null, 7, null, 9, 3, 3, null, null, null, null, 4, null, null, 3, null, null, null, 3, 9, null, 0, 3, 1, 9, 6, 7, 9, 4, 8, null, null, 6, null, 1, 3, 7,
				null, null, null, 3, null, 2, null, 8, 1, 1, null, null, 6, null, 7, 3, 5, null, 6, 3, 4, null, null, 5, 7, 1, null, null, 6, 4, 6, null, null, null, null, 5, 7, 0, 7,
				0, null, 5, 8, 5, 5, 4, 5, null, null, null, null, null, null, 1, 7, null, null, 7, null, 9, 9, 6, 4, null, null, 3, 2, 1, null, 0, null, 0, 6, null, null, null, 1, 5,
				null, null, null, 8, null, null, null, null, 3, 4, 8, null, null, 9, 6, 4, null, null, null, null, 8, 9, null, 1, null, null, null, 7, null, null, null, null,
				null, 9, null, null, null, 4, 1, 6, 7, 0, null, null, null, 7, null, null, 8, null, null, null, null, null, null, null, 4, null, 9, null, null, null, null, 3,
				0, 6, null, 5, null, 9, 9, null, null, 4, 3, 4, null, null, null, null, 8, null, 5, null, null, null, null, 5, 2, null, null, null, null, null, null, null, 2,
				null, null, 2, 1, 8, 5, null, 0, null, 0, 3, 2, 4, 5, null, null, null, null, null, 7, null, null, 0, null, 0, null, null, null, 0, 3, 9, null, null, null, null,
				5, null, null, 0, 5, 0, 0, null, 9, null, null, null, null, null, null, null, null, 8, null, 9, 3, 5, 9, 0, 5, 9, null, null, 9, 4, null, 0, 2, 0, null, null, 7,
				null, 7, null, 5, 7, 8, 7, null, null, null, 3, 0, 3, null, null, null, null, null, 4, 5, null, null, 2, 3, null, 2, null, null, 7, null, null, 9, null, null,
				9, 7, 1, null, null, 1, 6, 1, 8, null, null, 5, null, null, 3, 7, 9, 6, null, null, null, null, 1, null, null, null, 3, 7, 3, 2, 3, 3, null, 1, null, null, null,
				1, null, null, 4, 3, 4, 8, 7, null, 0, 3, 0, null, 1, 1, null, null, null, null, null, 5, null, 6, 0, null, 3, 1, null, 6, null, null, 4, 0, 1, null, 6, 1, null, null,
				9, 6, 4, 9, 0, 8, 9, 3, 3, 6, null, null, null, null, null, null, null, null, null, null, null, null, 2, null, null, null, null, null, 8, 5, 8, 3, 5, 4, null, 6,
				null, 0, null, null, 6, null, 4, 3, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 7, 3, null, null, 1, null, 2, 4, null,
				null, null, 6, null, null, null, 6, null, 5, null, null, null, null, 1, null, null, 3, null, 1, null, 7, 1, null, null, 7, 1, 3, 4, 8, null, null, null, null,
				null, 4, null, null, 4, null, null, null, 7, null, 6, null, null, 1, null, null, null, 7, 3, 3, null, null, null, null, 3, 0, null, null, 4, null, null, null,
				null, null, null, null, null, null, null, 8, null, null, 9, null, null, 6, 6, 5, 2, null, 8, 3, 8, null, null, null, null, 6, 7, 0, null, null, null, null, 1, 1,
				5, null, 0, 5, null, 5, null, null, null, 1, 2, null, 2, 9, 1, null, 2, 4, 1, null, null, null, 1, 8, 4, 4, 5, 2, null, null, 6, 4, 7, 5, 2, 9, null, 4, null, null, null,
				null, null, 3, null, null, 5, 9, null, null, null, null, 9, null, 9, null, null, null, 2, null, 1, 9, null, null, null, null, null, 1, 9, 3, null, null, 1, 9, null,
				5, 2, 1, 0, null, null, 1, 9, 8, 4, 7, null, null, 5, 7, null, null, null, null, 1, 2, 8, null, 6, 0, null, null, null, null, 0, null, null, null, 6, null, 2, 3, 0, 9,
				null, null, 1, 4, 6, null, 8, null, null, 5, null, 3, 0, null, 6, null, null, null, null, null, 2, null, null, null, null, null, null, 2, 5, 8, 6, 9, null, null, null,
				8, null, null, 9, 6, null, null, null, null, 3, null, null, null, null, 9, null, null, 2, null, null, null, null, null, null, 8, 8, null, null, null, null, null,
				9, null, 6, null, 2, 5, null, null, 1, 2, null, 4, null, null, 4, null, null, 3, 5, null, 3, 3, null, null, 1, null, null, null, null, 4, null, 2, 3, null, 4, 5, 3, null,
				7, null, null, null, 7, 6, null, null, 1, 3, null, 4, 9, 8, null, null, 0, null, 3, 4, null, 8, null, 1, null, null, 2, 2, null, null, 4, null, null, null, 3, null,
				null, 2, null, null, null, 4, null, 5, null, null, null, null, 2, null, 5, null, null, null, null, null, null, 2, 7, 5, null, 6, null, null, null, null, 2, null, 0,
				null, 3, null, 1, null, 9, 4, null, 3, null, null, null, null, null, null, null, 5, 5, 7, null, null, 1, null, 4, 6, null, null, null, 2, null, 5, 9, 0, 6, 2, null,
				null, null, null, null, null, null, null, null, null, null, null, 5, null, 7, null, 2, 9, null, null, 1, null, null, null, 1, 6, null, 6, null, null, 0, 8, null,
				4, null, null, null, null, 4, null, null, 0, null, 6, 0, null, null, null, 4, null, null, null, null, null, 0, null, null, null, null, null, null, null, null,
				null, null, null, null, 0, 5, 4, 2, 6, 4, 5, 3, 4, null, null, 5, null, null, null, null, 4, null, null, 3, 6, 2, 0, null, 6, 6, null, null, null, null, 0, 6, null,
				null, null, 3, 9, 4, null, null, null, null, null, 0, null, null, 6, 7, 0, null, 9, 2, null, 3, 3, null, null, 8, null, 3, null, null, null, 8, 5, 3, null, 2, 4,
				null, 9, 6, 9, null, null, null, null, 6, null, 6, null, 5, 3, null, null, null, null, 4, null, null, null, 9, 0, 9, 7, 1, 1, null, 1, null, 1, 6, null, 5, null,
				6, null, null, 1, null, null, null, null, null, null, 5, null, null, null, null, null, 3, null, 6, 1, null, 0, 2, null, null, 0, null, null, 0, null, null, null,
				null, null, 3, null, null, 8, null, null, 5, 3, 3, null, null, null, null, null, null, null, 3, null, null, 0, 8, 7, null, null, 8, 1, null, null, null, null, null,
				null, 7, null, null, null, null, null, null, null, null, null, null, null, 5, 2, null, 2, 6, null, null, null, null, null, null, null, 1, 5, 0, null, null, 2, null,
				7, null, null, 6, null, null, null, null, null, null, null, null, null, null, null, null, null, 8, null, null, null, null, 3, null, null, 4, null, null, 2, null,
				null, null, null, 0, 3, null, null, null, null, null, 7, null, 8, null, null, null, null, 8, 5, null, 3, 4, null, null, null, 8, null, null, null, null, null, null,
				null, null, null, 3, 7, null, null, null, 4, 0, 3, null, null, 6, null, null, null, null, null, null, null, null, null, null, null, null, 8, null, null, null,
				null, null, 2, null, null, null, null, null, null, null, null, null, 0, null, null, null, 2, null, null, null, 8, 2, null, null, null, null, null, null
				, null, 8, null, null, null, null, null, null, null, null, null, null, 2, null, null, null, 2, 5, null, null, null, null, null, null, null, null, null,
				null, null, 2, null, null, null, null, null, 8, null, null, null, null, null, null, null, null, null, null, 0, 5};
		MyTreeNode head = MyUtils.arrayToTreeNode(arr);
		levelOrderTraversal(head);
		System.out.printf("\n\n%d\n", getMaxWidth(head));
		System.out.printf("\n%d\n", widthOfBinaryTree(head));
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
	
}
