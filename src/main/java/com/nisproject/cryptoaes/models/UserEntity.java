package com.nisproject.cryptoaes.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Id
	@Column(unique = true, nullable = false)
	@Size(min = 4, message = "UserId should be at least 4 characters")
	private String userId;

	@JsonIgnore
	private String pin;

	@Column(nullable = false)
	@JsonIgnore
	private String encryptedPassword;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sender")
	@JsonIgnore
	private List<ChatEntity> messagesS;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "recipient")
	@JsonIgnore
	private List<ChatEntity> messagesR;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userId1")
	@JsonIgnore
	private List<KeyEntity> keyUser1;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userId2")
	@JsonIgnore
	private List<KeyEntity> keyUser2;

	public UserEntity() {
	}

	public UserEntity(Long id, @Size(min = 4, message = "UserId should be at least 4 characters") String userId,
			String pin, String encryptedPassword, List<ChatEntity> messagesS, List<ChatEntity> messagesR,
			List<KeyEntity> keyUser1, List<KeyEntity> keyUser2) {
		super();
		this.id = id;
		this.userId = userId;
		this.pin = pin;
		this.encryptedPassword = encryptedPassword;
		this.messagesS = messagesS;
		this.messagesR = messagesR;
		this.keyUser1 = keyUser1;
		this.keyUser2 = keyUser2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public List<ChatEntity> getMessagesS() {
		return messagesS;
	}

	public void setMessagesS(List<ChatEntity> messagesS) {
		this.messagesS = messagesS;
	}

	public List<ChatEntity> getMessagesR() {
		return messagesR;
	}

	public void setMessagesR(List<ChatEntity> messagesR) {
		this.messagesR = messagesR;
	}

	public List<KeyEntity> getKeyUser1() {
		return keyUser1;
	}

	public void setKeyUser1(List<KeyEntity> keyUser1) {
		this.keyUser1 = keyUser1;
	}

	public List<KeyEntity> getKeyUser2() {
		return keyUser2;
	}

	public void setKeyUser2(List<KeyEntity> keyUser2) {
		this.keyUser2 = keyUser2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", userId=" + userId + ", pin=" + pin + ", encryptedPassword="
				+ encryptedPassword + "]";
	}

}
