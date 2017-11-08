package com.frischman.uri.gabbiapp.network.lambda;

import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunction;
import com.frischman.uri.gabbiapp.network.request.UserLoginRequest;
import com.frischman.uri.gabbiapp.network.request.UserSignUpRequest;
import com.frischman.uri.gabbiapp.network.response.UserLoginResponse;
import com.frischman.uri.gabbiapp.network.response.UserSignUpResponse;

public interface LambdaFunctions {

    @LambdaFunction(functionName = "user_login")
    UserLoginResponse userLogin(UserLoginRequest input);

    @LambdaFunction(functionName = "user_signup")
    UserSignUpResponse userSignUp(UserSignUpRequest input);
}
