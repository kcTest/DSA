package com.zkc.dp;

import com.zkc.utils.MyUtils;

/**
 * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * <p>
 * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。
 * 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
 * <p>
 * 求所能获得硬币的最大数量。
 * <p>
 * <p>
 * 示例 1：
 * 输入：nums = [3,1,5,8]
 * 输出：167
 * 解释：
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 * 示例 2：
 * <p>
 * 输入：nums = [1,5]
 * 输出：10
 */
public class BurstBalloons {
	
	public static void main(String[] args) {
//		int[] nums = MyUtils.getArray(5, 10);
		int[] nums = new int[]{3, 1, 5, 8, 9};
//		int[] nums = new int[]{3, 1, 5, 8};
//		int[] nums = new int[]{ 1, 5, };
//		int[] nums = new int[]{5,};
		MyUtils.printArr(nums);
//		System.out.println(maxCoins(nums));
		System.out.println(maxCoins2(nums));
		System.out.println(maxCoins3(nums));
	}
	
	
	/**
	 * 定义函数f(nums,l,r)  返回在l到r范围上击破气球能获得硬币的最大数量
	 * 假设l到r 每一个位置的气球都可能最后击破
	 * l最后击破时 获得硬币数量为 (左侧已越界没有气球1 或 nums[l-1])*nums[l]*(右侧已越界没有气球1 或 nums[r+1]) + f(nums,l+1,r)
	 * r最后击破时 获得硬币数量为 (左侧已越界没有气球1 或 nums[l-1])*nums[r]*(右侧已越界没有气球1 或 nums[r+1]) + f(nums,l,r-1)
	 * 中间l+1到r-1位置 枚举每一个位置i最后击破的可能， 分为三部分相加 i左侧 i右侧 i位置
	 * 获得硬币数量 f(nums,l+1,r-1)=
	 * f(nums,l+1,i-1)(加上剩余i左边范围内能获取的最大值) + f(nums,i+1,r-1)(右侧) + nums[l+1-1]*nums[i]*nums[r-1+1](当前位置值乘当前范围再往外的一个位置值 越界为1 )
	 * 每个位置算完后比较保留最大值
	 */
	private static int maxCoins(int[] nums) {
		int n = nums.length;
		int[] nums2 = new int[n + 2];
		//边界外设置为1 去除方法内边界判断  
		nums2[0] = 1;
		nums2[n + 1] = 1;
		System.arraycopy(nums, 0, nums2, 1, n);
		return f(nums2, 1, n);
	}
	
	/**
	 * 枚举每个位置都被最后击破的情况下 能获得的最大硬币数
	 * 最后击破 当前范围内只有自己一个元素 但范围外可能还有元素
	 */
	private static int f(int[] nums, int l, int r) {
		//l=r范围最小 当前位置结果先被确定 计算当前位置时 左或右位置可能有元素 
		if (l == r) {
			return nums[l - 1] * nums[l] * nums[l + 1];
		}
		
		int max = 0;
		
		//左边界最后击破[00000[l....]0000] l左侧可能有值 最右边界外r+1可能有值 
		int maxLeft = nums[l - 1] * nums[l] * nums[r + 1] + f(nums, l + 1, r);
		max = Math.max(max, maxLeft);
		
		//右边界最后击破[00000[....r]0000] r右侧可能有值 最左边界l-1可能有值
		int maxRight = f(nums, l, r - 1) + nums[l - 1] * nums[r] * nums[r + 1];
		max = Math.max(max, maxRight);
		
		//边界之间的位置最后击破[00000[..i..]0000]  l到r范围内最后只剩i l-1或r+1可能有值
		for (int i = l + 1; i <= r - 1; i++) {
			int maxMiddle = f(nums, l, i - 1) + f(nums, i + 1, r) + nums[l - 1] * nums[i] * nums[r + 1];
			max = Math.max(max, maxMiddle);
		}
		
		return max;
	}
	
	private static int maxCoins2(int[] nums) {
		int n = nums.length;
		int[] nums2 = new int[n + 2];
		nums2[0] = 1;
		nums2[n + 1] = 1;
		System.arraycopy(nums, 0, nums2, 1, n);
		//使用数组记录保存指定范围内的结果 不再重复递归
		int[][] ret = new int[n + 2][n + 2];
		int max = f2(nums2, 1, n, ret);
		return max;
	}
	
	private static int f2(int[] nums, int l, int r, int[][] ret) {
		if (ret[l][r] > 0) {
			return ret[l][r];
		}
		if (l == r) {
			ret[l][r] = nums[l - 1] * nums[l] * nums[l + 1];
			return ret[l][r];
		}
		
		int max = 0;
		
		int maxLeft = nums[l - 1] * nums[l] * nums[r + 1] + f2(nums, l + 1, r, ret);
		max = Math.max(max, maxLeft);
		
		int maxRight = f2(nums, l, r - 1, ret) + nums[l - 1] * nums[r] * nums[r + 1];
		max = Math.max(max, maxRight);
		
		for (int i = l + 1; i <= r - 1; i++) {
			int maxMiddle = f2(nums, l, i - 1, ret) + f2(nums, i + 1, r, ret) + nums[l - 1] * nums[i] * nums[r + 1];
			max = Math.max(max, maxMiddle);
		}
		ret[l][r] = max;
		return ret[l][r];
	}
	
	/**
	 * dp
	 * 每个单元格[i,j]表示 数组nums在i->j范围内击破气球能获得的最大硬币数
	 * ij表示数组范围索引 i不能大于j  设置的单元格均处于对角线及右上的半部分
	 * 根据f2对角线直接设置 其余每个单元格计算依赖其左及左下方的单元格的值
	 * 初始直接设置对角线的值 所以沿对角线从右下往左上设置值  直到右上角的单元格
	 */
	private static int maxCoins3(int[] nums) {
		int n = nums.length + 2;
		int[] arr = new int[n];
		arr[0] = 1;
		arr[n - 1] = 1;
		System.arraycopy(nums, 0, arr, 1, n - 2);
		int[][] ret = new int[n][n];
		//设置对角线已  对角线往上开始  arr比原数组长度+2 形成的表格多包围一层  最外层的单元格不需要设置  
		for (int i = 1; i <= n - 2; i++) {
			ret[i][i] = arr[i - 1] * arr[i] * arr[i + 1];
		}
		//斜线方向需要设置的轮次 由原数组长度决定 n-2-1  
		for (int loop = n - 3, dis = 0; loop >= 1; loop--, dis++) {
			//外层不设置 起始单元格为倒数第三行n-1-1-1 倒数第二排n-1-1 
			// i每轮初始减1,结束行为第一行; j与i的差=dis 每轮dis++ ;
			for (int i = n - 3 - dis, j = i + (dis + 1); i >= 1; i--, j--) {
				int maxLeft = arr[i - 1] * arr[i] * arr[j + 1] + ret[i + 1][j];
				int maxRight = ret[i][j - 1] + arr[i - 1] * arr[j] * arr[j + 1];
				int max = Math.max(maxLeft, maxRight);
				for (int m = i + 1; m <= j - 1; m++) {
					int maxMiddle = ret[i][m - 1] + ret[m + 1][j] + arr[i - 1] * arr[m] * arr[j + 1];
					max = Math.max(max, maxMiddle);
				}
				ret[i][j] = max;
			}
		}
		return ret[1][n - 2];
	}
	
	
}
