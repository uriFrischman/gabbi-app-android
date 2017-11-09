package com.frischman.uri.gabbiapp.loader;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.frischman.uri.gabbiapp.network.request.UserLoginRequest;
import com.frischman.uri.gabbiapp.network.response.UserLoginResponse;

import static com.frischman.uri.gabbiapp.utility.AWSLambdaUtil.getLambdaFunctions;

public class UserLoginRequestLoader extends AsyncTaskLoader<UserLoginResponse> {

    private String username;
    private String password;

    public UserLoginRequestLoader(Context context, String username, String password) {
        super(context);
        this.username = username;
        this.password = password;
    }

    @Override
    public UserLoginResponse loadInBackground() {
        return getLambdaFunctions().userLogin(new UserLoginRequest(username, password));
    }
}
