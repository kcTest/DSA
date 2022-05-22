package com.zkc;

import java.util.*;

public class Hanota {
	
	public static void main(String[] args) {
		
		List<Integer> A = new ArrayList();
		A.add(3);
		A.add(2);
		A.add(1);
		A.add(0);
		List<Integer> B = new ArrayList();
		List<Integer> C = new ArrayList();
		hanota(A, B, C);
		for (Integer integer : C) {
			System.out.println(integer);
		}
	}
	
	private static void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
		Stack<Plate> pillarOne = new Stack<>();
		Stack<Plate> pillarTwo = new Stack<>();
		Stack<Plate> pillarThree = new Stack<>();
		Stack<Plate>[] pillars = new Stack[]{pillarOne, pillarTwo, pillarThree};
		
		PriorityQueue<Plate> movePlateQ = new PriorityQueue<>(new MinStepComparator());
		int curIdx = A.size() - 1;
		for (int i = 0; i < A.size(); i++) {
			boolean odd = (i & 1) != 1;
			Plate plate = new Plate(A.get(i), odd, (int) Math.pow(2, (curIdx + 1)), (int) Math.pow(2, curIdx--), odd ? 2 : 1);
			pillars[plate.curPillarIdx].push(plate);
			movePlateQ.add(plate);
		}
		
		int maxSteps = (int) Math.pow(2, A.size());
		int curStep = 1;
		while (curStep < maxSteps) {
			Plate plate = pillars[movePlateQ.poll().curPillarIdx].pop();
			int newNextPillarIdx = plate.nextPillarIdx;
			pillars[newNextPillarIdx].push(plate);
			plate.curPillarIdx = newNextPillarIdx;
			newNextPillarIdx = plate.odd ? newNextPillarIdx - 1 : newNextPillarIdx + 1;
			if (newNextPillarIdx > 2) {
				newNextPillarIdx = 0;
			} else if (newNextPillarIdx < 0) {
				newNextPillarIdx = 2;
			}
			plate.nextPillarIdx = newNextPillarIdx;
			plate.triggerStep = curStep++ + plate.stepSpace;
			movePlateQ.add(plate);
		}
		for (Plate plate : pillarThree) {
			C.add(plate.val);
		}
	}
	
	private static class Plate {
		public int val;
		public boolean odd;
		public int curPillarIdx;
		public int nextPillarIdx;
		public int stepSpace;
		public int triggerStep;
		
		public Plate(int val, boolean odd, int stepSpace, int triggerStep, int nextPillarIdx) {
			this.val = val;
			this.odd = odd;
			this.stepSpace = stepSpace;
			this.triggerStep = triggerStep;
			this.curPillarIdx = 0;
			this.nextPillarIdx = nextPillarIdx;
		}
	}
	
	private static class MinStepComparator implements Comparator<Plate> {
		@Override
		public int compare(Plate o1, Plate o2) {
			return o1.triggerStep - o2.triggerStep;
		}
	}
	
}
