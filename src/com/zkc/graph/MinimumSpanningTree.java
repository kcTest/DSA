package com.zkc.graph;

import com.zkc.utils.MyUtils;

import java.util.*;

/**
 * 一个最小生成树或最小权生成树是一个连通的，边加权的无向图的边的子集，连接所有顶点，没有任何圈和最小可能的总边权。
 * 也就是说，它是一个边权之和尽可能小的生成树。更一般地说，任何边加权无向图(不一定连通)都有一个最小生成森林，它是其连通分支的最小生成树的并。
 */
public class MinimumSpanningTree {
	public static void main(String[] args) {
		MyGraph graph = MyUtils.getUnDirectedAcyclicGraph(7, 20);
		List<MyGraphEdge> edges = kruskalAlgorithm(graph);
		edges.forEach(e -> System.out.printf("%d,", e.weight));
	}
	
	private static List<MyGraphEdge> kruskalAlgorithm(MyGraph graph) {
		if (graph == null) {
			return null;
		}
		MyUnionFind unionFind = new MyUnionFind();
		graph.nodes.values().forEach(unionFind::makeSet);
		List<MyGraphEdge> edges = new ArrayList<>(graph.edges);
		edges.sort(Comparator.comparing(e -> e.weight));
		
		List<MyGraphEdge> result = new ArrayList<>();
		//所有边已排好序 从权值最小的边开始 判断边的两端节点是否已在一个集合中 不在合并，把边放入结果集中 
		for (MyGraphEdge edge : edges) {
			//源节点和目标节点的根
			MyGraphNode pFrom = unionFind.find(edge.from);
			MyGraphNode pTo = unionFind.find(edge.to);
			if (pFrom != pTo) {
				unionFind.union(pFrom, pTo);
				//不同根合并 合并后
				result.add(edge);
			}
		}
		return result;
	}
}
