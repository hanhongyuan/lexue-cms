package com.lexue.base.util;

import java.security.MessageDigest;

public class MD5 {
	public static String md5(String s) throws Exception {
		/*if (s.isEmpty()){
			throw new Exception();
		}*/
		return byte2hex(
				MessageDigest.getInstance("MD5").digest(s.getBytes("UTF-8")))
				.toLowerCase();
	}

	public static String byte2hex(byte[] bytes) {
		StringBuffer result = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			String b = Integer.toHexString(bytes[i] & 0xFF);
			if (b.length() == 1)
				b = "0" + b;
			result.append(b);
		}
		return result.toString().toUpperCase();
	}
	public static void main(String agrs[]) throws Exception{
		System.out.println(MD5.md5("lexue@150411"));
	}
}
