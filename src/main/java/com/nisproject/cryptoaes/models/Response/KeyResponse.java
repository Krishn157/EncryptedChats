package com.nisproject.cryptoaes.models.Response;

public class KeyResponse {
	private String ephemeralKey;
	private String finalKey;

	public KeyResponse() {
		super();
	}

	public KeyResponse(String ephemeralKey, String finalKey) {
		super();
		this.ephemeralKey = ephemeralKey;
		this.finalKey = finalKey;
	}

	public String getEphemeralKey() {
		return ephemeralKey;
	}

	public void setEphemeralKey(String ephemeralKey) {
		this.ephemeralKey = ephemeralKey;
	}

	public String getFinalKey() {
		return finalKey;
	}

	public void setFinalKey(String finalKey) {
		this.finalKey = finalKey;
	}

}
