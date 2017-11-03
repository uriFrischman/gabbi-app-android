package com.frischman.uri.gabbiapp;

import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunction;

public interface LambdaFunctions {

//    @LambdaFunction(functionName = "user_login")
//    UserLoginResponse userLogin(UserLoginRequest input);

    @LambdaFunction(functionName = "user_signup")
    UserSignUpResonse userSignUp(UserSignUpRequest input);
}
