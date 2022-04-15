package com.zkc.linkedList.singleLinkedList;

import com.zkc.utils.MyUtils;

/**
 * 反转单链表
 */
public class Reverse {
	
	public static void main(String[] args) {
		SingleLinkedList mySingleLinkedList = MyUtils.getSingleLinedList(7, 30);
		MyUtils.printSingleLinkedList(mySingleLinkedList);
		reverseMySingleLinedList(mySingleLinkedList);
		MyUtils.printSingleLinkedList(mySingleLinkedList);
	}
	
	private static void reverseMySingleLinedList(SingleLinkedList list) {
		if (list == null) {
			return;
		}
		SingleLinkedList.Node prev = null;
		SingleLinkedList.Node current = list.head;
		while (current != null) {
			//提前记录下一个节点的地址
			SingleLinkedList.Node next = current.next;
			
			//将指向下一个节点的指针改为指向前一个节点 
			current.next = prev;
			
			//保持每一轮的prev和current指向正确 
			
			//将当前节点作为下一轮开始时的上一个节点
			prev = current;
			//当前节点位置后移
			current = next;
		}
		//当前节点为NULL 位于最后一个节点的位置再向后一位，更新头节点为前一个即最后一个节点
		list.head = prev;
	}
}
