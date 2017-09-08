package com.frischman.uri.gabbiapp.utility;

import android.util.Log;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.frischman.uri.gabbiapp.R;
import com.frischman.uri.gabbiapp.model.User;

import static com.frischman.uri.gabbiapp.GabbiApp.getAppDynamoDBMapper;
import static com.frischman.uri.gabbiapp.utility.StringUtil.getString;

public class AWSDynamoDBUtil {
    private static final String TAG = "AWSDynamoDBUtil";

    public static AmazonDynamoDBClient getAmazonDynamoDBClient(AWSCredentialsProvider credentials) {
        return new AmazonDynamoDBClient(credentials);
    }

    public static DynamoDBMapper getDynamoDBMapper(AmazonDynamoDBClient client) {
        return new DynamoDBMapper(client);
    }

    public static User getUserWithId(int id) {
        return getAppDynamoDBMapper().load(User.class, id);
    }

    public static void saveObject(Object object) {
        getAppDynamoDBMapper().save(object);
    }

    public static void deleteUser(User user) {
        try {
            getAppDynamoDBMapper().delete(user);
        } catch (Exception e) {
            Log.d(TAG, getString(R.string.exception_user_does_not_exists));
        }
    }

    public static void deleteUserWithId(int id) {
        try {
            User userToDelete = getAppDynamoDBMapper().load(User.class, id);
            getAppDynamoDBMapper().delete(userToDelete);
        } catch (Exception e) {
            Log.d(TAG, getString(R.string.exception_user_does_not_exists));
        }
    }
}
