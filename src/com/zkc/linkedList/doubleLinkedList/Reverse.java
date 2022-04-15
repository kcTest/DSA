package com.zkc.linkedList.doubleLinkedList;

import com.zkc.utils.MyUtils;

/**
 * 反转双向链表
 */
public class Reverse {
	
	public static void main(String[] args) {
		DoubleLinkedList doubleLinkedList = MyUtils.getDoubleLinedList(5, 30);
		MyUtils.printDoubleLinkedList(doubleLinkedList);
		reverse(doubleLinkedList);
		MyUtils.printDoubleLinkedList(doubleLinkedList);
	}
	
	private static void reverse(DoubleLinkedList list) {
		
		if (list == null) {
			return;
		}
		DoubleLinkedList.Node prev = list.head.prev;
		DoubleLinkedList.Node current = list.head;
		while (current != null) {
			//提前记录下一个节点的地址
			DoubleLinkedList.Node next = current.next;
			
			//将指向下一个节点的指针改为指向上一个节点 
			current.prev = next;
			//将指向上一个节点的指针改为指向后一个节点 
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
