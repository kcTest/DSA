package com.zkc.linkedList;

import com.zkc.linkedList.singleLinkedList.SingleLinkedList;
import com.zkc.utils.MyUtils;

/**
 * 两个有环或无环单链表相交的第一个节点
 */
public class IntersectLinkedList {
	public static void main(String[] args) {
		SingleLinkedList[] singleLinedLists = MyUtils.getIntersectSingleLinedList(4, 30);
		if (singleLinedLists == null || singleLinedLists[0] == null || singleLinedLists[1] == null) {
			return;
		}
		SingleLinkedList singleLinedListA = singleLinedLists[0];
		SingleLinkedList singleLinedListB = singleLinedLists[1];
		MyUtils.printIntersectSingleLinkedList(singleLinedListA);
		MyUtils.printIntersectSingleLinkedList(singleLinedListB);
		SingleLinkedList.Node node = getIntersectionNode(singleLinedListA.head, singleLinedListB.head);
		System.out.println(node == null ? "IntersectionNode：null" : String.format("IntersectionNode：%d(%s)", node.data, Integer.toHexString(node.hashCode())));
	}
	
	private static SingleLinkedList.Node getIntersectionNode(SingleLinkedList.Node headA, SingleLinkedList.Node headB) {
		if (headA == null || headB == null) {
			return null;
		}
		//判断两个链表是否含有环
		SingleLinkedList.Node loopStartA = getLoopStartNode(headA);
		SingleLinkedList.Node loopStartB = getLoopStartNode(headB);
		System.out.println(loopStartA == null ? "loopStartA: null" : String.format("loopStartA: %d(%s)", loopStartA.data, Integer.toHexString(loopStartA.hashCode())));
		System.out.println(loopStartB == null ? "loopStartA: null" : String.format("loopStartB: %d(%s)", loopStartB.data, Integer.toHexString(loopStartB.hashCode())));
		
		//1均无环(相交、不相交)
		if (loopStartA == null && loopStartB == null) {
			return getIntersectionNodeNoLoop(headA, headB, null);
		}
		//2一个有环 一个无环（不可能相交）
		if (loopStartA == null || loopStartB == null) {
			return null;
		}
		//3均有环（不想交、相交（一个环 共享一个起点、一个环 各有一个环的起点））
		boolean intersect = false;
		SingleLinkedList.Node cur = loopStartA;
		while (cur != null) {
			//如果相交 遍历其中一个链表会遇到另一个链表的环的起点
			if (cur == loopStartB) {
				intersect = true;
				break;
			}
			cur = cur.next;
			//再次回到自己 没有遇到另一个链表的环的起点
			if (cur == loopStartA) {
				break;
			}
		}
		//3.1不相交
		if (!intersect) {
			return null;
		}
		//3.2相交 共享环
		//3.2.1 相交点位于环的起始节点及之前 各链表的环的起始节点为同一个节点
		if (loopStartA == loopStartB) {
			//传入起始节点作为终止节点，只遍历到终止节点，可以作为两个无环链表处理
			return getIntersectionNodeNoLoop(headA, headB, loopStartA.next);
		}
		//3.2.2 相交点位于各链表的环的起始节点，返回任意一个
		return loopStartA;
	}
	
	/**
	 * 获取环的起始节点
	 * 如果存在环 快慢指针一定会相遇，此时 (2m+2a)-(m+a)=bn
	 * (当m移动到环的起始位置时，m为慢指针需要移动的节点个数,a为从环的起始位置开始，慢指针与快指针相遇时再次移动的节点个数
	 * n为环中节点个数，第一次相遇时快指针与慢指针已经移动节点个数之差一定为n的倍数，a=bn-m,再次移动m个节点 慢指针会再次回到环的起始位置
	 * 此时让快指针从头部也移动m个节点正好也会落在环的起始位置，与慢指针相遇，相遇的节点为环的起始位置);
	 */
	private static SingleLinkedList.Node getLoopStartNode(SingleLinkedList.Node head) {
		SingleLinkedList.Node fast = head;
		SingleLinkedList.Node slow = head;
		int coincideCount = 0;
		while (slow != null) {
			slow = slow.next;
			if (coincideCount == 0) {
				if (fast.next != null) {
					fast = fast.next.next;
				}
				if (fast == null) {
					return null;
				}
				if (slow == fast) {
					coincideCount++;
					if (slow == head) {
						return slow;
					}
					fast = head;
				}
			} else if (coincideCount == 1) {
				fast = fast.next;
				if (slow == fast) {
					return slow;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * 两个无环单链表，如果相交获取相交的第一个节点
	 */
	private static SingleLinkedList.Node getIntersectionNodeNoLoop(SingleLinkedList.Node headA, SingleLinkedList.Node headB, SingleLinkedList.Node end) {
		
		SingleLinkedList.Node curA = headA;
		SingleLinkedList.Node curB = headB;
		if (curA == end || curB == end) {
			return end;
		}
		//判断长短
		int differ = 0;
		while (curA.next != end) {
			curA = curA.next;
			differ++;
		}
		while (curB.next != end) {
			curB = curB.next;
			differ--;
		}
		//如果相交最后会在同一个节点结束
		if (curA != curB) {
			return null;
		}
		//curA指向长链表的头部
		curA = differ > 0 ? headA : headB;
		curB = curA == headA ? headB : headA;
		// 起始位置对齐  使两个链表下次遍历次数相同
		differ = Math.abs(differ);
		while (differ > 0) {
			curA = curA.next;
			differ--;
		}
		//如果相交最后会在同一个节点结束
		while (curA != curB) {
			curA = curA.next;
			curB = curB.next;
		}
		return curA;
	}
	
}
