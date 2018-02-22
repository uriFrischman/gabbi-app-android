package com.frischman.uri.gabbiapp.model;


public class NonShabbosZman {

    private String Date;
    private String Alos16point1Degrees;
    private String Sunrise;
    private String SofZmanShemaMGA;
    private String SofZmanShemaGra;
    private String SofZmanTefilahGra;
    private String SofZmanTefilahMGA;
    private String Chatzos;
    private String MinchaGedolah;
    private String PlagHamincha;
    private String Shkia;
    private String Tzais;
    private String CandleLighting;

    public NonShabbosZman(String date, String alos16point1Degrees, String sunrise, String sofZmanShemaMGA, String sofZmanShemaGra, String sofZmanTefilahGra, String sofZmanTefilahMGA, String chatzos, String minchaGedolah, String plagHamincha, String shkia, String tzais, String candleLighting) {
        this.Date = date;
        this.Alos16point1Degrees = alos16point1Degrees;
        this.Sunrise = sunrise;
        this.SofZmanShemaMGA = sofZmanShemaMGA;
        this.SofZmanShemaGra = sofZmanShemaGra;
        this.SofZmanTefilahGra = sofZmanTefilahGra;
        this.SofZmanTefilahMGA = sofZmanTefilahMGA;
        this.Chatzos = chatzos;
        this.MinchaGedolah = minchaGedolah;
        this.PlagHamincha = plagHamincha;
        this.Shkia = shkia;
        this.Tzais = tzais;
        this.CandleLighting = candleLighting;
    }


    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public String getAlos16point1Degrees() {
        return Alos16point1Degrees;
    }

    public void setAlos16point1Degrees(String alos16point1Degrees) {
        this.Alos16point1Degrees = alos16point1Degrees;
    }

    public String getSunrise() {
        return Sunrise;
    }

    public void setSunrise(String sunrise) {
        this.Sunrise = sunrise;
    }

    public String getSofZmanShemaMGA() {
        return SofZmanShemaMGA;
    }

    public void setSofZmanShemaMGA(String sofZmanShemaMGA) {
        this.SofZmanShemaMGA = sofZmanShemaMGA;
    }

    public String getSofZmanShemaGra() {
        return SofZmanShemaGra;
    }

    public void setSofZmanShemaGra(String sofZmanShemaGra) {
        this.SofZmanShemaGra = sofZmanShemaGra;
    }

    public String getSofZmanTefilahGra() {
        return SofZmanTefilahGra;
    }

    public void setSofZmanTefilahGra(String sofZmanTefilahGra) {
        this.SofZmanTefilahGra = sofZmanTefilahGra;
    }

    public String getSofZmanTefilahMGA() {
        return SofZmanTefilahMGA;
    }

    public void setSofZmanTefilahMGA(String sofZmanTefilahMGA) {
        this.SofZmanTefilahMGA = sofZmanTefilahMGA;
    }

    public String getChatzos() {
        return Chatzos;
    }

    public void setChatzos(String chatzos) {
        this.Chatzos = chatzos;
    }

    public String getMinchaGedolah() {
        return MinchaGedolah;
    }

    public void setMinchaGedolah(String minchaGedolah) {
        this.MinchaGedolah = minchaGedolah;
    }

    public String getPlagHamincha() {
        return PlagHamincha;
    }

    public void setPlagHamincha(String plagHamincha) {
        this.PlagHamincha = plagHamincha;
    }

    public String getShkia() {
        return Shkia;
    }

    public void setShkia(String shkia) {
        this.Shkia = shkia;
    }

    public String getTzais() {
        return Tzais;
    }

    public void setTzais(String tzais) {
        this.Tzais = tzais;
    }

    public String getCandleLighting() {
        return CandleLighting;
    }

    public void setCandleLighting(String candleLighting) {
        this.CandleLighting = candleLighting;
    }

    @Override
    public String toString() {
        return "NonShabbosZman{" +
                "Date='" + Date + '\'' +
                ", Alos16point1Degrees='" + Alos16point1Degrees + '\'' +
                ", Sunrise='" + Sunrise + '\'' +
                ", SofZmanShemaMGA='" + SofZmanShemaMGA + '\'' +
                ", SofZmanShemaGra='" + SofZmanShemaGra + '\'' +
                ", SofZmanTefilahGra='" + SofZmanTefilahGra + '\'' +
                ", SofZmanTefilahMGA='" + SofZmanTefilahMGA + '\'' +
                ", Chatzos='" + Chatzos + '\'' +
                ", MinchaGedolah='" + MinchaGedolah + '\'' +
                ", PlagHamincha='" + PlagHamincha + '\'' +
                ", Shkia='" + Shkia + '\'' +
                ", Tzais='" + Tzais + '\'' +
                ", CandleLighting='" + CandleLighting + '\'' +
                '}';
    }
}
