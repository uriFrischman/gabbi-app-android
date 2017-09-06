package com.frischman.uri.gabbiapp;

import android.app.Application;
import android.util.Log;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.frischman.uri.gabbiapp.utility.StringUtil;

import static com.frischman.uri.gabbiapp.utility.AWSCognitoUtil.getCognitoCachingCredentialsProvider;
import static com.frischman.uri.gabbiapp.utility.AWSDynamoDBUtil.getAmazonDynamoDBClient;
import static com.frischman.uri.gabbiapp.utility.AWSDynamoDBUtil.getDynamoDBMapper;


public class GabbiApp extends Application {

    private CognitoCachingCredentialsProvider mCredentialsProvider;
    private static AmazonDynamoDBClient mAmazonDynamoDBClient;
    private static DynamoDBMapper mDynamoDBMapper;

    private static StringUtil mStringUtil;

    private static final String TAG = "GabbiApp";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "App starting");
        mStringUtil = new StringUtil(this);
        setCredentialsProvider();
        connectToDynamoDB();
    }

    private void setCredentialsProvider() {
        mCredentialsProvider = getCognitoCachingCredentialsProvider(this, mStringUtil.getString(R.string.aws_userpool_identity_pool_id), Regions.US_EAST_1);
    }

    private void connectToDynamoDB() {
        mAmazonDynamoDBClient = getAmazonDynamoDBClient(mCredentialsProvider);
        mDynamoDBMapper = getDynamoDBMapper(mAmazonDynamoDBClient);
    }

    public static AmazonDynamoDBClient getAppDynamoDBClient() {
        return mAmazonDynamoDBClient;
    }

    public static DynamoDBMapper getAppDynamoDBMapper() {
        return mDynamoDBMapper;
    }

    public static StringUtil getAppStringUtil() {
        return mStringUtil;
    }
}
