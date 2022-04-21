package com.learndaily.test;

import com.learndaily.security.PaswordService;

public class Test {

	public static void main(String[] args) {
		PaswordService ps = new PaswordService();
		System.out.println(ps.encode("karnan"));
		System.out.println(ps.decode(ps.encode("karnan")));
	}

}
