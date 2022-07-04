package com.zkc.design;

import com.zkc.utils.MyUtils;

import java.util.*;

/**
 * 给你一些日程安排 [start, end) ，请你在每个日程安排添加后，返回一个整数 k ，表示所有先前日程安排会产生的最大 k 次预订。
 * 当 k 个日程安排有一些时间上的交叉时（例如 k 个日程安排都在同一时间内），就会产生 k 次预订。
 */
public class MyCalendarSln {
	
	public static void main(String[] args) {
		List<int[]> lst = new ArrayList<>();
		lst.add(new int[]{26, 35});
		lst.add(new int[]{26, 32});
		lst.add(new int[]{25, 32});
		lst.add(new int[]{18, 26});

//		lst.add(new int[]{20, 27});
//		lst.add(new int[]{24, 33});
//		lst.add(new int[]{23, 28});
//		lst.add(new int[]{21, 27});
//		lst.add(new int[]{26, 32});
		for (int[] calendar : lst) {
			MyUtils.printArr(calendar);
		}
		MyCalendarThree calendars = new MyCalendarThree();
		MyCalendarThree2 calendars2 = new MyCalendarThree2();
		for (int[] calendar : lst) {
			System.out.printf("k1=%d,k2=%d\n", calendars.book(calendar[0], calendar[1]), calendars2.book(calendar[0], calendar[1]));
		}
	}
	
	private static class Calendar {
		public int start;
		public int end;
		
		public Calendar(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
	private static class MinStartComparator implements Comparator<Calendar> {
		@Override
		public int compare(Calendar prev, Calendar next) {
			return prev.start - next.start;
		}
	}
	
	
	/**
	 * 1 lst记录所有日程 每次根据起始日期建立小根堆minStartHeap，建立一个存放结束日期的小根堆minEndHeap
	 * 2 每次从minStartHeap弹出一个日程  curCalendar
	 * 3 检查minEndHeap堆顶结束日期 ，如果小于等于当前日程的起始日期 说明这俩个日程不相交   minEndHeap弹出当前结束日期。
	 * 4 重复3直到minEndHeap中的堆顶元素与当前日程是相交的 此时minEndHeap剩余元素也是与当前日程相交的 此时停止， minEndHeap加入当前日程的结束日期。
	 * 5 取minEndHeap剩余元素个数 即为当前日程与其他日程的最大重叠次数,记录。重复判断 3-4 直到minStartHeap为空。
	 * 返回每轮34判断产生的重叠次数中的最大值
	 */
	private static class MyCalendarThree2 {
		int max = 0;
		LinkedList<Calendar> lst;
		
		public MyCalendarThree2() {
			lst = new LinkedList<>();
		}
		
		public int book(int start, int end) {
			PriorityQueue<Calendar> minStartHeap = new PriorityQueue<>(new MinStartComparator());
			PriorityQueue<Integer> minEndHeap = new PriorityQueue<>();
			lst.addLast(new Calendar(start, end));
			for (Calendar calendar : lst) {
				if (calendar.start < end) {
					//不与新增日程产生交集的不需要加入
					minStartHeap.add(calendar);
				}
			}
			while (!minStartHeap.isEmpty()) {
				Calendar curCalendar = minStartHeap.poll();
				while (!minEndHeap.isEmpty() && minEndHeap.peek() <= curCalendar.start) {
					minEndHeap.poll();
				}
				minEndHeap.add(curCalendar.end);
				//剩余的结束日期与当日程相交
				max = Math.max(max, minEndHeap.size());
			}
			return max;
		}
	}
	
	/**
	 * 求最大重叠层数k
	 * 当前线段与其余线段重叠的线段 作为新的线段与链表中之前的线段再去判断是否重叠 一直递归到0层 0位置 取过程中能获取的最大重叠层数返回
	 * 用record记录已经判断过的线段及当前位置
	 * 从尾部添加新的日程  当前重叠次数依赖下一层
	 */
	private static class MyCalendarThree {
		int max = 1;
		LinkedList<int[]> lst;
		Map<String, Integer> record = new HashMap<>();
		
		public MyCalendarThree() {
			lst = new LinkedList<>();
		}
		
		public int book(int start, int end) {
			end--;
			max = Math.max(getMaxLevel(start, end, lst.size() - 1, record), max);
			lst.addLast(new int[]{start, end});
			return max;
		}
		
		private int getMaxLevel(int start, int end, int idx, Map<String, Integer> record) {
			String key = start + "." + end + "." + idx;
			if (record.containsKey(key)) {
				return record.get(key);
			}
			int curLevel = 1;
			//最后一层 没有可比较的线段 自身算一层1
			if (idx == -1) {
				record.put(key, curLevel);
				return curLevel;
			}
			for (int i = idx; i >= 0; i--) {
				int[] calendar = lst.get(i);
				int curStart = calendar[0];
				int curEnd = calendar[1];
				int newStart = start;
				int newEnd = end;
				if (end >= curStart && start <= curEnd) {
					if (start < curStart) {
						newStart = curStart;
					}
					if (end > curEnd) {
						newEnd = curEnd;
					}
					int preLevel = getMaxLevel(newStart, newEnd, i - 1, record);
					curLevel = Math.max(preLevel + 1, curLevel);
				}
			}
			record.put(key, curLevel);
			return curLevel;
		}
		
	}
	
}