package com.nisproject.cryptoaes.utils;

import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

@Service
public class HMACSHA256 {

	public String calcHmacSha256(String qunatumKey, String key) {

		byte[] secretKey = key.getBytes();
		byte[] message = qunatumKey.getBytes();
		byte[] hmacSha256 = null;
		try {
			Mac mac = Mac.getInstance("HmacSHA256");
			SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "HmacSHA256");
			mac.init(secretKeySpec);
			hmacSha256 = mac.doFinal(message);
		} catch (Exception e) {
			throw new RuntimeException("Failed to calculate hmac-sha256", e);
		}
		String base64HmacSha256 = Base64.getEncoder().encodeToString(hmacSha256);
		return base64HmacSha256;
	}

}
