package com.frischman.uri.gabbiapp.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.amazonaws.mobileconnectors.lambdainvoker.LambdaInvokerFactory;
import com.frischman.uri.gabbiapp.network.lambda.LambdaFunctions;
import com.frischman.uri.gabbiapp.network.request.UserSignUpRequest;
import com.frischman.uri.gabbiapp.network.response.UserSignUpResonse;
import com.frischman.uri.gabbiapp.model.User;

import static com.frischman.uri.gabbiapp.GabbiApp.getAppCognitoCachingCredentialsProvider;
import static com.frischman.uri.gabbiapp.GabbiApp.getAppContext;

public class UserSignUpRequestLoader extends AsyncTaskLoader<UserSignUpResonse> {

    private User user;

    public UserSignUpRequestLoader(Context context, User user) {
        super(context);
        this.user = user;
    }

    @Override
    public UserSignUpResonse loadInBackground() {
        LambdaInvokerFactory factory = LambdaInvokerFactory.builder().context(getAppContext()).credentialsProvider(getAppCognitoCachingCredentialsProvider()).build();
        final LambdaFunctions lambdaFunctions = factory.build(LambdaFunctions.class);
        final UserSignUpRequest userSignUpRequest = new UserSignUpRequest(user);
        return lambdaFunctions.userSignUp(userSignUpRequest);
    }
}
