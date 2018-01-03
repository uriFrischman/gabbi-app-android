package com.frischman.uri.gabbiapp.network.request;


import com.frischman.uri.gabbiapp.model.Aliyah;
import com.frischman.uri.gabbiapp.model.User;

public class ClaimAliyahRequest {

    private Aliyah aliyah;
    private User user;

    public ClaimAliyahRequest(Aliyah aliyah, User user) {
        this.aliyah = aliyah;
        this.user = user;
    }

    public Aliyah getAliyah() {
        return aliyah;
    }

    public void setAliyah(Aliyah aliyah) {
        this.aliyah = aliyah;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
