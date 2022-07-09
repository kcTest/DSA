package com.zkc.dp;

/**
 * 给定总和X,先手和后手依次拿走分数 谁 最先拿完 谁赢
 * 但每次只能拿走平方数1 ,4, 9, 16,....
 * 根据输入的X 返回先手会不会赢
 */
public class StoneGameIv {
	
	public static void main(String[] args) {
		int x = (int) (Math.random() * 100) + 1;
		System.out.println(x);
		System.out.println(win2(x));
		System.out.println(win(x));
	}
	
	/**
	 * 每个剩余x的情况下能否获胜 依赖 剩余值为x-所有可选平方数的情况下能否获胜
	 */
	private static boolean win2(int x) {
		boolean[] record = new boolean[x + 1];
		for (int rest = 1; rest <= x; rest++) {
			for (int base = 1; base * base <= rest; base++) {
				if (!record[rest - base * base]) {
					record[rest] = true;
					break;
				}
			}
		}
		return record[x];
	}
	
	private static boolean win(int x) {
		//当前方可选为0  说明另一方已经提前拿完x 另一方赢 当前方输
		if (x == 0) {
			return false;
		}
		//做出的选择
		for (int base = 1; base * base <= x; base++) {
			//另一方可拿的数为x-base 如果按当前选择进行下去对方输了 当前方赢 
			if (!win(x - base * base)) {
				return true;
			}
			//否则当前方再换一种选择  如果所有选择都是对方赢 当前方输
		}
		return false;
	}
}

