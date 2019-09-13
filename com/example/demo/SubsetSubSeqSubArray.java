package com.example.demo;

//https://www.techiedelight.com/difference-between-subarray-subsequence-subset/?source=post_page---------------------------
public class SubsetSubSeqSubArray {

	public static void main(String args[]) {
		String s1 = "12345";
		subseq(s1);
		subset(s1);
		subArray(s1);
	}

	// always contiguous elements,n*(n+1)/2
	private static void subArray(String s1) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s1.length(); i++) {
			for (int j = i; j < s1.length(); j++) {
				sb.append(s1.substring(i, j + 1) + " ");
			}
		}
		System.out.println(sb);
	}

	// no order not contiguous
	private static void subset(String s1) {

	}

	// need not be contiguous but order retained
	private static void subseq(String s1) {

	}

}
