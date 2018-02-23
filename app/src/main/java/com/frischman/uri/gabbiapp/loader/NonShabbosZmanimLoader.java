package com.frischman.uri.gabbiapp.loader;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.frischman.uri.gabbiapp.network.request.NonShabbosZmanimRequest;
import com.frischman.uri.gabbiapp.network.response.NonShabbosZmanimResponse;

import static com.frischman.uri.gabbiapp.utility.AWSLambdaUtil.getLambdaFunctions;

public class NonShabbosZmanimLoader extends AsyncTaskLoader<NonShabbosZmanimResponse> {

    private int longitude;
    private int  latitude;
    private boolean longAndLat;

    public NonShabbosZmanimLoader(Context context) {
        super(context);
        longAndLat = false;
    }

    public NonShabbosZmanimLoader(Context context, int longitude, int latitude) {
        super(context);
        this.longitude = longitude;
        this.latitude = latitude;
        longAndLat = true;
    }

    @Override
    public NonShabbosZmanimResponse loadInBackground() {
        if (longAndLat) {
            return getLambdaFunctions().getNonShabbosZmanim(new NonShabbosZmanimRequest(longitude, latitude));
        } else {
            return getLambdaFunctions().getNonShabbosZmanim(new NonShabbosZmanimRequest());
        }

    }
}
