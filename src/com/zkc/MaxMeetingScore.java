package com.zkc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定一些会议的截至时间和收益。{10,80}表示会议截至时间为10，收益为80
 * 一开始时间为0，任何会议都持续10的时间 ，要在会议截至时间之前开始，只有一个会议室，不能共用。
 * 返回每个会议都被正确安排后能获得的最大收益。
 */
public class MaxMeetingScore {
	public static void main(String[] args) {
		int[][] meetings = new int[][]{{6, 3}, {12, 6}, {12, 4}, {12, 9}, {13, 10}, {22, 4}, {22, 6}, {34, 2}};
		System.out.println(getMaxScore(meetings));
	}
	
	
	/**
	 * 使用小根堆存放会议的收益。
	 * 所有会议根据截至时间排序，从时间0开始 遍历每个会议 每次安排一个会议时间增加10.
	 * 某个会议可以在不违反规定的情况下被安排在其最晚开始时间(会议的截至时间-10)之前的任意时间内，是否选择根据具体情况。
	 * 如果当前时间<=会议的最晚开始时间，当前会议可以被安排 将当前会议收益加入堆中 时间+10，再去看下一个会议。
	 * 如果当前时间>会议的最晚开始时间，只考虑时间当前会议不能新增否则与截至时间冲突，但是如果当前会议的收益比之前安排的某个会议的收益大
	 * 就可以用当前会议替换掉之前的会议，即如果当前会议的收益比堆顶值大，将堆顶值弹出将当前收益值加入，相当于替换了之前选的会议。
	 * 最后统计堆中总的收益
	 */
	private static int getMaxScore(int[][] meetings) {
		//根据截至时间排序
		Arrays.sort(meetings, Comparator.comparingInt(x -> x[0]));
		//存放已安排会议的收益
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		//从0开始 没安排一个会议时间加10
		int time = 0;
		for (int[] meeting : meetings) {
			if (time <= meeting[0] - 10) {
				queue.add(meeting[1]);
				//当前时间段内已安排会议 
				time += 10;
			} else {
				//替换已选的会议 时间不增加
				if (queue.size() > 0 && queue.peek() < meeting[1]) {
					queue.poll();
					queue.add(meeting[1]);
				}
			}
		}
		int count = 0;
		while (!queue.isEmpty()) {
			count += queue.poll();
		}
		return count;
	}
}
