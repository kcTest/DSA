package com.zkc.graph;

import com.zkc.utils.MyUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 遍历图
 */
public class Traversal {
	
	public static void main(String[] args) {
		MyGraph graph = MyUtils.getGraph(7, 50);
		System.out.println("DFS RECUR");
		dfsRecur(graph);
		System.out.println("\nDFS NO RECUR");
		dfsNoRecur(graph);
		System.out.println("\n\nBFS");
		bfs(graph);
	}
	
	/**
	 * 深度优先遍历
	 * 递归
	 */
	private static void dfsRecur(MyGraph graph) {
		if (graph == null) {
			return;
		}
		graph.nodes.forEach((i, node) -> {
			dfs(node);
		});
	}
	
	private static void dfs(MyGraphNode curNode) {
		if (curNode == null || curNode.visits > 0) {
			return;
		}
		curNode.visits++;
		System.out.printf("%d,", curNode.val);
		for (MyGraphNode nextNode : curNode.nextNodes) {
			dfs(nextNode);
		}
	}
	
	/**
	 * 深度优先遍历
	 * 非递归 使用栈
	 */
	private static void dfsNoRecur(MyGraph graph) {
		if (graph == null) {
			return;
		}
		Stack<MyGraphNode> stack = new Stack<>();
		graph.nodes.forEach((i, node) -> {
			stack.push(node);
			while (!stack.isEmpty()) {
				MyGraphNode curNode = stack.pop();
				if (curNode.visits > 1) {
					continue;
				}
				curNode.visits++;
				System.out.printf("%d,", curNode.val);
				List<MyGraphNode> nextNodes = curNode.nextNodes;
				//逆序添加方便对比两个方法生成结果
				for (int j = nextNodes.size() - 1; j >= 0; j--) {
					stack.push(nextNodes.get(j));
				}
			}
		});
	}
	
	/**
	 * 宽度优先遍历
	 * 使用队列
	 */
	private static void bfs(MyGraph graph) {
		if (graph == null) {
			return;
		}
		Queue<MyGraphNode> queue = new LinkedList<>();
		graph.nodes.forEach((i, node) -> {
			queue.add(node);
			while (!queue.isEmpty()) {
				MyGraphNode curNode = queue.poll();
				if (curNode.visits > 2) {
					continue;
				}
				curNode.visits++;
				System.out.printf("%d,", curNode.val);
				List<MyGraphNode> nextNodes = curNode.nextNodes;
				queue.addAll(nextNodes);
			}
		});
	}
}
