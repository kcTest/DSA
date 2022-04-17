package com.zkc.linkedList.singleLinkedList;

/**
 * 自定义单向链表
 */
public class SpecialSingleLinkedList {
	
	/**
	 * 头节点
	 */
	public Node head;
	
	/**
	 * 自定义节点
	 */
	public static class Node {
		
		/**
		 * 当前节点的数据
		 */
		public int data;
		/**
		 * 当前节点的下一个节点地址
		 */
		public Node next;
		
		/**
		 * 随机指向链表中的某个节点或者指向NULL
		 */
		public Node rand;
		
		public Node(int data) {
			this.data = data;
		}
	}
	
}
