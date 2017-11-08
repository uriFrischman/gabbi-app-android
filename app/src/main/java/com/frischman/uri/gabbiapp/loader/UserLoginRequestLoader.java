package com.frischman.uri.gabbiapp.loader;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.amazonaws.mobileconnectors.lambdainvoker.LambdaInvokerFactory;
import com.frischman.uri.gabbiapp.network.lambda.LambdaFunctions;
import com.frischman.uri.gabbiapp.network.request.UserLoginRequest;
import com.frischman.uri.gabbiapp.network.response.UserLoginResponse;

import static com.frischman.uri.gabbiapp.GabbiApp.getAppCognitoCachingCredentialsProvider;
import static com.frischman.uri.gabbiapp.GabbiApp.getAppContext;

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
        LambdaInvokerFactory factory = LambdaInvokerFactory.builder().context(getAppContext()).credentialsProvider(getAppCognitoCachingCredentialsProvider()).build();
        final LambdaFunctions lambdaFunctions = factory.build(LambdaFunctions.class);
        final UserLoginRequest userLoginRequestRequest = new UserLoginRequest(username, password);
        return lambdaFunctions.userLogin(userLoginRequestRequest);
    }
}
