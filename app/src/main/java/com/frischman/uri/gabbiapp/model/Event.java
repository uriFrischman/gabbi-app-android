package com.frischman.uri.gabbiapp.model;

import android.support.annotation.NonNull;

import com.frischman.uri.gabbiapp.R;

import java.util.Date;

import static com.frischman.uri.gabbiapp.utility.DateUtil.getDateFromString;
import static com.frischman.uri.gabbiapp.utility.StringUtil.getString;

public class Event implements Comparable<Event> {
    private String eventName;
    private String eventDate;
    private int numberOfAliyahs;
    private int numberOfAliyahsTaken;
    private int eventId;
    private String formattedDate;

    public Event() {
    }

    public Event(String eventName, String eventDate, int numberOfAliyahs, int numberOfAliyahsTaken, int eventId, String formattedDate) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.numberOfAliyahs = numberOfAliyahs;
        this.numberOfAliyahsTaken = numberOfAliyahsTaken;
        this.eventId = eventId;
        this.formattedDate = formattedDate;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public int getNumberOfAliyahs() {
        return numberOfAliyahs;
    }

    public void setNumberOfAliyahs(int numberOfAliyahs) {
        this.numberOfAliyahs = numberOfAliyahs;
    }

    public int getNumberOfAliyahsTaken() {
        return numberOfAliyahsTaken;
    }

    public void setNumberOfAliyahsTaken(int numberOfAliyahsTaken) {
        this.numberOfAliyahsTaken = numberOfAliyahsTaken;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventName='" + eventName + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", formattedDate=" + formattedDate + '\'' +
                ", numberOfAliyahs=" + numberOfAliyahs +
                ", numberOfAliyahsTaken=" + numberOfAliyahsTaken +
                ", eventId=" + eventId +
                '}';
    }

    @Override
    public int compareTo(@NonNull Event o) {
        Date currentEventDate = getDateFromString(this.getEventDate(), getString(R.string.event_date_format));
        Date otherEventDate = getDateFromString(o.getEventDate(), getString(R.string.event_date_format));
        return currentEventDate.compareTo(otherEventDate);
    }
}
