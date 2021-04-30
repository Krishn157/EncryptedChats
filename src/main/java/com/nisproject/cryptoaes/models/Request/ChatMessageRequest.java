package com.nisproject.cryptoaes.models.Request;

import java.util.Date;

public class ChatMessageRequest {
	private String message;
	private String senderId;
	private String recipientId;
	private Date timestamp;

	public ChatMessageRequest() {
		super();
	}

	public ChatMessageRequest(String message, String senderId, String recipientId, Date timestamp) {
		super();
		this.message = message;
		this.senderId = senderId;
		this.recipientId = recipientId;
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "ChatMessageRequest [message=" + message + ", senderId=" + senderId + ", recipientId=" + recipientId
				+ ", timestamp=" + timestamp + "]";
	}

}
