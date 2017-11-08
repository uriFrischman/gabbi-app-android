package com.frischman.uri.gabbiapp.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.frischman.uri.gabbiapp.model.User;
import com.frischman.uri.gabbiapp.network.request.UserSignUpRequest;
import com.frischman.uri.gabbiapp.network.response.UserSignUpResonse;

import static com.frischman.uri.gabbiapp.utility.AWSLambdaUtil.getLambdaFunctions;

public class UserSignUpRequestLoader extends AsyncTaskLoader<UserSignUpResonse> {

    private User user;

    public UserSignUpRequestLoader(Context context, User user) {
        super(context);
        this.user = user;
    }

    @Override
    public UserSignUpResonse loadInBackground() {
        final UserSignUpRequest userSignUpRequest = new UserSignUpRequest(user);
        return getLambdaFunctions().userSignUp(userSignUpRequest);
    }
}
