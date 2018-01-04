package com.frischman.uri.gabbiapp.loader;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.frischman.uri.gabbiapp.network.request.GetAliyahsRequest;
import com.frischman.uri.gabbiapp.network.response.GetAliyahsResponse;

import static com.frischman.uri.gabbiapp.utility.AWSLambdaUtil.getLambdaFunctions;

public class GetAliyahsLoader extends AsyncTaskLoader<GetAliyahsResponse> {

    private String eventName;

    public GetAliyahsLoader(Context context, String eventName) {
        super(context);
        this.eventName = eventName;
    }

    @Override
    public GetAliyahsResponse loadInBackground() {
        return getLambdaFunctions().getAliyahs(new GetAliyahsRequest(eventName));
    }
}
