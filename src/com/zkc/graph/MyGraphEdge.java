package com.zkc.graph;

public class MyGraphEdge {
	
	public int weight;
	public MyGraphNode from;
	public MyGraphNode to;
	
	public MyGraphEdge(int weight, MyGraphNode from, MyGraphNode to) {
		this.weight = weight;
		this.from = from;
		this.to = to;
	}
}
