package com.nisproject.cryptoaes.models.Response;

public class UserResponse {

    private String userId;
    private String pin;

    // public UserResponse() {
    // }

    public UserResponse(String userId, String pin) {
        this.userId = userId;
        this.pin = pin;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPin() {
        return this.pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

}
