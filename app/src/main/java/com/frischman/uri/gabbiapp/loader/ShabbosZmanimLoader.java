package com.frischman.uri.gabbiapp.loader;


import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import com.frischman.uri.gabbiapp.network.request.ShabbosZmanimRequest;
import com.frischman.uri.gabbiapp.network.response.ShabbosZmanimResponse;

import static com.frischman.uri.gabbiapp.utility.AWSLambdaUtil.getLambdaFunctions;

public class ShabbosZmanimLoader extends AsyncTaskLoader<ShabbosZmanimResponse> {

    private int geoNameId;

    public ShabbosZmanimLoader(Context context, int geoNameId) {
        super(context);
        this.geoNameId = geoNameId;
    }

    @Override
    public ShabbosZmanimResponse loadInBackground() {
        return getLambdaFunctions().getShabbosZmanim(new ShabbosZmanimRequest(geoNameId));
    }
}
