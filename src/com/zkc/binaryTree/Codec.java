package com.zkc.binaryTree;

import com.zkc.utils.MyUtils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Codec {
	
	public static void main(String[] args) {
//		MyUtils.BTDS result = MyUtils.getBinaryTree(15, 20);
//		MyUtils.printNodes(result.nodes);
//		String s = serialize(result.head);
		Integer[] arr = new Integer[]{8, 7, 5, 13, 15, 17, 10, 7, 9, null, null, null, 11, 12, null,
				null, 9, 10, null, 13, 14, null, 16};
		MyTreeNode headTemp = MyUtils.arrayToTreeNode(arr);
		String s = serialize(headTemp);
		System.out.printf("serialize:\n%s\n", s);
		MyTreeNode head = deserialize(s);
		String s2 = serialize(head);
		System.out.printf("deserialize:\n%s\n", s2);
	}
	
	/**
	 * 二叉树序列化成字符串  宽度优先遍历  从左到右
	 * 每个节点转化后的字符串格式拼在一起:
	 * 非空值格式为: 值+空格, 空值格式为: 小数点+连续遇到的空值个数+空格
	 */
	private static String serialize(MyTreeNode head) {
		if (head == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Queue<MyTreeNode> queue = new LinkedList<>();
		queue.add(head);
		sb.append(head.val).append(" ");
		int zeroCount = 0;
		while (!queue.isEmpty()) {
			MyTreeNode cur = queue.remove();
			if (cur.left != null) {
				if (zeroCount > 0) {
					//追加之前收集的空节点字符串
					sb.append(".").append(zeroCount).append(" ");
					zeroCount = 0;
				}
				sb.append(cur.left.val).append(" ");
				queue.add(cur.left);
			} else {
				zeroCount++;
			}
			if (cur.right != null) {
				if (zeroCount > 0) {
					sb.append(".").append(zeroCount).append(" ");
					zeroCount = 0;
				}
				sb.append(cur.right.val).append(" ");
				queue.add(cur.right);
			} else {
				zeroCount++;
			}
		}
		return sb.toString();
	}
	
	/**
	 * 将序列化的字符串还原成二叉树，返回头节点
	 * 字符串使用空格拆分 不带点的为节点值  带点的为连续空节点个数
	 * 从头节点开始宽度优先  从左到右，对每个节点的左右子节点从解析后的数组中挨个取值作为左右子节点的值。
	 * 先生成左再生产成右。遇到空值 计算需要跳过的父节点数量以及下个节点在设置时应该是设置左子节点还是右子节点
	 */
	private static MyTreeNode deserialize(String s) {
		if (s == null || s.length() == 0) {
			return null;
		}
		LinkedList<MyTreeNode> queue = new LinkedList<>();
		String[] valArr = s.split(" ");
		MyTreeNode head = new MyTreeNode(Integer.parseInt(valArr[0]));
		queue.add(head);
		boolean isLeft = true;
		for (int i = 1; i < valArr.length && !queue.isEmpty(); i++) {
			String valStr = valArr[i];
			int zeroCount = 0;
			if (valStr.indexOf(".") > -1) {
				zeroCount += Integer.parseInt(valStr.split("\\.")[1]);
			}
			if (zeroCount == 0) {
				MyTreeNode child = new MyTreeNode(Integer.parseInt(valStr));
				if (isLeft) {
					queue.getFirst().left = child;
				} else {
					queue.removeFirst().right = child;
				}
				queue.add(child);
				isLeft = !isLeft;
			} else {
				//left: n/2==0  left,n/2; n/2==1  right,n/2。
				//right: n/2==0  right,n/2; n/2==1  left,n/2+1。
				int skip = zeroCount / 2;
				if (zeroCount % 2 == 1) {
					if (!isLeft) {
						skip += 1;
					}
					isLeft = !isLeft;
				}
				while (skip-- > 0 && !queue.isEmpty()) {
					queue.removeFirst();
				}
			}
		}
		
		return head;
	}
}
