package com.frischman.uri.gabbiapp;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.frischman.uri.gabbiapp.model.Sefer;
import com.frischman.uri.gabbiapp.utility.SnappyDBUtil;
import com.frischman.uri.gabbiapp.utility.StringUtil;
import com.google.gson.Gson;
import com.snappydb.DB;
import com.snappydb.SnappydbException;

import static com.frischman.uri.gabbiapp.utility.AWSCognitoUtil.getCognitoCachingCredentialsProvider;
import static com.frischman.uri.gabbiapp.utility.AWSDynamoDBUtil.getAmazonDynamoDBClient;
import static com.frischman.uri.gabbiapp.utility.AWSDynamoDBUtil.getDynamoDBMapper;
import static com.frischman.uri.gabbiapp.utility.FileUtil.rawFileToString;
import static com.frischman.uri.gabbiapp.utility.SnappyDBUtil.getDBWithName;


public class GabbiApp extends Application {

    private static CognitoCachingCredentialsProvider mCredentialsProvider;
    private static DynamoDBMapper mDynamoDBMapper;
    private static AmazonDynamoDBClient mAmazonDynamoDBClient;

    private static Context mContext;

    private static final String TAG = "GabbiApp";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "App starting");
        setContext(this);
        setStringUtil();
        setSnappyDBUtil();
        setCredentialsProvider();
        connectToDynamoDB();
        addTorahToSnappyDB();
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

    private void connectToDynamoDB() {
        mAmazonDynamoDBClient = getAmazonDynamoDBClient(mCredentialsProvider);
        mDynamoDBMapper = getDynamoDBMapper(mAmazonDynamoDBClient);
    }

    public static AmazonDynamoDBClient getAppAmazonDynamoDBClient() {
        return mAmazonDynamoDBClient;
    }

    public static DynamoDBMapper getAppDynamoDBMapper() {
        return mDynamoDBMapper;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public static Context getAppContext() {
        return mContext;
    }

    public static Resources getAppResources() {
        return getAppContext().getResources();
    }

    private void addTorahToSnappyDB() {
        String bereishit = rawFileToString(R.raw.bereishit);
        String shemot = rawFileToString(R.raw.shemot);
        String vayikra = rawFileToString(R.raw.vayikra);
        String bamidbar = rawFileToString(R.raw.bamidbar);
        String devarim = rawFileToString(R.raw.devarim);

        Gson gson = new Gson();

        Sefer seferBereishit = gson.fromJson(bereishit, Sefer.class);
        Sefer seferShemot = gson.fromJson(shemot, Sefer.class);
        Sefer seferVayikra = gson.fromJson(vayikra, Sefer.class);
        Sefer seferBamidbar = gson.fromJson(bamidbar, Sefer.class);
        Sefer seferDevarim = gson.fromJson(devarim, Sefer.class);

        DB torahDatabse = getDBWithName(getString(R.string.database_name_torah));

        try {
            torahDatabse.put(getString(R.string.database_key_bereishit), seferBereishit);
            torahDatabse.put(getString(R.string.database_key_shemot), seferShemot);
            torahDatabse.put(getString(R.string.database_key_vayikra), seferVayikra);
            torahDatabse.put(getString(R.string.database_key_bamidbar), seferBamidbar);
            torahDatabse.put(getString(R.string.database_key_devarim), seferDevarim);
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

}
