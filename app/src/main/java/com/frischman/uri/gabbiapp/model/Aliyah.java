package com.frischman.uri.gabbiapp.model;

public class Aliyah {

    private int aliyahId;
    private String aliyahName;
    private String aliyahEvent;
    private String aliyahReader;
    private boolean aliyahTaken;
    private int numPsukim;
    private String reading;
    private String aliyahNumber;

    public Aliyah() {
    }

    public Aliyah(int aliyahId, String aliyahName, String aliyahEvent, String aliyahReader, boolean aliyahTaken, int numPsukim, String reading, String aliyahNumber) {
        this.aliyahId = aliyahId;
        this.aliyahName = aliyahName;
        this.aliyahEvent = aliyahEvent;
        this.aliyahReader = aliyahReader;
        this.aliyahTaken = aliyahTaken;
        this.numPsukim = numPsukim;
        this.reading = reading;
        this.aliyahNumber = aliyahNumber;
    }

    public int getAliyahId() {
        return aliyahId;
    }

    public void setAliyahId(int aliyahId) {
        this.aliyahId = aliyahId;
    }

    public String getAliyahName() {
        return aliyahName;
    }

    public void setAliyahName(String aliyahName) {
        this.aliyahName = aliyahName;
    }

    public String getAliyahEvent() {
        return aliyahEvent;
    }

    public void setAliyahEvent(String aliyahEvent) {
        this.aliyahEvent = aliyahEvent;
    }

    public String getAliyahReader() {
        return aliyahReader;
    }

    public void setAliyahReader(String aliyahReader) {
        this.aliyahReader = aliyahReader;
    }

    public boolean getAliyahTaken() {
        return aliyahTaken;
    }

    public void setAliyahTaken(boolean aliyahTaken) {
        this.aliyahTaken = aliyahTaken;
    }

    public int getNumPsukim() {
        return numPsukim;
    }

    public void setNumPsukim(int numPsukim) {
        this.numPsukim = numPsukim;
    }

    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    public String getAliyahNumber() {
        return aliyahNumber;
    }

    public void setAliyahNumber(String aliyahNumber) {
        this.aliyahNumber = aliyahNumber;
    }

    @Override
    public String toString() {
        return "Aliyah{" +
                "aliyahId='" + aliyahId + '\'' +
                "aliyahName='" + aliyahName + '\'' +
                ", aliyahEvent='" + aliyahEvent + '\'' +
                ", aliyahReader='" + aliyahReader + '\'' +
                ", aliyahTaken=" + aliyahTaken +
                ", numPsukim=" + numPsukim +
                ", reading='" + reading + '\'' +
                '}';
    }
}
