package com.frischman.uri.gabbiapp;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.frischman.uri.gabbiapp.utility.SnappyDBUtil;
import com.frischman.uri.gabbiapp.utility.StringUtil;
import com.frischman.uri.gabbiapp.utility.TorahUtil;

import static com.frischman.uri.gabbiapp.utility.AWSCognitoUtil.getCognitoCachingCredentialsProvider;

public class GabbiApp extends Application {

    private static final String TAG = "GabbiApp";
    private static CognitoCachingCredentialsProvider mCredentialsProvider;
    private static Context mContext;


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

    public void setContext(Context context) {
        mContext = context;
    }

}
