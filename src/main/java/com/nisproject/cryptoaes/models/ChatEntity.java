package com.nisproject.cryptoaes.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "chats")
public class ChatEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@Size(min = 1, message = "Message should be of at least 1 character")
	private String message;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sender_id", nullable = false)
	private UserEntity sender;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "recipient_id", nullable = false)
	private UserEntity recipient;

	private Date timestamp;

	public ChatEntity() {
		super();
	}

	public ChatEntity(Long id, @Size(min = 1, message = "Message should be of at least 1 character") String message,
			UserEntity sender, UserEntity recipient, Date timestamp) {
		super();
		this.id = id;
		this.message = message;
		this.sender = sender;
		this.recipient = recipient;
		this.timestamp = timestamp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserEntity getSender() {
		return sender;
	}

	public void setSender(UserEntity sender) {
		this.sender = sender;
	}

	public UserEntity getRecipient() {
		return recipient;
	}

	public void setRecipient(UserEntity recipient) {
		this.recipient = recipient;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
