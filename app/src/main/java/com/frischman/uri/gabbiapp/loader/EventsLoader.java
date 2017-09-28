package com.frischman.uri.gabbiapp.loader;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.frischman.uri.gabbiapp.model.Event;

import java.util.List;

import static com.frischman.uri.gabbiapp.utility.EventUtil.getAllEvents;

public class EventsLoader extends AsyncTaskLoader<List<Event>> {

    private boolean mIncludePastEvents;


    public EventsLoader(Context context, boolean includePastEvents) {
        super(context);
        mIncludePastEvents = includePastEvents;
    }

    @Override
    public List<Event> loadInBackground() {
        return getAllEvents(mIncludePastEvents);
    }
}
