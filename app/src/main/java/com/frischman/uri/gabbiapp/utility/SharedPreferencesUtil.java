package com.frischman.uri.gabbiapp.utility;

import android.app.Activity;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferencesUtil {

    public static void putObjectInSharedPreferences(Activity activity, String key, Object object) {
        SharedPreferences.Editor editor = activity.getPreferences(MODE_PRIVATE).edit();
        editor.putString(key, new Gson().toJson(object));
        editor.apply();
    }

    public static boolean checkIfSharedPreferencesContainsKey(Activity activity, String key) {
        return activity.getPreferences(MODE_PRIVATE).contains(key);
    }
}
