package com.zkc.graph;

import java.util.HashMap;
import java.util.Map;

public class MyTrieNode {
	
	/**
	 * 前缀树更新后 当前节点被途经过几次
	 */
	public int pass;
	/**
	 * 前缀树更新后 当前节点当过叶节点的次数
	 */
	public int end;
	/**
	 * 子节点个数 可以根据具体情况调整结构
	 */
	public Map<Character, MyTrieNode> children;
	
	public MyTrieNode() {
		this.pass = 0;
		this.end = 0;
		this.children = new HashMap<>();
	}
}
