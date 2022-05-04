package com.zkc.graph;

import java.util.HashMap;
import java.util.HashSet;

public class MyGraph {
	public HashMap<Integer, MyGraphNode> nodes;
	public HashSet<MyGraphEdge> edges;
	
	public MyGraph() {
		this.nodes = new HashMap<>();
		this.edges = new HashSet<>();
	}
}
