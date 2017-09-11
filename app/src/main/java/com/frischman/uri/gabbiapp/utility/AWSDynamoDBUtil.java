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

    public static void saveObject(Object object) {
        getAppDynamoDBMapper().save(object);
    }

}
