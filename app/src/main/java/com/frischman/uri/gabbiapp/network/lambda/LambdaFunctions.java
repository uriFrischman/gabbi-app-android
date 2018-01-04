package com.frischman.uri.gabbiapp.network.lambda;

import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunction;
import com.frischman.uri.gabbiapp.network.request.ClaimAliyahRequest;
import com.frischman.uri.gabbiapp.network.request.GetAliyahsRequest;
import com.frischman.uri.gabbiapp.network.request.GetEventsRequest;
import com.frischman.uri.gabbiapp.network.request.UserLoginRequest;
import com.frischman.uri.gabbiapp.network.request.UserSignUpRequest;
import com.frischman.uri.gabbiapp.network.response.ClaimAliyahResponse;
import com.frischman.uri.gabbiapp.network.response.GetAliyahsResponse;
import com.frischman.uri.gabbiapp.network.response.GetEventsResponse;
import com.frischman.uri.gabbiapp.network.response.UserLoginResponse;
import com.frischman.uri.gabbiapp.network.response.UserSignUpResponse;

public interface LambdaFunctions {

    @LambdaFunction(functionName = "user_login")
    UserLoginResponse userLogin(UserLoginRequest input);

    @LambdaFunction(functionName = "user_signup")
    UserSignUpResponse userSignUp(UserSignUpRequest input);

    @LambdaFunction(functionName = "claim_aliyah")
    ClaimAliyahResponse claimAliyah(ClaimAliyahRequest input);

    @LambdaFunction(functionName = "get_events")
    GetEventsResponse getEvents(GetEventsRequest input);

    @LambdaFunction(functionName = "get_aliyahs")
    GetAliyahsResponse getAliyahs(GetAliyahsRequest input);
}
