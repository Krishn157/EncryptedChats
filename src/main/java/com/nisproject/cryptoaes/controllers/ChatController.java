package com.nisproject.cryptoaes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.nisproject.cryptoaes.models.ChatEntity;
import com.nisproject.cryptoaes.models.UserEntity;
import com.nisproject.cryptoaes.models.Request.ChatMessageRequest;
import com.nisproject.cryptoaes.models.Response.ChatResponse;
import com.nisproject.cryptoaes.repositories.ChatRepo;
import com.nisproject.cryptoaes.repositories.UserRepo;

@RestController
public class ChatController {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@Autowired
	private ChatRepo chatRepo;

	@Autowired
	private UserRepo userRepo;

	@MessageMapping("/chat")
	public void processMessage(@Payload ChatMessageRequest messageRequest) {
		// System.out.println(messageRequest);
		UserEntity userEntityS = userRepo.findByUserId(messageRequest.getSenderId());
		UserEntity userEntityR = userRepo.findByUserId(messageRequest.getRecipientId());
		ChatEntity chatEntity = new ChatEntity();
		// System.out.println(messageRequest.getEncryptedmsg());
		chatEntity.setMessage(messageRequest.getEncryptedmsg());
		chatEntity.setSender(userEntityS);
		chatEntity.setRecipient(userEntityR);
		chatEntity.setTimestamp(messageRequest.getTimestamp());
		chatRepo.save(chatEntity);
		messagingTemplate.convertAndSendToUser(messageRequest.getRecipientId(), "/queue/messages", new ChatResponse(
				messageRequest.getEncryptedmsg(), messageRequest.getSenderId(), messageRequest.getTimestamp()));
	}

}
