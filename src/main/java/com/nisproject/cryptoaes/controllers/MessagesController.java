package com.nisproject.cryptoaes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nisproject.cryptoaes.models.ChatEntity;
import com.nisproject.cryptoaes.repositories.ChatRepo;

@RestController
public class MessagesController {

	@Autowired
	private ChatRepo chatRepo;

	@GetMapping("/messages/{userId1}/{userId2}")
	public List<ChatEntity> getMessagesOfAUser(@PathVariable("userId1") String userId1,
			@PathVariable("userId2") String userId2) {
		List<ChatEntity> allMsgs = chatRepo.findChatsBySenderAndRecipient(userId1, userId2);
		return allMsgs;
	}
}
