package com.frischman.uri.gabbiapp.network.response;


import com.frischman.uri.gabbiapp.model.Event;

import java.util.List;

public class GetEventsResponse {

    List<Event> listOfEvents;

    public GetEventsResponse(List<Event> listOfEvents) {
        this.listOfEvents = listOfEvents;
    }

    public List<Event> getListOfEvents() {
        return listOfEvents;
    }

    public void setListOfEvents(List<Event> listOfEvents) {
        this.listOfEvents = listOfEvents;
    }
}
