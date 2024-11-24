package com.healog.mvc.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class EncryptUtil {
	
	public String getSalt() {
		SecureRandom secureRandom  = new SecureRandom();
		byte[] salt = new byte[10];
		
		//난수를 생성한 뒤
		secureRandom.nextBytes(salt);
		
		StringBuffer sb = new StringBuffer();
		for(byte b: salt) {
			//16진수로 변환
			//여기서 x가 16진수를 의미하고 02는 늘 두자리수로 만들겠다는 뜻
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
	
	public String getEncrypt(String password, String salt) {
		String encrypt = "";
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update((password+salt).getBytes());
			byte[] byteEncrypt = messageDigest.digest();
			
			StringBuffer sb = new StringBuffer();
			for(byte b : byteEncrypt) {
				sb.append(String.format("%02x", b));
			}
			encrypt = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encrypt;
	}
}
