package com.frischman.uri.gabbiapp;

import com.frischman.uri.gabbiapp.model.User;

public class UserSignUpRequest {

    User user;

    public UserSignUpRequest() {
    }

    public UserSignUpRequest(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
