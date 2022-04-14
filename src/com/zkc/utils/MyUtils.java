package com.zkc.utils;

import com.zkc.singleLinkedList.SingleLinkedList;

public class MyUtils {
	
	public static int[] getArray(int length, int bound) {
		if (length < 2) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		int[] ret = new int[length];
		for (int i = 0; i < length; i++) {
			ret[i] = (int) (Math.random() * bound);
		}
		return ret;
	}
	
	public static void printArr(int[] arr) {
		if (arr == null) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		StringBuilder sb = new StringBuilder();
		for (int j : arr) {
			sb.append(j).append(",");
		}
		System.out.println(sb.substring(0, sb.length() - 1));
		System.out.println("------------------------------");
	}
	
	
	public static SingleLinkedList getSingleLinedList(int length, int bound) {
		if (length < 2) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		
		SingleLinkedList list = new SingleLinkedList();
		SingleLinkedList.Node current = list.head;
		for (int i = 0; i < length; i++) {
			SingleLinkedList.Node sNode = new SingleLinkedList.Node((int) (Math.random() * bound));
			if (current == null) {
				list.head = sNode;
				current = sNode;
			} else {
				current.next = sNode;
				current = current.next;
			}
		}
		
		return list;
	}
	
	public static void printSingleLinkedList(SingleLinkedList list) {
		if (list == null) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		SingleLinkedList.Node current = list.head;
		StringBuilder sb = new StringBuilder();
		while (current != null) {
			sb.append(current.data).append(",");
			current = current.next;
		}
		System.out.println(sb.substring(0, sb.length() - 1));
		System.out.println("------------------------------");
	}
}
