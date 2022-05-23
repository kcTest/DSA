package com.zkc;

import java.util.*;

public class Hanota {
	
	public static void main(String[] args) {
		List<Integer> A = new ArrayList<>();
		A.add(4);
		A.add(3);
		A.add(2);
		A.add(1);
		A.add(0);
		List<Integer> B = new ArrayList<>();
		List<Integer> C = new ArrayList<>();
		hanota(A, B, C);
		for (Integer integer : C) {
			System.out.println(integer);
		}
	}
	
	/**
	 * ****| ① | ② | ③ | ④  | ⑤ | ⑥ |  ⑦ | ⑧ | ⑨  | ⑩ | ⑾ |  ⑿ | ⒀ | ⒁ |  ⒂ |  ⒃|
	 * ___________________________________________________________________________________
	 * (0)A|->2|    |->3 |    | ->1|    | ->2|    | ->3|    | ->1|    | ->2|    | ->3|    |
	 * __________________________________________________________________________________
	 * (1)B|   | ->3|    |    |    | ->2|    |    |    | ->1|    |    |    | ->3|    |    |
	 * __________________________________________________________________________________
	 * (2)C|   |    |    |->2 |    |    |    |    |    |    |    | ->3|    |    |    |    |
	 * __________________________________________________________________________________
	 * (3)D|   |    |    |    |    |    |    | ->3|    |    |    |    |    |    |    |    |
	 * ___________________________________________________________________________________
	 */
	private static void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
		List<Integer>[] pillars = new List[]{A, B, C};
		//根据被移动时的步数排序  弹出盘子需要被移动并更新信息
		PriorityQueue<Plate> movePlateQ = new PriorityQueue<>(new NearestComparator());
		int curIdx = A.size() - 1;
		//从A顶部开始向下遍历  生成Plate信息
		for (int i = 0; i < A.size(); i++) {
			boolean odd = (i & 1) != 1;
			Plate plate = new Plate(odd, 1 << (curIdx + 1), 1 << (curIdx--), odd ? 2 : 1);
			movePlateQ.add(plate);
		}
		//总步数为2^(n-1)次
		int maxSteps = 1 << A.size();
		int curStep = 0;
		while (++curStep < maxSteps) {
			Plate plate = movePlateQ.poll();
			List<Integer> pillar = pillars[plate.curPillarIdx];
			int newNextPillarIdx = plate.nextPillarIdx;
			pillars[newNextPillarIdx].add(pillar.remove(pillar.size() - 1));
			plate.curPillarIdx = newNextPillarIdx;
			plate.moveStep = curStep + plate.stepSpace;
			if (plate.odd && --newNextPillarIdx < 0) {
				//保持3->2->1->3
				newNextPillarIdx = 2;
			} else if (!plate.odd && ++newNextPillarIdx > 2) {
				//保持2->3->1->2
				newNextPillarIdx = 0;
			}
			plate.nextPillarIdx = newNextPillarIdx;
			movePlateQ.add(plate);
		}
	}
	
	private static class Plate {
		/**
		 * 记录元素在A中从顶部开始idx的奇偶性
		 */
		public boolean odd;
		/**
		 * 初始都处于第一个柱子
		 */
		public int curPillarIdx;
		/**
		 * 在A中从顶部开始遍历时
		 * 下标idx为偶数的盘子初始时目标为第二个柱子  后续目标柱子序号依次递增 顺序为2->3->1->2
		 * 下标idx为奇数的盘子初始时目标为第三个柱子  后续目标柱子序号依次递减 顺序为3->2->1->3
		 */
		public int nextPillarIdx;
		/**
		 * 当前盘子每隔2^(idx+1)步移动一次
		 */
		public int stepSpace;
		/**
		 * 初始当前盘子在第2^(idx)步时会被移动 后续每次移动后加上stepSpace为下一次被移动时的步数
		 */
		public int moveStep;
		
		public Plate(boolean odd, int stepSpace, int moveStep, int nextPillarIdx) {
			this.odd = odd;
			this.stepSpace = stepSpace;
			this.moveStep = moveStep;
			this.nextPillarIdx = nextPillarIdx;
		}
	}
	
	private static class NearestComparator implements Comparator<Plate> {
		@Override
		public int compare(Plate p1, Plate p2) {
			return p1.moveStep - p2.moveStep;
		}
	}
	
	
	private static void hanota2(List<Integer> A, List<Integer> B, List<Integer> C) {
		move(A.size(), A, B, C);
	}
	
	/**
	 * @param n 第n个要移动的元素
	 * @param A 左
	 * @param B 中
	 * @param C 右
	 *          <p>左侧的前n-1个元素移到中间 第n个移到右侧 再把中间n-1个元素移动到右侧
	 */
	private static void move(int n, List<Integer> A, List<Integer> B, List<Integer> C) {
		if (n == 1) {
			C.add(A.remove(A.size() - 1));
			return;
		}
		move(n - 1, A, C, B);
		C.add(A.remove(A.size() - 1));
		move(n - 1, B, A, C);
	}
	
	
}
