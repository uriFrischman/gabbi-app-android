package com.frischman.uri.gabbiapp.model;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

@DynamoDBTable(tableName = "Aliyahs")
public class Aliyah {

    private String aliyahName;
    private String aliyahEvent;
    private String aliyahReader;
    private int aliyahTaken;
    private String numPsukim;
    private String reading;
    private String aliyahNumber;

    public Aliyah() {
    }

    public Aliyah(String aliyahName, String aliyahEvent, String aliyahReader, int aliyahTaken, String numPsukim, String reading, String aliyahNumber) {
        this.aliyahName = aliyahName;
        this.aliyahEvent = aliyahEvent;
        this.aliyahReader = aliyahReader;
        this.aliyahTaken = aliyahTaken;
        this.numPsukim = numPsukim;
        this.reading = reading;
        this.aliyahNumber = aliyahNumber;
    }

    @DynamoDBHashKey(attributeName = "aliyah_name")
    public String getAliyahName() {
        return aliyahName;
    }

    public void setAliyahName(String aliyahName) {
        this.aliyahName = aliyahName;
    }

    @DynamoDBIndexHashKey(attributeName = "aliyah_event")
    public String getAliyahEvent() {
        return aliyahEvent;
    }

    public void setAliyahEvent(String aliyahEvent) {
        this.aliyahEvent = aliyahEvent;
    }

    @DynamoDBAttribute(attributeName = "aliyah_reader")
    public String getAliyahReader() {
        return aliyahReader;
    }

    public void setAliyahReader(String aliyahReader) {
        this.aliyahReader = aliyahReader;
    }

    @DynamoDBAttribute(attributeName = "aliyah_taken")
    public int getAliyahTaken() {
        return aliyahTaken;
    }

    public void setAliyahTaken(int aliyahTaken) {
        this.aliyahTaken = aliyahTaken;
    }

    @DynamoDBAttribute(attributeName = "num_psukim")
    public String getNumPsukim() {
        return numPsukim;
    }

    public void setNumPsukim(String numPsukim) {
        this.numPsukim = numPsukim;
    }

    @DynamoDBAttribute(attributeName = "reading")
    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    @DynamoDBAttribute(attributeName = "aliyah_number")
    public String getAliyahNumber() {
        return aliyahNumber;
    }

    public void setAliyahNumber(String aliyahNumber) {
        this.aliyahNumber = aliyahNumber;
    }

    @Override
    public String toString() {
        return "Aliyah{" +
                "aliyahName='" + aliyahName + '\'' +
                ", aliyahEvent='" + aliyahEvent + '\'' +
                ", aliyahReader='" + aliyahReader + '\'' +
                ", aliyahTaken=" + aliyahTaken +
                ", numPsukim=" + numPsukim +
                ", reading='" + reading + '\'' +
                '}';
    }
}
