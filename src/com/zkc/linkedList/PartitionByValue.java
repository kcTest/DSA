package com.zkc.linkedList;

import com.zkc.linkedList.singleLinkedList.SingleLinkedList;
import com.zkc.utils.MyUtils;

public class PartitionByValue {
	
	public static void main(String[] args) {
		SingleLinkedList singleLinedList = MyUtils.getSingleLinedList(5, 30);
		int num = (int) (Math.random() * 30);
		MyUtils.printSingleLinkedList(singleLinedList);
		System.out.println(num);
		singleLinedList.head = partition(singleLinedList.head, num);
		MyUtils.printSingleLinkedList(singleLinedList);
	}
	
	/**
	 * 基于给定的一个值num,将单链表划分为 左边小 中间相等 右边大的形式 ，O(n) O(1)，各分区中需要保留每个节点相对于其它节点的初始前后顺序
	 */
	private static SingleLinkedList.Node partition(SingleLinkedList.Node head, int num) {
		if (head == null) {
			return null;
		}
		
		SingleLinkedList.Node lessNodesH = null;
		SingleLinkedList.Node lessNodesT = null;
		SingleLinkedList.Node grantNodesH = null;
		SingleLinkedList.Node grantNodesT = null;
		SingleLinkedList.Node equalNodesH = null;
		SingleLinkedList.Node equalNodesT = null;
		
		SingleLinkedList.Node current = head;
		
		while (current != null) {
			if (current.data < num) {
				if (lessNodesH == null) {
					lessNodesH = current;
					lessNodesT = current;
				} else {
					lessNodesT.next = current;
					lessNodesT = current;
				}
			} else if (current.data == num) {
				if (equalNodesH == null) {
					equalNodesH = current;
					equalNodesT = current;
				} else {
					equalNodesT.next = current;
					equalNodesT = current;
				}
			} else {
				if (grantNodesH == null) {
					grantNodesH = current;
					grantNodesT = current;
				} else {
					grantNodesT.next = current;
					grantNodesT = current;
				}
			}
			current = current.next;
		}
		if (lessNodesT != null) {
			lessNodesT.next = null;
		}
		if (equalNodesT != null) {
			equalNodesT.next = null;
		}
		if (grantNodesT != null) {
			grantNodesT.next = null;
		}
		
		if (lessNodesH != null) {
			head = lessNodesH;
			if (equalNodesH != null) {
				lessNodesT.next = equalNodesH;
				equalNodesT.next = grantNodesH;
			} else if (grantNodesH != null) {
				lessNodesT.next = grantNodesH;
			}
		} else if (equalNodesH != null) {
			head = equalNodesH;
			equalNodesT.next = grantNodesH;
		} else if (grantNodesH != null) {
			head = grantNodesH;
		}
		
		return head;
	}
	
	/**
	 * 基于给定的一个值num,将单链表划分为 左边小 右边大于等于的形式 ，O(n) O(1)，各分区不需要保留每个节点相对于其它节点的初始前后顺序
	 */
	private static SingleLinkedList.Node partition2(SingleLinkedList.Node head, int num) {
		SingleLinkedList.Node lessNodesH = null;
		SingleLinkedList.Node lessNodesT = null;
		SingleLinkedList.Node equalOrGrantH = null;
		SingleLinkedList.Node equalOrGrantT = null;
		
		SingleLinkedList.Node current = head;
		
		while (current != null) {
			if (current.data < num) {
				if (lessNodesH == null) {
					lessNodesH = current;
					lessNodesT = current;
				} else {
					lessNodesT.next = current;
					lessNodesT = current;
				}
			} else {
				if (equalOrGrantH == null) {
					equalOrGrantH = current;
					equalOrGrantT = current;
				} else {
					equalOrGrantT.next = current;
					equalOrGrantT = current;
				}
			}
			current = current.next;
			
		}
		if (lessNodesT != null) {
			lessNodesT.next = null;
		}
		if (equalOrGrantT != null) {
			equalOrGrantT.next = null;
		}
		
		if (lessNodesH != null) {
			head = lessNodesH;
			lessNodesT.next = equalOrGrantH;
		} else if (equalOrGrantH != null) {
			head = equalOrGrantH;
		}
		
		return head;
	}
	
}
