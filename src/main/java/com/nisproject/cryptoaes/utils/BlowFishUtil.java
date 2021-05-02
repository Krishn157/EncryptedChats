package com.nisproject.cryptoaes.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BlowFishUtil {

	@Value("${serversecretkey}")
	private String sharedKey;

	private final static String charSet = "UTF-8";

	public String decrypt(String cipherText) {
		Cipher cipher;
		try {
			byte[] decodedBytes = Base64.decodeBase64(cipherText.getBytes(charSet));
			if (decodedBytes.length % 8 != 0) { // not a multiple of 8
				System.out.println("decodedBytes is not padded properly in 8 bits");

				byte[] padded = new byte[decodedBytes.length + 8 - (decodedBytes.length % 8)];
				System.arraycopy(decodedBytes, 0, padded, 0, decodedBytes.length);
				decodedBytes = padded;
			} else {
				System.out.println("decodedBytes is padded properly in 8 bits");
			}

			byte[] keyBytes = sharedKey.getBytes(charSet);
			SecretKeySpec secretkey = new SecretKeySpec(keyBytes, "Blowfish");
			cipher = Cipher.getInstance("Blowfish/ECB/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE, secretkey);
			byte[] decryptedFinal = cipher.doFinal(decodedBytes);
			return new String(decryptedFinal);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
