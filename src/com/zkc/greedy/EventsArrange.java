package com.zkc.greedy;

import java.util.*;

/**
 * 数组 events，其中 events[i] = [startDay, endDay] ，表示会议 i 开始于 startDay ，结束于 endDay 。
 * 可以在满足 startDay <= d <= endDay 中的任意一天 d 参加会议 i 。注意，一天只能参加一个会议。
 * 返回可以参加的最大会议数目。
 */
public class EventsArrange {
	
	public static void main(String[] args) {
//		int[][] events = new int[][]{new int[]{1, 10}, new int[]{2, 2}, new int[]{2, 2}, new int[]{2, 2}, new int[]{2, 2}}; //2
//		int[][] events = new int[][]{new int[]{1, 2}, new int[]{2, 3}, new int[]{3, 4}, new int[]{1, 2}}; //4
//		int[][] events = new int[][]{new int[]{1, 5}, new int[]{1, 5}, new int[]{1, 5}, new int[]{2, 3}, new int[]{2, 3}}; //5
//		int[][] events = new int[][]{new int[]{1, 2}, new int[]{1, 2}, new int[]{1, 6}, new int[]{1, 2}, new int[]{1, 2}}; //3
//		int[][] events = new int[][]{new int[]{25, 27}, new int[]{22, 25}, new int[]{20, 24}, new int[]{8, 8}, new int[]{27, 27}}; //5
//		int[][] events = new int[][]{new int[]{1, 4}, new int[]{4, 4}, new int[]{2, 2}, new int[]{3, 4}, new int[]{1, 1}}; // 4
//		int[][] events = new int[][]{new int[]{1, 4}, new int[]{2, 2}, new int[]{1, 1}}; // 3
//		int[][] events = new int[][]{new int[]{1, 2}, new int[]{3, 6}, new int[]{2, 6}, new int[]{9, 13}, new int[]{6, 6},
//				new int[]{25, 27}, new int[]{22, 25}, new int[]{20, 24}, new int[]{8, 8}, new int[]{27, 27}};  //10
//		int[][] events = new int[][]{new int[]{26, 27}, new int[]{24, 26}, new int[]{6, 6}, new int[]{25, 27}, new int[]{27, 27}}; //5
//		int[][] events = new int[][]{new int[]{2, 10}, new int[]{2, 10},new int[]{3, 10}, new int[]{3, 10}, new int[]{4, 10},
//				new int[]{7, 8}, new int[]{8, 9}, new int[]{9, 10}, new int[]{12, 12}, new int[]{12, 12}}; //9
//		int[][] events = new int[][]{new int[]{1, 5}, new int[]{1, 5}, new int[]{1, 5}, new int[]{1, 5}, new int[]{1, 5}, new int[]{1, 10}, new int[]{1, 1}, new int[]{1, 4}, new int[]{5, 5}};//6
//		int[][] events = new int[][]{new int[]{1, 1}, new int[]{1, 2}, new int[]{1, 3}, new int[]{1, 4}, new int[]{1, 5}};//5
		int[][] events = new int[][]{new int[]{6, 6}, new int[]{3, 4}, new int[]{4, 5}, new int[]{3, 3}, new int[]{2, 5}, new int[]{7, 11}, new int[]{2, 3}};//6
		System.out.println(bestArrange(events));
	}
	
	/**
	 * 保持选取开始最早 结束最早
	 * 在按开始最早排序的堆中选取符合条件的event放入 按结束最早的堆中
	 */
	private static int bestArrange(int[][] events) {
		PriorityQueue<int[]> earliest = new PriorityQueue<>(events.length, new Earliest());
		for (int[] event : events) {
			earliest.add(event);
		}
		PriorityQueue<int[]> latest = new PriorityQueue<>(new Latest());
		int count = 0;
		int today = 0;
		while (earliest.size() > 0) {
			int[] event = earliest.peek();
			int start = event[0];
			int end = event[1];
			if (start <= today && end >= today) {
				latest.add(earliest.poll());
			}
			if (start > today || earliest.size() == 0) {
				while (latest.size() > 0) {
					if (latest.peek()[1] < today) {
						latest.poll();
					} else {
						if (earliest.size() > 0 && today == start) {
							break;
						}
						latest.poll();
						count++;
						today++;
					}
				}
				today = start;
			}
		}
		return count;
	}
	
	private static class Earliest implements Comparator<int[]> {
		@Override
		public int compare(int[] o1, int[] o2) {
			return o1[0] - o2[0];
		}
	}
	
	private static class Latest implements Comparator<int[]> {
		@Override
		public int compare(int[] o1, int[] o2) {
			return o1[1] - o2[1];
		}
	}
}
