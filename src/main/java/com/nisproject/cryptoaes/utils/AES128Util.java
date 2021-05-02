package com.nisproject.cryptoaes.utils;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AES128Util {

	@Value("${salt}")
	private String salt;

	public String encrypt(String plainText, String key) {
		byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		IvParameterSpec ivspec = new IvParameterSpec(iv);

		SecretKeyFactory factory = null;
		try {
			factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		} catch (NoSuchAlgorithmException e1) {

			e1.printStackTrace();
		}
		KeySpec spec = new PBEKeySpec(key.toCharArray(), salt.getBytes(), 65536, 128);
		SecretKey tmp = null;
		try {
			tmp = factory.generateSecret(spec);
		} catch (InvalidKeySpecException e1) {

			e1.printStackTrace();
		}
		SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {

			e.printStackTrace();
		}
		try {
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
		} catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		byte[] encrypted = null;
		try {
			encrypted = cipher.doFinal(plainText.getBytes());
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Base64.getEncoder().encodeToString(encrypted);
	}
}
