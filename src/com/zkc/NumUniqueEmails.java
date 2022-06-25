package com.zkc;

import java.util.HashSet;

public class NumUniqueEmails {
	
	public static void main(String[] args) {
		
		String[] emails = new String[]{
				"test.email+alex@leetcode.com",
				"test.e.mail+bob.cathy@leetcode.com",
				"testemail+david@lee.tcode.com"};
		
		System.out.println(numUniqueEmails(emails));
	}
	
	public static int numUniqueEmails(String[] emails) {
		HashSet<String> set = new HashSet<>();
		for (String email : emails) {
			int atIdx = email.indexOf('@');
			String firstPart = email.substring(0, atIdx);
			int plusIdx = firstPart.indexOf('+');
			if (plusIdx > -1) {
				firstPart = firstPart.substring(0, plusIdx);
			}
			firstPart = firstPart.replace(".", "");
			set.add(firstPart + '@' + email.substring(atIdx + 1));
		}
		return set.size();
	}
}
