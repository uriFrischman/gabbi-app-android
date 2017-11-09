package com.frischman.uri.gabbiapp;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.frischman.uri.gabbiapp.utility.SnappyDBUtil;
import com.frischman.uri.gabbiapp.utility.StringUtil;
import com.frischman.uri.gabbiapp.utility.TorahUtil;

import static com.frischman.uri.gabbiapp.utility.AWSCognitoUtil.getCognitoCachingCredentialsProvider;
import static com.frischman.uri.gabbiapp.utility.AWSDynamoDBUtil.getAmazonDynamoDBClient;
import static com.frischman.uri.gabbiapp.utility.AWSDynamoDBUtil.getDynamoDBMapper;

public class GabbiApp extends Application {

    private static final String TAG = "GabbiApp";
    private static CognitoCachingCredentialsProvider mCredentialsProvider;
    private static DynamoDBMapper mDynamoDBMapper;
    private static AmazonDynamoDBClient mAmazonDynamoDBClient;
    private static Context mContext;

    public static AmazonDynamoDBClient getAppAmazonDynamoDBClient() {
        return mAmazonDynamoDBClient;
    }

    public static DynamoDBMapper getAppDynamoDBMapper() {
        return mDynamoDBMapper;
    }

    public static Context getAppContext() {
        return mContext;
    }

    public static Resources getAppResources() {
        return getAppContext().getResources();
    }

    public static Looper getAppMainLooper() {
        return Looper.getMainLooper();
    }

    public static void loadRunnableOnToMainLooper(Runnable runnable) {
        new Handler(getAppMainLooper()).post(runnable);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "App starting");
        setContext(this);
        setStringUtil();
        setSnappyDBUtil();
        setCredentialsProvider();
        connectToDynamoDB();
        TorahUtil.addTorahToSnappyDB();
    }

    private void setSnappyDBUtil() {
        SnappyDBUtil mSnappyDBUtil = new SnappyDBUtil(getAppContext());
    }

    private void setStringUtil() {
        StringUtil mStringUtil = new StringUtil(getAppContext());
    }

    private void setCredentialsProvider() {
        mCredentialsProvider = getCognitoCachingCredentialsProvider(this, getString(R.string.aws_userpool_identity_pool_id), Regions.US_EAST_1);
    }

    public static CognitoCachingCredentialsProvider getAppCognitoCachingCredentialsProvider() {
        return mCredentialsProvider;
    }

    private void connectToDynamoDB() {
        mAmazonDynamoDBClient = getAmazonDynamoDBClient(mCredentialsProvider);
        mDynamoDBMapper = getDynamoDBMapper(mAmazonDynamoDBClient);
    }

    public void setContext(Context context) {
        mContext = context;
    }

}
