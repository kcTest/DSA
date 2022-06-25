package com.zkc;

/**
 * 实现函数 randPoint ，在圆中产生均匀随机点。
 */
public class RandPointSln {
	
	public static void main(String[] args) {
		double radius = 0.01;
		double x_center = -73839.1;
		double y_center = -3289891.3;
		Solution sln = new Solution(radius, x_center, y_center);
		double[] point = sln.randPoint();
		for (double v : point) {
			System.out.printf("%f,", v);
		}
		System.out.printf("\nr=%f,r1=%f", radius, Math.sqrt(Math.pow(point[0] - x_center, 2) + Math.pow(point[1] - y_center, 2)));
	}
	
	public static class Solution {
		
		private final double radius;
		private final double x_center;
		private final double y_center;
		
		public Solution(double radius, double x_center, double y_center) {
			this.radius = radius;
			this.x_center = x_center;
			this.y_center = y_center;
		}
		
		public double[] randPoint() {
			while (true) {
				double randomX = Math.random() * (radius * 2) - radius;
				double randomY = Math.random() * (radius * 2) - radius;
				//小于等于r^2
				if (randomX * randomX + randomY * randomY <= radius * radius) {
					return new double[]{randomX + x_center, randomY + y_center};
				}
			}
		}
		
	}
}
