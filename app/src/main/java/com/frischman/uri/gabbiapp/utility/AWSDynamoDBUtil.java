package com.frischman.uri.gabbiapp.utility;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import static com.frischman.uri.gabbiapp.GabbiApp.getAppDynamoDBMapper;

public class AWSDynamoDBUtil {
    private static final String TAG = "AWSDynamoDBUtil";

    public static AmazonDynamoDBClient getAmazonDynamoDBClient(AWSCredentialsProvider credentials) {
        return new AmazonDynamoDBClient(credentials);
    }

    public static DynamoDBMapper getDynamoDBMapper(AmazonDynamoDBClient client) {
        return new DynamoDBMapper(client);
    }

    public static void saveObject(final Object object) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                getAppDynamoDBMapper().save(object);
            }
        }).start();
    }

}
