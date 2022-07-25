package com.zkc.binaryTree;

import com.zkc.utils.MyUtils;

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
	
	/**
	 * 遍历字符
	 * 遇到'-' level增加；遇到数字 字符串记录，如果下一位是'-'或者当前已经是最后一位 用记录level和数字创建新节点node 。
	 * level递增 设置左子节点 ；level相等设置兄弟节点 ； level递减 设置某个父节点的右子节点。
	 * node.level>last.level 设置last.left=node; node.level==last.level，设置last.parent.right=node;
	 * node.level<last.leve,向上查找父节点直到找到一个节点的level>last.level(或者上级==last.level-1),设置last.right=node。
	 * 设置完成后cur指向新增节点，level再次从0开始增加,str清空。
	 */
	private static MyTreeNode recoverFromPreorder(String traversal) {
		int n = traversal.length();
		int level = 0;
		MyTreeNode2 head = null, last = null;
		StringBuilder sb = new StringBuilder();
		char[] chars = traversal.toCharArray();
		for (int i = 0; i < n; i++) {
			if (chars[i] == '-') {
				level++;
			} else {
				sb.append(chars[i]);
				if (i == n - 1 || (chars[i + 1] == '-')) {
					MyTreeNode2 node = new MyTreeNode2(level, new MyTreeNode(Integer.parseInt(sb.toString())));
					if (head != null) {
						if (level > last.level) {
							last.node.left = node.node;
						} else if (level >= last.level) {
							last = last.parent;
							last.node.right = node.node;
						} else {
							//一直向上
							while (level <= last.level) {
								last = last.parent;
							}
							last.node.right = node.node;
						}
					} else {
						head = node;
					}
					node.setParent(last);
					last = node;
					level = 0;
					sb = new StringBuilder();
				}
			}
		}
		return head.node;
	}
	
	/**
	 * 再包装一层 不用map
	 */
	private static class MyTreeNode2 {
		public int level;
		public MyTreeNode node;
		public MyTreeNode2 parent;
		
		public void setParent(MyTreeNode2 parent) {
			this.parent = parent;
		}
		
		public MyTreeNode2(int level, MyTreeNode node) {
			this.level = level;
			this.node = node;
		}
	}
}
