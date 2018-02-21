package com.frischman.uri.gabbiapp.network.request;


public class ShabbosZmanimRequest {

    int geoNameId;

    public ShabbosZmanimRequest(int geoNameId) {
        this.geoNameId = geoNameId;
    }

    public int getGeoNameId() {
        return geoNameId;
    }

    public void setGeoNameId(int geoNameId) {
        this.geoNameId = geoNameId;
    }
}
