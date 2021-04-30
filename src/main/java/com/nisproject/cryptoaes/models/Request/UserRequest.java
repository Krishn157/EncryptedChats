package com.nisproject.cryptoaes.models.Request;

public class UserRequest {

    String userId;
    String password;
    String confpassword;

    public UserRequest() {
    }

    public UserRequest(String userId, String password, String confpassword) {
        this.userId = userId;
        this.password = password;
        this.confpassword = confpassword;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfpassword() {
        return this.confpassword;
    }

    public void setConfpassword(String confpassword) {
        this.confpassword = confpassword;
    }

    public UserRequest userId(String userId) {
        setUserId(userId);
        return this;
    }

    public UserRequest password(String password) {
        setPassword(password);
        return this;
    }

    public UserRequest confpassword(String confpassword) {
        setConfpassword(confpassword);
        return this;
    }

    @Override
    public String toString() {
        return "{" + " userId='" + getUserId() + "'" + ", password='" + getPassword() + "'" + ", confpassword='"
                + getConfpassword() + "'" + "}";
    }

}
