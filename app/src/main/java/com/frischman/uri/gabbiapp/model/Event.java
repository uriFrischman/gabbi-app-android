package com.frischman.uri.gabbiapp.model;


import android.support.annotation.NonNull;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;
import com.frischman.uri.gabbiapp.R;

import java.util.Date;

import static com.frischman.uri.gabbiapp.utility.DateUtil.getDateFromString;
import static com.frischman.uri.gabbiapp.utility.StringUtil.getString;

@DynamoDBTable(tableName = "Events")
public class Event implements Comparable<Event> {
    private String eventName;
    private String eventDate;
    private int numberOfAliyahs;
    private int numberOfAliyahsTaken;

    public Event() {
    }

    public Event(String eventName, String eventDate, int numberOfAliyahs, int numberOfAliyahsTaken) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.numberOfAliyahs = numberOfAliyahs;
        this.numberOfAliyahsTaken = numberOfAliyahsTaken;
    }

    @DynamoDBHashKey(attributeName = "event_name")
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @DynamoDBAttribute(attributeName = "event_date")
    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    @DynamoDBAttribute(attributeName = "num_aliyahs")
    public int getNumberOfAliyahs() {
        return numberOfAliyahs;
    }

    public void setNumberOfAliyahs(int numberOfAliyahs) {
        this.numberOfAliyahs = numberOfAliyahs;
    }

    @DynamoDBAttribute(attributeName = "num_aliyahs_taken")
    public int getNumberOfAliyahsTaken() {
        return numberOfAliyahsTaken;
    }

    public void setNumberOfAliyahsTaken(int numberOfAliyahsTaken) {
        this.numberOfAliyahsTaken = numberOfAliyahsTaken;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventName='" + eventName + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", numberOfAliyahs=" + numberOfAliyahs +
                ", numberOfAliyahsTaken=" + numberOfAliyahsTaken +
                '}';
    }

    @Override
    public int compareTo(@NonNull Event o) {
        Date currentEventDate = getDateFromString(this.getEventDate(), getString(R.string.event_date_format));
        Date otherEventDate = getDateFromString(o.getEventDate(), getString(R.string.event_date_format));
        return currentEventDate.compareTo(otherEventDate);
    }
}
