package com.zkc.graph;

import com.zkc.utils.MyUtils;

import java.util.*;

public class FindingTheShortestPaths {
	
	public static void main(String[] args) {
		MyGraph graph = MyUtils.getUnDirectedAcyclicGraph(7, 30);
		dijkstraAlgorithm(graph);
	}
	
	/**
	 * Dijkstra 算法是一种用于寻找图中节点间最短路径的算法。
	 * 对于图中给定的源节点，该算法寻找该节点与其他节点之间的最短路径。
	 * 它也可以用来寻找从单个节点到单个目的地节点的最短路径，一旦确定了到目的地节点的最短路径，就停止该算法。
	 * 权值不能为负数 否则每次计算都会小于上一次计算的值  会一直更新下去
	 */
	private static void dijkstraAlgorithm(MyGraph graph) {
		if (graph == null || graph.nodes.size() == 0) {
			return;
		}
		//每次更新一个节点的距离 添加上下级关系  打印使用
		Map<MyGraphNode, MyGraphNode> parentMap = new HashMap<>();
		//设置一个源节点  返回该节点到其他所有节点的最短路径的权值之和
		MyGraphNode startNode = graph.nodes.get(0);
		parentMap.put(startNode, null);
		//返回结果 目标节点及距离
		Map<MyGraphNode, DistInfo> disMap = new HashMap<>(graph.nodes.size());
		//从初始节点开始添加判断  更新其后节点距离后将其后节点加入队列   到所有节点最小距离全部确定后队列为空  
		//某个节点修改后 无法自动调整成堆  只能通过添加删除调整  当前做法开始只取一个点 每次新增或移除  不修改队列中的值  不需要考虑 
		PriorityQueue<DistInfo> queue = new PriorityQueue<>(Comparator.comparingInt(p -> p.dis));
		for (MyGraphNode node : graph.nodes.values()) {
			//到其他所有节点距离未知  默认设置为一个最大值
			DistInfo destInfo = new DistInfo(node, Integer.MAX_VALUE);
			disMap.put(node, destInfo);
			if (node == startNode) {
				//初始节点为入口 设置最小  每次从当前队列中弹出离源节点路径最短的节点进行处理
				destInfo.dis = 0;
				queue.add(destInfo);
			}
		}
		while (!queue.isEmpty()) {
			//选择与源节点距离最小的未访问节点，计算源节点通过该节点到每个未访问邻居的距离，如果距离较小则更新邻居的距离 标记为已处理
			DistInfo distInfo = queue.poll();
			MyGraphNode curNode = distInfo.node;
			curNode.visits++;
			//更新当前节点的后面节点距离源节点的距离
			for (MyGraphEdge e : curNode.nextEdges) {
				//已经处理过不再处理
				if (e.to.visits == 0) {
					DistInfo nextNodeDistInfo = disMap.get(e.to);
					int oldDis = nextNodeDistInfo.dis;
					//当前节点距离再加上到下一个节点之间的距离
					int newDis = distInfo.dis + e.weight;
					//A-B-C    A-B的距离加B-C的距离如果小于上次记录的A-C的距离 则再次更新
					if (newDis < oldDis) {
						nextNodeDistInfo.dis = newDis;
						parentMap.put(e.to, curNode);
						disMap.put(e.to, nextNodeDistInfo);
						queue.add(nextNodeDistInfo);
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		disMap.forEach((target, distInfo) -> {
			sb.append("dist:(").append(distInfo.dis).append("),  [");
			StringBuilder sb2 = new StringBuilder();
			getParent(parentMap, target, sb2);
			if (sb2.length() > 0) {
				sb2.delete(sb2.length() - 2, sb2.length());
			}
			sb.append(sb2).append("]\n");
		});
		System.out.println(sb);
	}
	
	private static void getParent(Map<MyGraphNode, MyGraphNode> parentMap, MyGraphNode cur, StringBuilder sb2) {
		if (parentMap.containsKey(cur) && parentMap.get(cur) != null) {
			getParent(parentMap, parentMap.get(cur), sb2);
		}
		sb2.append(String.format("%d->", cur.val));
	}
	
	static class DistInfo {
		public MyGraphNode node;
		public int dis;
		
		public DistInfo(MyGraphNode target, int dis) {
			this.node = target;
			this.dis = dis;
		}
	}
}
