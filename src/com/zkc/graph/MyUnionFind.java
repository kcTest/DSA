package com.zkc.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * 不相交集是一种数据结构，它跟踪一组分割成若干不相交(不重叠)子集的元素。
 * 换句话说，不相交集是一组集合，其中任何项都不能在多个集合中。
 * 它也称为联合查找数据结构，因为它支持子集上的联合和查找操作
 * 1.查找过程中路径压缩
 * 2.合并过程中根据级别决定谁作为父树
 */
public class MyUnionFind {
	
	/**
	 * 是始终将较小的树附加到较大的树的根上。由于影响运行时间的是树的深度，
	 * 所以深度较小的树被添加到深度较大的树的根下，这样只会增加深度相等的树。如当两个相同深度 r 的树合并时，结果为 r + 1
	 */
	private Map<MyGraphNode, Integer> rankMap = new HashMap<>();
	private Map<MyGraphNode, MyGraphNode> parentMap = new HashMap<>();
	
	
	public void makeSet(MyGraphNode n) {
		if (!parentMap.containsKey(n)) {
			parentMap.put(n, n);
			rankMap.put(n, 0);
		}
	}
	
	/**
	 * Find 操作跟随指定查询节点 n 的父指针链，直到到达根元素。这个根元素表示 n 所属的集合，并且可能是 n 本身。Find 返回它到达的根元素
	 */
	public MyGraphNode find(MyGraphNode n) {
		//路径压缩 访问到根节点的每个节点可以直接附加到根节点使得到的树更加扁平化，不仅加快了未来对这些元素的操作，而且加快了直接或间接引用这些元素的操作。
		//n的父节点不是根节点继续向上找到根节点  n直接附加到根节点
		if (parentMap.get(n) != n) {
			parentMap.put(n, find(parentMap.get(n)));
		}
		return parentMap.get(n);
	}
	
	/**
	 * 如果根的级别不同，那么较大级别的树成为父树，而 n1 和 n2 的级别不变。如果级别相同，那么任何一个都可以成为父级，但是新父级的级别增加了一级
	 */
	public void union(MyGraphNode n1, MyGraphNode n2) {
		MyGraphNode p1 = find(n1);
		MyGraphNode p2 = find(n2);
		if (p1 == p2) {
			//同根
			return;
		}
		int r1 = rankMap.get(p1);
		int r2 = rankMap.get(p2);
		//n2的根的级别大于等于n1的根的级别时 统一将n2的根作为n1的根
		if (r2 >= r1) {
			parentMap.put(p1, p2);
			if (r1 == r2) {
				//相同 父树根级别提高
				rankMap.put(p2, r2 + 1);
			}
		} else {
			parentMap.put(p2, p1);
		}
	}
	
}
