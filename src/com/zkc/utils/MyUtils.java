package com.zkc.utils;

import com.zkc.binaryTree.MyNewTreeNode;
import com.zkc.binaryTree.MyTreeNode;
import com.zkc.graph.MyGraph;
import com.zkc.graph.MyGraphEdge;
import com.zkc.graph.MyGraphNode;
import com.zkc.linkedList.doubleLinkedList.DoubleLinkedList;
import com.zkc.linkedList.singleLinkedList.SingleLinkedList;
import com.zkc.linkedList.singleLinkedList.SpecialSingleLinkedList;
import jdk.nashorn.internal.lookup.Lookup;

import java.util.*;
import java.util.stream.Collectors;

public class MyUtils {
	
	public static int[] getArray(int length, int bound) {
		if (length < 2) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		int[] ret = new int[length];
		for (int i = 0; i < length; i++) {
			ret[i] = (int) (Math.random() * bound);
		}
		return ret;
	}
	
	public static void printArr(int[] arr) {
		if (arr == null) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		StringBuilder sb = new StringBuilder();
		for (int j : arr) {
			sb.append(j).append(",");
		}
		System.out.println(sb.substring(0, sb.length() - 1));
		System.out.println("------------------------------");
	}
	
	public static SingleLinkedList getSingleLinedList(int length, int bound) {
		if (length < 1) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		
		SingleLinkedList list = new SingleLinkedList();
		SingleLinkedList.Node prev = null;
		//新建节点后更新上一个节点的下一个节点
		for (int i = 0; i < length; i++) {
			SingleLinkedList.Node sNode = new SingleLinkedList.Node((int) (Math.random() * bound));
			if (prev == null) {
				list.head = sNode;
			} else {
				prev.next = sNode;
			}
			prev = sNode;
		}
		
		return list;
	}
	
	public static SpecialSingleLinkedList getSpecialSingleLinkedList(int length, int bound) {
		if (length < 1) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		
		SpecialSingleLinkedList list = new SpecialSingleLinkedList();
		SpecialSingleLinkedList.Node prev = null;
		
		Map<Integer, SpecialSingleLinkedList.Node> map = new HashMap<>();
		int[] array = getArray(length, bound);
		for (int i = 0; i < array.length; i++) {
			map.put(i, new SpecialSingleLinkedList.Node(array[i]));
		}
		
		//新建节点后更新上一个节点的下一个节点
		for (int i = 0; i < array.length; i++) {
			
			SpecialSingleLinkedList.Node sNode = map.get(i);
			sNode.rand = i == (int) (Math.random() * length) ? null : map.get((int) (Math.random() * length));
			
			if (prev == null) {
				list.head = sNode;
			} else {
				prev.next = sNode;
			}
			prev = sNode;
		}
		
		return list;
	}
	
	public static DoubleLinkedList getDoubleLinedList(int length, int bound) {
		if (length < 1) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		
		DoubleLinkedList list = new DoubleLinkedList();
		DoubleLinkedList.Node prev = null;
		for (int i = 0; i < length; i++) {
			DoubleLinkedList.Node sNode = new DoubleLinkedList.Node((int) (Math.random() * bound));
			if (prev == null) {
				list.head = sNode;
			} else {
				//新建节点后更新上一个节点的下一个节点
				prev.next = sNode;
				sNode.prev = prev;
			}
			prev = sNode;
		}
		
		return list;
	}
	
	public static void printSingleLinkedList(SingleLinkedList list) {
		if (list == null) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		SingleLinkedList.Node current = list.head;
		StringBuilder sb = new StringBuilder();
		while (current != null) {
			sb.append(current.data).append("->");
			current = current.next;
		}
		System.out.println(sb.substring(0, sb.length() - 2));
		System.out.println("------------------------------");
	}
	
	public static void printIntersectSingleLinkedList(SingleLinkedList listA) {
		if (listA == null) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		SingleLinkedList.Node current = listA.head;
		StringBuilder sb = new StringBuilder();
		Map<SingleLinkedList.Node, Integer> map = new HashMap<>();
		while (current != null) {
			if (map.containsKey(current)) {
				int count = map.get(current);
				if (count >= 1) {
					sb.append(String.format("LINK TO %d(%s)->", current.data, Integer.toHexString(current.hashCode())));
					break;
				}
				map.put(current, map.get(current) + 1);
			} else {
				map.put(current, 1);
			}
			sb.append(current.data).append("(").append(Integer.toHexString(current.hashCode())).append(")").append("->");
			current = current.next;
		}
		sb.delete(sb.length() - 2, sb.length());
		System.out.println(sb);
		System.out.println("------------------------------");
	}
	
	public static void printSpecialSingleLinkedList(SpecialSingleLinkedList list) {
		if (list == null) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		SpecialSingleLinkedList.Node current = list.head;
		StringBuilder sb = new StringBuilder();
		while (current != null) {
			sb.append(current.data).append("(").append(current.rand != null ? current.rand.data : "null").append(")").append("->");
			current = current.next;
		}
		System.out.println(sb.substring(0, sb.length() - 2));
		System.out.println("------------------------------");
	}
	
	public static void printDoubleLinkedList(DoubleLinkedList list) {
		if (list == null) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		DoubleLinkedList.Node current = list.head;
		StringBuilder sb = new StringBuilder();
		while (current != null) {
			sb.append(current.data).append("->");
			current = current.next;
		}
		System.out.println(sb.substring(0, sb.length() - 2));
		System.out.println("------------------------------");
	}
	
	public static SingleLinkedList getSortedSingleLinedList(int length, int bound) {
		if (length < 1) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		SingleLinkedList list = new SingleLinkedList();
		SingleLinkedList.Node prev = null;
		
		int[] sortedArray = getArray(length, bound);
		Arrays.sort(sortedArray);
		for (int j : sortedArray) {
			SingleLinkedList.Node sNode = new SingleLinkedList.Node(j);
			if (prev == null) {
				list.head = sNode;
			} else {
				prev.next = sNode;
			}
			prev = sNode;
		}
		
		return list;
	}
	
	public static SingleLinkedList getPalindromeSingleLinedList(int length, int bound) {
		if (length < 1) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		SingleLinkedList list = new SingleLinkedList();
		SingleLinkedList.Node prev = null;
		
		int[] sortedArray = getArray(length, bound);
		for (int i = 0; i < sortedArray.length / 2; i++) {
			sortedArray[sortedArray.length - 1 - i] = sortedArray[i];
		}
		for (int j : sortedArray) {
			SingleLinkedList.Node sNode = new SingleLinkedList.Node(j);
			if (prev == null) {
				list.head = sNode;
			} else {
				prev.next = sNode;
			}
			prev = sNode;
		}
		
		return list;
	}
	
	public static SingleLinkedList[] getIntersectSingleLinedList(int length, int bound) {
		
		SingleLinkedList singleLinkedListA = getSingleLinedList(((int) (Math.random() * length) + 1), bound);
		SingleLinkedList singleLinkedListB = getSingleLinedList(((int) (Math.random() * length) + 1), bound);
		SingleLinkedList.Node curA = singleLinkedListA.head;
		SingleLinkedList.Node curB = singleLinkedListB.head;
		if (curA == null || curB == null) {
			return null;
		}
		int countA = 0;
		int countB = 0;
		while (curA.next != null || curB.next != null) {
			if (curA.next != null) {
				curA = curA.next;
				countA++;
			}
			if (curB.next != null) {
				curB = curB.next;
				countB++;
			}
		}
		//B->C,A->C
		if (System.currentTimeMillis() % 2 == 0) {
			int countC = 0;
			SingleLinkedList singleLinkedListC = getSingleLinedList(((int) (Math.random() * length) + 1), bound);
			curA.next = singleLinkedListC.head;
			curB.next = singleLinkedListC.head;
			
			//ADD CLOSED-LOOP, C.tail->(A.rand|B.rand)
			if (System.currentTimeMillis() % 3 == 0) {
				SingleLinkedList.Node curC = singleLinkedListC.head;
				while ((curC != null && curC.next != null)) {
					curC = curC.next;
					countC++;
				}
				if (curC != null) {
					if (System.currentTimeMillis() % 2 == 0) {
						curA = singleLinkedListA.head;
						int connIndex = (int) (Math.random() * (countA + countC));
						countA = 0;
						while (curA != null) {
							if (connIndex == countA) {
								curC.next = curA;
								break;
							}
							curA = curA.next;
							countA++;
						}
					} else {
						curB = singleLinkedListB.head;
						int connIndex = (int) (Math.random() * (countB + countC));
						countB = 0;
						while (curB != null) {
							if (connIndex == countB) {
								curC.next = curB;
								break;
							}
							curB = curB.next;
							countB++;
						}
					}
				}
			}
		} else if (System.currentTimeMillis() % 3 == 0) {
			{
				int countC = 0;
				int countD = 0;
				SingleLinkedList singleLinkedListC = getSingleLinedList(((int) (Math.random() * length) + 1), bound);
				SingleLinkedList singleLinkedListD = getSingleLinedList(((int) (Math.random() * length) + 1), bound);
				curA.next = singleLinkedListC.head;
				curB.next = singleLinkedListD.head;
				
				//ADD CLOSED-LOOP, C.tail->(A.rand) D.tail->(B.rand)
				SingleLinkedList.Node curC = singleLinkedListC.head;
				while ((curC != null && curC.next != null)) {
					curC = curC.next;
					countC++;
				}
				SingleLinkedList.Node curD = singleLinkedListD.head;
				while ((curD != null && curD.next != null)) {
					curD = curD.next;
					countD++;
				}
				if (curC != null) {
					curA = singleLinkedListA.head;
					int connIndex = (int) (Math.random() * (countA + countC));
					countA = 0;
					while (curA != null) {
						if (connIndex == countA) {
							curC.next = curA;
							break;
						}
						curA = curA.next;
						countA++;
					}
				}
				if (curD != null) {
					curB = singleLinkedListB.head;
					int connIndex = (int) (Math.random() * (countB + countD));
					countB = 0;
					while (curB != null) {
						if (connIndex == countB) {
							curD.next = curB;
							break;
						}
						curB = curB.next;
						countB++;
					}
				}
			}
		}
		return new SingleLinkedList[]{singleLinkedListA, singleLinkedListB};
	}
	
	public static MyTreeNode arrayToTreeNode(Integer[] arr) {
		if (arr == null || arr.length == 0) {
			return null;
		}
		MyTreeNode head = new MyTreeNode(arr[0]);
		Queue<MyTreeNode> queue = new LinkedList<>();
		queue.add(head);
		boolean isLeft = true;
		for (int i = 1; i < arr.length && !queue.isEmpty(); i++) {
			MyTreeNode node = queue.peek();
			if (isLeft) {
				if (arr[i] != null) {
					node.left = new MyTreeNode(arr[i]);
					queue.add(node.left);
				}
				isLeft = false;
			} else {
				if (arr[i] != null) {
					node.right = new MyTreeNode(arr[i]);
					queue.add(node.right);
				}
				queue.poll();
				isLeft = true;
			}
		}
		return head;
	}
	
	public static class BTDS {
		public MyTreeNode head;
		public List<MyTreeNode> nodes;
		
		public BTDS(MyTreeNode head, List<MyTreeNode> nodes) {
			this.head = head;
			this.nodes = nodes;
		}
	}
	
	public static BTDS getBinaryTree(int length, int bound) {
		if (length < 1) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		MyTreeNode head = null;
		List<MyTreeNode> nodes = new ArrayList<>();
		//新建节点后更新上一个节点的下一个节点
		for (int i = 0; i < length; i++) {
			MyTreeNode node = new MyTreeNode((int) (Math.random() * bound));
			nodes.add(node);
		}
		for (int i = 0; i < nodes.size(); i++) {
			MyTreeNode node = nodes.get(i);
			MyTreeNode left = null;
			MyTreeNode right = null;
			int leftIndex = 2 * i + 1;
			int rightIndex = 2 * i + 2;
			
			
			if (node == null) {
				if (leftIndex < nodes.size()) {
					nodes.set(leftIndex, null);
				}
				if (rightIndex < nodes.size()) {
					nodes.set(rightIndex, null);
				}
			} else {
				if (head == null) {
					head = node;
				}
				if (leftIndex < nodes.size()) {
					long l = System.currentTimeMillis() % 5;
					if (l >= 4) {
						nodes.set(leftIndex, null);
					}
					left = nodes.get(leftIndex);
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (rightIndex < nodes.size()) {
					long l = System.currentTimeMillis() % 7;
					if (l >= 4) {
						nodes.set(rightIndex, null);
					}
					right = nodes.get(rightIndex);
				}
				node.left = left;
				node.right = right;
			}
		}
		List<MyTreeNode> treeNodes = new ArrayList<>();
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get((i - 1) / 2) != null) {
				treeNodes.add(nodes.get(i));
			}
		}
		return new BTDS(head, treeNodes);
	}
	
	public static BTDS getCompleteBinaryTree(int length, int bound) {
		MyTreeNode head = null;
		List<MyTreeNode> nodes = new ArrayList<>();
		//新建节点后更新上一个节点的下一个节点
		for (int i = 0; i < length; i++) {
			MyTreeNode node = new MyTreeNode((int) (Math.random() * bound));
			nodes.add(node);
		}
		for (int i = 0; i < nodes.size(); i++) {
			MyTreeNode node = nodes.get(i);
			MyTreeNode left = null;
			MyTreeNode right = null;
			int leftIndex = 2 * i + 1;
			int rightIndex = 2 * i + 2;
			if (head == null) {
				head = node;
			}
			if (leftIndex < nodes.size()) {
				left = nodes.get(leftIndex);
			}
			if (rightIndex < nodes.size()) {
				right = nodes.get(rightIndex);
			}
			node.left = left;
			node.right = right;
		}
		return new BTDS(head, nodes);
	}
	
	public static void printNodes(List<MyTreeNode> nodes) {
		if (nodes == null || nodes.isEmpty()) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		for (MyTreeNode node : nodes) {
			sb.append(node == null ? "null," : String.format("%d,", node.val));
		}
		System.out.printf("[%s]%n\n", sb.length() > 0 ? sb.substring(0, sb.length() - 1) : "");
	}
	
	public static void printBinaryTree(MyTreeNode head) {
		if (head == null) {
			return;
		}
		System.out.println("InOrder:");
		inOrderTraverse(head);
		System.out.println();
		System.out.println("======================");
		System.out.println("PreOrder:");
		preOrderTraverse(head);
		System.out.println();
		System.out.println("======================");
		System.out.println("PostOrder:");
		postOrderTraverse(head);
		System.out.println();
		System.out.println();
		System.out.println("----------------------");
		System.out.println();
	}
	
	private static void inOrderTraverse(MyTreeNode head) {
		if (head == null) {
			return;
		}
		//中序遍历 左->头->右
		inOrderTraverse(head.left);
		System.out.printf("%d,", head.val);
		inOrderTraverse(head.right);
	}
	
	private static void preOrderTraverse(MyTreeNode head) {
		if (head == null) {
			return;
		}
		System.out.printf("%d,", head.val);
		//先序遍历 头->左->右
		preOrderTraverse(head.left);
		preOrderTraverse(head.right);
	}
	
	private static void postOrderTraverse(MyTreeNode head) {
		if (head == null) {
			return;
		}
		//后序遍历 左->右->头
		postOrderTraverse(head.left);
		postOrderTraverse(head.right);
		System.out.printf("%d,", head.val);
	}
	
	public static class NewBTDS {
		public MyNewTreeNode head;
		public List<MyNewTreeNode> nodes;
		
		public NewBTDS(MyNewTreeNode head, List<MyNewTreeNode> nodes) {
			this.head = head;
			this.nodes = nodes;
		}
	}
	
	public static NewBTDS getNewBinaryTree(int length, int bound) {
		if (length < 1) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		MyNewTreeNode head = null;
		List<MyNewTreeNode> nodes = new ArrayList<>();
		//新建节点后更新上一个节点的下一个节点
		for (int i = 0; i < length; i++) {
			MyNewTreeNode node = new MyNewTreeNode((int) (Math.random() * bound));
			nodes.add(node);
		}
		for (int i = 0; i < nodes.size(); i++) {
			MyNewTreeNode node = nodes.get(i);
			MyNewTreeNode left = null;
			MyNewTreeNode right = null;
			int leftIndex = 2 * i + 1;
			int rightIndex = 2 * i + 2;
			if (node == null) {
				if (leftIndex < nodes.size()) {
					nodes.set(leftIndex, null);
				}
				if (rightIndex < nodes.size()) {
					nodes.set(rightIndex, null);
				}
			} else {
				if (head == null) {
					head = node;
				}
				if (leftIndex < nodes.size()) {
					long l = System.currentTimeMillis() % 5;
					if (l >= 4) {
						nodes.set(leftIndex, null);
					}
					left = nodes.get(leftIndex);
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (rightIndex < nodes.size()) {
					long l = System.currentTimeMillis() % 7;
					if (l >= 5) {
						nodes.set(rightIndex, null);
					}
					right = nodes.get(rightIndex);
				}
				if (left != null) {
					left.parent = node;
				}
				if (right != null) {
					right.parent = node;
				}
				node.left = left;
				node.right = right;
			}
		}
		List<MyNewTreeNode> treeNodes = new ArrayList<>();
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get((i - 1) / 2) != null) {
				treeNodes.add(nodes.get(i));
			}
		}
		return new NewBTDS(head, treeNodes);
	}
	
	public static void printNewBinaryTree(MyNewTreeNode head) {
		if (head == null) {
			return;
		}
		System.out.println("InOrder:");
		inOrderTraverseNew(head);
		System.out.println();
	}
	
	private static void inOrderTraverseNew(MyNewTreeNode head) {
		if (head == null) {
			return;
		}
		//中序遍历 左->头->右
		inOrderTraverseNew(head.left);
		System.out.printf("%d,", head.val);
		inOrderTraverseNew(head.right);
	}
	
	public static void printNewNodes(List<MyNewTreeNode> nodes) {
		if (nodes == null || nodes.isEmpty()) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		for (MyNewTreeNode node : nodes) {
			sb.append(node == null ? "null," : String.format("%d,", node.val));
		}
		System.out.printf("[%s]%n\n", sb.length() > 0 ? sb.substring(0, sb.length() - 1) : "");
	}
	
	public static void levelOrderTraversal(MyTreeNode head) {
		if (head == null) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		Queue<MyTreeNode> queue = new LinkedList<>();
		queue.add(head);
		while (!queue.isEmpty()) {
			MyTreeNode cur = queue.poll();
			sb.append(String.format("%d,", cur.val));
			if (cur.left != null) {
				queue.add(cur.left);
			}
			if (cur.right != null) {
				queue.add(cur.right);
			}
		}
		System.out.printf("[%s]%n\n", sb.length() > 0 ? sb.substring(0, sb.length() - 1) : "");
	}
	
	public static MyGraph getGraph(int nodeCount, int bound) {
		return getGraph(nodeCount, bound, false);
	}
	
	public static MyGraph getGraph(int nodeCount, int bound, boolean showWeight) {
		if (nodeCount == 0) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		//随机生成图结构  可能不连通 可能有向  
		//生成节点值 不重复  
		List<Integer> nodeValLst = new ArrayList<>();
		while (nodeValLst.size() < nodeCount) {
			int nodeVal = (int) (Math.random() * bound);
			while (nodeValLst.contains(nodeVal)) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				nodeVal = (int) (Math.random() * bound);
			}
			nodeValLst.add(nodeVal);
		}
		Collections.sort(nodeValLst);
		Map<Integer, Map<Integer, Integer>> adjacencyNodeMap = new LinkedHashMap<>();
		for (Integer val : nodeValLst) {
			//为当前节点生成邻接节点及权重  随机指向已存在的节点 数量随机  邻接的下一个节点不重复 
			Map<Integer, Integer> adjacentNodes = new HashMap<>();
			int adjacentNodeCount = ((int) (Math.random() * nodeCount)) / 2;
			//已经作为别的节点的邻接点 允许没有自己的邻接点 暂时只检查之前的 
			if (adjacentNodeCount == 0 && adjacencyNodeMap.values().stream().noneMatch(x -> x.containsKey(val))) {
				//减少不连通的情况
				adjacentNodeCount = 2;
			}
			for (int i = 0; i < adjacentNodeCount; i++) {
				//邻接点值
				int nextNodeVal = nodeValLst.get((int) (Math.random() * (nodeCount - 1)));
				while (adjacentNodes.containsKey(nextNodeVal) || nextNodeVal == val) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					nextNodeVal = nodeValLst.get((int) (Math.random() * (nodeCount - 1)));
				}
				//当前节点到邻接点的边权重 
				int weight = (int) (Math.random() * bound);
				if (adjacencyNodeMap.containsKey(nextNodeVal)) {
					Map<Integer, Integer> weightMap = adjacencyNodeMap.get(nextNodeVal);
					if (weightMap.containsKey(val)) {
						weight = weightMap.get(val);
					}
				}
				adjacentNodes.put(nextNodeVal, weight);
			}
			adjacencyNodeMap.put(val, adjacentNodes);
		}
		//以上先生成邻接表的数据 打印
		StringBuilder sbOriStr = new StringBuilder();
		adjacencyNodeMap.forEach((k, v) -> {
			sbOriStr.append("[").append(k).append(",");
			sbOriStr.append("[");
			if (v.size() > 0) {
				v.forEach((nextNode, weight) -> {
					sbOriStr.append(nextNode.intValue());
					if (showWeight) {
						sbOriStr.append("(").append(weight).append(")");
					}
					sbOriStr.append(",");
				});
				sbOriStr.deleteCharAt(sbOriStr.length() - 1);
			}
			sbOriStr.append("]");
			sbOriStr.append("]\n");
		});
		
		//根据基本的邻接表数据生成自定义的图结构（或其他结构）
		//nodes节点会按值顺序添加 方便对比新生成的邻接表字符串
		MyGraph g = new MyGraph();
		for (int i = 0; i < nodeValLst.size(); i++) {
			MyGraphNode node = new MyGraphNode(nodeValLst.get(i));
			//索引作为key
			g.nodes.put(i, node);
		}
		//
		for (int i = 0; i < nodeValLst.size(); i++) {
			MyGraphNode node = g.nodes.get(i);
			Map<Integer, Integer> weightMap = adjacencyNodeMap.get(node.val);
			weightMap.forEach((nextNodeVal, weight) -> {
				MyGraphNode nextNode = g.nodes.get(nodeValLst.indexOf(nextNodeVal));
				nextNode.in++;
				node.out++;
				node.nextNodes.add(nextNode);
				MyGraphEdge edge = new MyGraphEdge(weight, node, nextNode);
				node.nextEdges.add(edge);
				g.edges.add(edge);
			});
		}
		//生成好的图再打印成邻接表的字符串形式对比前后是否一致
		StringBuilder sbNewStr = new StringBuilder();
		g.nodes.forEach((index, node) -> {
			sbNewStr.append("[").append(node.val).append(",");
			if (node.nextNodes.size() > 0) {
				sbNewStr.append("[");
				node.nextNodes.forEach(nextNode -> {
					sbNewStr.append(nextNode.val);
					if (showWeight) {
						MyGraphEdge edge = g.edges.stream().filter(e -> e.from == node && e.to == nextNode).findFirst().orElse(null);
						if (edge == null) {
							throw new RuntimeException("edge not found");
						}
						sbNewStr.append("(").append(edge.weight).append(")");
					}
					sbNewStr.append(",");
				});
				sbNewStr.deleteCharAt(sbNewStr.length() - 1);
				sbNewStr.append("]");
			} else {
				sbNewStr.append("[]");
			}
			sbNewStr.append("]\n");
		});
		if (!sbOriStr.toString().equals(sbNewStr.toString())) {
			throw new RuntimeException("graph invalid");
		}
		System.out.println(sbNewStr);
		return g;
	}
	
}
