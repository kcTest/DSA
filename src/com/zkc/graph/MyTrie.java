package com.zkc.graph;

import com.zkc.utils.MyUtils;

public class MyTrie {
	
	public static void main(String[] args) {
		MyTrie trie = new MyTrie();
		String[] arr = new String[]{"abc", "abe", "abcd", "abe", "", "gh", "gz"};
		for (String s : arr) {
			trie.insert(s);
		}
		MyUtils.printTrie(trie.root);
		trie.search("abed");
		System.out.println();
		trie.prefixNum("gz");
		System.out.println();
		trie.insert("zkc");
		System.out.println();
		for (String s : arr) {
			trie.delete(s);
		}
		MyUtils.printTrie(trie.root);
	}
	
	/**
	 * 前缀树的根
	 */
	public MyTrieNode root;
	
	public MyTrie() {
		this.root = new MyTrieNode();
	}
	
	/**
	 * 根据字符更新前缀树
	 */
	public void insert(String keyWord) {
		if (keyWord == null) {
			return;
		}
		System.out.printf("[insert '%s']\n", keyWord);
		//当前来到根节点
		MyTrieNode cur = root;
		cur.pass++;
		for (char c : keyWord.toCharArray()) {
			//当前节点没有找到键值c对应的的子节点
			if (!cur.children.containsKey(c)) {
				MyTrieNode node = new MyTrieNode();
				//新增键值与子节点映射
				cur.children.put(c, node);
			}
			//移动到子节点
			cur = cur.children.get(c);
			cur.pass++;
		}
		//cur最终为叶节点
		cur.end++;
	}
	
	/**
	 * 返回 字符串被添加过几次  为0说明字符串在树中不存在
	 */
	public int search(String keyWord) {
		if (keyWord == null) {
			return 0;
		}
		System.out.printf("[search '%s'],", keyWord);
		MyTrieNode cur = root;
		for (char c : keyWord.toCharArray()) {
			if (!cur.children.containsKey(c)) {
				//字符串的字符还没有找完 无法继续向下 中断 说明不包含 返回不存在
				System.out.printf("[search error, '%s' not found]\n", keyWord);
				return 0;
			}
			cur = cur.children.get(c);
		}
		//end为曾经作为叶节点的次数 也是keyWord出现的总次数
		System.out.printf("[added count:%d]\n", cur.end);
		return cur.end;
	}
	
	/**
	 * 以当前字符串作为前缀的字符串个数
	 */
	public int prefixNum(String keyWord) {
		if (keyWord == null) {
			return 0;
		}
		MyTrieNode cur = root;
		for (char c : keyWord.toCharArray()) {
			if (!cur.children.containsKey(c)) {
				System.out.printf("[prefixNum error: '%s' not found]\n", keyWord);
				return 0;
			}
			cur = cur.children.get(c);
		}
		System.out.printf("[prefixNum %d]\n", cur.pass);
		return cur.pass;
	}
	
	/**
	 * 移除字符串
	 */
	public void delete(String keyWord) {
		if (keyWord == null) {
			return;
		}
		//先看是否存在 遍历时可以直接更新沿途节点信息
		if (search(keyWord) == 0) {
			System.out.printf("[delete err '%s' not found]\n", keyWord);
			return;
		}
		System.out.printf("[delete '%s']\n", keyWord);
		MyTrieNode cur = root;
		cur.pass--;
		for (char c : keyWord.toCharArray()) {
			// 先判断子节点的pass是否为0 方便直接从当前位置移除后续字符
			// 说明字符串中某个字符c只出现过一次,没有其他字符串还在使用当前字符，字符串中字符c及之后字符可以直接全部移除
			if (--cur.children.get(c).pass == 0) {
				//从当前节点操作 子节点中只与c对应的节点断开关系
				cur.children.remove(c);
				return;
			}
			cur = cur.children.get(c);
		}
		//还有其他字符串还在使用  最后一个c对应的节点end减少而且大于0  
		cur.end--;
	}
}
