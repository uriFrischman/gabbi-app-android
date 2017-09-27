package com.frischman.uri.gabbiapp.utility;

import android.util.Log;

import com.frischman.uri.gabbiapp.R;
import com.frischman.uri.gabbiapp.model.User;

import static android.content.ContentValues.TAG;
import static com.frischman.uri.gabbiapp.GabbiApp.getAppDynamoDBMapper;
import static com.frischman.uri.gabbiapp.utility.StringUtil.getString;

public class UserUtil {

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

    public static User getUserWithId(int id) {
        return getAppDynamoDBMapper().load(User.class, id);
    }
}
