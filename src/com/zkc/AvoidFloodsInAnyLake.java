package com.zkc;

import com.zkc.utils.MyUtils;

import java.util.*;

/**
 * Your country has an infinite number of lakes. Initially, all the lakes are empty, but when it rains over the nth lake,
 * the nth lake becomes full of water. If it rains over a lake that is full of water, there will be a flood.
 * Your goal is to avoid floods in any lake.
 * <p>
 * Given an integer array rains where:
 * rains[i] > 0 means there will be rains over the rains[i] lake.
 * rains[i] == 0 means there are no rains this day and you can choose one lake this day and dry it.
 * Return an array ans where:
 * <p>
 * ans.length == rains.length
 * ans[i] == -1 if rains[i] > 0.
 * ans[i] is the lake you choose to dry in the ith day if rains[i] == 0.
 * <p>
 * If there are multiple valid answers return any of them. If it is impossible to avoid flood return an empty array.
 * <p>
 * Notice that if you chose to dry a full lake, it becomes empty, but if you chose to dry an empty lake, nothing changes.
 */
public class AvoidFloodsInAnyLake {
	public static void main(String[] args) {
		int[] rains = new int[]{69, 0, 0, 0, 69};
//		int[] rains = new int[]{69,0,0,0,69,69};
		MyUtils.printArr(rains);
		MyUtils.printArr(avoidFlood(rains));
	}
	
	/**
	 * 遍历数组 遇到>0表示湖泊下雨记录出现该号湖泊下雨次数ans[i]=-1 遇到0表示晴天记录可以抽水的次数以及当前时间点  只有在当前时间点之前下雨的湖泊可以抽水
	 * 如果遇到某号x湖泊下雨超过俩次 查看有没有可用的抽水次数 如果有对该号湖泊抽水 ans[i]=x; 如果无法抽水该号湖泊遇到洪水返回空数组。
	 */
	private static int[] avoidFlood(int[] rains) {
		//第i号湖泊在哪天下雨
		Map<Integer, List<Integer>> lakeRainDayIdx = new HashMap<>();
		//哪天是晴天
		List<Integer> sunnyDayIdx = new ArrayList<>();
		int[] ans = new int[ rains.length];
		for (int i = 0; i <  rains.length; i++) {
			if (rains[i] > 0) {
				int lakeNo = rains[i];
				if (lakeRainDayIdx.containsKey(lakeNo)) {
					//最多存放俩个
					lakeRainDayIdx.get(lakeNo).add(i);
				} else {
					List<Integer> posLst = new ArrayList<>();
					posLst.add(i);
					lakeRainDayIdx.put(lakeNo, posLst);
				}
				List<Integer> posLst = lakeRainDayIdx.get(lakeNo);
				//下雨达到两次
				if (posLst.size() == 2) {
					//晴天数量
					if (sunnyDayIdx.size() > 0) {
						//求大于当前湖泊第一次下雨的最左的晴天出现在第几天   有可能存在比第一次下雨的那天更早的晴天 但是不能使用 只能找后面的
						int validSunnyDay = findDryDayPos(sunnyDayIdx, posLst.get(0));
						//可以抵消
						if (validSunnyDay > -1) {
							//当前湖泊抽干后再次被装满-1 
							ans[i] = -1;
							//用来抵消的晴天位置设置为当前湖泊的序号
							ans[validSunnyDay] = lakeNo;
							//耗掉一个晴天 当前湖泊第一次下雨的那天不需要再考虑
							lakeRainDayIdx.get(lakeNo).remove(0);
						} else {
							//无法抽水 遇到洪水
							return new int[]{};
						}
					} else {
						//无法抽水 遇到洪水
						return new int[]{};
					}
				} else {
					//当前湖泊被装满-1 
					ans[i] = -1;
				}
			} else {
				sunnyDayIdx.add(i);
			}
		}
		for (Integer dayIdx : sunnyDayIdx) {
			ans[dayIdx] = 1;
		}
		return ans;
	}
	
	private static int findDryDayPos(List<Integer> dryDay, int i) {
		int dst = -1;
		int left = 0;
		int right = dryDay.size() - 1;
		while (left <= right) {
			int mid = left + ((right - left) >> 1);
			if (dryDay.get(mid) > i) {
				dst = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		int ret = -1;
		if (dst > -1) {
			ret = dryDay.get(dst);
			dryDay.remove(dst);
		}
		return ret;
	}
	
}
