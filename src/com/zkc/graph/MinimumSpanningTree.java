package com.zkc.graph;

import com.zkc.utils.MyUtils;

/**
 * 一个最小生成树或最小权生成树是一个连通的，边加权的无向图的边的子集，连接所有顶点，没有任何圈和最小可能的总边权。
 * 也就是说，它是一个边权之和尽可能小的生成树。更一般地说，任何边加权无向图(不一定连通)都有一个最小生成森林，它是其连通分支的最小生成树的并。
 */
public class MinimumSpanningTree {
	public static void main(String[] args) {
		MyGraph graph = MyUtils.getUnDirectedAcyclicGraph(7, 20);
		
	}
}
