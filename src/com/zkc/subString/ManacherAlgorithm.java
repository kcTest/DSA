package com.zkc.subString;

public class ManacherAlgorithm {
	
	public static void main(String[] args) {
		
		String s1 = "abcd";
		int[] ret1 = manacher(s1);
		System.out.printf("\nC:%d,R:%d\n", ret1[0], ret1[1]);
		System.out.println(s1);
		for (int i = 0; i < ret1[0] + ret1[1]; i++) {
			if (i > ret1[0] - ret1[1] ) {
				System.out.printf("%s", s1.charAt(i));
			} else {
				System.out.print(" ");
			}
		}
		System.out.println();
		//m f dcd axa dcd f m
		String s2 = "lfdcdaxadcdfr";
		int[] ret2 = manacher(s2);
		System.out.printf("\nC:%d,R:%d\n", ret2[0], ret2[1]);
		System.out.println(s2);
		for (int i = 0; i < ret2[0] + ret2[1]; i++) {
			if (i > ret2[0] - ret2[1]) {
				System.out.printf("%s", s2.charAt(i));
			} else {
				System.out.print(" ");
			}
		}
		System.out.println();
		//m f dcdf axa fdcd f m
		String s3 = "lfdcdfaxafdcdfar";
		int[] ret3 = manacher(s3);
		System.out.printf("\nC:%d,R:%d\n", ret3[0], ret3[1]);
		System.out.println(s3);
		for (int i = 0; i < ret3[0] + ret3[1]; i++) {
			if (i > ret3[0] - ret3[1]) {
				System.out.printf("%s", s3.charAt(i));
			} else {
				System.out.print(" ");
			}
		}
		System.out.println();
		//m a f dcdf axa fdcd f b m
		String s4 = "lafdcdfaxafdcdfr";
		int[] ret4 = manacher(s4);
		System.out.printf("\nC:%d,R:%d\n", ret4[0], ret4[1]);
		System.out.println(s4);
		for (int i = 0; i < ret4[0] + ret4[1]; i++) {
			if (i > ret4[0] - ret4[1]) {
				System.out.printf("%s", s4.charAt(i));
			} else {
				System.out.print(" ");
			}
		}
	}
	
	private static int[] manacher(String s) {
		
		char[] oriChars = s.toCharArray();
		int maxRadiusCenter = -1;
		int maxRadius = 0;
		int oriLen = oriChars.length;
		char[] newChars = new char[(oriLen << 1) + 1];
		for (int i = 0; i < oriChars.length; i++) {
			newChars[i << 1] = '#';
			newChars[(i << 1) + 1] = oriChars[i];
		}
		newChars[newChars.length - 1] = '#';
		int[] record = new int[newChars.length];
		for (int i = 0; i < newChars.length; i++) {
			int curRadius = 1;
			int left = i - 1;
			int right = i + 1;
			if (i > maxRadiusCenter + maxRadius - 1) {
				while (left >= 0 && right < newChars.length) {
					if (newChars[left--] == newChars[right++]) {
						curRadius++;
					} else {
						break;
					}
				}
				record[i] = curRadius;
				if (curRadius >= maxRadius) {
					maxRadiusCenter = i;
					maxRadius = curRadius;
				}
			} else {
				if (maxRadiusCenter - maxRadius < maxRadiusCenter - (i - maxRadiusCenter) - record[maxRadiusCenter - (i - maxRadiusCenter)]) {
					record[i] = record[maxRadiusCenter - (i - maxRadiusCenter)];
				} else if (maxRadiusCenter - maxRadius == maxRadiusCenter - (i - maxRadiusCenter) - record[maxRadiusCenter - (i - maxRadiusCenter)]) {
					curRadius = record[i] = record[maxRadiusCenter - (i - maxRadiusCenter)];
					left = i - curRadius;
					right = i + curRadius;
					while (left >= 0 && right < newChars.length) {
						if (newChars[left--] == newChars[right++]) {
							curRadius++;
						} else {
							break;
						}
					}
					record[i] = curRadius;
					if (curRadius >= maxRadius) {
						maxRadiusCenter = i;
						maxRadius = curRadius;
					}
				} else if (maxRadiusCenter - maxRadius > maxRadiusCenter - (i - maxRadiusCenter) - record[maxRadiusCenter - (i - maxRadiusCenter)]) {
					record[i] = maxRadiusCenter + maxRadius - i;
				}
			}
		}
		int[] ret = new int[2];
		ret[0] = (maxRadiusCenter - 1) / 2;
		ret[1] = maxRadius / 2;
		return ret;
	}
}
