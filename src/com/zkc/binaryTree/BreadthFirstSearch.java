package com.zkc.binaryTree;

import com.zkc.utils.MyUtils;

import java.util.*;

/**
 * 宽度优先 遍历二叉树
 */
public class BreadthFirstSearch {
	
	public static void main(String[] args) {
		Object[] objects = MyUtils.getBinaryTree(1000, 10);
		if (objects.length != 2) {
			return;
		}
		MyTreeNode head = (MyTreeNode) objects[0];
		MyUtils.printNodes((List<MyTreeNode>) objects[1]);
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
		Queue<MyTreeNode> queue = new LinkedList<>();
		queue.add(head);
		//当前层的节点个数
		int currentLevelCount = queue.size();
		//下一层的节点个数
		int nextLevelCount = 0;
		int nextLevelTailNullCount = 0;
		//记录最大节点数
		int maxWidth = currentLevelCount;
		while (!queue.isEmpty()) {
			MyTreeNode cur = queue.poll();
			if (cur == null) {
				continue;
			} else {
				if (cur.left != null) {
					queue.add(cur.left);
					//左右节点加入队列 增加下层节点个数
					nextLevelCount++;
					nextLevelTailNullCount = 0;
				} else {
					if (nextLevelCount > 0) {
						queue.add(null);
						nextLevelCount++;
						nextLevelTailNullCount++;
					}
				}
				if (cur.right != null) {
					queue.add(cur.right);
					//左右节点加入队列 增加下层节点个数
					nextLevelCount++;
					nextLevelTailNullCount = 0;
				} else {
					if (nextLevelCount > 0) {
						queue.add(null);
						nextLevelCount++;
						nextLevelTailNullCount++;
					}
				}
			}
			currentLevelCount--;
			//当前层的节点正好全部出队列 下一层节点正好全部入队列， 可以比较当前最大值与下一层节点数谁大
			if (currentLevelCount == 0) {
				if ((nextLevelCount - nextLevelTailNullCount) > maxWidth) {
					//更新最大值为下一层节点总数
					maxWidth = nextLevelCount - nextLevelTailNullCount;
				}
				//下一层作为当前层
				currentLevelCount = nextLevelCount;
				//下一层节点数重置，重新开始记录
				nextLevelCount = 0;
				nextLevelTailNullCount = 0;
			}
		}
		return maxWidth;
	}
	
}
