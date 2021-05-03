package com.nisproject.cryptoaes.models.Request;

import java.util.Date;

public class ChatMessageRequest {
	private String encryptedmsg;
	private String senderId;
	private String recipientId;
	private Date timestamp;

	public ChatMessageRequest() {
	}

	public ChatMessageRequest(String encryptedmsg, String senderId, String recipientId, Date timestamp) {
		this.encryptedmsg = encryptedmsg;
		this.senderId = senderId;
		this.recipientId = recipientId;
		this.timestamp = timestamp;
	}

	public String getEncryptedmsg() {
		return this.encryptedmsg;
	}

	public void setEncryptedmsg(String encryptedmsg) {
		this.encryptedmsg = encryptedmsg;
	}

	public String getSenderId() {
		return this.senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getRecipientId() {
		return this.recipientId;
	}

	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "{" + " encryptedmsg='" + getEncryptedmsg() + "'" + ", senderId='" + getSenderId() + "'"
				+ ", recipientId='" + getRecipientId() + "'" + ", timestamp='" + getTimestamp() + "'" + "}";
	}

}
