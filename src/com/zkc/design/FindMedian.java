package com.zkc.design;

import java.util.Comparator;
import java.util.PriorityQueue;

public class FindMedian {
	
	public static void main(String[] args) {
		String[] actionArr = new String[]{"MedianFinder", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian"};
		Integer[] dataArr = new Integer[]{null, 40, null, 12, null, 16, null, 14, null, 35, null, 19, null, 34, null, 35, null, 28, null, 35, null, 26, null, 6, null, 8, null, 2, null, 14, null, 25, null, 25, null, 4, null, 33, null, 18, null, 10, null, 14, null, 27, null, 3, null, 35, null, 13, null, 24, null, 27, null, 14, null, 5, null, 0, null, 38, null, 19, null, 25, null, 11, null, 14, null, 31, null, 30, null, 11, null, 31, null, 0, null};
		
		MedianFinder medianFinder = null;
		for (int i = 0; i < dataArr.length; i++) {
			Integer num = dataArr[i];
			switch (actionArr[i]) {
				case "MedianFinder":
					medianFinder = new MedianFinder();
					break;
				case "addNum":
					medianFinder.addNum(num);
					break;
				case "findMedian":
					System.out.printf("[%10.5f   ]\n", medianFinder.findMedian());
					break;
			}
		}
		
	}
	
	private static class MedianFinder {
		
		/**
		 * 前一半较小数据降序排列要放入大根堆 后一半较大数据升序排列要放入小根堆 此时中位数位于中间位置 从前往后为递增顺序
		 * 此时可以通过两个堆顶位置的数求得中位数 需要保持两部分长度相差最多为1（奇数时取较大部分的堆顶数，偶数时两部分堆顶数除2）
		 */
		public PriorityQueue<Integer> firstHalfDesc;
		public PriorityQueue<Integer> secondHalfAsc;
		
		public MedianFinder() {
			this.secondHalfAsc = new PriorityQueue<>(new AscComparator());
			this.firstHalfDesc = new PriorityQueue<>(new DesComparator());
		}
		
		public void addNum(int num) {
			if (secondHalfAsc.size() > 0) {
				//如果当前数比后半的第一个小 较小默认加入前半部分
				if (num < secondHalfAsc.peek()) {
					//前半部分较长 如果继续加入到前半部分两部分长度差会到2 不能保证长度差为1  所以前半部分不能直接增加元素 需要根据情况调整
					if (firstHalfDesc.size() > secondHalfAsc.size()) {
						//如果当前数小于前半部分的最大数 当前数还是需要处于前半部分  此时可以将前半部分最大数移动到后半部分之后再把当前数插入到前半部分
						//保持了从前到后顺序递增而且长度一致
						if (num < firstHalfDesc.peek()) {
							secondHalfAsc.add(firstHalfDesc.poll());
							firstHalfDesc.add(num);
						} else {
							//如果当前数大于等于前半部分的最大数 可以直接将当前数放入后半部分，保持了从前到后顺序递增而且长度一致
							secondHalfAsc.add(num);
						}
					} else {//前半部分小于或等于后半部分的长度  可以继续直接添加  数量差最多为1
						firstHalfDesc.add(num);
					}
				} else {//如果当前数大于等于后半的第一个数 较大默认加入后半部分
					//后半部分较长 如果继续加入到后半部分两部分长度差会到2 不能保证长度差为1  所以后半部分不能直接增加元素 需要根据情况调整
					if (secondHalfAsc.size() > firstHalfDesc.size()) {
						//如果当前数大于后半部分的最小数 当前数还是需要处于后半部分  此时可以将后半部分最小数移动到前半部分之后再把当前数插入到后半部分
						//保持了从前到后顺序递增而且长度一致
						if (num > secondHalfAsc.peek()) {
							firstHalfDesc.add(secondHalfAsc.poll());
							secondHalfAsc.add(num);
						} else {
							//如果当前数等于后半部分的最小数  可以直接将当前数放入前半部分，保持了从前到后顺序递增而且长度一致
							firstHalfDesc.add(num);
						}
					} else {
						//后半部分小于或等于前半部分的长度 可以继续直接添加  数量差最多为1
						secondHalfAsc.add(num);
					}
				}
			} else {
				//当前初始选择加入后半部分  后续每次都需要与后半部分最小数比较再决定如何加入
				secondHalfAsc.add(num);
			}
		}
		
		public double findMedian() {
			if (secondHalfAsc.size() > firstHalfDesc.size()) {
				return secondHalfAsc.peek();
			} else if (firstHalfDesc.size() > secondHalfAsc.size()) {
				return firstHalfDesc.peek();
			} else {
				if (firstHalfDesc.size() == 0) {
					return 0;
				}
				return (secondHalfAsc.peek() + firstHalfDesc.peek()) / 2.0;
			}
		}
	}
	
	private static class AscComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o1 - o2;
		}
	}
	
	private static class DesComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}
	}
}
