package com.zkc.dp;

public class WalkWay {
	public static void main(String[] args) {
		int n = 9;
		int start = 3;
		int end = 5;
		int k = 4;
		System.out.println(walkway1(n, start, end, k));
		System.out.println(walkway2(n, start, end, k));
		System.out.println(walkway3(n, start, end, k));
	}
	
	/**
	 * @param N N个位置
	 * @param S 起始位置
	 * @param E 结束位置
	 * @param K 走K步
	 * @return 从S走K步到E一共有几种走法
	 * <p>1 2 3 4 5 6 7 8 9
	 * <p>    S       E
	 */
	private static int walkway1(int N, int S, int E, int K) {
		return sln1(N, E, S, K);
	}
	
	/**
	 * @param cur  当前位置
	 * @param rest 剩余步数
	 */
	private static int sln1(int N, int E, int cur, int rest) {
		if (rest == 0) {
			//不能继续走如果已经来到目标位置 当前路线算一种走法
			return cur == E ? 1 : 0;
		}
		//左边界只能向右走
		if (cur == 1) {
			return sln1(N, E, cur + 1, rest - 1);
		}
		//右边界向左走
		if (cur == N) {
			return sln1(N, E, cur - 1, rest - 1);
		}
		//否则返回向左右俩侧尝试的结果 
		return sln1(N, E, cur - 1, rest - 1) + sln1(N, E, cur + 1, rest - 1);
	}
	
	private static int walkway2(int N, int S, int E, int K) {
		//记录 在当前位置和剩余步数下到达目标位置有几种走法 -1表示没有经过
		//剩余步数范围为0-k,位置范围为1-N
		int[][] record = new int[N + 1][K + 1];
		return sln2(N, E, S, K, record);
	}
	
	/**
	 * O(NxK)
	 *
	 * @param cur    当前位置
	 * @param rest   剩余步数
	 * @param record 结果缓存 不需要重复向下走
	 */
	private static int sln2(int N, int E, int cur, int rest, int[][] record) {
		//如果已经存在 直接从缓存中获取
		if (record[cur][rest] > 0) {
			return record[cur][rest];
		}
		if (rest == 0) {
			//返回之前缓存当前路线结果
			record[cur][rest] = cur == E ? 1 : 0;
			return record[cur][rest];
		}
		if (cur == 1) {
			//返回之前缓存当前路线结果
			record[cur][rest] = sln2(N, E, cur + 1, rest - 1, record);
		} else if (cur == N) {
			//返回之前缓存当前路线结果
			record[cur][rest] = sln2(N, E, cur - 1, rest - 1, record);
		} else {
			//返回之前缓存当前路线结果
			record[cur][rest] = sln2(N, E, cur - 1, rest - 1, record) + sln2(N, E, cur + 1, rest - 1, record);
		}
		return record[cur][rest];
	}
	
	/**
	 * O(NxK)
	 */
	private static int walkway3(int N, int S, int E, int K) {
		//记录 在当前位置和剩余步数下到达目标位置有几种走法 -1表示没有经过
		//剩余步数范围为0-k,位置范围为1-N
		int[][] record = new int[N + 1][K + 1];
		//目标位置剩余零步可以直接返回1
		record[E][0] = 1;
		//从表格左侧开始从上到下设置缓存
		for (int col = 1; col < record[0].length; col++) {
			for (int row = 1; row < record.length; row++) {
				//当前位置不会为0 第0缓存结果默认行全为0。直接从1开始
				if (row == 1) {
					//根据sln2 当前位置直接取左上（0行）及左下位置的缓存
					record[row][col] = record[row + 1][col - 1];
				} else if (row == record.length - 1) {
					//根据sln2 当前位置直接取左上位置的缓存
					record[row][col] = record[row - 1][col - 1];
				} else {
					//根据sln2 当前位置直接取左上及左下位置的缓存
					record[row][col] = record[row - 1][col - 1] + record[row + 1][col - 1];
				}
			}
		}
		//获取结果需要遍历到最后一列 
		return record[S][K];
	}
}
