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
		System.out.printf("serialize:\n%s\n", s == null ? "" : s);
		MyTreeNode head = deserialize(s);
		String s2 = serialize(head);
		System.out.printf("deserialize:\n%s\n", s2 == null ? "" : s2);
	}
	
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
	
	private static MyTreeNode deserialize(String s) {
		if (s == null || s.length() == 0) {
			return null;
		}
		Queue<MyTreeNode> queue = new LinkedList<>();
		String[] valArr = s.split(" ");
		MyTreeNode head = new MyTreeNode(Integer.parseInt(valArr[0].trim()));
		queue.add(head);
		boolean isLeft = true;
		int zeroCount = 0;
		for (int i = 1; i < valArr.length && !queue.isEmpty(); i++) {
			String valStr = valArr[i];
			MyTreeNode child = null;
			if (valStr.contains(".")) {
				zeroCount += Integer.parseInt(valStr.split("\\.")[1].trim());
			}
			if (zeroCount == 0) {
				child = new MyTreeNode(Integer.parseInt(valStr.trim()));
				MyTreeNode curHead = isLeft ? queue.peek() : queue.remove();
				if (isLeft) {
					curHead.left = child;
				} else {
					curHead.right = child;
				}
				queue.add(child);
			} else {
				int skip = zeroCount / 2;
				if (zeroCount % 2 == 1) {
					if (!isLeft) {
						skip += 1;
					}
				} else {
					isLeft = !isLeft;
				}
				
				while (skip-- > 0 && !queue.isEmpty()) {
					queue.remove();
				}
				zeroCount = 0;
			}
			isLeft = !isLeft;
		}
		
		return head;
	}
}
