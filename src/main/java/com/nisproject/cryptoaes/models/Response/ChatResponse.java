package com.nisproject.cryptoaes.models.Response;

import java.util.Date;

public class ChatResponse {
	private String message;
	private String sender;
	private Date timestamp;

	public ChatResponse() {
		super();
	}

	public ChatResponse(String message, String sender, Date timestamp) {
		super();
		this.message = message;
		this.sender = sender;
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
