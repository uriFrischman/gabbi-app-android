package com.frischman.uri.gabbiapp.network.response;

import com.frischman.uri.gabbiapp.model.User;

public class UserSignUpResponse {

    User user;
    boolean succesfulSignUp;
    String message;

    public UserSignUpResponse() {
    }

    public UserSignUpResponse(User user, boolean succesfulSignUp, String message) {
        this.user = user;
        this.succesfulSignUp = succesfulSignUp;
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isSuccesfulSignUp() {
        return succesfulSignUp;
    }

    public void setSuccesfulSignUp(boolean succesfulSignUp) {
        this.succesfulSignUp = succesfulSignUp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
