package com.zkc;

import com.zkc.utils.MyUtils;

import java.util.Arrays;

/**
 * 给定数组arr,求所有数字对差值的绝对值中 第K小的那个绝对值，K从1开始,不存在返回-1
 */
public class KthMinAbsoluteVal {
	
	public static void main(String[] args) {
		int len = 10;
		int[] arr = MyUtils.getArray(len, 20);
//		int[] arr = new int[]{1, 2, 3, 6, 9, 11, 14};
		MyUtils.printArr(arr);
		int k = (int) (Math.random() * (len * (len - 1) >> 1)) + 1;
//		int k = 9;
		System.out.printf("k=%d,abs1=%d\n", k, getKthMinAbsoluteVal(arr, k));
		System.out.printf("k=%d,abs2=%d\n", k, getKthMinAbsoluteVal2(arr, k));
//		for (int i = 0; i < 10000; i++) {
//			arr = MyUtils.getArray(10, 30);
//			k = (int) (Math.random() * 5) + 1;
//			int abs1 = getKthMinAbsoluteVal(arr, k);
//			int abs2 = getKthMinAbsoluteVal2(arr, k);
//			if (abs1 != abs2) {
//				System.out.printf("k=%d,abs1=%d,abs2=%d\n", k, abs1, abs2);
//			}
//		}
	}
	
	
	/**
	 * 假如已经将arr中所有数字对的差值绝对值由小到大排好序 并且每个位置的数量也已求出
	 * minK为最小为0,最大maxK为arr[max]-arr[min]，为arr产生绝对值可能的范围。数字对个数、绝对值个数相同都为n-1+n-2+.....+1=（n-1+1）* (n-1) /2个。
	 * 设k数组为绝对值范围[..minK....K.....maxK],num数组为每个绝对值0,1,2.....maxK对应的数量[numMink....0...numK..00...numMaxK],
	 * 其中为0的说明arr数组中的数字对不能产生这样的绝对值。
	 * 要求第K小的，也就是将num数组每个位置绝对值的数量累加 数量正好达到K的那个位置的绝对值为返回结果。
	 * <p>
	 * 可以从num数组从左往右找 去找到一个这样的位置【i】  i位置及之前累加数量小于K，i+1位置数量正好大于等于K，则i+1位置对应的绝对值为返回结果。
	 * 如果在某个区域内累加数量不变，说明这个区域的绝对值不存在，所以i的位置要尽量靠右。
	 * <p>
	 * 为了加快速度 二分查找绝对值的数量，初始最小值left为0 初始最大right为maxK,mid绝对值=(maxK-0)/2;
	 * 如果mid绝对值所在的位置及之前的累加数量大于K，位置太靠右数量累加多了要回退，数量等于K的位置肯定在mid所在位置左侧。
	 * 如果mid绝对值所在的位置及之前的累加数量小于K，位置太靠左数量累加的不够,数量等于K的位置肯定在mid所在位置右侧。
	 * 如果mid绝对值所在的位置及之前的累加数量等于K，因为只找最右小于K的位置(加+1得到最终结果)，所以相等归到大于情况处理。
	 */
	private static int getKthMinAbsoluteVal2(int[] arr, int k) {
		int n = arr.length;
		//数组长度小于2不能形成数字对  K要从第1个开始 而且不能大于能够产生的绝对值的总数量
		if (n < 2 || k < 1 || k > (((n - 1) * n) >> 1)) {
			return -1;
		}
		//先对数组排序
		Arrays.sort(arr);
		//绝对值二分 统计数量  
		int left = 0;
		int right = arr[n - 1] - arr[0];
		//目标结果 累加数量小于K的最右位置的绝对值 最终结果再加一   
		int ret = -1;
		while (left <= right) {
			int mid = left + ((right - left) >> 1);
			//判断mid位置的绝对值的累加数量是否小于K 如果是 记录一个结果 再向右找  最后一个记录的绝对值为ret
			if (sumLessThanK(arr, mid, k)) {
				ret = mid;
				left = mid + 1;
			} else {
				//大于减少查找的绝对值 向左
				right = mid - 1;
			}
		}
		//该位置绝对值再加一正好能达到k
		return ret + 1;
	}
	
	/**
	 * O(N)
	 * 双指针统计已排好序的arr中数字对的绝对值小于等于abs的总个数，如果小于K返回true
	 * <p>
	 * 遍历arr, left指向i位置,right分别指向i+1,i+2,....n-1位置，left、right位置形成的数字对求绝对值，
	 * 如果绝对值小于等于abs，right一直向右移动，直到遇到绝对值大于abs 统计一次数量, 为right-left-1。
	 * 此时left来到i+1位置,右指针不动,假设right停在j位置。
	 * 因为上次j与i位置得到的绝对值正好大于abs,那么j-1位置与i得到绝对值的肯定小于abs。
	 * 因为数组排过序此时i+1位置的数比i位置的数大，再与i+2....j-1位置的数得到绝对值肯定也小于abs，
	 * 所以right不用再回退到i+2位置,直接让right从当前j位置开始,再次停止时 返回当前遍历的结果 同样为right-left-1。
	 * <p>
	 * 如 arr=[1  2  3  6  9  11  14] k=5 ,第一轮left此时在0位置，right此时停在4位置,得到数量为right-left+1=4-0-1=3，
	 * 下一轮left来到1位置,right不用回到2位置, 因为2、3位置与1位置形成的绝对值肯定也小于abs,所以right直接从4位置开始向右移动，。
	 */
	private static boolean sumLessThanK(int[] arr, int maxAbs, int k) {
		int count = 0;
		int left = 0;
		int right = 1;
		while (left < arr.length - 1) {
			while (right < arr.length && Math.abs(arr[left] - arr[right]) <= maxAbs) {
				right++;
			}
			count += right - left - 1;
			//left向右移动后  如果right一直没动 可能与left重叠 重叠时r右移
			if (++left == right) {
				right++;
			}
		}
		return count < k;
	}
	
	private static int getKthMinAbsoluteVal(int[] arr, int k) {
		int n = arr.length;
		int[] nums = new int[(n * (n - 1)) >> 1];
		int idx = 0;
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				nums[idx++] = Math.abs(arr[i] - arr[j]);
			}
		}
		Arrays.sort(nums);
		MyUtils.printArr(nums);
		return nums[k - 1];
	}
}
