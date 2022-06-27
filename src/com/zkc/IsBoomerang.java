package com.zkc;

/**
 * 三个点不在一条直线上 。
 */
public class IsBoomerang {
	public static void main(String[] args) {
		int[][] points = new int[][]{new int[]{1, 0}, new int[]{0, 2}, new int[]{1, 2}};
		System.out.println(isBoomerang(points));
	}
	
	/**
	 * 重合直接返回  任意两段相交或斜率相同
	 */
	public static boolean isBoomerang(int[][] points) {
		int failed = 0;
		for (int i = 0; i < points.length; i++) {
			boolean f = false;
			double x1 = points[i][0];
			double y1 = points[i][1];
			double x2 = points[i + 1 >= 3 ? i + 1 - 3 : i + 1][0];
			double y2 = points[i + 1 >= 3 ? i + 1 - 3 : i + 1][1];
			double x3 = points[i + 2 >= 3 ? i + 2 - 3 : i + 2][0];
			double y3 = points[i + 2 >= 3 ? i + 2 - 3 : i + 2][1];
			if (x1 == x2 && y1 == y2) {
				return false;
			}
			if (x1 == x3 && y1 == y3) {
				return false;
			}
			if (x2 == x3 && y2 == y3) {
				return false;
			}
			boolean xj1 = false;
			double xl1 = 0;
			double x11 = x2 - x1;
			double y11 = y2 - y1;
			if (x11 == 0 || y11 == 0) {
				xj1 = true;
			} else {
				xl1 = y11 / x11;
			}
			boolean xj2 = false;
			double xl2 = 0;
			double x22 = x3 - x1;
			double y22 = y3 - y1;
			if (x22 == 0 || y22 == 0) {
				xj2 = true;
			} else {
				xl2 = y22 / x22;
			}
			if (xj1 && xj2) {
				f = true;
			}
			if (xl1 == xl2) {
				f = true;
			}
			if (f) {
				failed++;
			}
		}
		return failed != 3;
	}
	
}
