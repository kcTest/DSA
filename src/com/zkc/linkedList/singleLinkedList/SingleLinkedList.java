package com.zkc.linkedList.singleLinkedList;

/**
 * 自定义单向链表
 */
public class SingleLinkedList {
	
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
		
		public Node(int data) {
			this.data = data;
		}
	}
	
}
