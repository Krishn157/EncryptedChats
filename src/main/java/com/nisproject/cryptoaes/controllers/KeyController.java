package com.nisproject.cryptoaes.controllers;

import javax.management.RuntimeErrorException;

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

import com.nisproject.cryptoaes.models.KeyEntity;
import com.nisproject.cryptoaes.models.UserEntity;
import com.nisproject.cryptoaes.repositories.KeyRepo;
import com.nisproject.cryptoaes.repositories.UserRepo;
import com.nisproject.cryptoaes.utils.BlowFishUtil;
import com.nisproject.cryptoaes.utils.HMACSHA256;

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
	private HMACSHA256 hmacSha256;

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
		UserEntity user2 = userRepo.findByUserId(secondUser);
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
			keyEntitySave.setUserId1(user1);
			keyEntitySave.setUserId2(user2);
			keyRepo.save(keyEntitySave);
			String hashKey = user1.getPin() + user2.getPin();
			String finalKey = hmacSha256.calcHmacSha256(quantumKey, hashKey);
			return finalKey;
		} else {
			quantumKey = keyEntity.getSharedKey();
			String hashKey = null;
			if (keyEntity.getUserId1().getUserId().equals(user1.getUserId())) {
				hashKey = user1.getPin() + user2.getPin();
			} else {
				hashKey = user2.getPin() + user1.getPin();
			}
			String finalKey = hmacSha256.calcHmacSha256(quantumKey, hashKey);
			return finalKey;
		}

	}
}
