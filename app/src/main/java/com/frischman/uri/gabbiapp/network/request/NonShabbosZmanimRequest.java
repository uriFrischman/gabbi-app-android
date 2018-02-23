package com.frischman.uri.gabbiapp.network.request;


public class NonShabbosZmanimRequest {

    int longitude;
    int latitude;

    public NonShabbosZmanimRequest() {

    }

    public NonShabbosZmanimRequest(int longitude, int latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }
}
