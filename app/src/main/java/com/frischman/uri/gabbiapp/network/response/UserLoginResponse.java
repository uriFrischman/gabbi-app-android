package com.frischman.uri.gabbiapp.network.response;


import com.frischman.uri.gabbiapp.model.User;

public class UserLoginResponse {

    boolean successfulLogin;
    String message;
    User user;

    public UserLoginResponse() {
    }

    public UserLoginResponse(boolean successfulLogin, String message, User user) {
        this.successfulLogin = successfulLogin;
        this.message = message;
        this.user = user;
    }

    public boolean isSuccessfulLogin() {
        return successfulLogin;
    }

    public void setSuccessfulLogin(boolean successfulLogin) {
        this.successfulLogin = successfulLogin;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserLoginResponse{" +
                "successfulLogin=" + successfulLogin +
                ", message='" + message + '\'' +
                ", user=" + user +
                '}';
    }
}
