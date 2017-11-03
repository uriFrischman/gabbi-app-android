package com.frischman.uri.gabbiapp.network.response;

import com.frischman.uri.gabbiapp.model.User;

public class UserSignUpResonse {

    User user;
    boolean succesfulSignUp;

    public UserSignUpResonse() {
    }

    public UserSignUpResonse(User user, boolean succesfulSignUp) {
        this.user = user;
        this.succesfulSignUp = succesfulSignUp;
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
}
