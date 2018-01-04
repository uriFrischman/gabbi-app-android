package com.frischman.uri.gabbiapp.network.request;


public class GetEventsRequest {

    boolean containPastEvents;

    public GetEventsRequest(boolean containPastEvents) {
        this.containPastEvents = containPastEvents;
    }

    public boolean isContainPastEvents() {
        return containPastEvents;
    }

    public void setContainPastEvents(boolean containPastEvents) {
        this.containPastEvents = containPastEvents;
    }
}
