package com.zkc.graph;

import com.zkc.utils.MyUtils;

import java.util.*;

/**
 * 对于一个有向无环图 G=(V,E）来说，其拓扑排序是G中所有结点的一种线性次序，
 * 该次序满足如下条件：如果图 G 包含边（u,v) ，则结点 u 在拓扑排序中处于结点 v 的前面（如果图 G 包含环路．则不可能排出一个线性次序）。
 * 可以将图的拓扑排序看做是将图的所有结点在一条水平线上排开，图的所有有向边都从左指向右。
 * 许多实际应用都需要使用有向无环图来指明事件的优先次序,对有向无环图进行拓扑排序所获得的就是一种合理的次序。
 */
public class TopologicalSort {
	
	public static void main(String[] args) {
		MyGraph graph = MyUtils.getDirectedAcyclicGraph(7, 20);
		List<MyGraphNode> lst = dfsRecur(graph);
		for (MyGraphNode node : lst) {
			System.out.printf("%d,", node.val);
		}
		lst = kahnAlgorithm(graph);
		System.out.println("\n");
		for (MyGraphNode n : lst) {
			System.out.printf("%d,", n.val);
		}
	}
	
	/**
	 * 深度优先搜索  当节点处理完成后(后续节点也已处理完成)加入到链表的头部  最后从头输出。
	 * 如果每个节点的记录访问时间和处理完成时间  输出的结果也为处理完成时间的逆序
	 */
	private static List<MyGraphNode> dfsRecur(MyGraph graph) {
		if (graph == null) {
			return null;
		}
		List<MyGraphNode> lst = new LinkedList<>();
		for (MyGraphNode node : graph.nodes.values()) {
			dfs(node, lst);
		}
		return lst;
	}
	
	private static void dfs(MyGraphNode curNode, List<MyGraphNode> lst) {
		if (curNode == null || curNode.visits > 0) {
			return;
		}
		curNode.visits++;
		for (MyGraphNode nextNode : curNode.nextNodes) {
			dfs(nextNode, lst);
		}
		lst.add(0, curNode);
	}
	
	/**
	 * 在有向无环图G = (V,E)上执行拓扑排序还有一种办法，就是重复寻找入度为0的结点，
	 * 输出该结点，将该结点及从其发出的边从图中删除。
	 */
	private static List<MyGraphNode> kahnAlgorithm(MyGraph graph) {
		if (graph == null) {
			return null;
		}
		//存储入度为0的结点 
		Stack<MyGraphNode> stack = new Stack<>();
		// 不破坏图的数据结构 记录其它待处理节点的剩余入度
		Map<MyGraphNode, Integer> map = new HashMap<>();
		for (MyGraphNode node : graph.nodes.values()) {
			if (node.in == 0) {
				stack.add(node);
			} else {
				//记录
				map.put(node, node.in);
			}
		}
		List<MyGraphNode> lst = new ArrayList<>();
		while (!stack.isEmpty()) {
			MyGraphNode startNode = stack.pop();
			lst.add(startNode);
			for (MyGraphNode nextNode : startNode.nextNodes) {
				int in = map.get(nextNode);
				if (--in > 0) {
					map.put(nextNode, in);
				} else if (in == 0) {
					stack.push(nextNode);
					map.remove(nextNode);
				}
			}
		}
		
		return lst;
	}
}
