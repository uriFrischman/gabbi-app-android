package com.frischman.uri.gabbiapp.utility;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class SharedPreferencesUtil {

    public static void putObjectInSharedPreferences(Context context, String preferencesName, int mode,  String preferencesKey, Object object) {
        SharedPreferences pref = context.getSharedPreferences(preferencesName, mode);
        pref.edit().putString(preferencesKey, new Gson().toJson(object)).apply();
    }

    public static boolean checkIfSharedPreferencesContainsKey(Context context, String preferencesName, int mode,  String preferencesKey) {
        return context.getSharedPreferences(preferencesName, mode).contains(preferencesKey);
    }

    public static Object getObjectInSharedPreferences(Context context, String preferencesName, int mode, String key, Class objectType) {
        if (checkIfSharedPreferencesContainsKey(context, preferencesName, mode, key)) {
            String json = context.getSharedPreferences(preferencesName, mode).getString(key, null);
            return new Gson().fromJson(json, objectType);
        } else {
            return null;
        }
    }
}
