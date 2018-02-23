package com.frischman.uri.gabbiapp.model;


public class ShabbosZman {

    private String candleLighting;
    private String havdalah;

    public ShabbosZman(String candleLighting, String havdalah) {
        this.candleLighting = candleLighting;
        this.havdalah = havdalah;
    }

    public String getCandleLighting() {
        return candleLighting;
    }

    public void setCandleLighting(String candleLighting) {
        this.candleLighting = candleLighting;
    }

    public String getHavdalah() {
        return havdalah;
    }

    public void setHavdalah(String havdalah) {
        this.havdalah = havdalah;
    }
}
