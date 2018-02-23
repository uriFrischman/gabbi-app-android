package com.frischman.uri.gabbiapp.network.response;


import com.frischman.uri.gabbiapp.model.ShabbosZman;

public class ShabbosZmanimResponse {

    ShabbosZman shabbosZmanim;

    public ShabbosZmanimResponse(ShabbosZman shabbosZmanim) {
        this.shabbosZmanim = shabbosZmanim;
    }

    public ShabbosZman getShabbosZmanim() {
        return shabbosZmanim;
    }

    public void setShabbosZmanim(ShabbosZman shabbosZmanim) {
        this.shabbosZmanim = shabbosZmanim;
    }
}
