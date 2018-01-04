package com.frischman.uri.gabbiapp.loader;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.frischman.uri.gabbiapp.model.Aliyah;
import com.frischman.uri.gabbiapp.model.User;
import com.frischman.uri.gabbiapp.network.request.ClaimAliyahRequest;
import com.frischman.uri.gabbiapp.network.response.ClaimAliyahResponse;

import static com.frischman.uri.gabbiapp.utility.AWSLambdaUtil.getLambdaFunctions;

public class AliyahClaimLoader extends AsyncTaskLoader<ClaimAliyahResponse> {

    private Aliyah aliyah;
    private User user;

    public AliyahClaimLoader(Context context, Aliyah aliyah, User user) {
        super(context);
        this.aliyah = aliyah;
        this.user = user;
    }

    @Override
    public ClaimAliyahResponse loadInBackground() {
        return getLambdaFunctions().claimAliyah(new ClaimAliyahRequest(aliyah, user));
    }

}
