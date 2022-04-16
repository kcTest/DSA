package com.zkc.linkedList;

import com.zkc.linkedList.singleLinkedList.SingleLinkedList;
import com.zkc.utils.MyUtils;

/**
 * 打印两个有序链表中的公共节点 O(n)
 */
public class FindCommonNodesIn2SortedLinkedLists {
	
	public static void main(String[] args) {
		int length = 7;
		int bound = 20;
		SingleLinkedList singleLinedListA = MyUtils.getSortedSingleLinedList(length, bound);
		SingleLinkedList singleLinedListB = MyUtils.getSortedSingleLinedList(length, bound);
		MyUtils.printSingleLinkedList(singleLinedListA);
		MyUtils.printSingleLinkedList(singleLinedListB);
		findCommonNodesIn2SortedLinkedLists(singleLinedListA, singleLinedListB);
	}
	
	private static void findCommonNodesIn2SortedLinkedLists(SingleLinkedList listA, SingleLinkedList listB) {
		if (listA == null || listB == null || listA.head == null || listB.head == null) {
			return;
		}
		SingleLinkedList.Node currentA = listA.head;
		SingleLinkedList.Node currentB = listB.head;
		//移动较小者 相同时打印
		while (currentA != null && currentB != null) {
			if (currentA.data < currentB.data) {
				currentA = currentA.next;
			} else if (currentA.data > currentB.data) {
				currentB = currentB.next;
			} else {
				System.out.printf("%d ", currentA.data);
				
				currentA = currentA.next;
				currentB = currentB.next;
			}
		}
	}
}
