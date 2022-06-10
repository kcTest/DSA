package com.zkc.dp;

public class HorseJump {
	
	/**
	 * 棋盘10条横线 9条竖线 每个棋子横坐标0-8 纵坐标0-9
	 * 求马从原点出发在指定步数正好到达目的位置一共有几种路线
	 */
	private static int maxX = 8;
	private static int maxY = 9;
	
	public static void main(String[] args) {
		//目标位置
		int destX = (int) (Math.random() * maxX);
		int destY = (int) (Math.random() * maxY);
		//限制几步到达目的位置
		int steps = Math.max(destX, destY);
		System.out.printf("destX:%d,destY:%d,steps:%d\n", destX, destY, steps);
		System.out.printf("horseJump1:%d\n", horseJump1(destX, destY, steps));
		System.out.printf("horseJump2:%d\n", horseJump2(destX, destY, steps));
	}
	
	private static int horseJump2(int destX, int destY, int restSteps) {
		//x轴 目标横坐标 y轴 目标纵坐标  z轴 几步达到目标位置  
		int[][][] record = new int[maxX + 1][maxY + 1][restSteps + 1];
		//0层原点直接可以确定  不需要移动  存在一种路线 。 0层其余点均不可能存在0步到达路线 已经为0
		record[0][0][0] = 1;
		// 第一层开始 每层依赖下一层  所以从下层往上设置
		for (int k = 1; k < restSteps + 1; k++) {
			for (int i = 0; i < record.length; i++) {
				for (int j = 0; j < record[0].length; j++) {
					//依赖八个位置  
					record[i][j][k] = (i - 1 >= 0 && j - 2 >= 0 ? record[i - 1][j - 2][k - 1] : 0)
							+ (i - 1 >= 0 && j + 2 <= maxY ? record[i - 1][j + 2][k - 1] : 0)
							+ (i + 1 <= maxX && j - 2 >= 0 ? record[i + 1][j - 2][k - 1] : 0)
							+ (i + 1 <= maxX && j + 2 <= maxY ? record[i + 1][j + 2][k - 1] : 0)
							+ (i - 2 >= 0 && j - 1 >= 0 ? record[i - 2][j - 1][k - 1] : 0)
							+ (i - 2 >= 0 && j + 1 <= maxY ? record[i - 2][j + 1][k - 1] : 0)
							+ (i + 2 <= maxX && j + 1 <= maxY ? record[i + 2][j + 1][k - 1] : 0)
							+ (i + 2 <= maxX && j - 1 >= 0 ? record[i + 2][j - 1][k - 1] : 0);
				}
			}
		}
		//当前位置为所求结果
		return record[destX][destY][restSteps];
	}
	
	private static int horseJump1(int destX, int destY, int restSteps) {
		return sln1(destX, destY, restSteps);
	}
	
	
	private static int sln1(int destX, int destY, int restSteps) {
		//目标超出棋盘位置 
		if (destX < 0 || destX > maxX || destY < 0 || destY > maxY) {
			return 0;
		}
		// 0步情况下
		if (restSteps == 0) {
			//原点不需要移动 路线为1
			if (destX == 0 && destY == 0) {
				return 1;
			}
			//所有不在原点的位置均不能通过0步到达
			return 0;
		}
		//逆向  对于到达目的位置之前可能经过的前一个位置 用restSteps - 1步到达 有几种可能路线  马周围所有可能坐标 
		return sln1(destX - 1, destY - 2, restSteps - 1)
				+ sln1(destX - 1, destY + 2, restSteps - 1)
				+ sln1(destX + 1, destY - 2, restSteps - 1)
				+ sln1(destX + 1, destY + 2, restSteps - 1)
				+ sln1(destX - 2, destY - 1, restSteps - 1)
				+ sln1(destX - 2, destY + 1, restSteps - 1)
				+ sln1(destX + 2, destY + 1, restSteps - 1)
				+ sln1(destX + 2, destY - 1, restSteps - 1);
	}
}
