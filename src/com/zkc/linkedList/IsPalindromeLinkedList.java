package com.zkc.linkedList;

import com.zkc.linkedList.singleLinkedList.SingleLinkedList;
import com.zkc.utils.MyUtils;

/**
 * 判断链表是不是回文链表
 * 回文是一个单词、数字、短语或其他向前和向后读起来一样的字符序列，如madam或racecar, AA ABA ABBA
 */
public class IsPalindromeLinkedList {
	public static void main(String[] args) {
		SingleLinkedList singleLinedList = MyUtils.getPalindromeSingleLinedList(((int) (Math.random() * 3) + 3), 20);
		MyUtils.printSingleLinkedList(singleLinedList);
		System.out.println(isPalindrome(singleLinedList));
		MyUtils.printSingleLinkedList(singleLinedList);
	}
	
	private static boolean isPalindrome(SingleLinkedList list) {
		if (list == null || list.head == null) {
			return false;
		}
		if (list.head.next == null) {
			return true;
		}
		//当前指针指向头节点，向后遍历结束后再指向中间位置向前遍历  
		SingleLinkedList.Node forwardAndBackward = list.head;
		//当前指针指向头节点，向后遍历直到结束  指针向后移动的速度是forward的1/2,
		//当前移动到中间时（奇数：n/2，偶数：n/2-1)forwardAndBackward正好遍历结束为null
		SingleLinkedList.Node forward = list.head;
		//当前指针指向当前节点的上一个节点
		SingleLinkedList.Node prev = null;
		//当前指针指向当前节点的下一个节点
		SingleLinkedList.Node next;
		//forwardAndBackward每移动两次移动一次forward
		int distance = 0;
		//统计节点个数
		int count = 0;
		while (forwardAndBackward != null) {
			forwardAndBackward = forwardAndBackward.next;
			distance++;
			count++;
			if (distance == 2 && forwardAndBackward != null) {
				//每次提前记录下一个节点地址
				next = forward.next;
				
				//将forward之前的所有节点指针反转  后续会再次恢复
				forward.next = prev;
				
				//记录当前节点为下一个节点的上一个节点
				prev = forward;
				//移动到下一个位置
				forward = next;
				//重置
				distance = 0;
			}
		}
		if (count % 2 != 0) {
			//奇数时forward停在中间n/2位置  将forwardAndBackward指向其前一个节点即(n/2-1)位置 作为回溯对比的起始位置
			forwardAndBackward = prev;
			//记录当前节点为下一个节点的上一个节点 指针反转时使用
			prev = forward;
			//forward继续向前来到(n/2+1)位置
			forward = forward.next;
			
		} else {
			//记录当前位置的下一个位置(n/2+1)作为prev 以便forwardAndBackward再次将每个节点反转恢复原始指向
			next = forward.next;
			
			//偶数时forward停在中间(n/2-1)位置  将forwardAndBackward指向该位置 为回溯对比的起始位置
			forwardAndBackward = forward;
			//回溯前将当前位置节点指针反转 左右两部分正好分开 后续会再次恢复
			forward.next = prev;
			
			//记录next节点为forward节点的上一个节点 指针再次反转恢复时使用
			prev = next;
			//forward继续向前来到(n/2)位置
			forward = next;
		}
		while (forward != null && forwardAndBackward != null) {
			//对比位置，forwardAndBackward：n/2-1,从中间向起始位置移动；forward：n/2，从中间向结束位置移动
			if (forward.data != forwardAndBackward.data) {
				return false;
			}
			forward = forward.next;
			
			next = forwardAndBackward.next;
			
			//反转指针 恢复原始指向
			forwardAndBackward.next = prev;
			
			//记录当前节点为下一个节点的上一个节点
			prev = forwardAndBackward;
			forwardAndBackward = next;
		}
		
		return true;
	}
}
