package com.nisproject.cryptoaes.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_keys")
public class KeyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String sharedKey;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id_1", nullable = false)
	private UserEntity userId1;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id_2", nullable = false)
	private UserEntity userId2;

	public KeyEntity() {
		super();
	}

	public KeyEntity(Long id, String sharedKey, UserEntity userId1, UserEntity userId2) {
		super();
		this.id = id;
		this.sharedKey = sharedKey;
		this.userId1 = userId1;
		this.userId2 = userId2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSharedKey() {
		return sharedKey;
	}

	public void setSharedKey(String sharedKey) {
		this.sharedKey = sharedKey;
	}

	public UserEntity getUserId1() {
		return userId1;
	}

	public void setUserId1(UserEntity userId1) {
		this.userId1 = userId1;
	}

	public UserEntity getUserId2() {
		return userId2;
	}

	public void setUserId2(UserEntity userId2) {
		this.userId2 = userId2;
	}

	@Override
	public String toString() {
		return "KeyEntity [id=" + id + ", sharedKey=" + sharedKey + ", userId1=" + userId1 + ", userId2=" + userId2
				+ "]";
	}

}
