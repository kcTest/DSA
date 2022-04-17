package com.zkc.linkedList;

import com.zkc.linkedList.singleLinkedList.SpecialSingleLinkedList;
import com.zkc.utils.MyUtils;

/**
 * 特殊单链表  含有额外属性rand  随机指向链表中的某个节点或者指向NULL
 * 给定一个新的head，根据原单链表复制出一个新的链表，每个节点的状态与原单链表保持相同， 返回新的head.
 * O(n) O(1)
 */
public class CopyRandomLinkedList {
	public static void main(String[] args) {
		
		SpecialSingleLinkedList singleLinedList = MyUtils.getSpecialSingleLinkedList(((int) (Math.random() * 4) + 3), 20);
		MyUtils.printSpecialSingleLinkedList(singleLinedList);
		SpecialSingleLinkedList singleLinedList2 = new SpecialSingleLinkedList();
		singleLinedList2.head = copyRandomLinkedList(singleLinedList.head);
		MyUtils.printSpecialSingleLinkedList(singleLinedList2);
		MyUtils.printSpecialSingleLinkedList(singleLinedList);
	}
	
	private static SpecialSingleLinkedList.Node copyRandomLinkedList(SpecialSingleLinkedList.Node head) {
		if (head == null) {
			return null;
		}
		//新链表头节点
		SpecialSingleLinkedList.Node head2 = null;
		
		//第一次遍历将每个新节点放到对应原始节点之后
		SpecialSingleLinkedList.Node current = head;
		while (current != null) {
			SpecialSingleLinkedList.Node next = current.next;
			
			SpecialSingleLinkedList.Node newNode = new SpecialSingleLinkedList.Node(current.data);
			newNode.next = next;
			if (head2 == null) {
				head2 = newNode;
			}
			current.next = newNode;
			
			current = next;
		}
		
		//第二遍 当前原始节点的rand指向的节点 即为 其右侧相邻新节点的rand对应节点
		current = head;
		while (current != null) {
			//如果不需要设置继续看下一个
			if (current.rand != null) {
				current.next.rand = current.rand.next;
			}
			current = current.next.next;
		}
		
		//原始节点恢复原始指向，每个节点指向其下下节点，完成后原始链表和新链表被分离 
		current = head;
		while (current.next != null) {
			SpecialSingleLinkedList.Node next = current.next;
			current.next = current.next.next;
			//来到下下个节点
			current = next;
		}
		
		return head2;
	}
}
