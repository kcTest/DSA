package com.zkc.linkedList.doubleLinkedList;

/**
 * 自定义双向链表
 */
public class DoubleLinkedList {
	
	public int data;
	public Node head;
	
	public static class Node {
		
		public int data;
		public Node prev;
		public Node next;
		
		public Node(int data) {
			this.data = data;
		}
	}
}
