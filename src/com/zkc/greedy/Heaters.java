package com.zkc.greedy;

import com.zkc.utils.MyUtils;

import java.util.Arrays;

/**
 * Winter is coming! During the contest, your first job is to design a standard heater with a fixed warm radius to warm all the houses.
 * <p>
 * Every house can be warmed, as long as the house is within the heater's warm radius range.
 * <p>
 * Given the positions of houses and heaters on a horizontal line,
 * return the minimum radius standard of heaters so that those heaters could cover all houses.
 * <p>
 * Notice that all the heaters follow your radius standard, and the warm radius will the same.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * Input: houses = [1,2,3], heaters = [2]
 * Output: 1
 * Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
 * <p>
 * Example 2:
 * Input: houses = [1,2,3,4], heaters = [1,4]
 * Output: 1
 * Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.
 * <p>
 * Example 3:
 * Input: houses = [1,5], heaters = [2]
 * Output: 3
 */
public class Heaters {
	public static void main(String[] args) {
//		int[] houses = MyUtils.getArray((int) (Math.random() * 10) + 1, 50);
//		int[] heaters = MyUtils.getArray((int) (Math.random() * 10) + 1, 50);
		int[] houses = new int[]{1, 5};
		int[] heaters = new int[]{2};
		System.out.println(findRadius(houses, heaters));
	}
	
	/**
	 * 分别对房子和加热器的位置进行排序
	 * 为每个房子选取一个离它最近的加热器，算出需要的加热半径。最后每一个房子都选好了最适合自己的加热器，在这些加热器的加热半径中取最大值。
	 * <p>
	 * 双指针 top指向房子 bot指向加热器, 假设分别来到i,j位置,求出此时j位置加热器与i位置房子的距离d1,再看j+1位置的加热器与i位子房子的距离d2,
	 * <p>
	 * 如果d2小于d1,j+1位置加热器离i位置房间更近一些的,差值d再下降,再继续往下看j+2...位置是否可以更近,直到遇到一个k位置d2>d1时停止,因为房子不动,加热器按距离排序,
	 * k位置及之后的加热器算出的距离差d会逐渐升高,此时k-1位置的加热器选为离i位置最近的加热器,距离为heaters[k-1]-heaters[i]。
	 * <p>
	 * 选好后 top右移来到i+1位置 bot仍然在k-1位置，用同样的方法为i+1位置的房子选加热器。
	 * <p>
	 * 如果d2等于d1,俩个位置距房子一样近，当做d2小于d1的情况处理继续往后检查,如果多个相同只取最右边的。因为相等的情况下,j和j+1位置重叠,如果选j位置,
	 * 在为i+1位置选取加热器的时候，还是比较j、j+1位置加热器分别与i+1位置房子的距离，结果还是取j，bot不会突破j,j+1，
	 * 右侧的其它加热器永远无法被检查，有可能为房子选到的加热器不是最近的。
	 */
	private static int findRadius(int[] houses, int[] heaters) {
		Arrays.sort(houses);
		Arrays.sort(heaters);
		int m = houses.length, n = heaters.length;
		int ho = 0, he = 0;
		int minR = 0;
		while (ho < m) {
			//如果后面的加热器离的更近，再向后看有没有更近的，如果d突然变远,取后面加热器算出的d会更远，不需要再往后看。前一个位置为离i位置更近的。
			//如果d没有遇到升高的而且来到了末尾，末尾为最近的。
			while (he < n - 1 && Math.abs(heaters[he] - houses[ho]) >= Math.abs(heaters[he + 1] - houses[ho])) {
				he++;
			}
			//算出每个房子与距它最近的热水器的距离d。每个位置最优解取最大值,所有位置的房子都会被热水器覆盖。
			minR = Math.max(minR, Math.abs(heaters[he] - houses[ho++]));
		}
		return minR;
	}
}
