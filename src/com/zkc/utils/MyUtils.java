package com.zkc.utils;

import com.zkc.linkedList.doubleLinkedList.DoubleLinkedList;
import com.zkc.linkedList.singleLinkedList.SingleLinkedList;
import com.zkc.linkedList.singleLinkedList.SpecialSingleLinkedList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		SingleLinkedList.Node prev = null;
		//新建节点后更新上一个节点的下一个节点
		for (int i = 0; i < length; i++) {
			SingleLinkedList.Node sNode = new SingleLinkedList.Node((int) (Math.random() * bound));
			if (prev == null) {
				list.head = sNode;
			} else {
				prev.next = sNode;
			}
			prev = sNode;
		}
		
		return list;
	}
	
	public static SpecialSingleLinkedList getSpecialSingleLinkedList(int length, int bound) {
		if (length < 2) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		
		SpecialSingleLinkedList list = new SpecialSingleLinkedList();
		SpecialSingleLinkedList.Node prev = null;
		
		Map<Integer, SpecialSingleLinkedList.Node> map = new HashMap<>();
		int[] array = getArray(length, bound);
		for (int i = 0; i < array.length; i++) {
			map.put(i, new SpecialSingleLinkedList.Node(array[i]));
		}
		
		//新建节点后更新上一个节点的下一个节点
		for (int i = 0; i < array.length; i++) {
			
			SpecialSingleLinkedList.Node sNode = map.get(i);
			sNode.rand = i == (int) (Math.random() * length) ? null : map.get((int) (Math.random() * length));
			
			if (prev == null) {
				list.head = sNode;
			} else {
				prev.next = sNode;
			}
			prev = sNode;
		}
		
		return list;
	}
	
	public static DoubleLinkedList getDoubleLinedList(int length, int bound) {
		if (length < 2) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		
		DoubleLinkedList list = new DoubleLinkedList();
		DoubleLinkedList.Node prev = null;
		for (int i = 0; i < length; i++) {
			DoubleLinkedList.Node sNode = new DoubleLinkedList.Node((int) (Math.random() * bound));
			if (prev == null) {
				list.head = sNode;
			} else {
				//新建节点后更新上一个节点的下一个节点
				prev.next = sNode;
				sNode.prev = prev;
			}
			prev = sNode;
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
			sb.append(current.data).append("->");
			current = current.next;
		}
		System.out.println(sb.substring(0, sb.length() - 2));
		System.out.println("------------------------------");
	}
	
	public static void printSpecialSingleLinkedList(SpecialSingleLinkedList list) {
		if (list == null) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		SpecialSingleLinkedList.Node current = list.head;
		StringBuilder sb = new StringBuilder();
		while (current != null) {
			sb.append(current.data).append("(").append(current.rand != null ? current.rand.data : "null").append(")").append("->");
			current = current.next;
		}
		System.out.println(sb.substring(0, sb.length() - 2));
		System.out.println("------------------------------");
	}
	
	public static void printDoubleLinkedList(DoubleLinkedList list) {
		if (list == null) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		DoubleLinkedList.Node current = list.head;
		StringBuilder sb = new StringBuilder();
		while (current != null) {
			sb.append(current.data).append("->");
			current = current.next;
		}
		System.out.println(sb.substring(0, sb.length() - 2));
		System.out.println("------------------------------");
	}
	
	public static SingleLinkedList getSortedSingleLinedList(int length, int bound) {
		if (length < 2) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		SingleLinkedList list = new SingleLinkedList();
		SingleLinkedList.Node prev = null;
		
		int[] sortedArray = getArray(length, bound);
		Arrays.sort(sortedArray);
		for (int j : sortedArray) {
			SingleLinkedList.Node sNode = new SingleLinkedList.Node(j);
			if (prev == null) {
				list.head = sNode;
			} else {
				prev.next = sNode;
			}
			prev = sNode;
		}
		
		return list;
	}
	
	public static SingleLinkedList getPalindromeSingleLinedList(int length, int bound) {
		if (length < 3) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		SingleLinkedList list = new SingleLinkedList();
		SingleLinkedList.Node prev = null;
		
		int[] sortedArray = getArray(length, bound);
		for (int i = 0; i < sortedArray.length / 2; i++) {
			sortedArray[sortedArray.length - 1 - i] = sortedArray[i];
		}
		for (int j : sortedArray) {
			SingleLinkedList.Node sNode = new SingleLinkedList.Node(j);
			if (prev == null) {
				list.head = sNode;
			} else {
				prev.next = sNode;
			}
			prev = sNode;
		}
		
		return list;
	}
	
	
}
