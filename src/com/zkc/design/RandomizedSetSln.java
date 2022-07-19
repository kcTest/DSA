package com.zkc.design;

import java.util.*;

public class RandomizedSetSln {
	
	public static void main(String[] args) {
		RandomizedSet obj = new RandomizedSet();
		boolean insert = obj.insert(-1);
		boolean remove = obj.remove(-2);
		boolean insert1 = obj.insert(-2);
		int random = obj.getRandom();
		boolean remove1 = obj.remove(-1);
		boolean insert2 = obj.insert(-2);
		int random2 = obj.getRandom();
		System.out.println(insert);
		System.out.println(remove);
		System.out.println(insert1);
		System.out.println(random);
		System.out.println(remove1);
		System.out.println(insert2);
		System.out.println(random2);
//		obj.insert(1);
//		obj.insert(2);
//		obj.insert(3);
//		Map<Integer, Integer> map = new HashMap<>();
//		for (int i = 0; i < 10000; i++) {
//			int param = obj.getRandom();
//			map.put(param, map.getOrDefault(param, 0) + 1);
//		}
//		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//			System.out.printf("num=%d,count=%d\n", entry.getKey(), entry.getValue());
//		}
	}
	
	private static class RandomizedSet {
		private Map<Integer, Integer> idxMap;
		private Map<Integer, Integer> valMap;
		int size;
		
		public RandomizedSet() {
			idxMap = new HashMap<>(200000);
			valMap = new HashMap<>(200000);
			size = 0;
		}
		
		public boolean insert(int val) {
			if (valMap.containsKey(val)) {
				return false;
			} else {
				idxMap.put(size, val);
				valMap.put(val, size++);
				return true;
			}
		}
		
		public boolean remove(int val) {
			if (!valMap.containsKey(val)) {
				return false;
			} else {
				//使用末尾数顶替 保持索引连续
				int oldIdx = valMap.get(val);
				int replaceVal = idxMap.get(--size);
				//覆盖
				valMap.put(replaceVal, oldIdx);
				//覆盖
				idxMap.put(oldIdx, replaceVal);
				valMap.remove(val);
				return true;
			}
		}
		
		public int getRandom() {
			int idx = (int) (Math.random() * size);
			return idxMap.get(idx);
		}
	}
}
