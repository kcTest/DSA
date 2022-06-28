package com.zkc;

/**
 * 有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉
 * 在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
 */
public class MinEatingSpeed {
	public static void main(String[] args) {
		int[] piles = new int[]{3, 6, 7, 11};
		int h = 8;
//		int[] piles = new int[]{30, 11, 23, 4, 20};
//		int h = 5;
//		int[] piles = new int[]{30, 11, 23, 4, 20};
//		int h = 6;
//		int[] piles = new int[]{6};
//		int h = 5;
//		int[] piles = new int[]{1000000000, 1000000000};
//		int h = 3;
//		int[] piles = new int[]{6, 8};
//		int h = 8;
//		int[] piles = new int[]{110, 5033};
//		int h = 112;
		System.out.println(minEatingSpeed(piles, h));
	}
	
	public static int minEatingSpeed(int[] piles, int h) {
		int maxSpeed = Integer.MIN_VALUE;
		for (int pile : piles) {
			if (pile > maxSpeed) {
				maxSpeed = pile;
			}
		}
		return sln(piles, h, maxSpeed);
	}
	
	private static int sln(int[] piles, int limit, int end) {
		if (piles.length == 1) {
			return piles[0] % limit == 0 ? piles[0] / limit : piles[0] / limit + 1;
		}
		int minSpeed = end;
		int low = 1;
		int high = end;
		//二分调整速度
		while (low <= high) {
			int usedHour = 0;
			int mid = low + (high - low) / 2;
			for (int i = 0; i < piles.length; i++) {
				usedHour += piles[i] % mid == 0 ? piles[i] / mid : piles[i] / mid + 1;
				if (usedHour > limit) {
					low = mid + 1;
					break;
				} else if (i == piles.length - 1) {
					minSpeed = Math.min(minSpeed, mid);
					high = mid - 1;
				}
			}
		}
		return minSpeed;
	}
	
	
}
