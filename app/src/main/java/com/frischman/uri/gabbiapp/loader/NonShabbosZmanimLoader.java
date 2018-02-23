package com.frischman.uri.gabbiapp.loader;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.frischman.uri.gabbiapp.network.request.NonShabbosZmanimRequest;
import com.frischman.uri.gabbiapp.network.response.NonShabbosZmanimResponse;

import static com.frischman.uri.gabbiapp.utility.AWSLambdaUtil.getLambdaFunctions;

public class NonShabbosZmanimLoader extends AsyncTaskLoader<NonShabbosZmanimResponse> {

    private String date;
    private int longitude;
    private int  latitude;

    public NonShabbosZmanimLoader(Context context, String date) {
        super(context);
        this.date = date;
    }

    public NonShabbosZmanimLoader(Context context, String date, int longitude, int latitude) {
        super(context);
        this.date = date;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public NonShabbosZmanimResponse loadInBackground() {
        return getLambdaFunctions().getNonShabbosZmanim(new NonShabbosZmanimRequest(date, longitude, latitude));
    }
}
