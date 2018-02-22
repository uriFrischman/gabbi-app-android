package com.frischman.uri.gabbiapp.network.response;


import com.frischman.uri.gabbiapp.model.NonShabbosZman;

public class NonShabbosZmanimResponse {

    NonShabbosZman nonShabbosZmanim;

    public NonShabbosZmanimResponse(NonShabbosZman nonShabbosZmanim) {
        this.nonShabbosZmanim = nonShabbosZmanim;
    }

    public NonShabbosZman getNonShabbosZmanim() {
        return nonShabbosZmanim;
    }

    public void setNonShabbosZmanim(NonShabbosZman nonShabbosZmanim) {
        this.nonShabbosZmanim = nonShabbosZmanim;
    }
}
