package com.frischman.uri.gabbiapp.network.request;


public class NonShabbosZmanimRequest {

    String date;
    int longitude;
    int latitude;

    public NonShabbosZmanimRequest(String date, int longitude, int latitude) {
        this.date = date;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
