package com.zkc.binaryTree;

/**
 * 请把一张纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。此时折痕是凹下去的，即折痕突起的方向指向纸条的背面。
 * 如果从纸条的下边向上方连续对折2次，压出折痕后展开，此时有三条折痕，从上到下依次是下折痕、下折痕和上折痕。
 * 给定一个输入参数N，代表纸条都从下边向上方连续对折N次，请从上到下打印所有折痕的方向。
 * 1：                 0
 * 2:           0             1
 * 3:       0      1      0       1
 * 4：   0    1  0   1  0   1   0     1
 * 第一次向上，以后每次会在上次的折痕左右生成新的向下和向上的折痕，形成满二叉树 左子节点为向下 右子节点为上的形式
 * 折痕顺序为中序遍历的顺序
 */
public class PaperFolding {
	public static void main(String[] args) {
		int n = (int) (Math.random() * 5);
		System.out.println(n + "\n");
		getCreaseDirection(n);
	}
	
	private static void getCreaseDirection(int n) {
		if (n <= 0) {
			return;
		}
		inOrder(1, n, true);
	}
	
	private static void inOrder(int curLevel, int totalLevel, boolean down) {
		if (curLevel > totalLevel) {
			return;
		}
		inOrder(++curLevel, totalLevel, true);
		System.out.println(down ? "down " : "up ");
		inOrder(curLevel, totalLevel, false);
	}
}
