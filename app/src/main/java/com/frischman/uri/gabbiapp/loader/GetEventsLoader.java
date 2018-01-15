package com.frischman.uri.gabbiapp.loader;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.frischman.uri.gabbiapp.network.request.GetEventsRequest;
import com.frischman.uri.gabbiapp.network.response.GetEventsResponse;

import static com.frischman.uri.gabbiapp.utility.AWSLambdaUtil.getLambdaFunctions;

public class GetEventsLoader extends AsyncTaskLoader<GetEventsResponse> {

    private boolean containPastEvents;

    public GetEventsLoader(Context context, boolean containPastEvents) {
        super(context);
        this.containPastEvents = containPastEvents;
    }

    @Override
    public GetEventsResponse loadInBackground() {
        return getLambdaFunctions().getEvents(new GetEventsRequest(containPastEvents));
    }
}
