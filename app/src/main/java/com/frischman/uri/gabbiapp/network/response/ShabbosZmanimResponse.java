package com.frischman.uri.gabbiapp.network.response;


import com.frischman.uri.gabbiapp.model.ShabbosZman;

import java.util.List;

public class ShabbosZmanimResponse {

    List<ShabbosZman> zmanimInformation;

    public ShabbosZmanimResponse(List<ShabbosZman> zmanimInformation) {
        this.zmanimInformation = zmanimInformation;
    }

    public List<ShabbosZman> getZmanimInformation() {
        return zmanimInformation;
    }

    public void setZmanimInformation(List<ShabbosZman> zmanimInformation) {
        this.zmanimInformation = zmanimInformation;
    }
}
