package com.frischman.uri.gabbiapp.network.request;


public class GetAliyahsRequest {

    String eventName;

    public GetAliyahsRequest(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
