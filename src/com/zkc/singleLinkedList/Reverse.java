package com.zkc.singleLinkedList;

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
		SingleLinkedList.Node  current = list.head;
		while (current != null) {
			//提前记录下一个节点的地址
			SingleLinkedList.Node next = current.next;
			
			//将下一个节点改为指向前一个节点 
			current.next = prev;
			
			//保持每一轮的prev和current指向正确 
			
			//将当前节点作为下一轮开始时的上一个节点
			prev = current;
			//更新当前节点位置
			current = next;
		}
		list.head = prev;
	}
}
