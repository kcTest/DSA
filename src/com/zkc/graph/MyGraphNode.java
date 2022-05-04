package com.zkc.graph;

import java.util.ArrayList;
import java.util.List;

public class MyGraphNode {
	public int val;
	public int in;
	public int out;
	public int visits;
	public List<MyGraphNode> nextNodes;
	public List<MyGraphEdge> nextEdges;
	
	public MyGraphNode(int val) {
		this.val = val;
		nextNodes = new ArrayList<>();
		nextEdges = new ArrayList<>();
	}
}
