package com.zkc.greedy;

import com.zkc.utils.MyUtils;

import java.util.*;

/**
 * 给定一个正数数组 每个位置的数都是0-9 使用每个位置的数字拼出一个能被3整除的最大的数字,数字顺序可以打乱。 用str形式返回
 */
public class Mod3MaxCombination {
	public static void main(String[] args) {
		int[] arr = MyUtils.getArray(3, 10);
		MyUtils.printArr(arr);
		System.out.println(getMod3MaxNum(arr));
	}
	
	
	/**
	 * 一个数每个位置的数字除以3得到的余数相加 得到的和如果是3的倍数 对3取模是0,那么这个数本身也是3的倍数能被3整除。
	 * 一个数对3取模 得到的可能结果是0 1 2。
	 * 1如果把所有数字相加得到的和对3取模为0，说明说明这个数组每个数随意改变顺序得到
	 * 的数都能整除3。可以直接取每个位置的数从大到小排序，直接将数组每位按顺序拼成符串返回。
	 * <p>
	 * 2如果第一条不满足。先将每个位置的数对3取模得到的余数相加，再用这个余数之和sum对3取模。如果取模结果正好为0说明余数之和
	 * 是3的倍数，那么每个数之和本身就可以被三整数，这符合第一条。所以考虑余数之和sum对3取模为1,2的情况，想办法让sum%3=0;
	 * <p> (1):如果为1，去除多出的1，sum%3的余数能变为0。为了保持结果最大，从数组中每个位置的数%3为1的位置选取数值最小的一个数
	 * 去掉，如果没有%3=1的，从数组中每个位置的数%3=2的位置选取数值最小的俩个数去掉，因为这两个数的余数相加%3=1。如果这俩种情况下
	 * 都找不出待去除的数，说明数组里所有的数无法拼凑成一个能被3整除的数。
	 * <p> (2):如果为2，去除多出的2，sum%3的余数能变为0。为了保持结果最大，从数组中每个位置的数%3为1的位置选取数值最小的俩个
	 * 去掉，因为这两个数的余数相加%3=1。如果没有%3=1的，从数组中每个位置的数%3=2的位置选取数值最小的一个数去掉。如果这俩种情况下
	 * 都找不出待去除的数，说明数组里所有的数无法拼凑成一个能被3整除的数。
	 * <p>最后从能留下来的位置中取出数从大到小排序，按顺序拼成符串返回。
	 */
	private static String getMod3MaxNum(int[] arr) {
		int sum = 0;
		LinkedList<Integer> lst = new LinkedList<>();
		for (int num : arr) {
			lst.addLast(num);
			sum += num;
		}
		lst.sort(Comparator.reverseOrder());
		int sumMod3 = sum % 3;
		if (sumMod3 == 0) {
			//0组成
			if (lst.getFirst() == 0) {
				return "0";
			}
		} else if (sum % 3 == 1) {
			if (!removeRedundant(lst, 1, 2)) {
				return "";
			}
		} else if (sum % 3 == 2) {
			if (!removeRedundant(lst, 2, 1)) {
				return "";
			}
		}
		StringBuilder sb = new StringBuilder();
		for (Integer integer : lst) {
			sb.append(integer);
		}
		return sb.toString();
	}
	
	private static boolean removeRedundant(LinkedList<Integer> lst, int remainder1Num, int remainder2Num) {
		List<Integer> remainder1Lst = new ArrayList<>(2);
		List<Integer> remainder2Lst = new ArrayList<>(2);
		//尽量选择可以只去除1个位置的
		for (int i = lst.size() - 1; i >= 0; i--) {
			if (lst.get(i) % 3 == 1 && remainder1Lst.size() < remainder1Num) {
				remainder1Lst.add(i);
			}
			if (lst.get(i) % 3 == 2 && remainder2Lst.size() < remainder2Num) {
				remainder2Lst.add(i);
			}
		}
		List<Integer> target;
		if (remainder1Lst.size() != remainder1Num && remainder2Lst.size() != remainder2Num) {
			return false;
		} else if (remainder1Lst.size() == remainder1Num && remainder2Lst.size() != remainder2Num) {
			target = remainder1Lst;
		} else if (remainder1Lst.size() != remainder1Num) {
			target = remainder2Lst;
		} else {
			target = remainder1Num < remainder2Num ? remainder1Lst : remainder2Lst;
		}
		for (int removePos : target) {
			lst.remove(removePos);
		}
		return true;
	}
}
