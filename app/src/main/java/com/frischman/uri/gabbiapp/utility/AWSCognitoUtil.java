package com.frischman.uri.gabbiapp.utility;

import android.content.Context;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;

public class AWSCognitoUtil {

    public static CognitoCachingCredentialsProvider getCognitoCachingCredentialsProvider(Context context, String userPoolId, Regions region) {
        return new CognitoCachingCredentialsProvider(context, userPoolId, region);
    }
}
