package com.zkc.linkedList;

import com.zkc.utils.MyUtils;

import java.util.PriorityQueue;

/**
 * Example 1:
 * <p>
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 * <p>
 * Example 2:
 * <p>
 * Input: lists = []
 * Output: []
 * Example 3:
 * <p>
 * Input: lists = [[]]
 * Output: []
 */
public class MergeKSortedLists {
	
	public static void main(String[] args) {
		int[][] ints = MyUtils.get2DArray(3, 3, 10);
		MyUtils.print2DArray(ints);
		ListNode[] listNodeArr = new ListNode[ints.length];
		for (int i = 0; i < ints.length; i++) {
			int[] arr = ints[i];
			ListNode head = new ListNode(arr[0]);
			ListNode cur = head;
			for (int j = 1; j < arr.length; j++) {
				cur.next = new ListNode(arr[j]);
				if (j != arr.length - 1) {
					cur = cur.next;
				}
			}
			listNodeArr[i] = head;
		}
		
		ListNode head = mergeKLists(listNodeArr);
		while (head != null) {
			System.out.printf("%d->", head.val);
			head = head.next;
		}
	}
	
	private static ListNode mergeKLists(ListNode[] lists) {
		if (lists.length == 0) {
			return null;
		}
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		for (int i = 0; i < lists.length; i++) {
			while (lists[i] != null) {
				heap.add(lists[i].val);
				lists[i] = lists[i].next;
			}
		}
		if (heap.size() == 0) {
			return null;
		}
		ListNode head = new ListNode(heap.poll());
		ListNode cur = head;
		while (!heap.isEmpty()) {
			cur.next = new ListNode((heap.poll()));
			cur = cur.next;
		}
		return head;
	}
	
	
	private static class ListNode {
		int val;
		ListNode next;
		
		ListNode() {
		}
		
		ListNode(int val) {
			this.val = val;
		}
		
		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}
	
	
}
