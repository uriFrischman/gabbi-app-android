package com.frischman.uri.gabbiapp.utility;


import com.amazonaws.mobileconnectors.lambdainvoker.LambdaInvokerFactory;
import com.frischman.uri.gabbiapp.network.lambda.LambdaFunctions;

import static com.frischman.uri.gabbiapp.GabbiApp.getAppCognitoCachingCredentialsProvider;
import static com.frischman.uri.gabbiapp.GabbiApp.getAppContext;

public class AWSLambdaUtil {

    public static LambdaFunctions getLambdaFunctions() {
        LambdaInvokerFactory factory = LambdaInvokerFactory.builder().context(getAppContext()).credentialsProvider(getAppCognitoCachingCredentialsProvider()).build();
        return factory.build(LambdaFunctions.class);
    }
}
