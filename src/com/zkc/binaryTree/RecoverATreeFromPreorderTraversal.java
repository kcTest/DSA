package com.zkc.binaryTree;

import com.zkc.utils.MyUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * We run a preorder depth-first search (DFS) on the root of a binary tree.
 * <p>
 * At each node in this traversal, we output D dashes (where D is the depth of this node),
 * then we output the value of this node.  If the depth of a node is D, the depth of its immediate child is D + 1.
 * The depth of the root node is 0.
 * <p>
 * If a node has only one child, that child is guaranteed to be the left child.
 * <p>
 * Given the output traversal of this traversal, recover the tree and return its root.
 * <p>
 * 示例 1：
 * 输入："1-2--3--4-5--6--7"
 * 输出：[1,2,5,3,4,6,7]
 * <p>
 * 示例 2：
 * 输入："1-2--3---4-5--6---7"
 * 输出：[1,2,5,3,null,6,null,4,null,7]
 * <p>
 * 示例 3：
 * 输入："1-401--349---90--88"
 * 输出：[1,401,null,349,88,90]
 */
public class RecoverATreeFromPreorderTraversal {
	
	public static void main(String[] args) {
		String s = "1-2--3--4-5--6--7";
		System.out.println(s);
		MyUtils.printBinaryTree(recoverFromPreorder(s));
	}
	
	private static MyTreeNode recoverFromPreorder(String traversal) {
		int n = traversal.length();
		Map<MyTreeNode, MyTreeNode> parentMap = new HashMap<>();
		Map<MyTreeNode, Integer> levelMap = new HashMap<>();
		int level = 0;
		int preLevel = 0;
		MyTreeNode head = null;
		MyTreeNode cur = null;
		StringBuilder sb = new StringBuilder();
		boolean set = false;
		for (int i = 0; i < n; i++) {
			if (set) {
				int val = Integer.parseInt(sb.toString());
				MyTreeNode node = new MyTreeNode(val);
				if (head == null) {
					head = node;
				} else {
					if (level > preLevel) {
						cur.left = node;
					} else if (level == preLevel) {
						cur = parentMap.get(cur);
						cur.right = node;
					} else {
						while (levelMap.get(cur) >= level) {
							cur = parentMap.get(cur);
						}
						cur.right = node;
					}
				}
				parentMap.put(node, cur);
				levelMap.put(node, level);
				cur = node;
				preLevel = level;
				level = 0;
				set = false;
				sb = new StringBuilder();
			} else {
				char c = traversal.charAt(i);
				if (c == '-') {
					level++;
				} else {
					sb.append(c);
					if (i == n - 1 || (traversal.charAt(i + 1) == '-')) {
						i--;
						set = true;
					}
				}
			}
		}
		return head;
	}
}
