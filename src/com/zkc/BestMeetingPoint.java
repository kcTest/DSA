package com.zkc;

/**
 * 给你一个 m x n 的二进制网格 grid ，其中 1 表示某个朋友的家所处的位置。返回 最小的 总行走距离 。
 * <p>
 * 总行走距离 是朋友们家到碰头地点的距离之和。
 * <p>
 * 我们将使用 曼哈顿距离 来计算，其中 distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y| 。
 * <p>
 * 输入: grid =
 * [[1,0,0,0,1],
 * [ 0,0,0,0,0],
 * [ 0,0,1,0,0]]
 * 输出: 6
 * 解释: 给定的三个人分别住在(0,0)，(0,4) 和 (2,2):
 * (0,2) 是一个最佳的碰面点，其总行走距离为 2 + 2 + 2 = 6，最小，因此返回 6。
 */
public class BestMeetingPoint {
	public static void main(String[] args) {
		int[][] grid = new int[][]{
				{1, 0, 0, 0, 1},
				{0, 0, 0, 0, 0},
				{0, 0, 1, 0, 0}
		};
		System.out.println(minTotalDistance(grid));
	}
	
	/**
	 * 分别求出最佳地点p所在的行列
	 * <p>
	 * 使用数组统计每行有几个1。假设[1,1]行1的数量为a,[m,m]行1的数量为b,双指针分别指向1和m位置，假设a>b，假设p在下端。
	 * 假设最佳位置p在第m行,p距离[1,m-1]行所有1的距离总和为: p距离[1,1]行所有1的距离总和为(m-1)*a, 加 p距离[2,m-1]行的所有1的距离总和假设为x；
	 * 假设最佳位置p在第m-1行,p距离[m,m],[1,m-2]行所有1的距离总和为: p距离[1,1]行所有1的距离总和为(m-2)*a，
	 * 加 p距离[2,m-2]行的所有1的距离总和假设为y, 加 p到[m,m]行所有1的距离b；
	 * 首先可以知道y<x，因为m-1行1数量不变，y情况的距离更短,
	 * a>b的情况下 (m-1)*a+x=(m-2)*a+x+a,  (m-2)*a+y+b,可以得到p位置如果在m-1行总距离会更小,p位置的范围缩小到[1,m-1]。
	 * 此时继续对比[1,1]行1的数量,[m-1,m]行1的数量，让小的一方作为p的位置,用同样的方法处理，
	 * 仍然会得到结论p在较小的一方的前一个位置(p在上端，取下一个位置，p在下端，取前一个位置)总距离会更好。
	 * 指针不断移动缩小范围直到相遇,此时的位置p所在行求出。
	 * <p>
	 * 再用相同的方法确定p所在的列。
	 * 把俩个过程统计的距离总和返回。
	 */
	private static int minTotalDistance(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		//第i行有几个1
		int[] rowOnes = new int[m], colOnes = new int[n];
		//第j列有几个1
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					rowOnes[i]++;
					colOnes[j]++;
				}
			}
		}
		int sum = 0;
		int top = 0, bot = m - 1, onesBeforeTop = 0, onesAfterBot = 0;
		while (top < bot) {
			//较小一端移动
			if (rowOnes[top] + onesBeforeTop > rowOnes[bot] + onesAfterBot) {
				//总距离需要加上[bot,bot]行1的总距离 
				sum += onesAfterBot + rowOnes[bot];
				//bot之后的所有1数量更新 也就是加上当前行的数量  然后下指针上移1位来到bot-1.
				onesAfterBot += rowOnes[bot--];
			} else {
				//总距离需要加上[top,top]行1的总距离 
				sum += onesBeforeTop + rowOnes[top];
				//top之前的所有1数量更新 也就是加上当前行的数量  然后上指针下移1位来到top+1.
				onesBeforeTop += rowOnes[top++];
			}
		}
		int left = 0, right = n - 1, onesBeforeLeft = 0, onesAfterRight = 0;
		while (left < right) {
			//较小一端移动
			if (colOnes[left] + onesBeforeLeft > colOnes[right] + onesAfterRight) {
				//总距离需要加上[right,right]列1的总距离 
				sum += onesAfterRight + colOnes[right];
				//right之后的所有1数量更新 也就是加上当前列的数量  然后右指针左移1位来到right-1.
				onesAfterRight += colOnes[right--];
			} else {
				//总距离需要加上[left,left]列1的总距离 
				sum += onesBeforeLeft + colOnes[left];
				//left之前的所有1数量更新 也就是加上当前列的数量  然后左指针右移1位来到left+1.
				onesBeforeLeft += colOnes[left++];
			}
		}
		return sum;
	}
}
