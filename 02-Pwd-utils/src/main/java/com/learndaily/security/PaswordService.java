package com.learndaily.security;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class PaswordService {


	public static String encode(String txt) {
		Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(txt.getBytes());
	}
	
	public static String decode(String entxt) {
		Decoder decoder = Base64.getDecoder();
		byte[] b = decoder.decode(entxt);
		return new String(b);
		
	}
	
	public static void main(String[] args) {
		String encode = PaswordService.encode("asokit");
		System.out.println(encode);
		
		String text = PaswordService.decode(encode);
		System.out.println(text);
	}
}
