package com.nisproject.cryptoaes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.management.RuntimeErrorException;

import com.nisproject.cryptoaes.models.KeyEntity;
import com.nisproject.cryptoaes.models.UserEntity;
import com.nisproject.cryptoaes.repositories.KeyRepo;
import com.nisproject.cryptoaes.repositories.UserRepo;
import com.nisproject.cryptoaes.utils.AES128Util;
import com.nisproject.cryptoaes.utils.BlowFishUtil;
import com.nisproject.cryptoaes.utils.TripleDES;

@RestController
@RequestMapping("keys")
public class KeyController {

	@Autowired
	private KeyRepo keyRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private BlowFishUtil blowFish;

	@Autowired
	private AES128Util aes128;

	@Autowired
	private TripleDES tripleDES;

	@Value("${flaskUrl}")
	private String url;

	@Value("${authKey}")
	private String authKey;

	@GetMapping("/")
	public String getUsersSharedKey(@RequestParam("user_1") String currUser,
			@RequestParam("user_2") String secondUser) {

		if (currUser.equals(secondUser))
			throw new RuntimeErrorException(null, "Same user");
		KeyEntity keyEntity = keyRepo.findKeyOfUsers(currUser, secondUser);
		UserEntity user1 = userRepo.findByUserId(currUser);
		String quantumKey = null;
		if (keyEntity == null) {

			// call to flask api
			HttpHeaders headers = new HttpHeaders();
			headers.set("authKey", authKey);
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<String> respEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			String encryptedQuantumKey = respEntity.getBody();
			System.out.println("encrypted qunatum key is " + encryptedQuantumKey);
			// decrypt(blowfish)
			quantumKey = blowFish.decrypt(encryptedQuantumKey);
			// setKeyEntitySave
			// save to db
			KeyEntity keyEntitySave = new KeyEntity();
			keyEntitySave.setSharedKey(quantumKey);
			UserEntity user2 = userRepo.findByUserId(secondUser);
			keyEntitySave.setUserId1(user1);
			keyEntitySave.setUserId2(user2);
			keyRepo.save(keyEntitySave);
		} else {
			quantumKey = keyEntity.getSharedKey();
		}
		System.out.println("quantum key is " + quantumKey);
		// getCurrUserPin
		String currUserPin = user1.getPin();
		// encrypt using aes-128
		// String finalKey = aes128.encrypt(quantumKey, currUserPin);
		String finalKey = "";
		try {
			finalKey = tripleDES.encrypt(quantumKey, currUserPin);
		} catch (InvalidKeyException | NoSuchAlgorithmException | UnsupportedEncodingException | NoSuchPaddingException
				| IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return key
		return finalKey;
	}
}
