package com.zkc.graph;

import com.zkc.utils.MyUtils;

import java.util.*;

/**
 * 一个最小生成树或最小权生成树是一个连通的，边加权的无向图的边的子集，连接所有顶点，没有任何圈和最小可能的总边权。
 * 也就是说，它是一个边权之和尽可能小的生成树。更一般地说，任何边加权无向图(不一定连通)都有一个最小生成森林，它是其连通分支的最小生成树的并。
 */
public class MinimumSpanningTree {
	public static void main(String[] args) {
		MyGraph graph = MyUtils.getUnDirectedAcyclicGraph(7, 10);
		List<MyGraphEdge> edges = kruskalAlgorithm(graph);
		edges.forEach(e -> System.out.printf("[%d->%d]%d,", e.from.val, e.to.val, e.weight));
		System.out.println();
		List<MyGraphEdge> edges2 = primAlgorithm(graph);
		edges2.forEach(e -> System.out.printf("[%d->%d]%d,", e.from.val, e.to.val, e.weight));
	}
	
	/**
	 * 克鲁斯卡尔算法找到无向边加权图的最小生成森林。如果这个图是连通的，它就会找到一个最小生成树。
	 * (连通图的最小生成树是一个 包含每个顶点的树的边 的子集，其中树中所有边的权之和最小。对于一个【不连通图】，最小生成森林由每个最小生成树组成).
	 * 它是图论中的一个贪婪算法，因为在每个步骤中，它添加下一个权重最小的边，而不会形成一个环到最小生成森林。
	 */
	private static List<MyGraphEdge> kruskalAlgorithm(MyGraph graph) {
		if (graph == null) {
			return null;
		}
		MyUnionFind<MyGraphNode> unionFind = new MyUnionFind<>();
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
				//不同根没有共同节点合并  合并后
				result.add(edge);
			}
		}
		return result;
	}
	
	/**
	 * Prim算法是一种贪婪算法，它为一个加权无向图找到一个最小生成树。
	 * 这意味着它找到一个边的子集，形成一棵包含每个顶点的树，其中树中所有边的总权最小化。
	 * 该算法通过每次从任意起始点生成一个顶点，在每个步骤中添加从树到另一个顶点的最小边
	 * <p>
	 * Prim算法最基本的形式只在连通图中找到最小生成树。然而，对图的每个子图分别运行Prim的算法，它也可以用来寻找最小生成森林
	 */
	private static List<MyGraphEdge> primAlgorithm(MyGraph graph) {
		if (graph == null) {
			return null;
		}
		List<MyGraphEdge> edges = new ArrayList<>();
		for (MyGraphNode node : graph.nodes.values()) {
			//标记该节点 相当于加入了最小生成树
			if (node.visits++ == 0) {
				//所有新找到的节点的外向边  从中找出权值最小的
				Queue<MyGraphEdge> uncheckedEdges = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
				uncheckedEdges.addAll(node.nextEdges);
				while (!uncheckedEdges.isEmpty()) {
					//找出权值最小边加入返回结果集 
					MyGraphEdge minEdge = uncheckedEdges.poll();
					//标记下一节点相当于加入了最小生成树
					if (minEdge.to.visits++ == 0) {
						//如果全部边都已比较过 相当于最小生成树已经形成 不需要再从剩下未移除的边中挑选 跳到下一节点或另一个树中
						edges.add(minEdge);
						//新增待比较的边 为当前边指向的节点所对应的外向边
						uncheckedEdges.addAll(minEdge.to.nextEdges);
					}
				}
			}
		}
		return edges;
	}
}
